package Models.Gamers;

import Models.Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Gamer {
    public String name;
    public Bullet bullet = null;
    public List <Card> deck = new ArrayList<>();

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

    public String getDecision(){
        System.out.println("Игрок " + this.name + " принимает решение...");
        return null;
    }

    public void getRunkForHandDeck(List<Card> deck ){
        for (Card card : deck) {
            card.setRunk();
        }
    }
}
