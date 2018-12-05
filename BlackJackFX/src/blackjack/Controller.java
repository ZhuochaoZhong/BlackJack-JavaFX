package blackjack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    private Hand playerHand = new Hand();
    private Hand dealerHand = new Hand();
    private Player player = new Player();
    private Deck deck = new Deck();
    private List<ImageView> playerCards = new ArrayList<>();
    private List<ImageView> dealerCards = new ArrayList<>();

    @FXML
    public BorderPane rootPane;

    @FXML
    public Button newGame;

    @FXML
    public Button deal;

    @FXML
    public Button hit;

    @FXML
    public Button stand;

    @FXML
    public Button quit;

    @FXML
    public TextArea dealerScore;

    @FXML
    public TextArea playerScore;

    @FXML
    public TextArea newDeck;

    @FXML
    public TextArea bet;

    @FXML
    public TextArea chip;

    @FXML
    public TextArea result;

    @FXML
    public ImageView dCard1 = new ImageView();

    @FXML
    public ImageView dCard2 = new ImageView();

    @FXML
    public ImageView dCard3 = new ImageView();

    @FXML
    public ImageView dCard4 = new ImageView();

    @FXML
    public ImageView dCard5 = new ImageView();

    @FXML
    public ImageView dCard6 = new ImageView();

    @FXML
    public ImageView pCard1;

    @FXML
    public ImageView pCard2;

    @FXML
    public ImageView pCard3;

    @FXML
    public ImageView pCard4;

    @FXML
    public ImageView pCard5;

    @FXML
    public ImageView pCard6;

    @FXML
    public ImageView pCard7;

    @FXML
    public ImageView pCard8;

    @FXML
    public ImageView pCard9;

    @FXML
    public ImageView pCard10;

    @FXML
    public ImageView bet50;

    @FXML
    public ImageView bet100;

    @FXML
    public ImageView bet200;

    @FXML
    public ImageView bet500;


    @FXML
    public void initialize() {
        resetHands();
        deck.newDeck();
        player.setChips(1000);
        playerCards.addAll(Arrays.asList(pCard1, pCard2, pCard3, pCard4, pCard5, pCard6, pCard7, pCard8, pCard9, pCard10));
        dealerCards.addAll(Arrays.asList(dCard1, dCard2, dCard3, dCard4, dCard5, dCard6));
        bet.setText("Please place your bet first!");
        chip.setText("You have " + player.getChips() + " chips");
        result.clear();
        initButtons();
    }

    @FXML
    public void setBet(int bet) {
        player.setBet(bet);
        chip.setText("You placed " + player.getBet() + " bet.");
        resetHands();
        playerHand.reset();
        dealerHand.reset();
        dealerScore.clear();
        playerScore.clear();
        result.clear();
        resetButtons();
    }

    @FXML
    public void dealCards() {

        if (deck.getStandardDeck().size() > 10) {
            bet.clear();
            deck.shuffle();

            playerHand.addCard(deck.deal());
            dealerHand.addCard(deck.deal());
            playerHand.addCard(deck.deal());
            dealerHand.addCard(deck.deal());

            dCard1.setImage(new Image("/resources/Cards/back.jpg"));
            dCard2.setImage(dealerHand.getCards().get(1).getImage());

            pCard1.setImage(playerHand.getCards().get(0).getImage());
            pCard2.setImage(playerHand.getCards().get(1).getImage());

            dealerScore.setText("Dealer is showing " + dealerHand.getDealerValue() + " points with one card facing down");
            playerScore.setText("You have " + playerHand.getValue() + " points now");

            deal.setDisable(true);
            hit.setDisable(false);
            stand.setDisable(false);

        } else {
            newDeck.setText("Starting New Deck Now!");
            deck.newDeck();
            dealCards();
        }

        if (blackJackCheck()) {
            result.setText("You Win With a Black Jack!!!");
            betResult(true);
            initButtons();
        }

    }

    @FXML
    public void hit() {
        newDeck.clear();
        int indexCard = playerHand.getCards().size();

        if (playerHand.getValue() < 21) {
            playerHand.addCard(deck.deal());
            playerCards.get(indexCard).setImage(playerHand.getCards().get(indexCard).getImage());
            playerScore.setText("You have " + playerHand.getValue() + " points now");
        }

        if (playerHand.getValue() > 21) {
            result.setText("Busted! You Lost!");
            dealerCards.get(0).setImage(dealerHand.getCards().get(0).getImage());
            dealerScore.setText("Dealer has " + dealerHand.getValue() + " points");
            betResult(false);
            initButtons();
        }

        if (playerHand.getValue() == 21) {
            result.setText("Your got a 21! Now Finger Crossed");
            stand();
        }
    }

    @FXML
    public void stand() {
        newDeck.clear();
        while (dealerHand.getValue() < 17) {
            int indexCard = dealerHand.getCards().size();
            dealerHand.addCard(deck.deal());
            dealerCards.get(indexCard).setImage(dealerHand.getCards().get(indexCard).getImage());
            dealerScore.setText("Dealer is showing " + dealerHand.getDealerValue() + " points with one card facing down");
        }

        dealerCards.get(0).setImage(dealerHand.getCards().get(0).getImage());
        dealerScore.setText("Dealer has " + dealerHand.getValue() + " points");

        if (dealerHand.getValue() > 21) {
            result.setText("Dealer Busted. You Win!!!");
            betResult(true);

        } else if (dealerHand.getValue() < playerHand.getValue()) {
            result.setText("You Win!!!");
            betResult(true);

        } else if (dealerHand.getValue() == playerHand.getValue()) {
            result.setText("Push! You Lost!");
            betResult(false);

        } else {
            result.setText("You Lost!");
            betResult(false);
        }
        initButtons();
    }

    private boolean blackJackCheck() {
        String rank1 = playerHand.getCards().get(0).getRanking();
        String rank2 = playerHand.getCards().get(1).getRanking();

        return (rank1.equals("A") && (rank2.equals("J") || rank2.equals("Q") || rank2.equals("K")))
                || (rank2.equals("A") && (rank1.equals("J") || rank1.equals("Q") || rank1.equals("K")));
    }

    private void betResult(boolean playerWin) {
        if (playerWin) {
            player.setChips(player.getChips() + player.getBet());

        } else {
            player.setChips(player.getChips() - player.getBet());
        }
        chip.setText("You have " + player.getChips() + " chips now!");
        bet.setText("Please place your bet first!");
    }

    private void resetHands() {
        for (ImageView imageView : playerCards) {
            imageView.setImage(null);
        }

        for (ImageView imageView : dealerCards) {
            imageView.setImage(null);
        }
    }

    private void resetButtons() {
        deal.setDisable(false);
        hit.setDisable(true);
        stand.setDisable(true);
        bet50.setDisable(true);
        bet100.setDisable(true);
        bet200.setDisable(true);
        bet500.setDisable(true);
    }

    private void initButtons() {
        deal.setDisable(true);
        hit.setDisable(true);
        stand.setDisable(true);

        int currentChips = player.getChips();

        if (currentChips >= 500) {
            bet50.setDisable(false);
            bet100.setDisable(false);
            bet200.setDisable(false);
            bet500.setDisable(false);
        } else if (currentChips >= 200 && currentChips < 500) {
            bet50.setDisable(false);
            bet100.setDisable(false);
            bet200.setDisable(false);
            bet500.setDisable(true);
        } else if (currentChips >= 100 && currentChips < 200) {
            bet50.setDisable(false);
            bet100.setDisable(false);
            bet200.setDisable(true);
            bet500.setDisable(true);
        } else if (currentChips >= 50 && currentChips < 100) {
            bet50.setDisable(false);
            bet100.setDisable(true);
            bet200.setDisable(true);
            bet500.setDisable(true);
        } else {
            bet50.setDisable(true);
            bet100.setDisable(true);
            bet200.setDisable(true);
            bet500.setDisable(true);
        }

        bet50.addEventHandler(MouseEvent.MOUSE_CLICKED, event50 -> setBet(50));
        bet100.addEventHandler(MouseEvent.MOUSE_CLICKED, event100 -> setBet(100));
        bet200.addEventHandler(MouseEvent.MOUSE_CLICKED, event200 -> setBet(200));
        bet500.addEventHandler(MouseEvent.MOUSE_CLICKED, event500 -> setBet(500));
    }

    @FXML
    public void quit() {
        System.exit(0);
    }
}
