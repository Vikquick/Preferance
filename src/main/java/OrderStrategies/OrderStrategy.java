package OrderStrategies;

import Models.Cards.Card;
import Models.Gamers.Gamer;

import java.util.List;

public interface OrderStrategy {
    public List<Card> makeDecision(List<Card> desk, Gamer gamer);
}
