package Models.Games;

import Main.Game;
import Models.Cards.Card;
import Models.Gamers.Gamer;
import OrderStrategies.Maximal;
import OrderStrategies.Minimal;

import java.util.ArrayList;
import java.util.List;

public class Order {
    int number;
    Gamer winner;

    public Order(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Gamer getWinner() {
        return winner;
    }

    public void setWinner(Gamer winner) {
        this.winner = winner;
    }

    public Gamer startOrdering(Gamer first, Gamer second, Gamer third, List<Decision> decisions) {
        List<Card> deck = new ArrayList<>();
        if (decisions.contains(Decision.MIZER)) {
            Minimal minimal = new Minimal();
            minimal.makeDecision(deck, first);
            minimal.makeDecision(deck, second);
            minimal.makeDecision(deck, third);

            System.out.println("Во взятке следующие три карты:");
            for (Card aDeck : deck) {
                System.out.println(aDeck.getCardWeight() + " " + aDeck.getSuit() + " её ранг - " + aDeck.getCardWeightRunk());
            }
        }
        if (decisions.contains(Decision.BRIBES)) {
            Maximal maximal = new Maximal();
            maximal.makeDecision(deck, first);
            maximal.makeDecision(deck, second);
            maximal.makeDecision(deck, third);

            System.out.println("Во взятке следующие три карты:");
            for (Card aDeck : deck) {
                System.out.println(aDeck.getCardWeight() + " " + aDeck.getSuit() + " её ранг - " + aDeck.getCardWeightRunk());
            }
        }
            winner = defineWinner(deck, first, second, third);
        return winner;
    }


    public Gamer defineWinner(List<Card> deck, Gamer first, Gamer second, Gamer third) {
        int max;
        max = Math.max(deck.get(0).getCardWeightRunk(), deck.get(1).getCardWeightRunk());
        max = Math.max(max, deck.get(2).getCardWeightRunk());

        if (max == deck.get(0).getCardWeightRunk()) return first;
        else if (max == deck.get(1).getCardWeightRunk()) return second;
        else return third;

    }
}
