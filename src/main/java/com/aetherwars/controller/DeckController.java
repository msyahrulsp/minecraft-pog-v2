package com.aetherwars.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.aetherwars.AetherWars;
import com.aetherwars.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private ImageView cardImage;
    @FXML
    private Label cardStats;
    @FXML
    private Label cardDesc;
    @FXML
    private Label deckCount;
    @FXML
    private Label manaCount;

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

    public void initDeck() {
        this.currentPlayer.getCardToHand("first");

        this.getChildren().clear();

        for(Card card: this.currentPlayer.getHand().getCards()) {
            this.getChildren().add(new HandCardController(this.baseGameController, card));
        }
    }
}

