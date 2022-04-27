package com.aetherwars.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.aetherwars.AetherWars;
import com.aetherwars.model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class DeckController extends HBox {

    private BaseGameController baseGameController;
    private Player currentPlayer;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
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

    public DeckController(BaseGameController controller, Player activePlayer) {
        FXMLLoader handLoader = new FXMLLoader(AetherWars.class.getResource("gui/Deck.fxml"));
        handLoader.setRoot(this);
        handLoader.setController(this);
        try {
            handLoader.load();
            this.currentPlayer = activePlayer;
            this.baseGameController = controller;
            this.initDeck();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setCardInfo(Card card) {
        Image cardImg = new Image(AetherWars.class.getResource(card.getImageLoc()).toString());
        this.cardInfoImage.setImage(cardImg);
        this.cardInfoStats.setText(card.toString());
        this.cardInfoDesc.setText(card.getDesc());
    }

    public void initDeck() {
        this.currentPlayer.getCardToHand("first");

        for(int i=0; i<this.currentPlayer.getHand().getSize(); i++) {
            this.handSlot.add(new HandCardController(this.baseGameController, this.currentPlayer.getHand().getElmt(i)), i, 0);
        }
    }
}

