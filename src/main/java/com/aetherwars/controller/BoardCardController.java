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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BoardCardController extends StackPane {
    private BaseGameController baseGameController;
    private Card card;
    private String playerName;

    @FXML
    private Label cardAttack;
    @FXML
    private Label cardHealth;
    @FXML
    private ImageView cardImage;
    @FXML
    private ImageView cardAttackImg;
    @FXML
    private ImageView cardHealthImg;
    @FXML
    private Label cardLevel;

    /**
     * Constructor for board card controller,
     * used to control the cards placed on player board.
     * @param baseGameController
     * @param card - current card
     */
    public BoardCardController(BaseGameController baseGameController, Card card, String playerName) {
        FXMLLoader boardLoader = new FXMLLoader(AetherWars.class.getResource("gui/BoardCard.fxml"));
        boardLoader.setRoot(this);
        boardLoader.setController(this);
        try {
            boardLoader.load();
            this.baseGameController = baseGameController;
            this.card = card;
            this.playerName = playerName;
            this.initBoard();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Display card image, attack, and health information
     * that placed player board.
     */
    public void initBoard() {
        Image cardImg = new Image(AetherWars.class.getResource(this.card.getImageLoc()).toString());
        System.out.println(this.card.getImageLoc());
        Image attackImg = new Image(AetherWars.class.getResource("card/image/attack.png").toString());
        Image healthImg = new Image(AetherWars.class.getResource("card/image/heart.png").toString());
        this.cardImage.setImage(cardImg);
        this.cardAttackImg.setImage(attackImg);
        this.cardHealthImg.setImage(healthImg);

        this.cardAttack.setText(Integer.toString(((Character) card).getAttack())+"("+ Integer.toString(((Character) card).getTotalAttackBuff())  +")");
        this.cardHealth.setText(Integer.toString(((Character) card).getHealth())+"("+ Integer.toString(((Character) card).getTotalHealthBuff())  +")");
    }

    @FXML
    void onClicked(MouseEvent event) {
        // playerboard getplayername
        Phase phase = baseGameController.getCurrentPhase();
        String activePlayerName = baseGameController.getActivePlayer().getName();

        baseGameController.setClickedBoardCard(((GridPane)this.getParent()).getChildren().indexOf(this));
        // TODO Handle attack here

        if (!this.playerName.equals(activePlayerName)) {
            baseGameController.getIdlePlayerBoardController().setClickedCard((Character) this.card);
            baseGameController.getIdlePlayerBoardController().setClickedCardController(this);
        } else {
            baseGameController.getActivePlayerBoardController().setClickedCard((Character) this.card);
            baseGameController.getActivePlayerBoardController().setClickedCardController(this);
        }

        // if (phase == phase.ATTACK) {
        //     Character cardActive = baseGameController.getActivePlayerBoardController().getClickedCard();
        //     Character cardIdle = baseGameController.getIdlePlayerBoardController().getClickedCard();

        //     if (cardActive != null && cardIdle != null) {
        //         System.out.println("Berhasil");
        //     }
        // }
        // if (baseGameController.getActivePlayer().getName() != )
        // System.out.println("Player: " + (this.);
        System.out.println("Player: " + this.playerName);

        this.setStyle("-fx-background-color:" + "#90ee90");
        this.getChildren().get(0).setStyle("-fx-border-color:" + "#ffa500");
        baseGameController.getDeckController().setCardInfo(this.card);
    }

    public Card getCard() {
        return this.card;
    }

    public Label getCharLevelLabel() {
        return this.cardLevel;
    }
}

