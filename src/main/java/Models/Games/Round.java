package Models.Games;

import Controllers.DeckController;
import Models.Cards.Card;
import Models.Gamers.Gamer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

//Класс раунда содержит запуск торговли и запуск 10 раундов с сохранением каждого объекта раунда в массив и сохранением объекта торговли
public class Round {
    public static final Logger logger = Logger.getLogger(Round.class);

    DeckController deckController = new DeckController();
    int number;
    List<Decision> decisions;
    List<Order> orders;
    Merchency merchency;

    public List<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
    }

    public Round(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @SuppressWarnings("Duplicates")
    public void startRound(Gamer first, Gamer second, Gamer third) {
        int firstWins = 0;
        int secondWins = 0;
        int thirdWins = 0;


        List<Card> deck = deckController.fillDeck();

        logger.info("Перетасовываем колоду");
        deckController.reshuffleDeck(deck);

        //Раздача карт игрокам
        deckController.startingGivingCards(first, second, third, deck);
        first.getRunkForHandDeck(first.deck);
        second.getRunkForHandDeck(second.deck);
        third.getRunkForHandDeck(third.deck);

        //Торговля
        merchency = new Merchency();
        Gamer orderGamer = null;
        setDecisions(merchency.startMerchency(first, second, third));
        logger.info("Торговля окончена\n");
        List<Gamer> winners = new ArrayList<>();

        orders = new ArrayList<>();

        logger.info("Находим игрока, который принял решение сыграть");
        for (int i = 0; i < decisions.size(); i++) {
            if (!decisions.get(i).toString().equals("PASS")) {
                switch (i) {
                    case 0:
                        orderGamer = first;
                        break;
                    case 1:
                        orderGamer = second;
                        break;
                    case 2:
                        orderGamer = third;
                        break;
                }
            }
        }
        if (orderGamer != null)
        logger.info("Игрок, который принял решение сыграть - " + orderGamer.getName());

        for (int i = 0; i < 10; i++) {
            logger.info("Раздача №" + (i + 1));
            Order order = new Order(i);
            winners.add(order.startOrdering(first, second, third, decisions, winners, orderGamer));
            logger.info(winners.get(i).getName() + " - забирает взятку");
            orders.add(order);
        }


        logger.info("Определяем победителя раунда");
        for (int i = 0; i < 10; i++) {
            if (orders.get(i).getWinner().getName().equals(first.getName())) {
                firstWins++;
            }
            if (orders.get(i).getWinner().getName().equals(second.getName())) {
                secondWins++;
            }
            if (orders.get(i).getWinner().getName().equals(third.getName())) {
                thirdWins++;
            }
        }

        int max = Math.max(firstWins, secondWins);
        max = Math.max(max, thirdWins);

        int min = Math.min(firstWins, secondWins);
        min = Math.min(min, thirdWins);


//Игра на взятки
        if (decisions.contains(Decision.BRIBES)) {
            logger.info("Игра была на взятки");
            //Если заявку подал первый игрок
            if (orderGamer.getName().equals(first.getName())) {
                if (max == firstWins && firstWins > 5) {
                    logger.info("Игрок " + first.getName() + " выиграл своё заявление");
                    first.bullet.bullet += 6 * 20;
                    second.bullet.mountain += secondWins * 10;
                    third.bullet.mountain += thirdWins * 10;
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    first.bullet.mountain += (6 - firstWins) * 10;
                    second.bullet.whists += secondWins;
                    third.bullet.whists += thirdWins;
                }
            }
            //Если заявку подал второй игрок
            if (orderGamer.getName().equals(second.getName())) {
                if (max == secondWins && secondWins > 5) {
                    logger.info("Игрок " + first.getName() + " выиграл своё заявление");
                    second.bullet.bullet += 6 * 20;
                    first.bullet.mountain += firstWins * 10;
                    third.bullet.mountain += thirdWins * 10;
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    second.bullet.mountain += (6 - secondWins) * 10;
                    first.bullet.whists += firstWins;
                    third.bullet.whists += thirdWins;
                }
            }
            //Если заявку подал третий игрок
            if (orderGamer.getName().equals(third.getName())) {
                if (max == thirdWins && thirdWins > 5) {
                    logger.info("Игрок " + first.getName() + " выиграл своё заявление");
                    third.bullet.bullet += 6 * 20;
                    second.bullet.mountain += secondWins * 10;
                    first.bullet.mountain += firstWins * 10;
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    third.bullet.mountain += (6 - thirdWins) * 10;
                    second.bullet.whists += secondWins;
                    first.bullet.whists += firstWins;
                }
            }
        }

        //Игра на мизер
        if (decisions.contains(Decision.MIZER)) {
            //Если заявку подал первый игрок
            logger.info("Игра была на мизер");
            if (orderGamer.getName().equals(first.getName())) {
                if (min == firstWins) {
                    logger.info("Игрок " + first.getName() + " выиграл своё заявление");
                    first.bullet.bullet += 6 * 20;
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    first.bullet.mountain += (firstWins - min) * 10;
                    second.bullet.bullet += (secondWins - min) * 10;
                    third.bullet.bullet += (thirdWins - min) * 10;
                }
            }

            if (orderGamer.getName().equals(second.getName())) {
                if (min == secondWins) {
                    logger.info("Игрок " + first.getName() + " выиграл своё заявление");
                    second.bullet.bullet += 6 * 20;
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    second.bullet.mountain += (secondWins - min) * 10;
                    first.bullet.bullet += (firstWins - min) * 10;
                    third.bullet.bullet += (thirdWins - min) * 10;
                }
            }

            if (orderGamer.getName().equals(third.getName())) {
                if (min == thirdWins) {
                    logger.info("Игрок " + first.getName() + " выиграл своё заявление");
                    third.bullet.bullet += 6 * 20;
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    third.bullet.mountain += (thirdWins - min) * 10;
                    second.bullet.bullet += (secondWins - min) * 10;
                    first.bullet.bullet += (firstWins - min) * 10;
                }
            }
        }
    }
}

