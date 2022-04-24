package com.aetherwars.controller;

import com.aetherwars.controller.DeckController;
import com.aetherwars.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

public class BaseGameController  {

    @FXML private StackPane handCard;
    @FXML private StackPane boardLeft;
    @FXML private StackPane boardRight;
    @FXML private StackPane deck;

    private DeckController deckController;
    private HandController handController;
    private LeftBoardController leftBoardController;
    private RightBoardController rightBoardController;
    private PhaseController phaseController;

    /**
     * player one used left board
     * player two used right board
     */
    private Player playerOne;
    private Player playerTwo;

    public void initialize() {
        this.playerOne = new Player();
        this.playerTwo = new Player();
        System.out.println("base game initialized");
    }

}
