package vendingmachinecontrolsystem.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.model.Stock;

public class CustomerControllerTest {

	@Before
	public void setUp() throws Exception {
		List<Stock> coinStocks = new ArrayList<Stock>();
		List<Stock> drinkStocks = new ArrayList<Stock>();
		for (int i = 1; i < 10; i++) {
			Coin coin = new Coin();
			coin.setName(i + "c");
			coin.setQuantity(i);
			coin.setValue(i);
			coinStocks.add(coin);
		}
		for (int i = 1; i < 10; i++) {
			Drink drink = new Drink();
			drink.setName(i + "");
			drink.setQuantity(i);
			drink.setValue(i);
			drinkStocks.add(drink);
		}
		CustomerController.get().setCoinStocks(coinStocks);
		CustomerController.get().setDrinkStocks(drinkStocks);
	}

	@Test
	public void testCoinEvent() {
		Coin validCoin = new Coin();
		validCoin.setName("Valid");
		validCoin.setQuantity(1);
		validCoin.setValue(1);
		Coin invalidCoin = new Coin();
		invalidCoin.setName("inalid");
		invalidCoin.setQuantity(1);
		invalidCoin.setValue(0);
		boolean result = CustomerController.get().coinEvent(validCoin);
		assertTrue(result);
		result = CustomerController.get().coinEvent(invalidCoin);
		assertFalse(result);
	}

	@Test
	public void testDrinkEvent() {
		Drink drink = new Drink();
		drink.setName("coke");
		drink.setQuantity(1);
		drink.setValue(1);
		CustomerController.get().startTransaction(drink);
		assertSame(drink.getQuantity(), 0);
	}
	
	@Test
	public void testDrinkAvail() {
		String drinkName = "5";
		boolean result = CustomerController.get().isDrinkAvailable(drinkName);
		assertTrue(result);
		drinkName = "100";
		result = CustomerController.get().isDrinkAvailable(drinkName);
		assertFalse(result);
	}
	
	@Test
	public void testDrinkSelected() {
		Drink drink = new Drink();
		drink.setName("coke");
		drink.setQuantity(1);
		drink.setValue(1);
		CustomerController.get().startTransaction(drink);
		Drink drinkSelected = CustomerController.get().getSelectedDrink();
		assertEquals(drink, drinkSelected);
	}

	@Test
	public void testDrinkRestore() {
		Drink drink = new Drink();
		drink.setName("coke");
		drink.setQuantity(1);
		drink.setValue(1);
		CustomerController.get().startTransaction(drink);
		Drink drinkRestore = CustomerController.get().restoreDrinkStockAftTermination();
		assertEquals(drink, drinkRestore);
	}
}
