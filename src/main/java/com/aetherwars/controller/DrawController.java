package com.aetherwars.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.aetherwars.AetherWars;
import com.aetherwars.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DrawController extends HBox {
    private BaseGameController baseGameController;
    private Player currentPlayer;

    @FXML
    private GridPane drawGrid;

    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    @FXML
    private ImageView cardImage;

    public DrawController(BaseGameController controller, Player activePlayer) {
        FXMLLoader drawLoader = new FXMLLoader(AetherWars.class.getResource("gui/DrawCard.fxml"));
        drawLoader.setController(this);
        try {
            drawLoader.load();
            this.currentPlayer = activePlayer;
            this.baseGameController = controller;
            this.initDraw();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void initDraw() {
        this.currentPlayer.getCardToHand("add");
        this.getChildren().clear();

        for (Card card: this.currentPlayer.getAddCard().getCards()) {
            this.getChildren().add(new HandCardController(this.baseGameController, card));
        }
    }
}
