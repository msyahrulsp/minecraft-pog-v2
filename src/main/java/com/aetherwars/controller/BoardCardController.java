package com.aetherwars.controller;

import com.aetherwars.AetherWars;
import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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

    @FXML
    void initialize() {
        assert cardAttack != null : "fx:id=\"cardAttack\" was not injected: check your FXML file 'BoardCard.fxml'.";
        assert cardHealth != null : "fx:id=\"cardHealth\" was not injected: check your FXML file 'BoardCard.fxml'.";
        assert cardImage != null : "fx:id=\"cardImage\" was not injected: check your FXML file 'BoardCard.fxml'.";
        assert cardLevel != null : "fx:id=\"cardLevel\" was not injected: check your FXML file 'BoardCard.fxml'.";
    }

    public BoardCardController(BaseGameController baseGameController, Card card) {
//        System.out.println("jalan");
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

    public void initBoard() {
        Image cardImg = new Image(AetherWars.class.getResource(this.card.getImageLoc()).toString());
        this.cardImage.setImage(cardImg);

        if (this.card instanceof Character) {
            this.cardAttack.setText(Integer.toString(((Character) card).getAttack()));
            this.cardHealth.setText(Integer.toString(((Character) card).getHealth()));
        }
//        else if (card instanceof Spell) {
//            if (((Spell) card).getType().equals(spellType.PTN)) {
//                this.cardEffect.setText("ATK " + Integer.toString(((Potion) card).getAttackBuff()) + "/HP " + Integer.toString(((Potion) card).getHealthBuff()));
//            } else if (card instanceof Swap) {
//                this.cardEffect.setText("SWAP");
//            } else if (card instanceof Level) {
//                this.cardEffect.setText("LEVEL");
//            } else if (this.card instanceof Morph) {
//                this.cardEffect.setText("MORPH");
//            }
//        }
    }
}

