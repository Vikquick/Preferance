package Main;

import org.apache.log4j.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        Director director = new Director(1);
        Game game = director.buildGame();
        logger.info("Игра запущена");

        game.getResultsOfGivingCardsInRound(3);
        game.getResultOfMerchencyInRound(5);
        game.getProcessOfOrderingInRound(7);
        game.getResultOfRound(2);
        game.getFullProcessOfRound(6);
        game.getProcessOfGame();
        game.getResults();
    }
}
