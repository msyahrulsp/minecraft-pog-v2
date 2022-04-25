package com.aetherwars.controller;

import javafx.fxml.FXMLLoader;
import com.aetherwars.AetherWars;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;


public class HandController extends StackPane {

    private BaseGameController baseGameController;

    public HandController(BaseGameController baseGameController) {
        FXMLLoader handLoader = new FXMLLoader(AetherWars.class.getResource("gui/Hand.fxml"));
        handLoader.setRoot(this);
        handLoader.setController(this);

        try {
            handLoader.load();
            this.baseGameController = baseGameController;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
