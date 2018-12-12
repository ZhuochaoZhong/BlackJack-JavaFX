package blackjack;

import javafx.scene.image.Image;

public class Card {

    private String suit;
    private String ranking;
    private int cardValue;
    private Image image;


    Card(String suit, String ranking, int cardValue, Image image) {
        this.suit = suit;
        this.ranking = ranking;
        this.cardValue = cardValue;
        this.image = image;
    }

    String getRanking() {
        return ranking;
    }

    int getCardValue() {
        return cardValue;
    }

    Image getImage() {
        return image;
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
