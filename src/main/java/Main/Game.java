package Main;

import Controllers.DeckController;
import Controllers.NamingController;
import Models.Cards.Card;
import Models.Gamers.Bullet;
import Models.Gamers.Gamer;
import Models.Games.Round;

import java.util.List;

public class Game {

    public static Gamer first;
    public static Gamer second;
    public static Gamer third;
    public static  Gamer fourth;
    static int co;

    public void start() {
        DeckController deckController = new DeckController();
        NamingController namingController = new NamingController();
        List<Card> deck = deckController.fillDeck();
        System.out.println(deck.size());
        deckController.reshuffleDeck(deck);

        first = new Gamer(namingController.nameGamer(), new Bullet(0,0,0));
        second = new Gamer(namingController.nameGamer(), new Bullet(0,0,0));
        third = new Gamer(namingController.nameGamer(), new Bullet(0,0,0));
        fourth = new Gamer(namingController.nameGamer(), null); //Раздающий

        deckController.startingGivingCards(first, second, third, deck);
        System.out.println(first.getName());
        System.out.println(second.getName());
        System.out.println(third.getName());

        //Отладка, вывод карт в руке игрока №1
        System.out.println("Колода игрока " + first.getName() + " - ");
        for (int i = 0; i < first.deck.size(); i++) {
            System.out.println(first.deck.get(i).cardWeight + " " + first.deck.get(i).suit);
        }

        //Отладка, вывод рангов карт в руке игрока №1
        first.getRunkForHandDeck(first.deck);
        for (int i = 0; i < first.deck.size(); i++) {
            System.out.println("Ранг " + i + " карты игрока " + first.name + " - " + first.deck.get(i).getRunk());
        }

        //Отладка, вывод карт в руке игрока №2
        System.out.println("Колода игрока " + first.getName() + " - ");
        for (int i = 0; i < first.deck.size(); i++) {
            System.out.println(first.deck.get(i).cardWeight + " " + first.deck.get(i).suit);
        }

        //Отладка, вывод рангов карт в руке игрока №2
        second.getRunkForHandDeck(second.deck);
        for (int i = 0; i < second.deck.size(); i++) {
            System.out.println("Ранг " + i + " карты игрока " + second.name + " - " + second.deck.get(i).getRunk());
        }

        //Отладка, вывод карт в руке игрока №3
        System.out.println("Колода игрока " + first.getName() + " - ");
        for (int i = 0; i < first.deck.size(); i++) {
            System.out.println(first.deck.get(i).cardWeight + " " + first.deck.get(i).suit);
        }

        //Отладка, вывод рангов карт в руке игрока №3
        third.getRunkForHandDeck(third.deck);
        for (int i = 0; i < third.deck.size(); i++) {
            System.out.println("Ранг " + i + " карты игрока " + third.name + " - " + third.deck.get(i).getRunk());
        }

        Round firstRound = new Round();
        firstRound.startRound(first, second, third);
    }
}
