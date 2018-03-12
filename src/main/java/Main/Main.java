package Main;

import org.apache.log4j.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        Game game = new Game();
        logger.info("Игра запущена");
        game.start();
        logger.info(game.first.bullet.bullet + " " + game.first.bullet.mountain + " "+ game.first.bullet.whists);
    }
}
