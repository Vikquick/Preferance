package Controllers;

import Models.Cards.Card;
import Models.Cards.CardWeight;
import Models.Cards.Suit;
import Models.Gamers.Gamer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckController {
    public static final Logger logger = Logger.getLogger(DeckController.class);

    //Создаем колоду
    public List<Card> fillDeck() {

        logger.info("Создаем колоду");
        List<Card> deck = new ArrayList<>();

        logger.info("Создаем матрицу объектов карт");
        CardWeight[] cardWeights = CardWeight.values();
        Suit[] suits = Suit.values();
        for (int i = 0; i < cardWeights.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                Card card = new Card();
                card.cardWeight = cardWeights[i];
                card.suit = suits[j];
                deck.add(card);
                logger.info("В колоду помещена " + card.cardWeight + " " + card.suit);
            }
        }
        return deck;
    }


    //Перетасовываем колоду

    public List<Card> reshuffleDeck(List<Card> deck) {

        Collections.shuffle(deck);
        for (int i = 0; i < deck.size(); i++) {
            logger.info("Теперь в колоде " + deck.get(i).cardWeight + " " + deck.get(i).suit);
        }
        return deck;
    }


    //Метод выдачи карт из колоды игроку
    private void giveCards(List<Card> deck, Gamer gamer, int count) {

        for (int i = 0; i < count; i++) {
            gamer.deck.add(deck.get(i));
            logger.info("Игроку " + gamer.name + " выдана " + deck.get(i).cardWeight + " " + deck.get(i).suit);
            deck.remove(i);
            logger.info("Карта удалена из колоды");
        }
    }

    // Раздаем начальные карты игрокам
    public void startingGivingCards(Gamer first, Gamer second, Gamer third, List<Card> deck) {
        logger.info("Начинается выдача карт\n");
        for (int i = 0; i < 5; i++) {
            giveCards(deck, first, 2);
            giveCards(deck, second, 2);
            giveCards(deck, third, 2);
        }
    }

    //Выкладываем карты в прикуп
    public void throwCartsToBuyIn(List<Card> buyInDeck, Gamer fourth) {
        giveCards(buyInDeck, fourth, 2);
    }

}
