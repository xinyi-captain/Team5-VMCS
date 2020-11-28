package vendingmachinecontrolsystem.util;

import vendingmachinecontrolsystem.model.Stock;

public class StockHelper {
	
	public boolean isStockAvailable(Stock stock, String stockName) {
		boolean result = false;
		if (stock.getName().equalsIgnoreCase(stockName)) {
			if (stock.getQuantity() > 0)
				result = true;
		}
		return result;
	}

}
