package com.aetherwars.controller;

import com.aetherwars.AetherWars;
import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
    PlayerBoardController activePlayerController;
    private Card card;
    private Integer selectedCardIdx;

    @FXML
    private ImageView cardImage;
    @FXML
    private Label cardMana;
    @FXML
    private Label cardEffect;
    @FXML
    private Button throwBtn;

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

    /** HandCardController Contructor.
     * @param baseGameController .
     * @param card - card to be display
     */
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
            this.setActivePlayerController();
            this.initCard();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void setActivePlayerController() {
        if (baseGameController.getActivePlayer().getName().equals("Player One")) {
            activePlayerController = baseGameController.getPlayerOneController();
        } else {
            activePlayerController = baseGameController.getPlayerTwoController();
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

    /**
     * Delete card from hand slot.
     * @param event - throw button is clicked
     */
    @FXML
    void onClickBtn(MouseEvent event) {
        selectedCardIdx = GridPane.getColumnIndex(this);
        baseGameController.getDeckController().removeHandSlot(this);
        baseGameController.getActivePlayer().throwCardFromHand(selectedCardIdx);
        this.relayout();
    }

    /**
     * Readjust the layout of hand slot.
     * eg. fill the empty card space
     */
    public void relayout() {
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
    }

    @FXML
    //TODO tambahin komentar di semua fungsi
    //TODO implementasi efek spell kalo dipilih
    /*
    * Event card yang ditambahkan ke board baseGameController,
    * memiliki 1 indexing di children dari gridpane player board
     */
    void onClick(MouseEvent event) {
        selectedCardIdx = GridPane.getColumnIndex(this);
        if (this.card instanceof Character) {
            if (baseGameController.getActivePlayer().getBoard().getSize() < 5) {
                System.out.println("new deck size: " + baseGameController.getActivePlayer().getBoard().getSize() + 1);
                int i;
                int xPos = -1;
                int yPos = -1;

                // get index of empty player's board slot
                for(i=0; i<5; i++) {
                    if (!activePlayerController.isFilledSlot()[i]) {
                        xPos = BoardSlot.slot[i].getX();
                        yPos = BoardSlot.slot[i].getY();
                        activePlayerController.isFilledSlot()[i] = true;
                        break;
                    }
                }

                if (xPos != -1 && yPos != -1) {
                    BoardCardController temp = new BoardCardController(this.baseGameController, this.card);
                    baseGameController.getActivePlayer().addCardToBoard(selectedCardIdx); // otomatis throw dari hand
                    activePlayerController.getPlayerBoard().add(temp, yPos, xPos);
                }

                // remove clicked card
                baseGameController.getDeckController().removeHandSlot(this);
                this.relayout();
            } else {
                AlertBox.display("Board sudah penuh!");
            }
        }
        else if (this.card instanceof  Potion) {
            if (((Potion) this.card).getDuration() == 0) {
                /*
                * get clicked board card
                * character add buf based on potion atk health (permanent potion)
                */
                // TODO: tambah current card attack dengan buff attack
//                baseGameController.getActivePlayerBoardController().getClickedCard().setAttack(((Potion) this.card).getAttackBuff());
//                baseGameController.getActivePlayerBoardController().getClickedCard().setHealth(((Potion) this.card).getHealthBuff());
                // TODO: harus set card di player board biar keupdate juga guinya

            } else {
                System.out.println("TODO: HANDLING NON PERMANENT POTION");
            }
        }
        else {
            System.out.println("CLICKED CARD IS NOT A CHARACTER, ADD HANDLING FOR SPELL!!!!");
        }

    }

    public void setBoardCardEffect(Card card) {

    }

    @FXML
    public void onHover(MouseEvent event) {
        baseGameController.getDeckController().setCardInfo(this.card);
//        System.out.println("hovered card idx on hand: " + GridPane.getColumnIndex(this));
        this.setStyle("-fx-background-color:" + "#32a85e");
    }

    @FXML
    public void onLeave(MouseEvent event) {
        this.setStyle("-fx-background-color: transparent");
    }
}