package com.aetherwars.controller;

import com.aetherwars.AetherWars;
import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class BoardCardController extends StackPane {
    private BaseGameController baseGameController;
    private Card card;

    @FXML
    private Label cardAttack;
    @FXML
    private Label cardHealth;
    @FXML
    private ImageView cardImage;
    @FXML
    private Label cardLevel;

    /**
     * Constructor for board card controller,
     * used to control the cards placed on player board.
     * @param baseGameController
     * @param card - current card
     */
    public BoardCardController(BaseGameController baseGameController, Card card) {
        FXMLLoader boardLoader = new FXMLLoader(AetherWars.class.getResource("gui/BoardCard.fxml"));
        boardLoader.setRoot(this);
        boardLoader.setController(this);
        try {
            boardLoader.load();
            this.baseGameController = baseGameController;
            this.card = card;
            this.initBoard();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Display card image, attack, and health information
     * that placed player board.
     */
    public void initBoard() {
        Image cardImg = new Image(AetherWars.class.getResource(this.card.getImageLoc()).toString());
        this.cardImage.setImage(cardImg);

        if (this.card instanceof Character) {
            this.cardAttack.setText(Integer.toString(((Character) card).getAttack()));
            this.cardHealth.setText(Integer.toString(((Character) card).getHealth()));
        }
    }

    @FXML
    void onClicked(MouseEvent event) {
        this.setStyle("-fx-background-color:" + "#90ee90");
        this.getChildren().get(0).setStyle("-fx-border-color:" + "#ffa500");
        baseGameController.getDeckController().setCardInfo(this.card);
    }
}

