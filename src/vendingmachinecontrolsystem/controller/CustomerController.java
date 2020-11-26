package vendingmachinecontrolsystem.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import vendingmachinecontrolsystem.memento.TransactionCaretaker;
import vendingmachinecontrolsystem.memento.TransactionOriginator;
import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.model.Stock;
import vendingmachinecontrolsystem.ui.CustomerPanel;
import vendingmachinecontrolsystem.util.CurrencyHelper;

public class CustomerController implements Observer {

	private static volatile CustomerController customerController;

	private List<Stock> COIN_STOCKS;
	private List<Stock> DRINK_STOCKS;
	private CustomerPanel customerPanel;
	private TransactionOriginator transactionCoinOriginator;
	private TransactionOriginator transactionDrinkOriginator;
	private TransactionCaretaker transactionCoinCaretaker;
	private TransactionCaretaker transactionDrinkCaretaker;

	public static CustomerController get() {
		if (customerController == null) {
			synchronized (CustomerController.class) {
				if (customerController == null) {
					customerController = new CustomerController();
				}
			}
		}
		return customerController;
	}

	private CustomerController() {
		if (customerController != null) {
			throw new RuntimeException("Use get() method to get the single instance of this class.");
		}
		customerPanel = new CustomerPanel();
	}

	public void showPanel() {
		if (customerPanel != null)
			customerPanel.setVisible(true);
	}
	
	public void closePanel() {
		if (customerPanel != null)
			customerPanel.setVisible(false);
	}

	public void setCoinStocks(List<Stock> coinList) {
		COIN_STOCKS = coinList;
		Iterator<Stock> iterator = COIN_STOCKS.iterator();
		while (iterator.hasNext()) {
			Coin coin = (Coin) iterator.next();
			customerPanel.addNewCoins(coin);
		}
		customerPanel.pack();
		customerPanel.setLocationRelativeTo(null);
	}

	public void coinEvent(Coin coin) {
		if (coin.getValue() == 0) {
			invalidCoin();
		} else {
			validCoin(coin);
		}
	}

	private void validCoin(Coin coin) {
		transactionCoinOriginator.setStock(coin);
		transactionDrinkCaretaker.addMemento(transactionCoinOriginator.saveStateToMemento());
		coin.setQuantity(coin.getQuantity() + 1);
		customerPanel.updateInsertedAmount(coin.getValue());
		checkAmountSufficiency();
	}

	private void invalidCoin() {
		customerPanel.displayInvalidCoin();
	}

	public void setDrinkStocks(List<Stock> drinkList) {
		DRINK_STOCKS = drinkList;
		Iterator<Stock> iterator = DRINK_STOCKS.iterator();
		while (iterator.hasNext()) {
			Drink drink = (Drink) iterator.next();
			customerPanel.addNewDrink(drink);
		}
		customerPanel.pack();
		customerPanel.setLocationRelativeTo(null);
	}

	public void startTransaction(Drink drink) {
		transactionDrinkOriginator = new TransactionOriginator();
		transactionDrinkOriginator.setStock(drink);
		transactionCoinOriginator = new TransactionOriginator();
		transactionCoinCaretaker = new TransactionCaretaker();
		transactionDrinkCaretaker = new TransactionCaretaker();
		transactionDrinkCaretaker.addMemento(transactionDrinkOriginator.saveStateToMemento());
		customerPanel.setSelectedDrink(drink);
		drink.setQuantity(drink.getQuantity() - 1);
		customerPanel.enableTerminateButton();
	}

	public boolean isDrinkAvailable(String drinkName) {
		boolean result = false;
		Iterator<Stock> iterator = DRINK_STOCKS.iterator();
		while (iterator.hasNext()) {
			Drink drink = (Drink) iterator.next();
			if (drink.getName().equalsIgnoreCase(drinkName)) {
				if (drink.getQuantity() > 0)
					result = true;
			}
		}
		return result;
	}

	private void checkAmountSufficiency() {
		double amount = CurrencyHelper.coinsToAmount(customerPanel.getInsertedAmount());
		Drink drink = getSelectedDrink();
		if (amount >= drink.getValue()) {
			customerPanel.dispenseDrink(drink);
			calculateChange(amount, drink.getValue());
		}
	}

	private void calculateChange(double amount, double drinkValue) {
		double change = CurrencyHelper.subtract(amount, drinkValue);
		double highestChangeAvailable = calculateHighestChange(change);
		customerPanel.displayChange(change, highestChangeAvailable);
		new Timer().schedule(new TimerTask() {
			public void run() {
				customerPanel.resetState();
			}
		}, 2500);
	}

	private double calculateHighestChange(double change) {
		double result = 0;
		double remainder = change;
		double lowestCoinDenomination = 0;
		for (int i = 0; i < COIN_STOCKS.size(); i++) {
			Coin coin = (Coin) COIN_STOCKS.get(i);
			if (coin.getQuantity() > 0) {
				if (i == 0 || lowestCoinDenomination == 0)
					lowestCoinDenomination = coin.getValue();
				else if (coin.getValue() < lowestCoinDenomination)
					lowestCoinDenomination = coin.getValue();
			}
		}

		for (int i = 0; i < COIN_STOCKS.size(); i++) {
			if (remainder == 0 | remainder <= lowestCoinDenomination)
				break; // no coin available to subtract and return
			System.out.println("remainder " + remainder);
			System.out.println("lowestCoinDenomination " + lowestCoinDenomination);
			System.out.println("result " + result);
			Coin coin = (Coin) COIN_STOCKS.get(i);
			if (coin.getQuantity() > 0) {
				if (coin.getValue() <= remainder) {
					if (!checkIfHigerCoinAvailable(remainder, coin.getValue())) {
						remainder = CurrencyHelper.subtract(remainder, coin.getValue());
						result = CurrencyHelper.add(result, coin.getValue());
						coin.setQuantity(coin.getQuantity() - 1); // must subtract to return to customer
					}
				}
			}
			if (i == COIN_STOCKS.size() - 1)
				i = -1; // restart loop
			System.out.println("i -----------" + i);
		}
		return result;
	}

	public boolean checkIfHigerCoinAvailable(double amount, double coinValueCheck) {
		boolean result = false;
		Iterator<Stock> iterator = COIN_STOCKS.iterator();
		while (iterator.hasNext()) {
			Coin coin = (Coin) iterator.next();
			if (coin.getValue() >= amount && amount % coin.getValue() == 0) {
				if (coin.getValue() > coinValueCheck)
					result = true;
			}
		}
		return result;
	}

	public void terminateTransaction() {
		if (transactionCoinOriginator != null) {
			restoreDrinkStockAftTermination();
			restoreCoinStockAftTermination();
			transactionCoinOriginator = null;
			transactionDrinkOriginator = null;
			transactionCoinCaretaker = null;
			transactionDrinkCaretaker = null;
			customerPanel.terminateTransaction();
			customerPanel.disableTerminateButton();
		}
	}

	public void restoreDrinkStockAftTermination() {
		transactionDrinkOriginator.getStateFromMemento(transactionDrinkCaretaker.get(0));
		Drink drink = (Drink) transactionDrinkOriginator.getStock();
		drink.setQuantity(drink.getQuantity() + 1);
	}

	public void restoreCoinStockAftTermination() {
		for (int i = 0; i < transactionCoinCaretaker.size(); i++) {
			transactionCoinOriginator.getStateFromMemento(transactionCoinCaretaker.get(i));
			Coin coin = (Coin) transactionCoinOriginator.getStock();
			coin.setQuantity(coin.getQuantity() + 1);
		}
	}

	public Drink getSelectedDrink() {
		transactionDrinkOriginator.getStateFromMemento(transactionDrinkCaretaker.get(0));
		Drink drink = (Drink) transactionDrinkOriginator.getStock();
		return drink;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("---------------------- CustomerController UPDATE Start ----------------------");
		if (o instanceof Coin) {
			System.out.println("Coin Update");
			Iterator<Stock> iterator = COIN_STOCKS.iterator();
			while (iterator.hasNext()) {
				Coin coin = (Coin) iterator.next();
				System.out.println(coin.toString());
			}
		} else if (o instanceof Drink) {
			System.out.println("Drink Update");
			Iterator<Stock> iterator = DRINK_STOCKS.iterator();
			while (iterator.hasNext()) {
				Drink drink = (Drink) iterator.next();
				System.out.println(drink.toString());
			}
			customerPanel.refreshDrinkPanel(DRINK_STOCKS);
		}
		System.out.println("---------------------- CustomerController UPDATE End ----------------------");
	}

}
