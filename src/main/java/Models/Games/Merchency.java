package Models.Games;

import Models.Gamers.Gamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Merchency {
    static String firstDecision;
    static String secondDecision;
    static String thirdDecision;
    List<String> decisions;
    public void startMerchency(Gamer first, Gamer second, Gamer third) {
        decisions = new ArrayList<>();
        while ((!Objects.equals(firstDecision, "PASS") && !Objects.equals(secondDecision, "PASS"))
                || (!Objects.equals(firstDecision, "PASS") && !Objects.equals(thirdDecision, "PASS"))
                || (!Objects.equals(thirdDecision, "PASS") && !Objects.equals(secondDecision, "PASS"))) {
            firstDecision =  first.getDecision(decisions);
            secondDecision = second.getDecision(decisions);
            thirdDecision = third.getDecision(decisions);
            decisions.add(firstDecision);
            decisions.add(secondDecision);
            decisions.add(thirdDecision);
            break;
        }
    }
}
