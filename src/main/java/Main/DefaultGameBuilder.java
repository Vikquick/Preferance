package Main;

import org.apache.log4j.Logger;

class DefaultGameBuilder extends GameBuilder {
    public static final Logger logger = Logger.getLogger(DefaultGameBuilder.class);

    public DefaultGameBuilder() {
        game = new Game();
    }

    @Override
    public Game createRounds() {
        game.startRounds();
        return game;
    }
}
