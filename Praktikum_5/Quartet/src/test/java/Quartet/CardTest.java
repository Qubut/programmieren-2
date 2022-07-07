package Quartet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class CardTest {

	@Test
	public void test_constructorValid() {
		Card card1 = new Card("Ferrari F512 M",4943,12,440,5);
		//Card card2 = new Card("Ferrari 812 Competizione",6496,12,830,3);
		
		assertEquals("Name not properly set", "Ferrari F512 M", card1.getName());
		assertEquals("Cylinder Capacity not properly set", 4943, card1.getCylinderCapacity());
		assertEquals("Cylinder Count not properly set", 12, card1.getCylinderCount());
		assertEquals("Horse power not properly set", 440, card1.getHorsePower());
		assertEquals("Acceleration not properly set", 5, card1.getAcceleration());
	}
	
	@Test
	public void test_constructorInvalid() {

		assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class,
				() -> new Card(null,1900,4,180,12));

		assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class,
				() -> new Card("",1900,4,180,12));

		assertThrows("Must throw IllegalArgumentException on cylinder capacity < 1", IllegalArgumentException.class,
				() -> new Card("Golf V",0,4,180,12));
		
		assertThrows("Must throw IllegalArgumentException on cylinder count < 1", IllegalArgumentException.class,
				() -> new Card("Golf V",1900,0,180,12));
		
		assertThrows("Must throw IllegalArgumentException on horsepower < 1", IllegalArgumentException.class,
				() -> new Card("Golf V",1900,4,0,12));
		
		assertThrows("Must throw IllegalArgumentException on acceleration < 1", IllegalArgumentException.class,
				() -> new Card("Golf V",1900,4,0,0));

	}
	
	@Test
	public void test_naturalOrder() {
		
		
		/*
		 * Wert muss < 0 sein, wenn this vor other
		 * Wert muss > 0 sein, wenn this hinter other
		 * 
		 */
		
		
		Card original = new Card("Ferrari F512 M",4943,12,440,5);
		Card compare = new Card("Ferrari F512 M",4943,12,440,6);
		
		//Beschleunigung, Orginal ist schnell, muss vor compare liegen
		assertTrue(original.compareTo(compare)<0);
		assertTrue(compare.compareTo(original)>0);
		
		//Perdest�rken, orginal hat mehr, muss also vor compare liegen
		compare = new Card("Ferrari F512 M",4943,12,439,5);
		assertTrue(original.compareTo(compare)<0);
		assertTrue(compare.compareTo(original)>0);
		
		//Hubraum, orginal hat mehr, muss also vor compare liegen
		compare = new Card("Ferrari F512 M",4942,12,440,5);
		assertTrue(original.compareTo(compare)<0);
		assertTrue(compare.compareTo(original)>0);
		
		//Zylinder, orginal hat mehr, muss also vor compare liegen
		compare = new Card("Ferrari F512 M",4943,10,440,5);
		assertTrue(original.compareTo(compare)<0);
		assertTrue(compare.compareTo(original)>0);
		
		//Name, orginal (F) steht vor compare (Z)
		compare = new Card("Zerrari F512 M",4943,12,440,5);
		assertTrue(original.compareTo(compare)<0);
		assertTrue(compare.compareTo(original)>0);
		
		//alle! Attribute identisch
		compare = new Card("Ferrari F512 M",4943,12,440,5);
		assertTrue(original.compareTo(compare)==0);

	}

	
	
	@Test
	public void test_equals() {
		Card card1 = new Card("Ferrari F512 M",4943,12,440,5);
		Card card2 = new Card("Ferrari F512 M",4943,12,440,5);
		Card card3 = new Card("Ferrari Testarossa",4943,12,440,5);
		
		assertEquals("Equals distinguishes the objects by all final attributes",card1,card2);
		assertNotEquals("Equals distinguishes the objects by all final attributes", card1, card3);
		assertNotEquals("comparison with null",card1,null);
	}

}
