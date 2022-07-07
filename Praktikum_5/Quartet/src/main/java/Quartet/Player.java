package Quartet;

import java.util.Objects;

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

    public Player(String name, boolean human) {
        checkString(name);
        this.name = name;
        this.human = human;
        this.cards = new CardDeck();
    }

    public Player(String name) {
        this(name, false);
    }

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
