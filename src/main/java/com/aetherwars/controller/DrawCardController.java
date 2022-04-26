package com.aetherwars.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.aetherwars.AetherWars;
import com.aetherwars.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

public class DrawCardController extends VBox {
    private BaseGameController controller;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private HBox drawCard;
    @FXML
    private Button submitBtn;

    public DrawCardController(BaseGameController controller) {
        FXMLLoader drawCardLoader = new FXMLLoader(AetherWars.class.getResource("gui/DrawCard.fxml"));
        drawCardLoader.setRoot(this);
        drawCardLoader.setController(this);
        try {
            drawCardLoader.load();
            this.controller = controller;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.displayDrawCard();
    }

    public void displayDrawCard() {
        this.getChildren().clear();

        System.out.println(this.controller.getPlayer().getAddCard().getCards().size());
        this.controller.getPlayer().getCardToHand("add");
        System.out.println(this.controller.getPlayer().getAddCard().getCards().size());
        for (Card card : this.controller.getPlayer().getAddCard().getCards()) {
            drawCard.getChildren().add(new DrawController(this.controller, card));
            // DrawController drawCard = new DrawController(this.controller, card);
            // System.out.println(card.getName());
            // this.getChildren().add(drawCard);
        }
        this.getChildren().add(drawCard);
        this.submitBtn.setText("Choose");
        this.submitBtn.setOnAction(e -> {
            this.submitDraw();
        });
        this.getChildren().add(submitBtn);
    }

    public void submitDraw() {
        System.out.println(this.controller.getIndex());
        this.controller.getPlayer().addCardToHand(this.controller.getIndex());
        System.out.println(this.controller.getPlayer().getHand().getCards().size());
        this.getChildren().clear();
    }
}