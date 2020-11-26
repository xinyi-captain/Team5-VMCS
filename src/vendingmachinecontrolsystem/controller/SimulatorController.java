package vendingmachinecontrolsystem.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import vendingmachinecontrolsystem.factory.PropertiesFactory;
import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.DoorState;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.model.Stock;
import vendingmachinecontrolsystem.ui.SimulatorControlPanel;
import vendingmachinecontrolsystem.util.CurrencyHelper;

public class SimulatorController {

	private static volatile SimulatorController simulatorController;
	public final static String SEPERATOR = ";";

	private SimulatorControlPanel simulatorControlPanel;
	private PropertiesFactory propertiesFactory;
	private List<Stock> coinStocks;
	private List<Stock> drinkStocks;

	private SimulatorController() {
		if (simulatorController != null) {
			throw new RuntimeException("Use get() method to get the single instance of this class.");
		}
		simulatorControlPanel = new SimulatorControlPanel();
		propertiesFactory = new PropertiesFactory();
		coinStocks = initCoins();
		drinkStocks = initDrinks();
		DoorState doorState = initDoorSate();

		CustomerController.get().setCoinStocks(coinStocks);
		CustomerController.get().setDrinkStocks(drinkStocks);
		MaintainerController.get().setCoinStocks(coinStocks);
		MaintainerController.get().setDrinkStocks(drinkStocks);
		MachineryController.get().setDoorState(doorState);

		MachineryController.get().setCoinStocks(coinStocks);
		MachineryController.get().setDrinkStocks(drinkStocks);
		MaintainerController.get().lock();

	}

	private List<Stock> initCoins() {
		List<Stock> coinStocks = new ArrayList<>();
		Properties coinProperties = propertiesFactory.getProperty(PropertiesFactory.COIN);
		Enumeration<String> enums = (Enumeration<String>) coinProperties.propertyNames();
		while (enums.hasMoreElements()) {
			String key = enums.nextElement();
			String value = coinProperties.getProperty(key);
			String[] data = value.split(SEPERATOR);
			String price = data[0];
			String quantity = data[1];

			Coin coin = new Coin();
			coin.setName(key);
			coin.setValue(Double.parseDouble(price));
			coin.setQuantity(Integer.parseInt(quantity));
			coin.addObserver(CustomerController.get());
			coin.addObserver(MaintainerController.get());
			coin.addObserver(MachineryController.get());
			coinStocks.add(coin);
		}
		return coinStocks;
	}

	private DoorState initDoorSate() {
		DoorState doorState = DoorState.getInstance();
//        doorState.addObserver(CustomerController.get());
		doorState.addObserver(MachineryController.get());
		doorState.addObserver(MaintainerController.get());
		return doorState;
	}

	private List<Stock> initDrinks() {
		List<Stock> drinkStocks = new ArrayList<>();
		Properties drinkProperties = propertiesFactory.getProperty(PropertiesFactory.DRINK);
		Enumeration<String> enums = (Enumeration<String>) drinkProperties.propertyNames();
		while (enums.hasMoreElements()) {
			String key = enums.nextElement();
			String value = drinkProperties.getProperty(key);
			String[] data = value.split(SEPERATOR);
			String price = data[0];
			String quantity = data[1];

			Drink drink = new Drink();
			drink.setName(key.replace("_", "").toUpperCase());
			drink.setValue(CurrencyHelper.coinsToAmount(price));
			drink.setQuantity(Integer.parseInt(quantity));
			drink.addObserver(CustomerController.get());
			drink.addObserver(MaintainerController.get());
			drink.addObserver(MachineryController.get());
			drinkStocks.add(drink);
		}
		return drinkStocks;
	}

	public static SimulatorController get() {
		if (simulatorController == null) {
			synchronized (SimulatorController.class) {
				if (simulatorController == null) {
					simulatorController = new SimulatorController();
				}
			}
		}
		return simulatorController;
	}

	public void showPanel() {
		simulatorControlPanel.setVisible(true);
	}

	public void showCustomerPanel() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				CustomerController.get().showPanel();
			}
		});
	}

	public void showMaintenancePanel() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MaintainerController.get().showPanel();
			}
		});
	}

	public void showMachineryPanel() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MachineryController.get().showPanel();
			}
		});
	}

	public void saveProperties() {
		if (coinStocks != null && drinkStocks != null && propertiesFactory != null) {
			Properties coinProp = new Properties();
			Iterator<Stock> iterator = coinStocks.iterator();
			while (iterator.hasNext()) {
				Coin coin = (Coin) iterator.next();
				coinProp.put(coin.getName(), coin.getValue() + ";" + coin.getQuantity());
			}
			propertiesFactory.saveProperties(PropertiesFactory.COIN, coinProp);
			System.out.println(coinProp);
			Properties drinkProp = new Properties();
			iterator = drinkStocks.iterator();
			while (iterator.hasNext()) {
				Drink drink = (Drink) iterator.next();
				drinkProp.put(drink.getName(), drink.getValue() + ";" + drink.getQuantity());
			}
			propertiesFactory.saveProperties(PropertiesFactory.DRINK, drinkProp);
			System.out.println(drinkProp);
		}
	}
}
