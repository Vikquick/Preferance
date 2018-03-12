package Models.Games;

import Models.Cards.Card;
import Models.Gamers.Gamer;
import OrderStrategies.Maximal;
import OrderStrategies.Minimal;

import java.util.List;

public class Order {

    public void startOrdering(Gamer first, Gamer second, Gamer third, List<Decision> decisions, List<Card> deck) {
        if (decisions.contains(Decision.MIZER)) {
            Minimal minimal = new Minimal();
            minimal.makeDecision(deck, first);
            minimal.makeDecision(deck, second);
            minimal.makeDecision(deck, third);
        }
        if (decisions.contains(Decision.BRIBES)) {
            Maximal maximal = new Maximal();
            maximal.makeDecision(deck, first);
            maximal.makeDecision(deck, second);
            maximal.makeDecision(deck, third);
        }
    }
}
