/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachinecontrolsystem.util;

import vendingmachinecontrolsystem.model.OrderedProperties;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Dannel
 */
public class PropertiesFactory {

    private final static String COIN_PROPERTIES = "Coin.properties";
    private final static String DRINK_PROPERTIES = "Drink.properties";
    private final static String MACHINE_PROPERTIES = "VendingMachine.properties";
    public static final String SEPERATOR = ";";

    public static OrderedProperties getCoinProperties() throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(COIN_PROPERTIES);
        OrderedProperties p = new OrderedProperties();
        p.load(reader);
        return p;
    }
    
    public static OrderedProperties getDrinkProperties() throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(DRINK_PROPERTIES);
        OrderedProperties p = new OrderedProperties();
        p.load(reader);
        return p;
    }
    
    public static OrderedProperties getMachineProperties() throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(MACHINE_PROPERTIES);
        OrderedProperties p = new OrderedProperties();
        p.load(reader);
        return p;
    }

}
