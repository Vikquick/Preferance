package Main;

import Controllers.NamingController;
import Models.Gamers.Bullet;
import Models.Gamers.Gamer;
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

        this.first = new Gamer(namingController.nameGamer(), new Bullet(0, 0, 0));
        logger.info("Первого игрока зовут " + first.getName());
        this.second = new Gamer(namingController.nameGamer(), new Bullet(0, 0, 0));
        logger.info("Второго игрока зовут " + second.getName());
        this.third = new Gamer(namingController.nameGamer(), new Bullet(0, 0, 0));
        logger.info("Третьего игрока зовут " + third.getName());
        this.fourth = new Gamer(namingController.nameGamer(), null); //Раздающий
        logger.info("Раздающего зовут " + fourth.getName() + "\n");

        this.rounds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            logger.info("Раунд №" + (i + 1) + " начался");
            this.round = new Round(i + 1);
            this.round.startRound(first, second, third);
            this.rounds.add(round);
        }
    }

    public Round getRound(int i) {
        this.round = this.rounds.get(i);
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

        Round round = getRound(roundNumber);
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

    public void getResultOfMerchencyInRound(int roundNumber) {
        logger.info("Игрок " + first.getName() + " принимает решение - " + round.getMerchency().getFirstDecision().toString());
        logger.info("Игрок " + second.getName() + " - " + round.getMerchency().getSecondDecision().toString());
        logger.info("Игрок " + third.getName() + " - " + round.getMerchency().getThirdDecision().toString());
    }
}


abstract class GameBuilder {
    Game game;

    public abstract Game createRounds();
}
