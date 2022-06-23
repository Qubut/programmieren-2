package Praktikum_4.gamblinghall;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import Praktikum_4.gamblinghall.ArcadeFun;

public class ArcadeFunTest {

	@Test
	public void test_constructorValid() {

		ArcadeFun af1 = new ArcadeFun("Crazy Pony");
		ArcadeFun af2 = new ArcadeFun("Flip Flop", 2);

		assertEquals("Name not properly set", "Crazy Pony", af1.getName());
		assertEquals("Stake not properly set", 1, af1.getStake());
		assertEquals("Stake not properly set", 2, af2.getStake());

	}

	@Test
	public void test_constructorInvalid() {

		assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class,
				() -> new ArcadeFun(null));

		assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class,
				() -> new ArcadeFun(""));

		assertThrows("Must throw IllegalArgumentException on stake < 1", IllegalArgumentException.class,
				() -> new ArcadeFun("Crazy Pony", 0));

		assertThrows("Must throw IllegalArgumentException on stake > 10", IllegalArgumentException.class,
				() -> new ArcadeFun("Crazy Pony", 11));

	}

	@Test
	public void test_playInvalid() {
		ArcadeFun af1 = new ArcadeFun("Crazy Pony");

		assertThrows("Must throw IllegalArgumentException on stake != 1", IllegalArgumentException.class,
				() -> af1.play(2));
	}

	@Test
	public void test_playWorking() {

		int countIterations = 1000;
		ArcadeFun af1 = new ArcadeFun("Crazy Pony");
		double win = 0;
		for (int i = 0; i < countIterations; i++) {
			win += af1.play(1);
		}
		double profit = countIterations - win;

		assertEquals("Profit not correct calculated", profit, af1.getProfit(), 0.01);
		assertEquals("Revenue not correct calculated", (double) countIterations, af1.getRevenue(), 0.01);
		assertEquals("Win must be always 0", win, 0.0, 0.001);

	}

}
