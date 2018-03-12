package Models.Gamers;

import Models.Cards.Card;
import Models.Cards.Suit;
import Models.Games.Decision;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gamer {
    public String name;
    public Bullet bullet = null;
    public List<Card> deck = new ArrayList<>();

    public Gamer(String name, Bullet bullet) {
        this.name = name;
        this.bullet = bullet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public Decision getDecision(List<Decision> decisions) {
        System.out.println("Игрок " + this.name + " принимает решение...");
        CountOfSuits suitsInHand = new CountOfSuits(calculateSuitsInHand());

        int countSuitsInHand = suitsInHand.getCount();
        String typeOfSuitsInHand = suitsInHand.getSuit();


        System.out.println(countSuitsInHand + " - столько карт у него масти " + typeOfSuitsInHand);
        System.out.println(suitsInHand.getCardList().size());
        int runk = 0;

        for (int i = 0; i < suitsInHand.getCardList().size(); i++) {
            runk += suitsInHand.getCardList().get(i).getRunk();
        }

        //Если заявка еще не подана, то...
        if (!decisions.contains(Decision.BRIBES) && !decisions.contains(Decision.MIZER)) {

            if (countSuitsInHand < 4) {
                System.out.println("Игрок " + this.name + " принимает решение...не рисковать и пассует");
                return Decision.PASS;
            } else {
                System.out.println("Игрок " + this.name + " принимает решение...сыграть");
                if (runk < 50) {
                    {
                        System.out.println("Игрок " + this.name + " принимает решение...играть на мизер" +
                                "");
                        return Decision.MIZER;
                    }
                } else {
                    System.out.println("Игрок " + this.name + " принимает решение...играть на взятки");
                    return Decision.BRIBES;
                }
            } //А если заявка подана
        } else {
            System.out.println("Игрок " + this.name + " принимает решение...пассовать");
            return Decision.PASS;
        }
    }

    public List<Card> countCards(List<Card> deck, Suit suit) {
        List<Card> maxSuitCountCards = new ArrayList<>();
        for (Card card : deck) {
            if (card.getSuit().toString().equals(suit.toString()))
                maxSuitCountCards.add(card);
        }
        return maxSuitCountCards;
    }

    public void getRunkForHandDeck(List<Card> deck) {
        for (Card card : deck) {
            card.setRunk();
        }
    }

    private CountOfSuits calculateSuitsInHand() {
        CountOfSuits countOfSuits = new CountOfSuits();
        List<Card> maxSuitCountCards = new ArrayList<>();
        int pik = 0;
        int tref = 0;
        int bub = 0;
        int cherv = 0;
        int max1 = 0;
        int max2 = 0;
        for (Card aDeck : this.deck) {
            if (Objects.equals(aDeck.getSuit().toString(), "Пики")) {
                pik++;
            }
            if (Objects.equals(aDeck.getSuit().toString(), "Трефы")) {
                tref++;
            }
            if (Objects.equals(aDeck.getSuit().toString(), "Буби")) {
                bub++;
            }
            if (Objects.equals(aDeck.getSuit().toString(), "Черви")) {
                cherv++;
            }
        }
        max1 = Math.max(pik, tref);
        max2 = Math.max(bub, cherv);
        max1 = Math.max(max1, max2);
        if (max1 == pik) {
            maxSuitCountCards.addAll(countCards(this.deck, Suit.PICK));
            countOfSuits.setCount(pik);
            countOfSuits.setSuit("Пики");
            countOfSuits.setCardList(maxSuitCountCards);
            return countOfSuits;
        }
        if (max1 == tref) {
            maxSuitCountCards.addAll(countCards(this.deck, Suit.TREF));
            countOfSuits.setCount(tref);
            countOfSuits.setSuit("Трефы");
            countOfSuits.setCardList(maxSuitCountCards);
            return countOfSuits;
        }
        if (max1 == bub) {
            maxSuitCountCards.addAll(countCards(this.deck, Suit.BUBI));
            countOfSuits.setCount(bub);
            countOfSuits.setSuit("Буби");
            countOfSuits.setCardList(maxSuitCountCards);
            return countOfSuits;
        }
        if (max1 == cherv) {
            maxSuitCountCards.addAll(countCards(this.deck, Suit.CHERVI));
            countOfSuits.setCount(cherv);
            countOfSuits.setSuit("Черви");
            countOfSuits.setCardList(maxSuitCountCards);
            return countOfSuits;
        }
        return null;
    }

    public class CountOfSuits {
        int count;
        String suit;
        List<Card> cardList;

        public CountOfSuits() {
        }

        public CountOfSuits(CountOfSuits countOfSuits) {
            this.count = countOfSuits.count;
            this.suit = countOfSuits.suit;
            this.cardList = countOfSuits.cardList;
        }

        public List<Card> getCardList() {
            return cardList;
        }

        public void setCardList(List<Card> cardList) {
            this.cardList = cardList;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getSuit() {
            return suit;
        }

        public void setSuit(String suit) {
            this.suit = suit;
        }
    }
}
