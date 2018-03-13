package Main;

public class Director {
    public GameBuilder gameBuilder;

    public Director(int strategyNimber){
        gameBuilder = new DefaultGameBuilder(); //Выбираем стратегию игры, пока что она одна
    }

    public Game buildGame(){

        return gameBuilder.createRounds(); //Строим игру по стратегии
    }
}
