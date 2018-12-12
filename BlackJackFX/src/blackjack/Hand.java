package blackjack;

import java.util.ArrayList;
import java.util.List;

class Hand {

    private List<Card> cards = new ArrayList<>();
    private int value = 0;
    private boolean ace = false;


    List<Card> getCards() {
        return cards;
    }

    void addCard(Card card) {
        cards.add(card);

        if (card.getRanking().equals("A")) {
            ace = true;
        }
        value += card.getCardValue();
    }

    int getValue() {

        if (ace && value < 12) {
            return value + 10;
        } else {
            return value;
        }
    }

    int getDealerValue() {
        Card hiddenCard = cards.get(0);
        if (hiddenCard.getRanking().equals("A")) {
            return getValue() - 11;
        } else {
            return getValue() - hiddenCard.getCardValue();
        }
    }

    void reset() {
        cards.clear();
        value = 0;
        ace = false;
    }


}
