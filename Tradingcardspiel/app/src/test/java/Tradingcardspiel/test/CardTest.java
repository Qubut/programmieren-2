import static org.junit.Assert.*;
import org.junit.*;

public class CardTest {

  private Card wolf;

  @Before
  public void init(){
    wolf = new Card("Wolf", 1, 3, Category.ANIMAL, 2);
  }

  @Test
  public void testConstructorValid(){

    Card card = new Card("Wolf", 1, 3, Category.ANIMAL, 2);

    assertEquals("Name must be Wolf", "Wolf", card.getName());
    assertEquals("baseDefense must be 1", 1, card.getBaseDefense());
    assertEquals("baseAttack must be 3", 3, card.getBaseAttack());
    assertEquals("category must be ANIMAL", Category.ANIMAL, card.getCategory());
    assertEquals("health must be 2", 2, card.getHealth());
    assertNull("buff must be null", card.getBuff());
    assertTrue("isAlive must return true", card.isAlive());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullName(){
    new Card(null, 1, 3, Category.ANIMAL, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorEmptyName(){
    new Card(new String(""), 1, 3, Category.ANIMAL, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeDefense(){
    new Card("Wolf", -1, 3, Category.ANIMAL, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeAttack(){
    new Card("Wolf", 1, -3, Category.ANIMAL, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullCategory(){
    new Card("Wolf", 1, 3, null, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAlreadyDead(){
    new Card("Wolf", 1, 3, Category.ANIMAL, 0);
  }

  @Test
  public void testSetBuff(){
    Buff buff = new Buff("Attack Buff", 0, 3);
    wolf.setBuff(buff);
    assertSame("buff must be identical to set buff", buff, wolf.getBuff());
  }

  @Test
  public void testGetAttack(){
    Buff buff = new Buff("Attack Buff", 0, 3);
    wolf.setBuff(buff);
    assertEquals("attack must be 6", 6, wolf.getAttack());
     
    buff = new Buff("Negative Attack", 5, -4);
    wolf.setBuff(buff);
    assertEquals("attack must be 0", 0, wolf.getAttack());
  }

  @Test
  public void testGetDefense(){
    Buff buff = new Buff("Defense Buff", 3, 0);
    wolf.setBuff(buff);
    assertEquals("defense must be 6", 4, wolf.getDefense());
     
    buff = new Buff("Negative Defense", -4, 5);
    wolf.setBuff(buff);
    assertEquals("defense must be 0", 0, wolf.getDefense());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackInvalid(){
    wolf.attack(null);
  }

  @Test
  public void testAttack(){
    Card packMule = new Card("Pack Mule", 0, 0, Category.ANIMAL, 5);

    assertEquals("health of mule must be 5", 5, packMule.getHealth());

    wolf.attack(packMule);

    assertEquals("health of mule must be 2", 2, packMule.getHealth());

    packMule.attack(wolf);

    assertEquals("health of wolf must be 2", 2, wolf.getHealth());

    wolf.attack(packMule);

    assertEquals("health of pack mule must be 0", 0, packMule.getHealth());
    assertFalse("packMule must not be alive", packMule.isAlive());

    try{
      wolf.attack(packMule);
      fail("attack against dead card must not be possible");
    } catch (IllegalArgumentException e){
      
    }

    try{
      packMule.attack(wolf);
      fail("dead card cannot attack another card");
    } catch (IllegalArgumentException e){
      
    }


  }

  @Test
  public void testAttackWithBuff(){
    wolf = new Card("Wolf", 1, 3, Category.ANIMAL, 4);
    Card packMule = new Card("Pack Mule", 0, 0, Category.ANIMAL, 5);

    Buff defensebuff = new Buff("Defense Buff", 2, 1);

    packMule.attack(wolf);

    assertEquals("health of wolf must be 4", 4, wolf.getHealth());

    packMule.setBuff(defensebuff);
    wolf.attack(packMule);

    assertEquals("health of mule must be 4", 4, packMule.getHealth());

    Buff rageBuff = new Buff("Minor Rage", -1, 2);

    wolf.setBuff(rageBuff);

    wolf.attack(packMule);

    assertEquals("health of packMule must be 1", 1, packMule.getHealth());

    packMule.setBuff(new Buff("AttackBuff", 0, 1));
    packMule.attack(wolf);
    assertEquals("health of wolf must be 3", 3, wolf.getHealth());

  }

  @Test
  public void testEqualsIsCorrect() throws NoSuchMethodException, SecurityException {
    assertTrue("equals method must have Object as argument", wolf.getClass().getMethod("equals", Object.class).getDeclaringClass() == Card.class);
  }

  @Test
  public void testEquals(){

    assertTrue("equals must return true on identical objects",
        wolf.equals(wolf));

    assertFalse("equals must return false on false",
        wolf.equals(null));

    assertFalse("equals must return false on objects of different type",
        wolf.equals("Card!"));

    assertTrue("equals must return true on equals objects",
        wolf.equals(new Card("Wolf", 1, 3, Category.ANIMAL, 2)));

    assertFalse("equals must return false on different names",
        wolf.equals(new Card("Wolf!", 1, 3, Category.ANIMAL, 2)));

    assertFalse("equals must return false on different base defenses",
        wolf.equals(new Card("Wolf", 2, 3, Category.ANIMAL, 2)));

    assertFalse("equals must return false on different base attacks",
        wolf.equals(new Card("Wolf", 1, 4, Category.ANIMAL, 2)));

    assertFalse("equals must return false on different categorys",
        wolf.equals(new Card("Wolf", 1, 3, Category.MACHINE, 2)));

    assertFalse("equals must return false on different healths",
        wolf.equals(new Card("Wolf", 1, 3, Category.ANIMAL, 4)));

    wolf.setBuff(new Buff("Rage", -2, 4));

    assertFalse("equals must return false on different buffs",
        wolf.equals(new Card("Wolf", 1, 3, Category.ANIMAL, 2)));

    Card wolf2 = new Card("Wolf", 1, 3, Category.ANIMAL, 2);

    wolf2.setBuff(new Buff("Minor Rage", -1, 2));
    assertFalse("equals must return false on different buffs", wolf.equals(wolf2));

    wolf2.setBuff(new Buff("Rage", -2, 4));
    assertTrue("equals must return true on equal cards with _equal_ buffs", wolf.equals(wolf2));

  }
   
  
}
