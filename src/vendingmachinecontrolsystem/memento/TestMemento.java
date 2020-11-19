package vendingmachinecontrolsystem.memento;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.Stock;

public class TestMemento {

	@Test
	public void test() {
		
		TransactionOriginator originator = new TransactionOriginator();
		TransactionCaretaker careTaker = new TransactionCaretaker();
		
		assertNotNull(originator);
		assertNotNull(careTaker);
		
		for(int i=0;i<100;i++) {
			Coin coin = new Coin();
			coin.setName(String.valueOf(i));
			coin.setQuantity(i);
			coin.setValue(i);
			originator.setStock(coin);
			careTaker.addMemento(originator.saveStateToMemento());
		}
		
		for(int i=100;i<200;i++) {
			Coin coin = new Coin();
			coin.setName(String.valueOf(i));
			coin.setQuantity(i);
			coin.setValue(i);
			originator.setStock(coin);
			careTaker.addMemento(originator.saveStateToMemento());
		}
		
		for(int i=0;i<100;i++) {
			Coin coin = new Coin();
			coin.setName(String.valueOf(i));
			coin.setQuantity(i);
			coin.setValue(i);
			originator.getStateFromMemento(careTaker.get(i));
			Coin coin1 = (Coin) originator.getStock();
			assertEquals(coin, coin1);
		}
		
		for(int i=100;i<200;i++) {
			Coin coin = new Coin();
			coin.setName(String.valueOf(i));
			coin.setQuantity(i);
			coin.setValue(i);
			originator.getStateFromMemento(careTaker.get(i));
			Coin coin1 = (Coin) originator.getStock();
			assertEquals(coin, coin1);
		}
		
		
	}

}
