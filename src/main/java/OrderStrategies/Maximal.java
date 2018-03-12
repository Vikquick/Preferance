package OrderStrategies;

import Main.Game;
import Models.Cards.Card;
import Models.Gamers.Gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maximal implements OrderStrategy {
    private List<Card> desk = new ArrayList<>();



    @SuppressWarnings("Duplicates")
    @Override
    public List<Card> makeDecision(List<Card> desk, Gamer gamer) {
        List<Card> variants = new ArrayList<>();

        if (desk.size() != 0) {
            //Выбираем карту в масть
            for (Card gamersCard : gamer.deck) {
                System.out.println("Карта игрока " + gamersCard.cardWeight + " " + gamersCard.suit);
                if (desk.get(0).suit.toString().equals(gamersCard.suit.toString())) {
                    variants.add(gamersCard);
                }
            }
        }
        //Если карты в масть нет или взятка еще пуста, то в варианты попадают все карты колоды
        if (variants.size() == 0) {
            variants.addAll(gamer.deck);
        }

        Collections.sort(variants); //Сортируем варианты по возрастанию

        for (Card variant : variants) {
            System.out.println("Варианты на выкладку " + variant.cardWeight + " " + variant.suit);
        }

        //Выкидываем наименьшую карту
        desk.add(variants.get(variants.size() - 1));
        gamer.deck.remove(variants.get(variants.size() - 1));
        return desk;
    }
}