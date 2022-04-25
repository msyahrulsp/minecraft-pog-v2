package com.aetherwars.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.aetherwars.model.*;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class BaseGameController {

    private DeckController deckController;
    private PlayerBoardController[] playerBoardController;
    private PhaseController phaseController;

    /**
     * player one used left board
     * player two used right board
     */
    private Player playerOne;
    private Player playerTwo;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane deckSlot;

    @FXML
    private StackPane leftBoardSlot;

    @FXML
    private StackPane rightBoardSLot;

    @FXML
    void initialize() {

        Deck deckPlayerOne = new Deck();
        Deck deckPlayerTwo = new Deck();

        Hand handPlayerOne = new Hand();
        Hand handPlayerTwo = new Hand();

        CardHolder addCard = new CardHolder();

        Board boardPlayerOne = new Board();
        Board boardPlayerTwo = new Board();

        Player playerOne = new Player("Player One", 80, 1, deckPlayerOne, handPlayerOne, addCard, boardPlayerOne);
        Player playerTwo = new Player("Player Two", 80, 1, deckPlayerTwo, handPlayerTwo, addCard, boardPlayerTwo);

        this.setDeckInterface();
    }
    @FXML
    public void setDeckInterface() {
        this.deckSlot.getChildren().clear();
        this.deckController = new DeckController(this);
        this.deckSlot.getChildren().add(this.deckController);

    }
}
