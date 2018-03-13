package Models.Games;

import Models.Cards.Card;
import Models.Gamers.Gamer;

import java.util.List;

public class OrderStep {

    Gamer gamer;
    Card card;
    List<Card> orderStepDesk;

    public OrderStep(Gamer gamer, Card card, List<Card> orderStepDesk) {
        this.gamer = gamer;
        this.card = card;
        this.orderStepDesk = orderStepDesk;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Card> getOrderStepDesk() {
        return orderStepDesk;
    }

    public void setOrderStepDesk(List<Card> orderStepDesk) {
        this.orderStepDesk = orderStepDesk;
    }
}
