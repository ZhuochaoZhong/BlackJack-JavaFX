package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards = new ArrayList<>();
    private int value = 0;
    private boolean ace = false;


    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);

        if (card.getRanking().equals("A")) {
            ace = true;
        }
        value += card.getCardValue();
    }

    public int getValue() {

        if (ace && value < 12) {
            return value + 10;
        } else {
            return value;
        }
    }

    public int getDealerValue() {
        Card hiddenCard = cards.get(0);
        if (hiddenCard.getRanking().equals("A")) {
            return getValue() - 11;
        } else {
            return getValue() - cards.get(0).getCardValue();
        }
    }

    public void reset() {
        cards.clear();
        value = 0;
        ace = false;
    }


}
