package Quartet;

import java.util.Objects;

/**
@param name: player's name
@param CardDeck: cards each player has at each round of the Game
@param currentCard: the card deducted from cardDeck
at each round {@link #playCard} 
*/
public class Player {
    private String name;
    private CardDeck cards;
    private Card currentCard;
    private boolean human;

    private Boolean checkString(String str) {
        if (str == null)
            throw new IllegalArgumentException("name cann't be null");
        return true;
    }
    // constructors
    public Player(String name, boolean human) {
        checkString(name);
        this.name = name;
        this.human = human;
        this.cards = new CardDeck();
    }

    public Player(String name) {
        this(name, false);
    }
    // getters
    public String getName() {
        return name;
    }

    public CardDeck getCards() {
        return cards;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public boolean isHuman() {
        return human;
    }

    public void setCurrentCardNull() {
        this.currentCard = null;
    }
    /**
    @return currentCard 
    after removing it from cardDeck {@link #CardDeck::removeFirstCard}
    */
    public Card playCard() {
        if (hasCards())
            this.currentCard = this.cards.removeFirstCard();
        else
            this.currentCard = null;
        return this.currentCard;
    }

    public boolean hasCards() {
        return this.cards.size() > 0;
    }

    /**
    @return true if the currentCard the same card that won against the other players cards
    through a sorting Strategy
    {@link Game::play::sortByStrategy}
    */
    public boolean hasWon(Card otherCard) {
        return this.currentCard.compareTo(otherCard)==0;
    };

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o.getClass()==this.getClass()))
            return false;
        Player player = (Player) o;
        return isHuman() == player.isHuman() &&
                Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCards(), getCurrentCard(), isHuman());
    }
}
