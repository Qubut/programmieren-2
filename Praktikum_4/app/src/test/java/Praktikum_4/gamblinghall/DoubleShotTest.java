package Praktikum_4.gamblinghall;


import static org.junit.Assert.*;

import org.junit.Test;

import Praktikum_4.gamblinghall.DoubleShot;

public class DoubleShotTest {

	@Test
	public void test_constructorValid() {

		DoubleShot ds1 = new DoubleShot("Einarmiger Bandit");
		DoubleShot ds2 = new DoubleShot("Zweiarmiger Bandit", 2);

		assertEquals("Name not properly set", "Einarmiger Bandit", ds1.getName());
		assertEquals("Stake not properly set", 1, ds1.getStake());
		assertEquals("Stake not properly set", 2, ds2.getStake());

	}

	@Test
	public void test_constructorInvalid() {

		assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class,
				() -> new DoubleShot(null));

		assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class,
				() -> new DoubleShot(""));

		assertThrows("Must throw IllegalArgumentException on stake < 1", IllegalArgumentException.class,
				() -> new DoubleShot("Einarmiger Bandit", 0));

		assertThrows("Must throw IllegalArgumentException on stake > 10", IllegalArgumentException.class,
				() -> new DoubleShot("Einarmiger Bandit", 11));

	}

	@Test
	public void test_playInvalid() {
		DoubleShot ds1 = new DoubleShot("Einarmiger Bandit");

		assertThrows("Must throw IllegalArgumentException on stake != 1", IllegalArgumentException.class,
				() -> ds1.play(2));
	}

	@Test
	public void test_playWorking() {

		int countIterations = 1000000;
		DoubleShot ds1 = new DoubleShot("Einarmiger Bandit");
		double win = 0;
		for (int i = 0; i < countIterations; i++) {
			win += ds1.play(1);
		}
		double profit = countIterations - win;

		assertEquals("Profit not correct calculated", profit, ds1.getProfit(), 0.01);
		assertEquals("Revenue not correct calculated", (double) countIterations, ds1.getRevenue(), 0.01);

		double payOutRate = (countIterations - profit) / countIterations;

		assertEquals("payOutRate not correct calculated", payOutRate, ds1.getRealPayOutRate(), 0.01);

		double payOutRateSetting = 0.92;
		assertEquals("payOutRate should be 0.92", payOutRateSetting, ds1.getRealPayOutRate(), 0.1);

	}

}
