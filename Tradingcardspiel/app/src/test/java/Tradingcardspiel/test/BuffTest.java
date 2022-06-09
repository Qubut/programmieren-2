import static org.junit.Assert.*;
import org.junit.*;

public class BuffTest {

  @Test
  public void testConstructorValid(){
    Buff b = new Buff("Rage", -2, 4);

    assertEquals("Name must be Rage", b.getName(), "Rage");
    assertEquals("defenseBuff must be -2", b.getDefenseBuff(), -2);
    assertEquals("attackBuff must be 4", b.getAttackBuff(), 4);

    b = new Buff("Attack", 0, 2);
    assertEquals("defenseBuff must be 0", b.getDefenseBuff(), 0);

    b = new Buff("Defense", 2, 0);
    assertEquals("attackBuff must be 0", b.getAttackBuff(), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullName(){
    new Buff(null, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorEmptyName(){
    new Buff(new String(""), 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorBothZero(){
    new Buff("Useless", 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorOneZeroOneNegative(){
    new Buff("Useless", -1, 0);
  }

  @Test
  public void testEqualsIsCorrect() throws NoSuchMethodException, SecurityException {
    Buff b = new Buff("Rage", -2, 4);
    assertTrue("equals method must have Object as argument", b.getClass().getMethod("equals", Object.class).getDeclaringClass() == Buff.class);
  }

  @Test
  public void testEquals(){
    Buff b = new Buff("Rage", -2, 4);

    assertTrue("equals must return true on identical objects",
        b.equals(b));

    assertFalse("equals must return false on false",
        b.equals(null));

    assertFalse("equals must return false on objects of different type",
        b.equals("Buff!"));

    assertTrue("equals must return true on equals objects",
        b.equals(new Buff("Rage", -2, 4)));

    assertFalse("equals must return false on different names",
        b.equals(new Buff("Rage!", -2, 4)));

    assertFalse("equals must return false on different defense buffs",
        b.equals(new Buff("Rage", -1, 4)));

    assertFalse("equals must return false on different attack buffs",
        b.equals(new Buff("Rage", -2, 3)));

  }
  
}
