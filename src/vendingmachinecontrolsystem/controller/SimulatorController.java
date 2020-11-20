package vendingmachinecontrolsystem.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import vendingmachinecontrolsystem.factory.PropertiesFactory;
import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.model.Stock;
import vendingmachinecontrolsystem.ui.SimulatorControlPanel;
import vendingmachinecontrolsystem.util.CurrencyHelper;

public class SimulatorController {

	private static volatile SimulatorController simulatorController;
    public final static String SEPERATOR = ";";
    
	private SimulatorControlPanel simulatorControlPanel;
    private PropertiesFactory propertiesFactory;

    private SimulatorController() {
        if (simulatorController != null) {
            throw new RuntimeException("Use get() method to get the single instance of this class.");
        }
        simulatorControlPanel = new SimulatorControlPanel();
        propertiesFactory = new PropertiesFactory();
        List<Stock> coinStocks = initCoins(); 
        List<Stock> drinkStocks = initDrinks();
        CustomerController.get().setCoinStocks(coinStocks);
        CustomerController.get().setDrinkStocks(drinkStocks);
        MaintainerController.get().setCoinStocks(coinStocks);
        MaintainerController.get().setDrinkStocks(drinkStocks);
        MaintainerController.get().lock();
        MachineryController.get().setCoinStocks(coinStocks);
        MachineryController.get().setDrinkStocks(drinkStocks);
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
}
