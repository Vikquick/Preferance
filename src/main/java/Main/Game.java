package Main;

import Controllers.DeckController;
import Controllers.NamingController;
import Models.Cards.Card;
import Models.Gamers.Gamer;

import java.util.List;

public class Game {

    public static Gamer first;
    public static Gamer second;
    public static Gamer third;

    public void start() {
        DeckController deckController = new DeckController();
        NamingController namingController = new NamingController();
        List<Card> deck = deckController.fillDeck();
        System.out.println(deck.size());
        deckController.reshuffleDeck(deck);

        first = new Gamer(namingController.nameGamer(), null);
        second = new Gamer(namingController.nameGamer(), null);
        third = new Gamer(namingController.nameGamer(), null);

        deckController.startingGivingCards(first, second, third, deck);
        System.out.println(first.getName());
        System.out.println(second.getName());
        System.out.println(third.getName());

        System.out.println("Колода игрока " + first.getName() + " - ");
        for (int i = 0; i < first.deck.size(); i++) {
            System.out.println(first.deck.get(i).cardWeight + " " + first.deck.get(i).suit);
        }

        first.deck.get(0).setRunk();
        System.out.println("Ранг первой карты первого игрока - "+ first.deck.get(0).getRunk());
    }
}
