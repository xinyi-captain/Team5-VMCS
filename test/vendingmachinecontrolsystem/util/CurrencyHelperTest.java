package vendingmachinecontrolsystem.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CurrencyHelperTest {

	@Test
	public void testToCoins() {
		double amount = 0.0;
		String answer = "0 c";
		String wrongAnswer = "0.0 c";
		String result = CurrencyHelper.toCoins(amount);
		assertEquals(answer, result);
		assertNotEquals(wrongAnswer, result);
		amount = 1.0;
		answer = "100 c";
		wrongAnswer = "100.0 c";
		result = CurrencyHelper.toCoins(amount);
		assertEquals(answer, result);
		assertNotEquals(wrongAnswer, result);
		amount = 0.1;
		answer = "10 c";
		wrongAnswer = "10.0 c";
		result = CurrencyHelper.toCoins(amount);
		assertEquals(answer, result);
		assertNotEquals(wrongAnswer, result);
		amount = 0.01;
		answer = "1 c";
		wrongAnswer = "1.0 c";
		result = CurrencyHelper.toCoins(amount);
		assertEquals(answer, result);
		assertNotEquals(wrongAnswer, result);
		amount = 0.001;
		answer = "0 c";
		wrongAnswer = "1.0 c";
		result = CurrencyHelper.toCoins(amount);
		assertEquals(answer, result);
		assertNotEquals(wrongAnswer, result);
	}

	@Test
	public void testCoinsToAmount() {
		String coins = "1 c";
		double correctAnswer = 0.01;
		double wrongAnswer = 1;
		double result = CurrencyHelper.coinsToAmount(coins);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		coins = "10 c";
		correctAnswer = 0.1;
		wrongAnswer = 1;
		result = CurrencyHelper.coinsToAmount(coins);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		coins = "100 c";
		correctAnswer = 1;
		wrongAnswer = 0;
		result = CurrencyHelper.coinsToAmount(coins);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		coins = "1000 c";
		correctAnswer = 10;
		wrongAnswer = 0;
		result = CurrencyHelper.coinsToAmount(coins);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		coins = "10000 c";
		correctAnswer = 100;
		wrongAnswer = 0;
		result = CurrencyHelper.coinsToAmount(coins);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		coins = "100000 c";
		correctAnswer = 1000;
		wrongAnswer = 0;
		result = CurrencyHelper.coinsToAmount(coins);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		coins = "1c";
		correctAnswer = 0.01;
		wrongAnswer = 0;
		result = CurrencyHelper.coinsToAmount(coins);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		coins = "10c";
		correctAnswer = 0.1;
		wrongAnswer = 0;
		result = CurrencyHelper.coinsToAmount(coins);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		coins = "100c";
		correctAnswer = 1;
		wrongAnswer = 0;
		result = CurrencyHelper.coinsToAmount(coins);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
	}
	
	@Test
	public void testSubtract() {
		double a = 1;
		double b = 0.5;
		double correctAnswer = 0.5;
		double wrongAnswer = 0;
		double result = CurrencyHelper.subtract(a, b);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		a = 1;
		b = 0.65;
		correctAnswer = 0.35;
		wrongAnswer = 0;
		result = CurrencyHelper.subtract(a, b);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		a = 1;
		b = 0.25;
		correctAnswer = 0.75;
		wrongAnswer = 0;
		result = CurrencyHelper.subtract(a, b);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		a = 0.75;
		b = 0.25;
		correctAnswer = 0.5;
		wrongAnswer = 0;
		result = CurrencyHelper.subtract(a, b);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
		a = 0.75;
		b = 0.75;
		correctAnswer = 0;
		wrongAnswer = 1;
		result = CurrencyHelper.subtract(a, b);
		assertEquals(correctAnswer, result, 0.01);
		assertNotEquals(wrongAnswer, result);
	}

}
