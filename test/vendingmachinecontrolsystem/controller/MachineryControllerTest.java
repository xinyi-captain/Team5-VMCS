package vendingmachinecontrolsystem.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vendingmachinecontrolsystem.controller.MachineryController;
import vendingmachinecontrolsystem.controller.MaintainerController;
import vendingmachinecontrolsystem.model.DoorState;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.model.Stock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MachineryControllerTest {
	List<Stock> drinkList;

	@Before
	public void setUp() throws Exception {
		drinkList=new ArrayList<>();
		Drink cocacola = new Drink();
		cocacola.setQuantity(5);
		cocacola.setName("Coca-Cola");
		cocacola.setValue(0.75);
		drinkList.add(cocacola);
		Drink  pepsi = new Drink();
		pepsi.setQuantity(5);
		pepsi.setName("Pepsi");
		pepsi.setValue(0.9);
		drinkList.add(pepsi);

		MachineryController.get().setDrinkStocks(drinkList);
		MachineryController.get().setDoorState(DoorState.getInstance());

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void changeDrinkStock() {
		MachineryController.get().changeDrinkStock((Drink) drinkList.get(0),10);
		assertNotEquals(drinkList.get(0).getQuantity(),10);
		assertEquals(drinkList.get(0).getQuantity(),5);
		MachineryController.get().unLockDoor();
		MachineryController.get().changeDrinkStock((Drink) drinkList.get(0),10);
		assertEquals(drinkList.get(0).getQuantity(),10);

	}

	@Test
	public void changeCoinStock() {

	}

	@Test
	public void lockDoor() {
	}

	@Test
	public void unLockDoor() {
	}
}