package vendingmachinecontrolsystem.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.model.Stock;
import vendingmachinecontrolsystem.ui.CustomerPanel;
import vendingmachinecontrolsystem.util.CurrencyHelper;

public class CustomerController implements Observer {

	private static volatile CustomerController customerController;

	private List<Stock> COIN_STOCKS;
	private List<Stock> DRINK_STOCKS;
	private Drink SELECTED_DRINK;
	private CustomerPanel customerPanel;

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

	public void setCoinStocks(List<Stock> coinList) {
		COIN_STOCKS = coinList;
		Iterator<Stock> iterator = COIN_STOCKS.iterator();
		while (iterator.hasNext()) {
			Coin coin = (Coin) iterator.next();
			customerPanel.addNewCoins(coin);
		}
	}

	public void coinEvent(Coin coin) {
		if (coin.getValue() == 0) {
			invalidCoin();
		} else {
			validCoin(coin);
		}
	}

	private void validCoin(Coin coin) {
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
	}

	public void drinkEvent(Drink drink) {
		SELECTED_DRINK = drink;
		customerPanel.setSelectedDrink(drink);
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
		if (amount >= SELECTED_DRINK.getValue()) {
			customerPanel.dispenseDrink(SELECTED_DRINK);
			calculateChange(amount, SELECTED_DRINK.getValue());
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
		double currentChange = change;
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
			Coin coin = (Coin) COIN_STOCKS.get(i);
			if (coin.getQuantity() > 0) {
				remainder = CurrencyHelper.subtract(currentChange, coin.getValue());
				if (remainder < lowestCoinDenomination) {
					break; // no coin available to subtract and return
				}
				result = CurrencyHelper.add(result, coin.getValue());
				coin.setQuantity(coin.getQuantity() - 1); //must subtract to return to customer
			}
			if (i == COIN_STOCKS.size())
				i = -1; // reset loop
		}
		return result;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Coin) {
			Iterator<Stock> iterator = COIN_STOCKS.iterator();
			while(iterator.hasNext()) {
				Coin coin = (Coin) iterator.next();
				System.out.println(coin.toString());
			}
		} else if (o instanceof Drink) {
			Drink drink = (Drink) o;
			customerPanel.refreshDrinkPanel(DRINK_STOCKS);
		}
	}

}
