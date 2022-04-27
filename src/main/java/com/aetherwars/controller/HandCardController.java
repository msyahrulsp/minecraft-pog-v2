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

class tempCard {
    private HandCardController tempCard;
    public tempCard(HandCardController cardController) {
        this.tempCard = cardController;
    }
}

public class HandCardController extends StackPane {
    private BaseGameController baseGameController;
    private Card card;
    private Card selectedCard;

    public static class RemainingCard {
        private static Integer newSize;
        private static HandCardController[] card;
        private static void init() {
            card = new HandCardController[5];
        }
    }

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
            RemainingCard.init();
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
    //TODO implementasi efek spell kalo dipilih
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

        Integer selectedCardIdx = GridPane.getColumnIndex(this);
        baseGameController.getActivePlayer().addCardToBoard(selectedCardIdx);

        /**
         * Kenapa enggak langsung pake remove card at clicked index aja?
         * Soalnya deck card bakal dinamis (otomatis fill posisi card yang kosong),
         * jadinya harus inisialisasi ulang setiap ada card yang di delete
         */

        // remove clicked card
        HandCardController removedCard = baseGameController.getDeckController().getClickedCardController(selectedCardIdx);
        baseGameController.getDeckController().removeHandSlot(removedCard);

        // add removed card to temporary array of card
        RemainingCard.newSize = baseGameController.getDeckController().getHandSlot().getChildren().size();
        for(int j=0; j<RemainingCard.newSize; j++) {
            if ((HandCardController) baseGameController.getDeckController().getHandSlot().getChildren().get(j) != null) {
                RemainingCard.card[j] = (HandCardController) baseGameController.getDeckController().getHandSlot().getChildren().get(j); // card 0 1 2 jadi card 0 2
            }
        }

        // clear the deck and re-adding remaining cards
        baseGameController.getDeckController().getHandSlot().getChildren().clear();
        for(int k=0; k<RemainingCard.newSize; k++) {
            baseGameController.getDeckController().getHandSlot().add(RemainingCard.card[k], k, 0);
        }
    }



    @FXML
    public void onHover(MouseEvent event) {
        baseGameController.getDeckController().setCardInfo(this.card);
        System.out.println(GridPane.getColumnIndex(this));
        this.setStyle("-fx-background-color:" + "#32a85e");
    }

    @FXML
    public void onLeave(MouseEvent event) {
        this.setStyle("-fx-background-color: transparent");
    }
}