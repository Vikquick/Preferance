package Models.Cards;

public class Card implements Comparable<Card>{

    public CardWeight cardWeight;
    public int cardWeightRunk;
    public Suit suit;
    public boolean trump = false;
    public Integer runk;

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

    public int getCardWeightRunk() {
        return cardWeightRunk;
    }

    public void setCardWeightRunk() {
        switch (this.cardWeight.toString()) {
            case "6":
                this.cardWeightRunk = 1;
                break;
            case "7":
                this.cardWeightRunk = 2;
                break;
            case "8":
                this.cardWeightRunk = 3;
                break;
            case "9":
                this.cardWeightRunk = 4;
                break;
            case "10":
                this.cardWeightRunk = 5;
                break;
            case "Валет":
                this.cardWeightRunk = 6;
                break;
            case "Дама":
                this.cardWeightRunk = 7;
                break;
            case "Король":
                this.cardWeightRunk = 8;
                break;
            case "Туз":
                this.cardWeightRunk = 9;
                break;
            default:
                //TODO add log warning
        }
    }

    public void setRunk(Integer runk) {
        this.runk = runk;
    }

    public void setRunk() {
        int suitRunk = 0;
        int trumpRunk = 10;
        setCardWeightRunk();

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
            this.runk = this.cardWeightRunk * suitRunk;
        else
            this.runk = this.cardWeightRunk * suitRunk * trumpRunk;
    }

    @Override
    public int compareTo(Card o) {
        return runk.compareTo(o.getRunk());
    }
}
