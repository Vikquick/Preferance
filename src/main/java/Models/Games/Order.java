package Models.Games;

import Models.Cards.Card;
import Models.Gamers.Gamer;
import OrderStrategies.Maximal;
import OrderStrategies.Minimal;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public static final Logger logger = Logger.getLogger(Order.class);

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

    @SuppressWarnings("Duplicates")
    public Gamer startOrdering(Gamer first, Gamer second, Gamer third, List<Decision> decisions, List<Gamer> winners, Gamer orderGamer) {

        List<Card> deck = new ArrayList<>();


        if (decisions.contains(Decision.MIZER)) {
            logger.info("Результатом торговли был мизер, применяем стратегию Minimal");
            Minimal minimal = new Minimal();
            if (winners.size() == 0 || winners.get(winners.size() - 1) == first) {
                minimal.makeDecision(deck, first);
                minimal.makeDecision(deck, second);
                minimal.makeDecision(deck, third);
            } else if (winners.get(winners.size() - 1) == second) {
                minimal.makeDecision(deck, second);
                minimal.makeDecision(deck, third);
                minimal.makeDecision(deck, first);
            } else {
                minimal.makeDecision(deck, third);
                minimal.makeDecision(deck, first);
                minimal.makeDecision(deck, second);
            }

            logger.info("Во взятке следующие три карты:");
            for (Card aDeck : deck) {
                logger.info(aDeck.getCardWeight() + " " + aDeck.getSuit() + " её ранг - " + aDeck.getCardWeightRunk());
            }
        }
        if (decisions.contains(Decision.BRIBES)) {
            logger.info("Результатом торговли была игра на взятки, применяем стратегию Maximal");
            Maximal maximal = new Maximal();
            if (winners.size() == 0 || winners.get(winners.size() - 1) == first) {
                deck = maximal.makeDecision(deck, first);
                deck = maximal.makeDecision(deck, second);
                deck = maximal.makeDecision(deck, third);
            } else if (winners.get(winners.size() - 1) == second) {
                deck = maximal.makeDecision(deck, second);
                deck = maximal.makeDecision(deck, third);
                deck = maximal.makeDecision(deck, first);
            } else {
                deck = maximal.makeDecision(deck, third);
                deck = maximal.makeDecision(deck, first);
                deck = maximal.makeDecision(deck, second);
            }

            logger.info("Во взятке следующие три карты:");
            for (Card aDeck : deck) {
                logger.info(aDeck.getCardWeight() + " " + aDeck.getSuit() + " её ранг - " + aDeck.getCardWeightRunk());
            }
        }

        if (!decisions.contains(Decision.MIZER) && !decisions.contains(Decision.BRIBES)) {
            logger.info("Результатом торговли стала игра на распасы");

            Minimal minimal = new Minimal();
            if (winners.size() == 0 || winners.get(winners.size() - 1) == first) {
                minimal.makeDecision(deck, first);
                minimal.makeDecision(deck, second);
                minimal.makeDecision(deck, third);
            } else if (winners.get(winners.size() - 1) == second) {
                minimal.makeDecision(deck, second);
                minimal.makeDecision(deck, third);
                minimal.makeDecision(deck, first);
            } else {
                minimal.makeDecision(deck, third);
                minimal.makeDecision(deck, first);
                minimal.makeDecision(deck, second);
            }

            logger.info("Во взятке следующие три карты:");
            for (Card aDeck : deck) {
                logger.info(aDeck.getCardWeight() + " " + aDeck.getSuit() + " её ранг - " + aDeck.getCardWeightRunk());
            }
        }

        winner = defineWinner(deck, first, second, third);
        setWinner(winner);
        logger.info("Победитель - " + winner.getName());
        return winner;
    }


    public Gamer defineWinner(List<Card> deck, Gamer first, Gamer second, Gamer third) {
        logger.info("Определяем победителя раздачи");
        int max;
        max = Math.max(deck.get(0).getCardWeightRunk(), deck.get(1).getCardWeightRunk());
        max = Math.max(max, deck.get(2).getCardWeightRunk());

        if (max == deck.get(0).getCardWeightRunk()) {
            return first;
        } else if (max == deck.get(1).getCardWeightRunk()) {
            return second;
        } else {
            return third;
        }

    }
}
