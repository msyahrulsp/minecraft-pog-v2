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

import java.util.ArrayList;

class Slot {
    private Integer x;
    private Integer y;
    public Slot(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    public Integer getX() {
        return this.x;
    }
    public Integer getY() {
        return this.y;
    }
}

public class HandCardController extends StackPane {
    private BaseGameController baseGameController;
    private Card card;
    private Card selectedCard;

    private static class BoardSlot {
        private static Slot[] slot;
        /**
         * using 0 indexing for 5 slot position
         */
        private static void initSlot() {
            slot = new Slot[5];
            slot[0] = new Slot(1,0);
            slot[1] = new Slot(0,0);
            slot[2] = new Slot(0,1);
            slot[3] = new Slot(0,2);
            slot[4] = new Slot(1,2);
        }
    }

    @FXML
    private ImageView cardImage;
    @FXML
    private Label cardMana;
    @FXML
    private Label cardEffect;

    public HandCardController(BaseGameController baseGameController, Card card) {
        FXMLLoader handLoader = new FXMLLoader(AetherWars.class.getResource("gui/HandCard.fxml"));
        handLoader.setRoot(this);
        handLoader.setController(this);

        try {
            handLoader.load();
            BoardSlot.initSlot();
            this.card = card;
            this.baseGameController = baseGameController;
            this.initCard();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void initCard() {
        Image cardImg = new Image(AetherWars.class.getResource(this.card.getImageLoc()).toString());
        this.cardImage.setImage(cardImg);

        this.cardMana.setText("MANA " + Integer.toString(card.getMana()));
        if (this.card instanceof Character) {
            this.cardEffect.setText("ATK " + Integer.toString(((Character) card).getAttack()) + "/HP " + Integer.toString(((Character) card).getHealth()));
        } else if (card instanceof Spell) {
            if (((Spell) card).getType().equals(spellType.PTN)) {
                this.cardEffect.setText("ATK " + Integer.toString(((Potion) card).getAttackBuff()) + "/HP " + Integer.toString(((Potion) card).getHealthBuff()));
            } else if (card instanceof Swap) {
                this.cardEffect.setText("SWAP");
            } else if (card instanceof Level) {
                this.cardEffect.setText("LEVEL");
            } else if (this.card instanceof Morph) {
                this.cardEffect.setText("MORPH");
            }
        }
    }

    @FXML
    //TODO tambahin warning kalo boxnya udah full
    //TODO tambahin komentar di semua fungsi
    void onClick(MouseEvent event) {
        PlayerBoardController activePlayerController;
        int i;
        int xPos = -1;
        int yPos = -1;

        if (baseGameController.getActivePlayer().getName().equals("Player One")) {
            activePlayerController = baseGameController.getPlayerOneController();
        } else {
            activePlayerController = baseGameController.getPlayerTwoController();
        }

        for(i=0; i<5; i++) {
            if (!activePlayerController.isFilledSlot()[i]) {
                xPos = BoardSlot.slot[i].getX();
                yPos = BoardSlot.slot[i].getY();
                activePlayerController.isFilledSlot()[i] = true;
                break;
            }
        }

        if (xPos != -1 && yPos != -1) {
            activePlayerController.getPlayerBoard().add(new BoardCardController(this.baseGameController, this.card), yPos, xPos);
        }

       //TODO hapus card dari player handCard

    }

    @FXML
    void onHover(MouseEvent event) {
        baseGameController.getDeckController().setCardInfo(this.card);
//        System.out.println(GridPane.getColumnIndex(this));
        this.setStyle("-fx-background-color:" + "#32a85e");
    }

    @FXML
    void onLeave(MouseEvent event) {
        this.setStyle("-fx-background-color: transparent");
    }
}