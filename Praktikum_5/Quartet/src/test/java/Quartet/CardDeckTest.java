package Quartet;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class CardDeckTest {

	@Test
	public void test_constructorValid() {
		CardDeck cd = new CardDeck();
		assertFalse("cards must be initialized",cd.getCards()==null);
		assertTrue("cards must be initialized with a new and empty ArrayList<Card>",cd.getCards().size()==0);
	}
	
	@Test
	public void test_addCardValid() {
		CardDeck cd = new CardDeck();
		Card card1 = new Card("Ferrari F512 M",4943,12,440,5);
		Card card2 = new Card("Ferrari 812 Competizione",6496,12,830,3);
		
		cd.addCard(card1);
		cd.addCard(card2);
		
		assertEquals("Cards not properly set", 2, cd.getCards().size());
		assertEquals("Cards not in correct order set", card1, cd.getCards().get(0));
	}
	
	@Test
	public void test_addCardInvalid() {
		CardDeck cd = new CardDeck();
		Card card1 = new Card("Ferrari F512 M",4943,12,440,5);
		
		cd.addCard(card1);
		
		assertThrows("Must throw IllegalArgumentException on null card", IllegalArgumentException.class,() -> cd.addCard(null));
		assertThrows("Must throw CardDeckException on same card", CardDeckException.class,() -> cd.addCard(card1));
	}
	
	@Test
	public void test_removeFirstCard() {
		
		CardDeck cd = new CardDeck();
		Card card1 = new Card("Ferrari F512 M",4943,12,440,5);
		Card card2 = new Card("Ferrari 812 Competizione",6496,12,830,3);
		
		cd.addCard(card1);
		cd.addCard(card2);
		
		Card result = cd.removeFirstCard();
		assertEquals("The wrong card was removed", card1, result);
		assertEquals("The card was not removed", 1, cd.getCards().size());
		assertEquals("The wrong card was removed", card2, cd.getCards().get(0));
		
		result = cd.removeFirstCard();
		assertEquals("The wrong card was removed", card2, result);
		assertEquals("The card was not removed", 0, cd.getCards().size());	
		
	}
	
	@Test
	public void test_addAllCardsValid() {
		CardDeck cd = new CardDeck();
		CardDeck cdToAdd = new CardDeck();
		Card card1 = new Card("Ferrari F512 M",4943,12,440,5);
		Card card2 = new Card("Ferrari 812 Competizione",6496,12,830,3);
		
		cdToAdd.addCard(card1);
		cdToAdd.addCard(card2);
		
		cd.addAllCards(cdToAdd);
		
		assertEquals("the CardDeck paramter should be empty after all cards are added.", 0, cdToAdd.getCards().size());
		assertEquals("the cards of the CardDeck paramter should be in the currend cards List", 2, cd.getCards().size());
		assertEquals("wrong position of cards", card1, cd.getCards().get(0));
		assertEquals("wrong position of cards", card2, cd.getCards().get(1));
	}
	
	@Test
	public void test_addAllCardsInvalid() {
		CardDeck cd = new CardDeck();
		assertThrows("Must throw IllegalArgumentException on null CardDeck parameter", IllegalArgumentException.class,() -> cd.addAllCards(null));
	}
	
	@Test
	public void test_shuffleCards() {
		CardDeck cd = getFerrariCardDeck();
		Card[] usedCards = cd.getCards().toArray(new Card[cd.getCards().size()]);
		
		String cardPositionBefore = "";
		for(Card currentCard:usedCards) {
			cardPositionBefore += Integer.toString(cd.getCards().indexOf(currentCard));
		}
		
		cd.shuffleCards();
		
		String cardPositionAfter = "";
		for(Card currentCard:usedCards) {
			cardPositionAfter += Integer.toString(cd.getCards().indexOf(currentCard));
		}
		
		assertNotEquals("shuffleCards() doesn't change the positions of the cards.", cardPositionBefore, cardPositionAfter);		
		
	}
	
	@Test
	public void test_size() {
		CardDeck cd = getFerrariCardDeck();
		assertEquals("size doesn't return the correct value", cd.size(), cd.getCards().size());
		
	}
	
	@Test 
	public void test_isEmpty() {
		CardDeck cd1 = getFerrariCardDeck();
		CardDeck cd2 = new CardDeck();
		
		assertTrue("CardDeck is empty", cd2.isEmpty());
		assertFalse("CardDeck is not empty", cd1.isEmpty());
	}
	
	@Test
	public void test_sortByCylinderCapacity() {
		CardDeck cd = getFerrariCardDeck();
		Card[] usedCards = cd.getCards().toArray(new Card[cd.getCards().size()]);

		cd.sortByCylinderCapacity();
		
		String cardsPosition = "";
		for(Card currentCard:usedCards) {
			cardsPosition += Integer.toString(cd.getCards().indexOf(currentCard));
		}
		
		assertEquals("The cards are not in the right order", "1045326", cardsPosition);			
	}
	
	@Test
	public void test_sortByCylinderCount() {
		CardDeck cd = getFerrariCardDeck();
		Card[] usedCards = cd.getCards().toArray(new Card[cd.getCards().size()]);

		cd.sortByCylinderCount();
		
		String cardsPosition = "";
		for(Card currentCard:usedCards) {
			cardsPosition += Integer.toString(cd.getCards().indexOf(currentCard));
		}
		
		assertEquals("The cards are not in the right order", "3012546", cardsPosition);			
	}
	
	@Test
	public void test_sortByHorsePower() {
		CardDeck cd = getFerrariCardDeck();
		Card[] usedCards = cd.getCards().toArray(new Card[cd.getCards().size()]);

		cd.sortByHorsePower();
		
		String cardsPosition = "";
		for(Card currentCard:usedCards) {
			cardsPosition += Integer.toString(cd.getCards().indexOf(currentCard));
		}
		
		assertEquals("The cards are not in the right order", "6145302", cardsPosition);			
	}
	
	@Test
	public void test_sortByAcceleration() {
		CardDeck cd = getFerrariCardDeck();
		Card[] usedCards = cd.getCards().toArray(new Card[cd.getCards().size()]);

		cd.sortByAcceleration();
		
		String cardsPosition = "";
		for(Card currentCard:usedCards) {
			cardsPosition += Integer.toString(cd.getCards().indexOf(currentCard));
		}
		
		assertEquals("The cards are not in the right order", "6145302", cardsPosition);			
	}
	
	@Test
	public void test_iterator() {
		CardDeck cd = getFerrariCardDeck();
		assertTrue("CardDeck is not a instance of Iterable",cd instanceof Iterable);
		
		Card[] usedCards = cd.getCards().toArray(new Card[cd.getCards().size()]);
		
		Iterator<Card> it = cd.iterator();
		for(int i=0;i<usedCards.length;i++) {
			
			assertTrue("hasNext should return true", it.hasNext());
			
			Card card = it.next();
			assertNotNull("card shouldn't be null",card);
			assertTrue("Wrong card returned", card == usedCards[i]);
			assertTrue("Card is not removed from CardDeck", cd.getCards().size() == (usedCards.length - i -1));
			
		}
		
		assertThrows("Must throw NoSuchElementException on empty CardDeck", NoSuchElementException.class,() -> it.next());
		
	}
	
	@Test
	public void test_equals() {
		CardDeck cd1 = getFerrariCardDeck();
		CardDeck cd2 = getFerrariCardDeck();
		CardDeck cd3 = getFerrariCardDeck();
		cd3.removeFirstCard();
		assertEquals("Equals should compare the cards attribute",cd1,cd2);
		assertNotEquals("The cards of both objects are not equal",cd1,cd3);
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
