package Models.Games;

import Models.Cards.Card;
import Models.Gamers.Gamer;

import java.util.ArrayList;
import java.util.List;

//Класс раунда содержит запуск торговли и запуск 10 раундов с сохранением каждого объекта раунда в массив и сохранением объекта торговли
public class Round {

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

    public void startRound(Gamer first, Gamer second, Gamer third, List<Card> deck) {
        int firstWins = 0;
        int secondWins = 0;
        int thirdWins = 0;
        merchency = new Merchency();
        setDecisions(merchency.startMerchency(first, second, third));

        List<Gamer> winners = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            System.out.println("");
            System.out.println("Раздача №" + (i + 1));
            orders = new ArrayList<>();
            Order order = new Order(i);
            winners.add(order.startOrdering(first, second, third, decisions));
            System.out.println(winners.get(i).getName() + " - забирает взятку");
            System.out.println("");
            orders.add(order);
        }

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

        if (decisions.contains(Decision.BRIBES)) {
            if (max == firstWins && firstWins > 5) {
                first.bullet.bullet += 6 * 20;
                second.bullet.mountain += secondWins * 10;
                third.bullet.mountain += thirdWins * 10;
            } else if (max == firstWins) {
                first.bullet.mountain += (6 - firstWins) * 10;
                second.bullet.whists += secondWins;
                third.bullet.whists += secondWins;
            }

            if (max == secondWins && secondWins > 5) {
                second.bullet.bullet += 6 * 2;
                first.bullet.mountain += secondWins * 10;
                third.bullet.mountain += secondWins * 10;
            } else if (max == secondWins) {
                second.bullet.mountain += (6 - secondWins) * 10;
                first.bullet.whists += firstWins;
                third.bullet.whists += thirdWins;
            }

            if (max == thirdWins && thirdWins > 5) {
                third.bullet.bullet += 6 * 2;
                first.bullet.mountain += secondWins * 20;
                second.bullet.mountain += secondWins * 20;
            } else if (max == thirdWins) {
                third.bullet.mountain += (6 - secondWins) * 10;
                first.bullet.whists += firstWins;
                second.bullet.whists += thirdWins;
            }
        }

        if (decisions.contains(Decision.MIZER)) {
        }
    }
}

