package OrderStrategies;

import Models.Cards.Card;
import Models.Gamers.Gamer;
import Models.Games.OrderStep;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Minimal implements OrderStrategy {
    public static final Logger logger = Logger.getLogger(Minimal.class);

    OrderStep orderStep;
    List<Card> desk;

    public OrderStep getOrderStep() {
        return orderStep;
    }

    public void setOrderStep(OrderStep orderStep) {
        this.orderStep = orderStep;
    }

    public List<Card> getDesk() {
        return desk;
    }

    public void setDesk(List<Card> desk) {
        this.desk = desk;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public OrderStep makeDecision(List<Card> desk, Gamer gamer) {
        List<Card> variants = new ArrayList<>();

        if (desk.size() != 0) {
            //Выбираем карту в масть
            for (Card gamersCard : gamer.deck) {
                if (desk.get(0).suit.toString().equals(gamersCard.suit.toString())) {
                    logger.info("В рассматриваемые варианты добавляем карту " + gamersCard.cardWeight + " " + gamersCard.suit);
                    variants.add(gamersCard);
                }
            }
        }
        //Если карты в масть нет или взятка еще пуста, то в варианты попадают все карты колоды
        if (variants.size() == 0) {
            logger.info("Карты такой же масти нет, в варианты попадают все карты из руки");
            variants.addAll(gamer.deck);
        }

        logger.info("Сортируем варианты по возрастанию ранга");
        Collections.sort(variants); //Сортируем варианты по возрастанию

        for (Card variant : variants) {
            logger.info("Варианты на выкладку - " + variant.cardWeight + " " + variant.suit);
        }

        //Выкидываем наименьшую карту
        desk.add(variants.get(0));
        setOrderStep(new OrderStep(gamer, variants.get(0), desk));
        logger.info(gamer.name + " выкладывает карту " + variants.get(0).getCardWeight() + " " + variants.get(0).suit);
        gamer.deck.remove(variants.get(0));

        return orderStep;
    }
}
