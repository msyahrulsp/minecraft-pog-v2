package com.aetherwars.controller;

import com.aetherwars.AetherWars;
import com.aetherwars.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DeckController extends HBox {
    private final BaseGameController baseGameController;
    private Player currentPlayer;

    @FXML
    private GridPane handSlot;
    @FXML
    private ImageView cardInfoImage;
    @FXML
    private Label cardInfoStats;
    @FXML
    private Label cardInfoDesc;
    @FXML
    private Label deckInfoCount;
    @FXML
    private Label manaInfoCount;

    /**
     * Constructor for deck controller.
     * @param controller
     * @param activePlayer
     */
    public DeckController(BaseGameController controller, Player activePlayer, boolean First) {
        FXMLLoader handLoader = new FXMLLoader(AetherWars.class.getResource("gui/Deck.fxml"));
        handLoader.setRoot(this);
        handLoader.setController(this);
        try {
            handLoader.load();
            this.currentPlayer = activePlayer;
            this.baseGameController = controller;
            this.initDeck(First);
            this.setDeckInfo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // TODO handle give mana ke hand here
    // onclick dari file .fxmlnya
    @FXML
    public void giveMana() {
        Character target = this.baseGameController.getActivePlayer().getBoard().getActiveCharacter();
        if player.getMana() > 0 {
            target.addExp(4);
            player.setMana(player.getMana() - 1);
        }
        System.out.println("given mana to " + target.getName());
    }

    /**
     * Set deck card info.
     * @param card - onHover card
     */
    public void setCardInfo(Card card) {
        Image cardImg = new Image(AetherWars.class.getResource(card.getImageLoc()).toString());
        this.cardInfoImage.setImage(cardImg);
        this.cardInfoStats.setText(card.toString());
        this.cardInfoDesc.setText("\"" + card.getDesc() + "\"");
    }

    /**
     * Load 3 player card from deck to hand.
     */
    public void initDeck(boolean First) {
        if(First) {
            this.currentPlayer.getCardToHand("first");
        }

        for(int i=0; i < this.currentPlayer.getHand().getSize(); i++) {
            this.handSlot.add(new HandCardController(this.baseGameController, this.currentPlayer.getHand().getElmt(i)), i, 0);
        }
    }

    public void reInitDeck() {
//        this.currentPlayer.getCardToHand("first");

        for(int i=0; i < this.currentPlayer.getHand().getSize(); i++) {
            this.handSlot.add(new HandCardController(this.baseGameController, this.currentPlayer.getHand().getElmt(i)), i, 0);
        }
    }

    public void addDrawCardDeck() {
        this.handSlot.add(new HandCardController(this.baseGameController, this.currentPlayer.getHand().getElmt(this.currentPlayer.getHand().getSize()-1)), this.currentPlayer.getHand().getSize()-1, 0);
    }

    public void setDeckInfo() {
        if (this.currentPlayer.getDeck().getSize() != 0) {
            this.deckInfoCount.setText("Deck(s) Remaining: " + ((Integer) this.currentPlayer.getDeck().getSize()).toString());
        } else if (this.currentPlayer.getDeck().getSize() == 0 && this.currentPlayer.getAddCard().getSize() < 3) {
            this.deckInfoCount.setText("Deck(s) Remaining: " + ((Integer) this.currentPlayer.getAddCard().getSize()).toString());
        }
        this.manaInfoCount.setText("Mana: " + ((Integer) this.currentPlayer.getMana()).toString() + "/" + this.baseGameController.getManaCap());
    }

    /**
     * Getter for hand card slot gridpane.
     * @return gridpane
     */
    public GridPane getHandSlot() {
        return this.handSlot;
    }

    /**
     * Used to move hand card to player board card.
     * @param idx - clicked card index on hand slot
     * @return HandCardController
     */
    public HandCardController getClickedCardController(Integer idx) {
        return (HandCardController) this.handSlot.getChildren().get(idx);
    }

    /**
     * Remove removed card from hand slot gridpane children.
     * @param removedCard - removed card on hand slot
     */
    public void removeHandSlot(HandCardController removedCard) {
        this.handSlot.getChildren().remove(removedCard);
    }
}