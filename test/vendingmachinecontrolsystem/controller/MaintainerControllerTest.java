package vendingmachinecontrolsystem.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vendingmachinecontrolsystem.model.Stock;

public class MaintainerControllerTest {
	
	List<Stock> drinkList;
	
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
	public void testCollectAllCash() {
		fail("Not yet implemented");
		
	}

	@Test
	public void testShowTotalCashHeld() {
		fail("Not yet implemented");
	}

}
