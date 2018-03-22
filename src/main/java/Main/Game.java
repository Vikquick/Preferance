package Main;

import Controllers.NamingController;
import Models.Gamers.Bullet;
import Models.Gamers.Gamer;
import Models.Games.Order;
import Models.Games.OrderStep;
import Models.Games.Round;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Game {
    private static Logger logger = Logger.getLogger(Game.class);

    public Gamer first;
    public Gamer second;
    public Gamer third;
    public Gamer fourth;

    public List<Round> rounds;
    public Round round;

    public Game() {
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public Round getRound() {
        return round;
    }

    public Gamer getFirst() {
        return first;
    }

    public void setFirst(Gamer first) {
        this.first = first;
    }

    public Gamer getSecond() {
        return second;
    }

    public void setSecond(Gamer second) {
        this.second = second;
    }

    public Gamer getThird() {
        return third;
    }

    public void setThird(Gamer third) {
        this.third = third;
    }

    public Gamer getFourth() {
        return fourth;
    }

    public void setFourth(Gamer fourth) {
        this.fourth = fourth;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void startRounds() {
        NamingController namingController = new NamingController();

        first = new Gamer(namingController.nameGamer(), new Bullet(0, 0, 0));
        logger.info("Первого игрока зовут " + first.getName());
        second = new Gamer(namingController.nameGamer(), new Bullet(0, 0, 0));
        logger.info("Второго игрока зовут " + second.getName());
        third = new Gamer(namingController.nameGamer(), new Bullet(0, 0, 0));
        logger.info("Третьего игрока зовут " + third.getName());
        fourth = new Gamer(namingController.nameGamer(), null); //Раздающий
        logger.info("Раздающего зовут " + fourth.getName() + "\n");

        rounds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            logger.info("Раунд №" + (i + 1) + " начался");
            round = new Round(i + 1);
            round.startRound(first, second, third);
            rounds.add(round);
        }
    }

    public Round getRound(int i) {
        round = rounds.get(i);
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    //Получение пулек игроков
    public void getResults() {
        logger.info("Пулька первого игрока по имени " + first.getName() + " равна: Пуля - " + first.bullet.bullet + ", гора - " + first.bullet.mountain + ", висты - " + first.bullet.whists);
        logger.info("Пулька второго игрока по имени " + second.getName() + " равна: Пуля - " + second.bullet.bullet + ", гора - " + second.bullet.mountain + ", висты - " + second.bullet.whists);
        logger.info("Пулька третьего игрока по имени " + third.getName() + " равна: Пуля - " + third.bullet.bullet + ", гора - " + third.bullet.mountain + ", висты - " + third.bullet.whists);
    }

    //Получение результатов выдачи карт в нужном раунде
    public void getResultsOfGivingCardsInRound(int roundNumber) {

        Round round = getRound(roundNumber - 1);
        logger.info("Результаты раздачи №" + roundNumber + ":");

        for (int i = 0; i < round.getFirstDeck().size(); i++) {
            logger.info("Карта игрока " + first.getName() + " - " + round.getFirstDeck().get(i).cardWeight + " " + round.getFirstDeck().get(i).suit);
        }
        for (int i = 0; i < round.getSecondDeck().size(); i++) {
            logger.info("Карта игрока " + second.getName() + " - " + round.getSecondDeck().get(i).cardWeight + " " + round.getSecondDeck().get(i).suit);
        }
        for (int i = 0; i < round.getThirdDeck().size(); i++) {
            logger.info("Карта игрока " + third.getName() + " - " + round.getThirdDeck().get(i).cardWeight + " " + round.getThirdDeck().get(i).suit);
        }
    }

    //Результат торговли в нужном раунде
    public void getResultOfMerchencyInRound(int roundNumber) {
        Round round = getRound(roundNumber - 1);
        logger.info("Игрок " + first.getName() + " принимает решение - " + round.getMerchency().getFirstDecision().toString());
        logger.info("Игрок " + second.getName() + " - " + round.getMerchency().getSecondDecision().toString());
        logger.info("Игрок " + third.getName() + " - " + round.getMerchency().getThirdDecision().toString());
    }

    //Процесс розыгрыша нужного раунда
    public void getProcessOfOrderingInRound(int roundNumber) {
        Round round = getRound(roundNumber - 1);
        List<Order> orders = round.getOrders();
        Order order;
        OrderStep step;
        logger.info("Процесс " + roundNumber + "го розыгрыша:");
        for (int i = 0; i < orders.size(); i++) {
            order = orders.get(i);
            int orderStepsCount = order.getOrderSteps().size();
            for (int j = 0; j < orderStepsCount; j++) {
                step = order.getOrderSteps().get(j);
                logger.info("Игрок " + step.getGamer().getName() + " кладет карту " + step.getCard().getCardWeight() + " " + step.getCard().getSuit());
            }
            logger.info("Игрок " + order.getWinner().getName() + " забирает взятку");
        }
    }

    //Результат розыгрыша в нужном раунде
    public void getResultOfRound(int roundNumber) {
        Round round = getRound(roundNumber - 1);
        logger.info("Игроку " + first.getName() + " начислено в пульку:  " + round.getFirstResult().bullet + " пуль, "
                + round.getFirstResult().mountain + " в гору, и " + round.getFirstResult().whists + " вистов");
        logger.info("Игроку " + second.getName() + " начислено в пульку:  " + round.getSecondResult().bullet + " пуль, "
                + round.getSecondResult().mountain + " в гору, и " + round.getSecondResult().whists + " вистов");
        logger.info("Игроку " + third.getName() + " начислено в пульку:  " + round.getThirdResult().bullet + " пуль, "
                + round.getThirdResult().mountain + " в гору, и " + round.getThirdResult().whists + " вистов");
    }

    //Полный процесс розыгрыша в нужном раунде
    public void getFullProcessOfRound(int roundNumber) {
        getResultsOfGivingCardsInRound(roundNumber);
        getResultOfMerchencyInRound(roundNumber);
        getProcessOfOrderingInRound(roundNumber);
        getResultOfRound(roundNumber);
    }

    //Полный процесс игры
    public void getProcessOfGame() {
        for (int i = 0; i < 10; i++) {
            getFullProcessOfRound(i+1);
        }
    }
}

abstract class GameBuilder {
    Game game;

    public abstract Game createRounds();
}
