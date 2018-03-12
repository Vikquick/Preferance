package Main;

import org.apache.log4j.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Game game = new Game();
        logger.info("Игра запущена");
        game.start();
        logger.info("Пулька первого игрока по имени " + game.first.getName() + " равна: Пуля - " +game.first.bullet.bullet + ", гора - " + game.first.bullet.mountain + ", висты - " + game.first.bullet.whists);
        logger.info("Пулька второго игрока по имени " + game.second.getName() + " равна: Пуля - " +game.second.bullet.bullet + ", гора - " + game.second.bullet.mountain + ", висты - " + game.second.bullet.whists);
        logger.info("Пулька третьего игрока по имени " + game.third.getName() + " равна: Пуля - " +game.third.bullet.bullet + ", гора - " + game.third.bullet.mountain + ", висты - " + game.third.bullet.whists);


    }
}
