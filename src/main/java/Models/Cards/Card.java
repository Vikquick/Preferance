package Models.Cards;

public class Card {

    public CardWeight cardWeight;
    public Suit suit;
    public boolean trump = false;
    public int runk;

    public Card() {
    }

    public Card(CardWeight cardWeight, Suit suit) {

        this.cardWeight = cardWeight;
        this.suit = suit;
    }

    public boolean isTrump() {
        return trump;
    }

    public void setTrump(boolean trump) {
        this.trump = trump;
    }

    public CardWeight getCardWeight() {
        return cardWeight;
    }

    public void setCardWeight(CardWeight cardWeight) {
        this.cardWeight = cardWeight;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public int getRunk() {
        return runk;
    }

    public void setRunk() {
        int cardWeightRunk = 0;
        int suitRunk = 0;
        int trumpRunk = 10;

        switch (this.cardWeight.toString()) {
            case "6":
                cardWeightRunk = 1;
                break;
            case "7":
                cardWeightRunk = 2;
                break;
            case "8":
                cardWeightRunk = 3;
                break;
            case "9":
                cardWeightRunk = 4;
                break;
            case "10":
                cardWeightRunk = 5;
                break;
            case "Валет":
                cardWeightRunk = 6;
                break;
            case "Дама":
                cardWeightRunk = 7;
                break;
            case "Король":
                cardWeightRunk = 8;
                break;
            case "Туз":
                cardWeightRunk = 9;
                break;
            default:
                //TODO add log warning
        }

        switch (this.suit.toString()) {
            case "Пики":
                suitRunk = 1;
                break;
            case "Трефы":
                suitRunk = 2;
                break;
            case "Буби":
                suitRunk = 3;
                break;
            case "Черви":
                suitRunk = 4;
                break;
            default: //TODO add log warning
        }
        if (!trump)
            this.runk = cardWeightRunk * suitRunk;
        else
            this.runk = cardWeightRunk * suitRunk * trumpRunk;
    }
}
