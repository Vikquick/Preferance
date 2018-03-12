package Models.Games;

import Models.Gamers.Gamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Merchency {
    static Decision firstDecision;
    static Decision secondDecision;
    static Decision thirdDecision;
    List<Decision> decisions;

    public List<Decision> startMerchency(Gamer first, Gamer second, Gamer third) {
        System.out.println("");
        System.out.println("Начинается торговля!");
        decisions = new ArrayList<>();

        while ((!Objects.equals(firstDecision, Decision.PASS) && !Objects.equals(secondDecision, Decision.PASS))
                || (!Objects.equals(firstDecision, Decision.PASS) && !Objects.equals(thirdDecision, Decision.PASS))
                || (!Objects.equals(thirdDecision, Decision.PASS) && !Objects.equals(secondDecision, Decision.PASS)))
        {
            firstDecision = first.getDecision(decisions);
            decisions.add(firstDecision);
            secondDecision = second.getDecision(decisions);
            decisions.add(secondDecision);
            thirdDecision = third.getDecision(decisions);
            decisions.add(thirdDecision);
        }
        return decisions;
    }
}
