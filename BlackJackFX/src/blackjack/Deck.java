package blackjack;

import javafx.scene.image.Image;

import java.util.*;

import static java.util.Arrays.asList;

public class Deck {

    private List<String> SUITS = new ArrayList<String>() {{
        addAll(asList("Heart", "Diamond", "Club", "Spade"));
    }};

    private List<String> RANKING = new ArrayList<String>() {{
        addAll(asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
    }};

    private Map<String, Integer> CARDVALUE = new LinkedHashMap<String, Integer>() {{
        put("A", 1);
        put("2", 2);
        put("3", 3);
        put("4", 4);
        put("5", 5);
        put("6", 6);
        put("7", 7);
        put("8", 8);
        put("9", 9);
        put("10", 10);
        put("J", 10);
        put("Q", 10);
        put("K", 10);
    }};

    private List<String> imgPath = new ArrayList<String>() {{
        addAll(asList("/resources/Cards/ace_of_diamonds.png", "/resources/Cards/2_of_diamonds.png", "/resources/Cards/3_of_diamonds.png",
                "/resources/Cards/4_of_diamonds.png", "/resources/Cards/5_of_diamonds.png", "/resources/Cards/6_of_diamonds.png",
                "/resources/Cards/7_of_diamonds.png", "/resources/Cards/8_of_diamonds.png", "/resources/Cards/9_of_diamonds.png",
                "/resources/Cards/10_of_diamonds.png", "/resources/Cards/jack_of_diamonds.png", "/resources/Cards/queen_of_diamonds.png",
                "/resources/Cards/king_of_diamonds.png", "/resources/Cards/ace_of_hearts.png", "/resources/Cards/2_of_hearts.png", "/resources/Cards/3_of_hearts.png",
                "/resources/Cards/4_of_hearts.png", "/resources/Cards/5_of_hearts.png", "/resources/Cards/6_of_hearts.png",
                "/resources/Cards/7_of_hearts.png", "/resources/Cards/8_of_hearts.png", "/resources/Cards/9_of_hearts.png",
                "/resources/Cards/10_of_hearts.png", "/resources/Cards/jack_of_hearts.png", "/resources/Cards/queen_of_hearts.png",
                "/resources/Cards/king_of_hearts.png", "/resources/Cards/ace_of_clubs.png", "/resources/Cards/2_of_clubs.png", "/resources/Cards/3_of_clubs.png",
                "/resources/Cards/4_of_clubs.png", "/resources/Cards/5_of_clubs.png", "/resources/Cards/6_of_clubs.png",
                "/resources/Cards/7_of_clubs.png", "/resources/Cards/8_of_clubs.png", "/resources/Cards/9_of_clubs.png",
                "/resources/Cards/10_of_clubs.png", "/resources/Cards/jack_of_clubs.png", "/resources/Cards/queen_of_clubs.png",
                "/resources/Cards/king_of_clubs.png", "/resources/Cards/ace_of_spades.png", "/resources/Cards/2_of_spades.png", "/resources/Cards/3_of_spades.png",
                "/resources/Cards/4_of_spades.png", "/resources/Cards/5_of_spades.png", "/resources/Cards/6_of_spades.png",
                "/resources/Cards/7_of_spades.png", "/resources/Cards/8_of_spades.png", "/resources/Cards/9_of_spades.png",
                "/resources/Cards/10_of_spades.png", "/resources/Cards/jack_of_spades.png", "/resources/Cards/queen_of_spades.png",
                "/resources/Cards/king_of_spades.png"));
    }};



    private Card card;
    private List<Card> standardDeck = new ArrayList<>();
    private List<Image> imageListH = new ArrayList<>();
    private List<Image> imageListD = new ArrayList<>();
    private List<Image> imageListC = new ArrayList<>();
    private List<Image> imageListS = new ArrayList<>();


    public List<Card> getStandardDeck() {
        return standardDeck;
    }

    public void shuffle() {
        Collections.shuffle(standardDeck);
    }


    public Card deal() {
        // Dealing a card from the deck(card removed from deck),
        // card will be a String with suit and ranking (ie. "H3" --> three of hearts)
        return standardDeck.remove(0);
    }

    public void newDeck() {

        getImageListD();
        getImageListH();
        getImageListC();
        getImageListS();

        for (int i = 0; i < 12; i++) {
            card = new Card(SUITS.get(0), RANKING.get(i), CARDVALUE.get(RANKING.get(i)), imageListH.get(i));
            standardDeck.add(card);
        }

        for (int i = 0; i < 12; i++) {
            card = new Card(SUITS.get(1), RANKING.get(i), CARDVALUE.get(RANKING.get(i)), imageListD.get(i));
            standardDeck.add(card);
        }

        for (int i = 0; i < 12; i++) {
            card = new Card(SUITS.get(2), RANKING.get(i), CARDVALUE.get(RANKING.get(i)), imageListC.get(i));
            standardDeck.add(card);
        }

        for (int i = 0; i < 12; i++) {
            card = new Card(SUITS.get(3), RANKING.get(i), CARDVALUE.get(RANKING.get(i)), imageListS.get(i));
            standardDeck.add(card);
        }
    }

    public List<Image> getImageListD() {

        for (int i = 0; i < 13; i++) {
            imageListD.add(new Image(imgPath.get(i)));
        }
        return imageListD;
    }

    public List<Image> getImageListH() {

        for (int i = 13; i < 26; i++) {
            imageListH.add(new Image(imgPath.get(i)));
        }
        return imageListH;
    }

    public List<Image> getImageListC() {

        for (int i = 26; i < 39; i++) {
            imageListC.add(new Image(imgPath.get(i)));
        }
        return imageListC;
    }

    public List<Image> getImageListS() {

        for (int i = 39; i < 52; i++) {
            imageListS.add(new Image(imgPath.get(i)));
        }
        return imageListS;
    }
}
