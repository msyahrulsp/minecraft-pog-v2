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

        if (!this.playerName.equals(activePlayerName)) {
            
            baseGameController.getIdlePlayerBoardController().setClickedCard((Character) this.card);
            baseGameController.getIdlePlayerBoardController().setClickedCardController(this);

            int idx = baseGameController.getIdlePlayer().getIdxCard(this.card) + 1;
            baseGameController.setClickedIdleBoardCard(idx);
        } else {
            baseGameController.setClickedBoardCard(((GridPane)this.getParent()).getChildren().indexOf(this));
            System.out.println(baseGameController.getClickedBoardCard());
            baseGameController.getActivePlayerBoardController().setClickedCard((Character) this.card);
            baseGameController.getActivePlayerBoardController().setClickedCardController(this);
        }

        this.setStyle("-fx-background-color:" + "#90ee90");
        this.getChildren().get(0).setStyle("-fx-border-color:" + "#ffa500");
        baseGameController.getDeckController().setCardInfo(this.card);

        if (phase == phase.ATTACK) {
            Character cardActive = baseGameController.getActivePlayerBoardController().getClickedCard();
            Character cardIdle = baseGameController.getIdlePlayerBoardController().getClickedCard();

            if (cardActive != null && cardIdle != null && !cardActive.getAlreadyAttacked()) {
                cardActive.serang(cardIdle, true);
                System.out.println("Here2");
                System.out.println(cardActive);
                if (cardActive.getHealth() <= 0) {
                    System.out.println("Here1");
                    int idx = baseGameController.getClickedBoardCard() - 1;
                    System.out.println("Active Idx: " + idx);
                    baseGameController.getActivePlayer().removeCardFromBoard(idx);
                }

                if (cardIdle.getHealth() <= 0) {
                    System.out.println("Here4");
                    if (cardActive.getHealth() > 0) {
                        cardActive.addExp(cardIdle.getLvl());
                    }

                    int idx = baseGameController.getClickedIdleBoardCard() - 1;
                    System.out.println("Idle Idx: " + idx);

                    baseGameController.getIdlePlayer().removeCardFromBoard(idx);
                }
                // this.relayout();
            }
            // TODO update ui
            // baseGameController.setDeckInterface(baseGameController.getActivePlayer(), false);
        }
    }

    public Card getCard() {
        return this.card;
    }

    public Label getCharLevelLabel() {
        return this.cardLevel;
    }

    // public void relayout() {
    //     // add removed card to temporary array of card
    //     RemainingCard.newSize = baseGameController.getDeckController().getHandSlot().getChildren().size();
    //     for(int j=0; j<RemainingCard.newSize; j++) {
    //         if (baseGameController.getDeckController().getHandSlot().getChildren().get(j) != null) {
    //             RemainingCard.card[j] = (HandCardController) baseGameController.getDeckController().getHandSlot().getChildren().get(j); // card 0 1 2 jadi card 0 2
    //         }
    //     }

    //     // clear the deck and re-adding remaining cards
    //     baseGameController.getDeckController().getHandSlot().getChildren().clear();
    //     for(int k=0; k<RemainingCard.newSize; k++) {
    //         baseGameController.getDeckController().getHandSlot().add(RemainingCard.card[k], k, 0);
    //     }
    // }
}

