package Quartet;

import java.util.*;
/**
@class CardDeck represents the cards encapsulation entity
@param cards: list of cards  
*/
public class CardDeck implements Iterable<Card> {
    private final List<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }
    /**
    @exception @param cardDeck = null @throws IllegalArgumentException
    */
    public void addAllCards(CardDeck cardDeck) {
        if (cardDeck == null)
            throw new IllegalArgumentException("Cards cann't be null");
        this.cards.addAll(cardDeck.getCards());
        cardDeck.getCards().clear();

    }

    /**
    @exception @param card == null or card in cards @throws IllegalArgumentException
    */
    public void addCard(Card card) {
        if (card == null)
            throw new IllegalArgumentException("card cannot be null");
        if (cards.stream().anyMatch(c -> card.equals(c)))
            throw new CardDeckException("card already exists", new IllegalArgumentException());
        this.cards.add(card);
    }

    public void shuffleCards() {
        Collections.shuffle(this.cards);
    }

    public void sortByCylinderCapacity() {
        Collections.sort(this.cards,
                Comparator.comparingInt(Card::getCylinderCapacity).reversed().thenComparing(Card::compareTo));
    }

    public void sortByCylinderCount() {
        Collections.sort(this.cards,
                Comparator.comparingInt(Card::getCylinderCount).reversed().thenComparing(Card::compareTo));
    }

    public void sortByHorsePower() {
        Collections.sort(this.cards,
                Comparator.comparingInt(Card::getHorsePower).reversed().thenComparing(Card::compareTo));
    }

    public void sortByAcceleration() {
        Collections.sort(this.cards, Comparator.comparingInt(Card::getAcceleration).thenComparing(Card::compareTo));
    }


    /**
    @exception cards.size() == 0  @throws NoSuchElementException
    */
    public Card removeFirstCard() {
        if (size() == 0)
            throw new NoSuchElementException();
        return this.cards.remove(0);
    }

    public int size() {
        return this.cards.size();
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        var that = this;
        return new Iterator<Card>() {
            @Override
            public boolean hasNext() {
                if (that.size() != 0) {
                    return true;
                }
                return false;
            }

            @Override
    /**
    @throws NoSuchElementException {@link #removeFirstCard}
    */
            public Card next() {
                return that.removeFirstCard();
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o.getClass() == this.getClass()))
            return false;
        CardDeck cards1 = (CardDeck) o;
        return Objects.equals(getCards(), cards1.getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCards());
    }
}
