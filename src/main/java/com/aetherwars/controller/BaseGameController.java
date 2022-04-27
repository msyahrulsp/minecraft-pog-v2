package com.aetherwars.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.aetherwars.AetherWars;
import com.aetherwars.model.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.event.*;

public class BaseGameController {

    private DeckController deckController;
    private DrawCardController drawController;
    private PlayerBoardController playerOneController;
    private PlayerBoardController playerTwoController;
    private PhaseController phaseController;
    private HandCardController handCardController;
    private BoardCardController boardCardController;

    /**
     * player one used left board
     * player two used right board
     */
    private Player playerOne;
    private Player playerTwo;

    private Player activePlayer;
    private Player idlePlayer;

    private int index;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private StackPane deckSlot;
    @FXML
    private StackPane leftBoardSlot;
    @FXML
    private StackPane rightBoardSlot;
    @FXML
    private StackPane drawSlot;

    // @FXML
    public void initialize() {
        Deck deckPlayerOne = new Deck();
        Deck deckPlayerTwo = new Deck();

        Hand handPlayerOne = new Hand();
        Hand handPlayerTwo = new Hand();

        CardHolder addCard = new CardHolder();

        Board boardPlayerOne = new Board();
        Board boardPlayerTwo = new Board();

        this.playerOne = new Player("Player One", 80, 1, deckPlayerOne, handPlayerOne, addCard, boardPlayerOne);
        this.playerTwo = new Player("Player Two", 80, 1, deckPlayerTwo, handPlayerTwo, addCard, boardPlayerTwo);

        this.playerOneController = new PlayerBoardController(this, this.playerOne);
        this.playerTwoController = new PlayerBoardController(this, this.playerTwo);

        this.index = -1;

        try {
            this.playerOne.getDeck().loadDeck();
            this.playerTwo.getDeck().loadDeck();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        this.activePlayer = this.playerOne;
        this.setDeckInterface(this.activePlayer);
        //TODO setDrawInterface masih throw null pointer
//        this.setDrawInterface();
        this.setPlayerInterface();
        this.gameState();
    }
    @FXML
    public void setDeckInterface(Player activePlayer) {
        this.deckSlot.getChildren().clear();
        this.deckController = new DeckController(this, activePlayer);
        this.deckSlot.getChildren().add(this.deckController);
    }

    public DeckController getDeckController() {
        return this.deckController;
    }

    public PlayerBoardController getPlayerOneController() {
        return this.playerOneController;
    }

    public PlayerBoardController getPlayerTwoController() {
        return this.playerTwoController;
    }

    public Player getActivePlayer() {
        return this.activePlayer;
    }

    @FXML
    public void setPlayerInterface() {
        this.leftBoardSlot.getChildren().add(this.playerOneController);
        this.rightBoardSlot.getChildren().add(this.playerTwoController);
    }

    @FXML
    public void setDrawInterface() {
        // VBox draw = new VBox();
        // draw.setAlignment(Pos.CENTER);

        // Button button = new Button("Draw");
        // this.leftBoardSlot.getChildren().add(button);

        // this.drawController = new DrawController(this, playerOne);
        // draw.getChildren().add(this.drawController);
        // this.leftBoardSlot.getChildren().add(draw);
        this.drawSlot.getChildren().clear();
        this.drawController = new DrawCardController(this);
        this.drawSlot.getChildren().add(this.drawController);
    }

    public Player getPlayer() {
        return this.playerOne;
    }

    public Phase nextPhase(Player activePlayer) {

        if (this.activePlayer.getPhase() == Phase.DRAW) {
            return Phase.PLAN;
        } else if (this.activePlayer.getPhase() == Phase.PLAN) {
            return Phase.ATTACK;
        } else if (this.activePlayer.getPhase() == Phase.ATTACK) {
            return Phase.END;
        } else if (this.activePlayer.getPhase() == Phase.END) {
//            if (i == 1) {
//                rounds++;
//                capMana++;
//                if (capMana > 10) {
//                    capMana = 10;
//                }
//                this.player[i].setMana(capMana);
//            }
            return Phase.DRAW;
        }
        return null;
    }

    public void gameState() {
        // misal draw udah jalan, sekarang lanjut ke phase plan
        this.activePlayer.setPhase(this.nextPhase(this.activePlayer));

    }

    public int getIndex() { return this.index; }

    public void setIndex(int idx) { this.index = idx; }
}
