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

public class HandCardController extends StackPane {
    private BaseGameController baseGameController;
    private Card card;

    @FXML
    private ImageView cardImage;
    @FXML
    private Label cardMana;
    @FXML
    private Label cardEffect;

    @FXML
    void initialize() {
        assert cardImage != null : "fx:id=\"cardImage\" was not injected: check your FXML file 'HandCard.fxml'.";
        assert cardMana != null : "fx:id=\"cardMana\" was not injected: check your FXML file 'HandCard.fxml'.";
        assert cardEffect != null : "fx:id=\"cardEffect\" was not injected: check your FXML file 'HandCard.fxml'.";

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

    public HandCardController(BaseGameController baseGameController, Card card) {
        FXMLLoader handLoader = new FXMLLoader(AetherWars.class.getResource("gui/HandCard.fxml"));
        handLoader.setRoot(this);
        handLoader.setController(this);
        this.card = card;
        try {
            handLoader.load();
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
}