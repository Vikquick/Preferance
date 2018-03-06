package Models.Games;

import Models.Gamers.Gamer;

public class Round {

    public void startRound(Gamer first, Gamer second, Gamer third) {
        Merchency merchency = new Merchency();
        merchency.startMerchency(first, second, third);
    }
}
