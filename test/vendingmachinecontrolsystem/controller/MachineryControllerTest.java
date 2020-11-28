package vendingmachinecontrolsystem.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vendingmachinecontrolsystem.controller.MachineryController;
import vendingmachinecontrolsystem.controller.MaintainerController;
import vendingmachinecontrolsystem.model.*;

import javax.crypto.Cipher;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MachineryControllerTest {
	List<Stock> drinkList;
	List<Stock> coinList;

	@Before
	public void setUp() throws Exception {
		drinkList=new ArrayList<>();
		coinList=new ArrayList<>();

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
		Coin oneDollorCoin = new Coin();
		oneDollorCoin.setQuantity(6);
		oneDollorCoin.setName("1$");
		oneDollorCoin.setValue(1);
		coinList.add(oneDollorCoin);
		Coin c50 = new Coin();
		c50.setQuantity(5);
		c50.setName("50c");
		c50.setValue(0.5);
		coinList.add(c50);
		MachineryController.get().setDrinkStocks(drinkList);
		MachineryController.get().setCoinStocks(coinList);

		MachineryController.get().setDoorState(DoorState.getInstance());

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void changeDrinkStock() {
		MachineryController.get().lockDoor();

		MachineryController.get().changeDrinkStock((Drink) drinkList.get(0),10);
		assertNotEquals(drinkList.get(0).getQuantity(),10);
		assertEquals(drinkList.get(0).getQuantity(),5);
		MachineryController.get().unLockDoor();
		MachineryController.get().changeDrinkStock((Drink) drinkList.get(0),10);
		assertEquals(drinkList.get(0).getQuantity(),10);
		MachineryController.get().changeDrinkStock((Drink) drinkList.get(0),50);
		assertNotEquals(drinkList.get(0).getQuantity(),40);

	}

	@Test
	public void changeCoinStock() {
		MachineryController.get().lockDoor();
		MachineryController.get().changeCoinStock((Coin) coinList.get(0),15);
		assertNotEquals(coinList.get(0).getQuantity(),15);
		assertEquals(coinList.get(0).getQuantity(),6);
		MachineryController.get().unLockDoor();
		MachineryController.get().changeCoinStock((Coin) coinList.get(0),10);
		assertEquals(coinList.get(0).getQuantity(),10);
		MachineryController.get().changeCoinStock((Coin) coinList.get(0),50);
		assertNotEquals(coinList.get(0).getQuantity(),50);

	}

	@Test
	public void lockDoor() {
		MaintainerState.getInstance().setLogIn(false);
		MachineryController.get().lockDoor();
		assertTrue(!DoorState.getInstance().isLocked());
		MaintainerState.getInstance().setLogIn(true);
		MachineryController.get().lockDoor();
		assertTrue(DoorState.getInstance().isLocked());
	}

	@Test
	public void unLockDoor() {
		DoorState.getInstance().setLocked(true);
		MaintainerState.getInstance().setLogIn(false);
		MachineryController.get().unLockDoor();
		assertTrue(DoorState.getInstance().isLocked());
		MaintainerState.getInstance().setLogIn(true);
		MachineryController.get().unLockDoor();
		assertTrue(!DoorState.getInstance().isLocked());
	}
}