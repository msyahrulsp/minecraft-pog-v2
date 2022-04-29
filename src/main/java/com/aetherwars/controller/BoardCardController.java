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
        baseGameController.setClickedBoardCard(((GridPane)this.getParent()).getChildren().indexOf(this));
        // TODO Handle attack here
        baseGameController.getActivePlayerBoardController().setClickedCard((Character) this.card);
        baseGameController.getActivePlayerBoardController().setClickedCardController(this);
        System.out.println();
        // ini blom ditambahin sama previous attack/health
//        this.cardAttack.setText(String.valueOf(baseGameController.getActivePlayerBoardController().getClickedCard().getAttack()));
//        this.cardHealth.setText(String.valueOf(baseGameController.getActivePlayerBoardController().getClickedCard().getHealth()));
//        System.out.println(baseGameController.getActivePlayerBoardController().getClickedCard().getHealth());
//        System.out.println(baseGameController.getActivePlayerBoardController().getClickedCard().getAttack());

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

