package Controllers;

import Models.Cards.Card;
import Models.Cards.CardWeight;
import Models.Gamers.Gamer;
import Models.Cards.Suit;

import java.util.*;

public class DeckController {


    //Создаем колоду
    public List<Card> fillDeck() {

        List<Card> deck = new ArrayList<>();
        CardWeight[] cardWeights = CardWeight.values();
        Suit[] suits = Suit.values();
        for (int i = 0; i < cardWeights.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                Card card = new Card();
                card.cardWeight = cardWeights[i];
                card.suit = suits[j];
                deck.add(card);
                System.out.println("В колоду помещена " + card.cardWeight + " " + card.suit);
            }
        }
        return deck;
    }


    //Перетасовываем колоду

    public List<Card> reshuffleDeck(List<Card> deck){

        Collections.shuffle(deck);
        for (int i = 0; i < deck.size(); i++) {
            System.out.println("Теперь в колоде " + deck.get(i).cardWeight + " " + deck.get(i).suit);
        }

        return deck;
    }


    //Метод выдачи карт из колоды игроку
    private void giveTwoCardToPlayer(List<Card> deck, Gamer gamer, int count){

        for (int i = 0; i < count; i++) {

            gamer.deck.add(deck.get(i));
            System.out.println("Игроку "+ gamer.name + " выдана "+ deck.get(i).cardWeight + " "+ deck.get(i).suit);
            deck.remove(i);
        }
    }

    // Раздаем начальные карты игрокам
    public void startingGivingCards(Gamer first, Gamer second, Gamer third, List<Card> deck){
        for (int i = 0; i < 5; i++) {
            giveTwoCardToPlayer(deck, first, 2);
            giveTwoCardToPlayer(deck, second, 2);
            giveTwoCardToPlayer(deck, third, 2);
        }
    }

    //Выкладываем карты в прикуп
    public void throwCartsToBuyIn(){}

}
