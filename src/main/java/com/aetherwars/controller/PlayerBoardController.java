package com.aetherwars.controller;

import com.aetherwars.AetherWars;
import com.aetherwars.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PlayerBoardController extends StackPane {
    @FXML private ProgressBar playerHealth;
    @FXML private Label playerName;
    @FXML private GridPane playerBoard;

    public PlayerBoardController(BaseGameController baseGameController) {

    }
}
