package com.aetherwars.controller;

import com.aetherwars.model.*;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class BaseGameController  {

    @FXML private StackPane handSlot;
    @FXML private StackPane leftBoardSlot;
    @FXML private StackPane rightBoardSlot;
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
        Deck deckPlayerOne = new Deck();
        Deck deckPlayerTwo = new Deck();

        Hand handPlayerOne = new Hand();
        Hand handPlayerTwo = new Hand();

        CardHolder addCard = new CardHolder();

        Board boardPlayerOne = new Board();
        Board boardPlayerTwo = new Board();

        Player playerOne = new Player("Player One", 80, 1, deckPlayerOne, handPlayerOne, addCard, boardPlayerOne);
        Player playerTwo = new Player("Player Two", 80, 1, deckPlayerTwo, handPlayerTwo, addCard, boardPlayerTwo);
        System.out.println("base game initialized");
    }

//    @FXML
//    public void

    @FXML
    public void setHandController() {
        this.handSlot.getChildren().clear();
        this.handController = new HandController(this);
        this.handSlot.getChildren().add(this.handController);
    }

}
