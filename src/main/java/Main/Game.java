package Main;

import Controllers.NamingController;
import Models.Gamers.Bullet;
import Models.Gamers.Gamer;
import Models.Games.Round;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Logger logger = Logger.getLogger(Game.class);
    public Gamer first;
    public Gamer second;
    public Gamer third;
    public Gamer fourth;

    List<Round> rounds;
    Round round;

    public Game() {
    }

    public void start() {
        NamingController namingController = new NamingController();

        first = new Gamer(namingController.nameGamer(), new Bullet(0, 0, 0));
        logger.info("Первого игрока зовут " + first.getName());
        second = new Gamer(namingController.nameGamer(), new Bullet(0, 0, 0));
        logger.info("Второго игрока зовут " + second.getName());
        third = new Gamer(namingController.nameGamer(), new Bullet(0, 0, 0));
        logger.info("Третьего игрока зовут " + third.getName());
        fourth = new Gamer(namingController.nameGamer(), null); //Раздающий
        logger.info("Раздающего зовут " + fourth.getName()+"\n");

        rounds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            logger.info("Раунд №" + (i + 1) + " начался");
            round = new Round(i + 1);
            round.startRound(first, second, third);
            rounds.add(round);
        }
    }
}
