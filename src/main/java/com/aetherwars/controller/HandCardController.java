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

/**
 *
 */
class Slot {
    private final Integer x;
    private final Integer y;
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

/**
 * Controller class for HandCard.fxml,
 * used to control the behavior of cards
 * that placed on player's hand.
 */
public class HandCardController extends StackPane {
    private final BaseGameController baseGameController;
    private Card card;

    /**
     * Static class to store remaining card
     * that left on player's hand
     * to re-initialize to fill
     * the empty card slot.
     */
    public static class RemainingCard {
        private static Integer newSize;
        private static HandCardController[] card;
        private static void init() {
            card = new HandCardController[5];
        }
    }

    /**
     * Static class to store
     * the x and y position of player's board card
     * using 0 indexing to maintain 5 card slot location.
     * ex: Card 1 placed on position (1,0).
     */
    private static class BoardSlot {
        private static Slot[] slot;
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

        this.cardMana.setText("MANA " + card.getMana());
        if (this.card instanceof Character) {
            this.cardEffect.setText("ATK " + ((Character) card).getAttack() + "/HP " + ((Character) card).getHealth());
        } else if (card instanceof Spell) {
            if (((Spell) card).getType().equals(spellType.PTN)) {
                this.cardEffect.setText("ATK " + ((Potion) card).getAttackBuff() + "/HP " + ((Potion) card).getHealthBuff());
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
    //TODO tambahin komentar di semua fungsi
    //TODO implementasi efek spell kalo dipilih
    void onClick(MouseEvent event) {
        if (baseGameController.getActivePlayer().getBoard().getSize() < 5) {
            System.out.println("deck size: " + baseGameController.getActivePlayer().getBoard().getSize());
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
                if (baseGameController.getDeckController().getHandSlot().getChildren().get(j) != null) {
                    RemainingCard.card[j] = (HandCardController) baseGameController.getDeckController().getHandSlot().getChildren().get(j); // card 0 1 2 jadi card 0 2
                }
            }

            // clear the deck and re-adding remaining cards
            baseGameController.getDeckController().getHandSlot().getChildren().clear();
            for(int k=0; k<RemainingCard.newSize; k++) {
                baseGameController.getDeckController().getHandSlot().add(RemainingCard.card[k], k, 0);
            }
        } else {
            AlertBox.display("Board sudah penuh!");
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