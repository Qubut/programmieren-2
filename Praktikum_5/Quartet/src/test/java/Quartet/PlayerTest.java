package Quartet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerTest {
	
	@Test
	public void test_constructorValid() {
		Player hans = new Player("Hans",true);
		
		assertEquals("The name of the player is not properly set","Hans",hans.getName());
		assertTrue("The human attribute of the player is not properly set",hans.isHuman());
		
		Player r2d2 = new Player("R2D2");
		assertFalse("The human attribute of the player is not properly set",r2d2.isHuman());
	}
	
	@Test
	public void test_constructorInvalid() {
		
		assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class,
				() ->  new Player(null,true));
		assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class,
				() ->  new Player(null));

	}
	
	@Test
	public void test_hasCards() {
		Player gerd = new Player("gerd",true);
		CardDeck cd = getFerrariCardDeck();
		assertFalse("player has no cards but hasCards returns true", gerd.hasCards());
		gerd.getCards().addAllCards(cd);
		assertTrue("player has cards but hasCards returns false", gerd.hasCards());
		
	}
	
	@Test
	public void test_playCard() {
		Player gerd = new Player("gerd",true);
		CardDeck cd = getFerrariCardDeck();
		int startSize = cd.size();
		gerd.getCards().addAllCards(cd);
		
		 
		for(int i =startSize-1 ; i>= 0 ; i--) {
			Card playedCard = gerd.playCard();
			assertNotNull("The player should have cards but null was returned", playedCard);
			assertTrue("The current card of the player and the card returned from playCard() must be identical.", playedCard == gerd.getCurrentCard());
			assertTrue("The played card must be removed from CardDeck Object",gerd.getCards().size() == i);
		}
		
		assertNull("The player has no cards but a card was returned",gerd.playCard());

	}
	
	@Test
	public void test_setCurrentCardNull() {
		Player gerd = new Player("gerd",true);
		CardDeck cd = getFerrariCardDeck();
		gerd.getCards().addAllCards(cd);
		gerd.playCard();
		gerd.setCurrentCardNull();
		assertNull("the current card should be set to null", gerd.getCurrentCard());
	}
	
	@Test
	public void test_hasWon() {
		Player gerd = new Player("gerd",true);
		CardDeck cd = getFerrariCardDeck();
		gerd.getCards().addAllCards(cd);
		Card card = gerd.playCard();
		assertTrue("The winner was not recognized from the winning card", gerd.hasWon(card));
		gerd.playCard();
		assertFalse("The loser was recognized as winner", gerd.hasWon(card));
		
	}
	
	@Test
	public void test_Equals() {
		Player p1 = new Player("Nancy");
		Player p2 = new Player("Nancy");
		assertEquals("Players must be equal",p1,p2);
		Player p3 = new Player("Kevin",true);
		Player p4 = new Player("Kevin",true);
		p4.getCards().addAllCards(getFerrariCardDeck());
		assertEquals("Players must be equal",p3,p4);
		
	}
	
	private CardDeck getFerrariCardDeck() {
		CardDeck cd = new CardDeck();
		Card card0 = new Card("Ferrari F512 M",4943,12,440,5);
		Card card1 = new Card("Ferrari 812 Competizione A",6496,12,830,3);
		Card card2 = new Card("Ferrari Portofino M",3855,12,620,3);
		Card card3 = new Card("Ferrari Roma",3855,12,620,3);
		Card card4 = new Card("Ferrari F8 Spider",3902,8,720,3);
		Card card5 = new Card("Ferrari SF90 Stradale",3990,8,1000,2);
		Card card6 = new Card("Ferrari 296 GTB",2992,6,830,3);
		
		cd.addCard(card0);
		cd.addCard(card1);
		cd.addCard(card2);
		cd.addCard(card3);
		cd.addCard(card4);
		cd.addCard(card5);
		cd.addCard(card6);
		
		return cd;
	}
	

}
