package Quartet;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
/**
class Game: represents the interaction between the players and the deck of cards
@param players: list of players in the game
@param cards: the card's deck of type CardDeck
@param sizeOfDeck: number of cards in the deck
*/
public class Game {
    private final List<Player> players;
    private final CardDeck cards;
    private final int sizeOfDeck;

    /**
    @exception human Player > 1 or players < 2 or cards number< players number 
    @throws IllegalArgumentException 
    */
    public Game(CardDeck cardDeck, List<Player> players) {
        if (players.stream().filter(p -> p.isHuman()).count() > 1 || players.size() < 2
                || cardDeck.size() < players.size())
            throw new IllegalArgumentException();
        this.players = players;
        cardDeck.shuffleCards();
        this.cards = cardDeck;
        this.sizeOfDeck = cardDeck.size();
    }
    /**
    cards are distributed sequentially by removing the first element 
    from the deck {@link #CardDeck::removeFirstCard}
    */
    public void distributeCards() {
        IntStream.range(0, sizeOfDeck).forEach(n -> players.get((n < players.size()) ? n : n % players.size())
                .getCards().addCard(cards.removeFirstCard()));

    }

    public void play() {
        distributeCards();
        CardDeck cardDeck = new CardDeck();
        IntStream.iterate(1, n -> n + 1).takeWhile(n -> !isGameOver()).forEach(n -> {
            displayRundeInfo(n);
            players.stream().forEach(p -> {
                if (p.isHuman() && p.hasCards())
                    displayPlayerInfo(p, p.getCards().getCards().get(0));
                // adding play card
                var playCard = p.playCard();
                if (playCard != null)
                    cardDeck.addCard(playCard);
            });
            sortByStrategy(cardDeck);
            var winCard = cardDeck.getCards().get(0);
            // player that won
            players.stream().filter(pl -> (pl.getCurrentCard() != null) ? pl.hasWon(winCard) : false).findFirst()
                    .ifPresent(winner -> {
                        winner.getCards().addAllCards(cardDeck);
                        displayWinnerInfo(winner);
                    });

        });
    }

    public Player getHumanPlayer() {
        return players.stream().filter(p -> p.isHuman()).findFirst().orElse(null);
    }

    public boolean hasHumanPlayer() {
        return players.stream().anyMatch(p -> p.isHuman());
    }

    public boolean isGameOver() {
        return players.stream().anyMatch(p -> p.getCards().size() == this.sizeOfDeck);
    }

    private void sortByStrategy(CardDeck cardDeck) {
        var criteria = (hasHumanPlayer() && getHumanPlayer().hasCards()) ? new Scanner(System.in).nextInt()
                : setCriteria();
        switch (criteria) {
            case 0:
                cardDeck.sortByAcceleration();
                break;
            case 1:
                cardDeck.sortByCylinderCapacity();
                break;
            case 2:
                cardDeck.sortByCylinderCount();
                break;
            default:
                cardDeck.sortByHorsePower();
                break;
        }
    }

    private void displayPlayerInfo(Player human, Card cardOfHuman) {
        // players cards remaining
        var cardsAvailable = human.getCards().size();
        System.out.println("Ihre aktuelle Karte:");
        System.out.println(cardOfHuman.toString());
        System.out.println("Sie haben noch " + cardsAvailable + " Karte.");
        System.out.println("Welche Eigenschaft wollen Sie spielen?");
        System.out.println("0: Hubraum");
        System.out.println("1: Zylinder");
        System.out.println("2: PS");
        System.out.println("3: 0 - 100 km/h");
    }

    private void displayRundeInfo(int i) {
        System.out.println("-------------------------------------------");
        System.out.println("Runde " + i);
    }

    private int setCriteria() {
        return (int) Math.floor(Math.random() * 4);
    }

    private void displayWinnerInfo(Player winner) {
        if (winner.isHuman()) {
            System.out.println("Sie haben die Runde gewonnen");
            return;
        }
        System.out.println(winner.getName() + " hat die Runde gewonnen. Seine Karte war::");
        System.out.println(winner.getCurrentCard().toString());

    }
}
