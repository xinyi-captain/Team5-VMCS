package vendingmachinecontrolsystem.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.model.Stock;

public class MaintainerControllerTest {
	
	List<Stock> coinlist;
	
	@Before
	public void setUp() throws Exception{

		
	}

	@Test
	public void testCheckPassword() {
		String password = "password123";
		boolean result = MaintainerController.get().validatePassword(password);
		assertEquals(result, true);
		assertNotEquals(result, false);
		password = "password13";
		result = MaintainerController.get().validatePassword(password);
		assertEquals(result, false);
		assertNotEquals(result, true);
		password = "password123     ";
		result = MaintainerController.get().validatePassword(password);
		assertEquals(result, false);
		assertNotEquals(result, true);
	}

	@Test
	public void testGetTotalCash() {
		coinlist=new ArrayList<>();
		Coin coin1 = new Coin();
		coin1.setQuantity(6);
		coin1.setName("1$");
		coin1.setValue(1);
		coinlist.add(coin1);
		Coin coin2 = new Coin();
		coin2.setQuantity(5);
		coin2.setName("50c");
		coin2.setValue(0.5);
		coinlist.add(coin2);
		MaintainerController.get().setCoinStocks(coinlist);
		
	
		double amount1 = coin1.getValue();
		int quantity1 = coin1.getQuantity();
		double total1 = amount1 * quantity1;
		double amount2 = coin2.getValue();
		int quantity2 = coin2.getQuantity();
		double total2 = amount2 * quantity2;
		double total = total1+total2;
		
		MaintainerController.get().getTotalCash(total);
		assertEquals(total, 8.5,0.01);
	}
	
	@Test
	public void testChangePrice() {
		Drink fanta=new Drink();
		fanta.setName("fanta");
		fanta.setQuantity(10);
		fanta.setValue(1);
		Drink selectedDrink = fanta;
		
		String oriValue=String.valueOf("0.75");
		MaintainerController.get().setSelectedDrink(selectedDrink);
		MaintainerController.get().changePrice(oriValue);
		assertEquals(selectedDrink.getValue(), 0.75,0.01);
	
		
	}

}
