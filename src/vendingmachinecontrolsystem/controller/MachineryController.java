package vendingmachinecontrolsystem.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import vendingmachinecontrolsystem.model.*;
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
		else{
			machineryPanel = new MachineryPanel();

			addDrinksToUI();
			addCoinsToUI();
			updateDoorStateToUI();
			updateUIAccordingToDoorState(doorState);

		}
	}
	
	public void closePanel() {
		if (machineryPanel != null)
			machineryPanel.setVisible(false);

	}

	private void updateDoorStateToUI() {
		machineryPanel.updateDoorLockState(doorState.isLocked());
	}

	private void addCoinsToUI() {
		Iterator<Stock> iterator = COIN_STOCKS.iterator();
		while (iterator.hasNext()) {
			Coin coin = (Coin) iterator.next();
			machineryPanel.addNewCoins(coin);
		}
		machineryPanel.pack();
		machineryPanel.setLocationRelativeTo(null);
	}

	private void addDrinksToUI() {
		Iterator<Stock> iterator = DRINK_STOCKS.iterator();
		while (iterator.hasNext()) {
			Drink drink = (Drink) iterator.next();
			machineryPanel.addNewDrink(drink);
		}
		machineryPanel.pack();
		machineryPanel.setLocationRelativeTo(null);
	}

	public void setDrinkStocks(List<Stock> drinkList) {
		DRINK_STOCKS = drinkList;
	}

	public void changeDrinkStock(Drink drink, int qty){
		if(!doorState.isLocked()){
			if(qty>=0&&qty<=20){
				drink.setQuantity(qty);
			}
		}
	}

	public void changeCoinStock(Coin coin, int qty){
		if(!doorState.isLocked()){
			if(qty>=0&&qty<=40){
				coin.setQuantity(qty);
			}
		}
	}

	public void setCoinStocks(List<Stock> coinList) {
		COIN_STOCKS = coinList;
	}
	public void setDoorState(DoorState doorState) {
		this.doorState=doorState;
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

			if(machineryPanel!=null) {
				System.out.println("Coin Update");

				Coin coin= (Coin) arg0;
				machineryPanel.updateCoinUI(coin);
			}

			} else if (arg0 instanceof Drink) {
			System.out.println("Drink MachineryController  Update");

			if(machineryPanel!=null) {

				Drink drink= (Drink) arg0;
				machineryPanel.updateDrinkUI(drink);
				System.out.println(arg0.toString());
			}

			}else if (arg0 instanceof DoorState) {
			if(machineryPanel!=null){
				updateUIAccordingToDoorState((DoorState) arg0);
			}

		}
		System.out.println("---------------------- MachineryController UPDATE End ----------------------");
	}

	private void updateUIAccordingToDoorState(DoorState arg0) {
		machineryPanel.updateDoorLockState(arg0.isLocked());
		machineryPanel.changeTextFieldState(arg0.isLocked());
	}


	public void lockDoor(){
		if(MaintainerState.getInstance().isLogIn()){
			doorState.setLocked(true);
		}
	}
	public void unLockDoor(){

		if(MaintainerState.getInstance().isLogIn()){
			doorState.setLocked(false);
		}
	}



}
