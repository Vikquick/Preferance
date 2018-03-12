package Models.Games;

import Models.Cards.Card;
import Models.Cards.CardWeight;
import Models.Cards.Suit;
import Models.Gamers.Gamer;
import OrderStrategies.Minimal;

import java.util.ArrayList;
import java.util.List;

public class Round {

    int number;
    List<Decision> decisions;

    public List<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
    }

    public Round(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void startRound(Gamer first, Gamer second, Gamer third, List<Card> deck) {
        Merchency merchency = new Merchency();
        setDecisions(merchency.startMerchency(first, second, third));

        Order order = new Order();
        order.startOrdering(first, second, third, decisions, deck);
    }


}
