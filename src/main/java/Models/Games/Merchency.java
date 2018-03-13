package Models.Games;

import Models.Gamers.Gamer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Merchency {
    public static final Logger logger = Logger.getLogger(Merchency.class);

    public Decision firstDecision;
    public Decision secondDecision;
    public Decision thirdDecision;
    List<Decision> decisions;

    public List<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
    }

    public Decision getFirstDecision() {
        return firstDecision;
    }

    public void setFirstDecision(Decision firstDecision) {
        this.firstDecision = firstDecision;
    }

    public Decision getSecondDecision() {
        return secondDecision;
    }

    public void setSecondDecision(Decision secondDecision) {
        this.secondDecision = secondDecision;
    }

    public Decision getThirdDecision() {
        return thirdDecision;
    }

    public void setThirdDecision(Decision thirdDecision) {
        this.thirdDecision = thirdDecision;
    }

    public List<Decision> startMerchency(Gamer first, Gamer second, Gamer third) {

        logger.info("Начинается торговля");
        decisions = new ArrayList<>();
        while ((!Objects.equals(firstDecision, Decision.PASS) && !Objects.equals(secondDecision, Decision.PASS))
                || (!Objects.equals(firstDecision, Decision.PASS) && !Objects.equals(thirdDecision, Decision.PASS))
                || (!Objects.equals(thirdDecision, Decision.PASS) && !Objects.equals(secondDecision, Decision.PASS))) {
            setFirstDecision(first.getDecision(decisions));
            decisions.add(firstDecision);
            setSecondDecision(second.getDecision(decisions));
            decisions.add(secondDecision);
            setThirdDecision(third.getDecision(decisions));
            decisions.add(thirdDecision);
        }
        return decisions;
    }
}
