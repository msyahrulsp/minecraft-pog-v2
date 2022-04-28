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

        System.out.println(this.controller.getActivePlayer().getAddCard().getCards().size());
        this.controller.getActivePlayer().getCardToHand("add");
        System.out.println(this.controller.getActivePlayer().getAddCard().getCards().size());
        for (Card card : this.controller.getActivePlayer().getAddCard().getCards()) {
            drawCard.getChildren().add(new DrawController(this.controller, card));
        }
        this.getChildren().add(drawCard);
    }
}