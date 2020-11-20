package vendingmachinecontrolsystem.factory;

import java.util.Properties;

import vendingmachinecontrolsystem.bridge.CoinProperties;
import vendingmachinecontrolsystem.bridge.DrinkProperties;
import vendingmachinecontrolsystem.bridge.MachineProperties;

public class PropertiesFactory {
	
	public static final String COIN = "COIN";
	public static final String DRINK = "DRINK";
	public static final String MACHINE = "MACHINE";
	
	public Properties getProperty(String propertyType) {
		if(propertyType == null){
	         return null;
	      }		
	      if(propertyType.equalsIgnoreCase(COIN)){
	         return new CoinProperties().getProperties();
	         
	      } else if(propertyType.equalsIgnoreCase(DRINK)){
	         return new DrinkProperties().getProperties();
	         
	      } else if(propertyType.equalsIgnoreCase(MACHINE)){
	         return new MachineProperties().getProperties();
	      }
	      return null;
	}

}
