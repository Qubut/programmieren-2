package Praktikum_4.gamblinghall;

import static org.junit.Assert.*;

import org.junit.Test;

import Praktikum_4.gamblinghall.Roulette;
import Praktikum_4.gamblinghall.RouletteGameType;

public class RouletteTest {

	@Test
	public void test_constructorValid() {

		Roulette r1 = new Roulette("Monte Carlo");
		Roulette r2 = new Roulette("Las Vegas", 2);
		Roulette r3 = new Roulette("Macao", RouletteGameType.EVEN);

		assertEquals("Name not properly set", "Monte Carlo", r1.getName());
		assertEquals("RouletteGameType not properly set", RouletteGameType.BLACK, r1.getRouletteGameType());
		assertEquals("RouletteGameType not properly set", RouletteGameType.NUMBER, r2.getRouletteGameType());
		assertEquals("BetNumber not properly set", 2, r2.getBetNumber());
		assertEquals("RouletteGameType not properly set", RouletteGameType.EVEN, r3.getRouletteGameType());

	}

	@Test
	public void test_constructorInvalid() {

		assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class,
				() -> new Roulette(null));

		assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class,
				() -> new Roulette(""));

		assertThrows("Must throw IllegalArgumentException on betNumber < 0", IllegalArgumentException.class,
				() -> new Roulette("Monte Carlo", -1));

		assertThrows("Must throw IllegalArgumentException on betNumber > 36", IllegalArgumentException.class,
				() -> new Roulette("Monte Carlo", 37));

		assertThrows("Must throw IllegalArgumentException RouletteGameType = NUMBER", IllegalArgumentException.class,
				() -> new Roulette("Monte Carlo", RouletteGameType.NUMBER));

	}

	@Test
	public void test_setRouletteGameTypeInvalid() {
		Roulette r1 = new Roulette("Monte Carlo");

		assertThrows("Must throw IllegalArgumentException on RouletteGameType = NULL", IllegalArgumentException.class,
				() -> r1.setRouletteGameType(null));

	}

	@Test
	public void test_setBetNumberInvalid() {
		Roulette r1 = new Roulette("Monte Carlo");
		r1.setRouletteGameType(RouletteGameType.NUMBER);

		assertThrows("Must throw IllegalArgumentException on betNumber < 0", IllegalArgumentException.class,
				() -> r1.setBetNumber(-1));

		assertThrows("Must throw IllegalArgumentException on betNumber > 36", IllegalArgumentException.class,
				() -> r1.setBetNumber(37));

	}

	@Test
	public void test_playInvalid() {
		Roulette r1 = new Roulette("Monte Carlo");

		assertThrows("Must throw IllegalArgumentException on stake <= 0", IllegalArgumentException.class,
				() -> r1.play(0));
		assertThrows("Must throw IllegalArgumentException on stake > 10", IllegalArgumentException.class,
				() -> r1.play(11));

	}

	@Test
	public void test_playWorking() {

		int countIterations = 10000000;
		Roulette r1 = new Roulette("Monte Carlo", RouletteGameType.EVEN);
		double win = 0;
		for (int i = 0; i < countIterations; i++) {
			win += r1.play(1);
		}
		double profit = countIterations - win;

		assertEquals("Profit not correct calculated", profit, r1.getProfit(), 0.01);
		assertEquals("Revenue not correct calculated", (double) countIterations, r1.getRevenue(), 0.01);

		double payOutRate = (countIterations - profit) / countIterations;

		assertEquals("payOutRate not correct calculated", payOutRate, r1.getRealPayOutRate(), 0.01);

		double payOutRateSetting = 0.97;
		assertEquals("payOutRate should be 0.97", payOutRateSetting, r1.getRealPayOutRate(), 0.1);

	}

}
