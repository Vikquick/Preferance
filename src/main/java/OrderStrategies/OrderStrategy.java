package OrderStrategies;

import Models.Cards.Card;
import Models.Gamers.Gamer;
import Models.Games.OrderStep;

import java.util.List;

public interface OrderStrategy {
    OrderStep makeDecision(List<Card> desk, Gamer gamer);
}
