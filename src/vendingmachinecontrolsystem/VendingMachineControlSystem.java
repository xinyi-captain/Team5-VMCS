/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachinecontrolsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import vendingmachinecontrolsystem.controller.CustomerController;
import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.model.Stock;
import vendingmachinecontrolsystem.ui.CustomerPanel;
import vendingmachinecontrolsystem.ui.SimulatorControlPanel;
import vendingmachinecontrolsystem.util.CurrencyHelper;
import vendingmachinecontrolsystem.util.PropertiesFactory;

/**
 *
 * @author Dannel
 */
public class VendingMachineControlSystem {

    public static void main(String[] args) {
        initLooksAndFeel();
        SimulatorControlPanel.get().setVisible(true);
        initDrinks();
        CustomerController.get().setCoinStocks(initCoins());
        CustomerController.get().setDrinkStocks(initDrinks());
    }

    private static void initLooksAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VendingMachineControlSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendingMachineControlSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendingMachineControlSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendingMachineControlSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private static List<Stock> initCoins() {
    	List<Stock> coinStocks = new ArrayList<>();
        try {
            Properties coinProperties = PropertiesFactory.getCoinProperties();
            Enumeration<String> enums = (Enumeration<String>) coinProperties.propertyNames();
            while (enums.hasMoreElements()) {
                String key = enums.nextElement();
                String value = coinProperties.getProperty(key);
                String[] data = value.split(PropertiesFactory.SEPERATOR);
                String price = data[0];
                String quantity = data[1];
                
                Coin coin = new Coin();
                coin.setName(key);
                coin.setValue(Double.parseDouble(price));
                coin.setQuantity(Integer.parseInt(quantity));
                coin.addObserver(CustomerController.get());
                coinStocks.add(coin);
            }
        } catch (IOException ex) {
            Logger.getLogger(VendingMachineControlSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coinStocks;
    }
    
    private static List<Stock> initDrinks() {
    	List<Stock> drinkStocks = new ArrayList<>();
        try {
            Properties drinkProperties = PropertiesFactory.getDrinkProperties();
            Enumeration<String> enums = (Enumeration<String>) drinkProperties.propertyNames();
            while (enums.hasMoreElements()) {
                String key = enums.nextElement();
                String value = drinkProperties.getProperty(key);
                String[] data = value.split(PropertiesFactory.SEPERATOR);
                String price = data[0];
                String quantity = data[1];
                
                Drink drink = new Drink();
                drink.setName(key.replace("_", "").toUpperCase());
                drink.setValue(CurrencyHelper.coinsToAmount(price));
                drink.setQuantity(Integer.parseInt(quantity));
                drink.addObserver(CustomerController.get());
                drinkStocks.add(drink);
            }
        } catch (IOException ex) {
            Logger.getLogger(VendingMachineControlSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return drinkStocks;
    }
}
