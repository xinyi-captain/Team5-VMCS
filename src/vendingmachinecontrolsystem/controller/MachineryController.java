package vendingmachinecontrolsystem.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.DoorState;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.model.Stock;
import vendingmachinecontrolsystem.ui.MachineryPanel;

public class MachineryController implements Observer {
	
	private static volatile MachineryController machineryController;

	private List<Stock> COIN_STOCKS;
	private List<Stock> DRINK_STOCKS;
	private MachineryPanel machineryPanel;
	private DoorState doorState;
	private boolean currentDoorSate;

	public void coinEvent(Coin coin) {
		//do your coin event here
	}
	
	public void showPanel() {
		if (machineryPanel != null)
			machineryPanel.setVisible(true);
	}
	
	public void setDrinkStocks(List<Stock> drinkList) {
		DRINK_STOCKS = drinkList;
		Iterator<Stock> iterator = DRINK_STOCKS.iterator();
		while (iterator.hasNext()) {
			Drink drink = (Drink) iterator.next();
			machineryPanel.addNewDrink(drink);
		}
		machineryPanel.pack();
		machineryPanel.setLocationRelativeTo(null);
	}
	
	public void setCoinStocks(List<Stock> coinList) {
		COIN_STOCKS = coinList;
		Iterator<Stock> iterator = COIN_STOCKS.iterator();
		while (iterator.hasNext()) {
			Coin coin = (Coin) iterator.next();
			machineryPanel.addNewCoins(coin);
		}
		machineryPanel.pack();
		machineryPanel.setLocationRelativeTo(null);
	}
	public void setDoorState(DoorState doorState) {
		this.doorState=doorState;
		machineryPanel.updateDoorLockState(doorState.isLocked());
	}
	
	public static MachineryController get() {
		if (machineryController == null) {
			synchronized (MachineryController.class) {
				if (machineryController == null) {
					machineryController = new MachineryController();
				}
			}
		}
		return machineryController;
	}

	private MachineryController() {
		if (machineryController != null) {
			throw new RuntimeException("Use get() method to get the single instance of this class.");
		}
		machineryPanel = new MachineryPanel();

//		machineState.addDoorStateObserver(this);
//		machineState.addCoinStockObserver(this);
//		machineState.addDrinkStockObserver(this);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//do full panel refresh here
		System.out.println("---------------------- MachineryController UPDATE Start ----------------------");
		if (arg0 instanceof Coin) {
			System.out.println("Coin Update");
//			Iterator<Stock> iterator = COIN_STOCKS.iterator();
//			while (iterator.hasNext()) {
//				Coin coin = (Coin) iterator.next();
//				System.out.println(coin.toString());
//			}

			Coin coin= (Coin) arg0;
			machineryPanel.updateCoinUI(coin);

		} else if (arg0 instanceof Drink) {
			System.out.println("Drink MachineryController  Update");
//			Iterator<Stock> iterator = DRINK_STOCKS.iterator();
//			while (iterator.hasNext()) {
//				Drink drink = (Drink) iterator.next();
//				System.out.println(drink.toString());
//				machineryPanel.
//			}
			Drink drink= (Drink) arg0;
			machineryPanel.updateDrinkUI(drink);
			System.out.println(arg0.toString());

		}else if (arg0 instanceof DoorState) {
			machineryPanel.updateDoorLockState(((DoorState) arg0).isLocked());
			machineryPanel.changeTextFieldState(((DoorState) arg0).isLocked());

		}
		System.out.println("---------------------- MachineryController UPDATE End ----------------------");
	}


	public void lockDoor(){
		doorState.setLocked(true);
	}
	public void unLockDoor(){
		doorState.setLocked(false);
	}



}
