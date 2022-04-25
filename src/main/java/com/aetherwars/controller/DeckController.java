package com.aetherwars.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.aetherwars.AetherWars;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class DeckController extends HBox {
    private BaseGameController baseGameController;
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

    @FXML
    void initialize() {
        assert handSlot != null : "fx:id=\"handSlot\" was not injected: check your FXML file 'Deck.fxml'.";
        assert cardImage != null : "fx:id=\"cardImage\" was not injected: check your FXML file 'Deck.fxml'.";
        assert cardStats != null : "fx:id=\"cardStats\" was not injected: check your FXML file 'Deck.fxml'.";
        assert cardDesc != null : "fx:id=\"cardDesc\" was not injected: check your FXML file 'Deck.fxml'.";
        assert deckCount != null : "fx:id=\"deckCount\" was not injected: check your FXML file 'Deck.fxml'.";
        assert manaCount != null : "fx:id=\"manaCount\" was not injected: check your FXML file 'Deck.fxml'.";

    }

        public DeckController(BaseGameController controller) {
        FXMLLoader handLoader = new FXMLLoader(AetherWars.class.getResource("gui/Deck.fxml"));
        handLoader.setRoot(this);
        handLoader.setController(this);


        try {

            handLoader.load();
            this.baseGameController = controller;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

