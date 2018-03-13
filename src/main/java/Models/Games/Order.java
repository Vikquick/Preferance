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
    OrderStep orderStep;
    List<OrderStep> orderSteps;

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

    public OrderStep getOrderStep() {
        return orderStep;
    }

    public void setOrderStep(OrderStep orderStep) {
        this.orderStep = orderStep;
    }

    public List<OrderStep> getOrderSteps() {
        return orderSteps;
    }

    public void setOrderSteps(List<OrderStep> orderSteps) {
        this.orderSteps = orderSteps;
    }

    @SuppressWarnings("Duplicates")
    public Gamer startOrdering(Gamer first, Gamer second, Gamer third, List<Decision> decisions, List<Gamer> winners, Gamer orderGamer) {

        List<Card> deck = new ArrayList<>();
        orderSteps = new ArrayList<>();

        if (decisions.contains(Decision.MIZER)) {
            logger.info("Результатом торговли был мизер, применяем стратегию Minimal");
            Minimal minimal = new Minimal();
            if (winners.size() == 0 || winners.get(winners.size() - 1) == first) {
                orderSteps.add(minimal.makeDecision(deck, first));
                orderSteps.add(minimal.makeDecision(deck, second));
                orderSteps.add(minimal.makeDecision(deck, third));
            } else if (winners.get(winners.size() - 1) == second) {
                orderSteps.add(minimal.makeDecision(deck, second));
                orderSteps.add(minimal.makeDecision(deck, third));
                orderSteps.add(minimal.makeDecision(deck, first));
            } else {
                orderSteps.add(minimal.makeDecision(deck, third));
                orderSteps.add(minimal.makeDecision(deck, first));
                orderSteps.add(minimal.makeDecision(deck, second));
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
                orderSteps.add(maximal.makeDecision(deck, first));
                orderSteps.add(maximal.makeDecision(deck, second));
                orderSteps.add(maximal.makeDecision(deck, third));
            } else if (winners.get(winners.size() - 1) == second) {
                orderSteps.add(maximal.makeDecision(deck, second));
                orderSteps.add(maximal.makeDecision(deck, third));
                orderSteps.add(maximal.makeDecision(deck, first));
            } else {
                orderSteps.add(maximal.makeDecision(deck, third));
                orderSteps.add(maximal.makeDecision(deck, first));
                orderSteps.add(maximal.makeDecision(deck, second));
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
                orderSteps.add(minimal.makeDecision(deck, first));
                orderSteps.add(minimal.makeDecision(deck, second));
                orderSteps.add(minimal.makeDecision(deck, third));
            } else if (winners.get(winners.size() - 1) == second) {
                orderSteps.add(minimal.makeDecision(deck, second));
                orderSteps.add(minimal.makeDecision(deck, third));
                orderSteps.add(minimal.makeDecision(deck, first));
            } else {
                orderSteps.add(minimal.makeDecision(deck, third));
                orderSteps.add(minimal.makeDecision(deck, first));
                orderSteps.add(minimal.makeDecision(deck, second));
            }

            logger.info("Во взятке следующие три карты:");
            for (Card aDeck : deck) {
                logger.info(aDeck.getCardWeight() + " " + aDeck.getSuit() + " её ранг - " + aDeck.getCardWeightRunk());
            }
        }

        setWinner(defineWinner(orderSteps));
        logger.info("Победитель - " + winner.getName());
        return winner;
    }


    public Gamer defineWinner(List<OrderStep> orderSteps) {
        logger.info("Определяем победителя раздачи");
        int max;
        if (orderSteps.get(0).getCard().getSuit().equals(orderSteps.get(1).getCard().getSuit())) {
            max = Math.max(orderSteps.get(0).getCard().getCardWeightRunk(), orderSteps.get(1).getCard().getCardWeightRunk());
            if (orderSteps.get(0).getCard().getSuit().equals(orderSteps.get(2).getCard().getSuit())) {
                max = Math.max(max, orderSteps.get(2).getCard().getCardWeightRunk());
            }
        } else {
            max = orderSteps.get(0).getCard().getCardWeightRunk();
            if (orderSteps.get(0).getCard().getSuit().equals(orderSteps.get(2).getCard().getSuit())) {
                max = Math.max(max, orderSteps.get(0).getCard().getCardWeightRunk());
            }
        }


        if (max == orderSteps.get(0).getCard().getCardWeightRunk()) {
            return orderSteps.get(0).getGamer();
        } else if (max == orderSteps.get(1).getCard().getCardWeightRunk()) {
            return orderSteps.get(1).getGamer();
        } else {
            return orderSteps.get(2).getGamer();
        }
    }
}
