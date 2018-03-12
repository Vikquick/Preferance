package OrderStrategies;

import Main.Game;
import Models.Cards.Card;
import Models.Gamers.Gamer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maximal implements OrderStrategy {
    public static final Logger logger = Logger.getLogger(Maximal.class);

    private List<Card> desk = new ArrayList<>();

    @SuppressWarnings("Duplicates")
    @Override
    public List<Card> makeDecision(List<Card> desk, Gamer gamer) {
        List<Card> variants = new ArrayList<>();

        if (desk.size() != 0) {
            //Выбираем карту в масть
            for (Card gamersCard : gamer.deck) {
               logger.info("Карта игрока " + gamersCard.cardWeight + " " + gamersCard.suit);
                if (desk.get(0).suit.toString().equals(gamersCard.suit.toString())) {
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
        desk.add(variants.get(variants.size() - 1));
        logger.info(gamer.name + " выкладывает карту " + variants.get(0).getCardWeight() + " " + variants.get(0).suit);
        gamer.deck.remove(variants.get(variants.size() - 1));
        return desk;
    }
}