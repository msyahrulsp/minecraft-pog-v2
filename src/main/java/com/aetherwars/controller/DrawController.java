package com.aetherwars.controller;

import com.aetherwars.AetherWars;
import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import com.aetherwars.model.Spell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class DrawController extends VBox {
    @FXML private ImageView imgDraw;
    @FXML private Label manaDraw;
    @FXML private Label effectDraw;
    @FXML private Button draw;

    private BaseGameController controller;
    private Card card;

    public DrawController(BaseGameController controller, Card card) {
        FXMLLoader drawLoader = new FXMLLoader(AetherWars.class.getResource("gui/Draw.fxml"));
        drawLoader.setRoot(this);
        drawLoader.setController(this);
        this.card = card;
        try {
            drawLoader.load();
            this.controller = controller;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.initDrawCard();
    }

    public void initDrawCard() {
        Image imageDraw = new Image(AetherWars.class.getResource(this.card.getImageLoc()).toString());
        this.imgDraw.setImage(imageDraw);

        this.manaDraw.setText("MANA " + Integer.toString(card.getMana()));
        if (this.card instanceof Character) {
            this.effectDraw.setText("ATK " + Integer.toString(((Character) card).getAttack()) + "/HP " + Integer.toString(((Character) card).getHealth()));
        } else if (card instanceof Spell) {
            if (((Spell) card).getType().equals(spellType.PTN)) {
                this.effectDraw.setText("ATK " + Integer.toString(((Potion) card).getAttackBuff()) + "/HP " + Integer.toString(((Potion) card).getHealthBuff()));
            } else if (card instanceof Swap) {
                this.effectDraw.setText("SWAP");
            } else if (card instanceof Level) {
                this.effectDraw.setText("LEVEL");
            } else if (this.card instanceof Morph) {
                this.effectDraw.setText("MORPH");
            }
        }
        this.draw.setText("Select");
        this.draw.setOnAction(e -> {
            this.selectCard();
        });
        System.out.println(this.controller.getIndex());
    }

     public void selectCard() {
//         this.controller.getPlayer().addCardToHand(((DrawCardController)this.getParent()).getChildren().indexOf(this));
//         System.out.println(((DrawCardController)this.getParent()).getChildren().indexOf(this));
//         System.out.println(this.controller.getPlayer().getHand().getCards().size());
        int idx = ((HBox)this.getParent()).getChildren().indexOf(this);
        this.controller.setIndex(idx);
        System.out.println(this.controller.getIndex());
     }
}
