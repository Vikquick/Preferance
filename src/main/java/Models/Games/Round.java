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
    Decision decision;
    Gamer winner;
    List<Card> firstDeck = new ArrayList<>();
    List<Card> secondDeck = new ArrayList<>();
    List<Card> thirdDeck = new ArrayList<>();

    public Round(int number) {
        this.number = number;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
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

    public Merchency getMerchency() {
        return merchency;
    }

    public void setMerchency(Merchency merchency) {
        this.merchency = merchency;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Gamer getWinner() {
        return winner;
    }

    public void setWinner(Gamer winner) {
        this.winner = winner;
    }

    public List<Card> getFirstDeck() {
        return firstDeck;
    }

    public void setFirstDeck(List<Card> firstDeck) {
        this.firstDeck = firstDeck;
    }

    public List<Card> getSecondDeck() {
        return secondDeck;
    }

    public void setSecondDeck(List<Card> secondDeck) {
        this.secondDeck = secondDeck;
    }

    public List<Card> getThirdDeck() {
        return thirdDeck;
    }

    public void setThirdDeck(List<Card> thirdDeck) {
        this.thirdDeck = thirdDeck;
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
        firstDeck.addAll(first.deck);
        second.getRunkForHandDeck(second.deck);
        secondDeck.addAll(second.deck);
        third.getRunkForHandDeck(third.deck);
        thirdDeck.addAll(third.deck);

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
                        decision = decisions.get(i);
                        break;
                    case 1:
                        orderGamer = second;
                        decision = decisions.get(i);
                        break;
                    case 2:
                        orderGamer = third;
                        decision = decisions.get(i);
                        break;
                }
            }
        }
        if (orderGamer != null) {
            logger.info("Игрок, который принял решение сыграть - " + orderGamer.getName());

            //Игрок победивший в торговле забирает прикуп
            logger.info("Игрок " + orderGamer.getName() + " забирает прикуп");
            deckController.giveCards(deck, orderGamer, 2);
            orderGamer.throwCardsAfterBuyIn(decision);
        }
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
                    first.bullet.bullet += firstWins * 2;
                    second.bullet.mountain += (5 - secondWins);
                    third.bullet.mountain += (5 - thirdWins);
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    first.bullet.mountain += (6 - firstWins) * 2;
                    second.bullet.whists += secondWins * 2;
                    third.bullet.whists += thirdWins * 2;
                }
            }
            //Если заявку подал второй игрок
            if (orderGamer.getName().equals(second.getName())) {
                if (max == secondWins && secondWins > 5) {
                    logger.info("Игрок " + first.getName() + " выиграл своё заявление");
                    second.bullet.bullet += secondWins * 2;
                    first.bullet.mountain += (5 - firstWins);
                    third.bullet.mountain += (5 - thirdWins);
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    second.bullet.mountain += (6 - secondWins) * 2;
                    first.bullet.whists += firstWins * 2;
                    third.bullet.whists += thirdWins * 2;
                }
            }
            //Если заявку подал третий игрок
            if (orderGamer.getName().equals(third.getName())) {
                if (max == thirdWins && thirdWins > 5) {
                    logger.info("Игрок " + first.getName() + " выиграл своё заявление");
                    third.bullet.bullet += thirdWins * 2;
                    second.bullet.mountain += (5 - secondWins);
                    first.bullet.mountain += (5 - firstWins);
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    third.bullet.mountain += (6 - thirdWins) * 2;
                    second.bullet.whists += secondWins * 2;
                    first.bullet.whists += firstWins * 2;
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
                    first.bullet.bullet += 10;
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    first.bullet.mountain += firstWins * 10;
                    second.bullet.bullet += 10;
                    third.bullet.bullet += 10;
                }
            }

            if (orderGamer.getName().equals(second.getName())) {
                if (min == secondWins) {
                    logger.info("Игрок " + first.getName() + " выиграл своё заявление");
                    second.bullet.bullet += 10;
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    second.bullet.mountain += secondWins * 10;
                    first.bullet.bullet += 10;
                    third.bullet.bullet += 10;
                }
            }

            if (orderGamer.getName().equals(third.getName())) {
                if (min == thirdWins) {
                    logger.info("Игрок " + first.getName() + " выиграл своё заявление");
                    third.bullet.bullet += 10;
                } else {
                    logger.info("Игрок " + first.getName() + " проиграл своё заявление");
                    third.bullet.mountain += thirdWins * 10;
                    second.bullet.bullet += 10;
                    first.bullet.bullet += 10;
                }
            }
        }
    }
}

