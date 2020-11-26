package vendingmachinecontrolsystem.bridge;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import vendingmachinecontrolsystem.model.OrderedProperties;

public class CoinProperties implements PropertiesAPI {

	@Override
	public Properties getProperties() {
        try {
    		FileReader reader = new FileReader(COIN_PROPERTIES);
            OrderedProperties p = new OrderedProperties();
			p.load(reader);
			return p;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveProperties(Properties properties) {
		// TODO Auto-generated method stub
		try (OutputStream output = new FileOutputStream(COIN_PROPERTIES)) {
			properties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
	}

}
