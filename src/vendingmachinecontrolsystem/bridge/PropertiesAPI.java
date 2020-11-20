package vendingmachinecontrolsystem.bridge;

import java.util.Properties;

public interface PropertiesAPI {
	
	public final static String COIN_PROPERTIES = "Coin.properties";
	public final static String DRINK_PROPERTIES = "Drink.properties";
    public final static String MACHINE_PROPERTIES = "VendingMachine.properties";
	
	public Properties getProperties();

}
