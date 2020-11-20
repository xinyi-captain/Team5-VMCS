package vendingmachinecontrolsystem.bridge;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import vendingmachinecontrolsystem.model.OrderedProperties;

public class MachineProperties implements PropertiesAPI {

	@Override
	public Properties getProperties() {
		try {
    		FileReader reader = new FileReader(MACHINE_PROPERTIES);
            OrderedProperties p = new OrderedProperties();
			p.load(reader);
			return p;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
