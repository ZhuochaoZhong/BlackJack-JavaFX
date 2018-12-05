package blackjack;

import javafx.scene.image.Image;

public class Card {

    private String suit;
    private String ranking;
    private int cardValue;
    private Image image;


    public Card(String suit, String ranking, int cardValue, Image image) {
        this.suit = suit;
        this.ranking = ranking;
        this.cardValue = cardValue;
        this.image = image;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public int getCardValue() {
        return cardValue;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", ranking='" + ranking + '\'' +
                ", cardValue=" + cardValue +
                '}';
    }
}
