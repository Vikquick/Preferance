package Models.Games;

import Models.Gamers.Gamer;

import java.util.Objects;

public class Merchency {
    static String firstDecision;
    static String secondDecision;
    static String thirdDecision;
    public void startMerchency(Gamer first, Gamer second, Gamer third) {
        while ((!Objects.equals(firstDecision, "PASS") && !Objects.equals(secondDecision, "PASS"))
                || (!Objects.equals(firstDecision, "PASS") && !Objects.equals(thirdDecision, "PASS"))
                || (!Objects.equals(thirdDecision, "PASS") && !Objects.equals(secondDecision, "PASS"))) {
            firstDecision =  first.getDecision();
            secondDecision = second.getDecision();
            thirdDecision = third.getDecision();
        }
    }
}
