package com.aetherwars.controller;

import com.aetherwars.model.*;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;

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
    private boolean isDrawing = false;

    @FXML
    private StackPane deckSlot;
    @FXML
    private StackPane leftBoardSlot;
    @FXML
    private StackPane rightBoardSlot;
    @FXML
    private StackPane drawSlot;
    @FXML
    private Button drawSec;

    /**
     * Initialize all components when main program is started.
     */
     @FXML
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
        this.setPlayerInterface();
        this.gameState();
        this.drawSec.setOnAction(e -> {
            this.setDrawInterface();
        });
    }

    /**
     * Set deck interface such as initialize player deck,
     * get card from player deck to hand,
     * and display the cards on player's hand slol.
     * @param activePlayer
     */
    @FXML
    public void setDeckInterface(Player activePlayer) {
        this.deckSlot.getChildren().clear();
        this.deckController = new DeckController(this, activePlayer);
        this.deckSlot.getChildren().add(this.deckController);
    }

    /**
     * Getter for deck controller.
     * @return DeckController
     */
    public DeckController getDeckController() {
        return this.deckController;
    }

    /**
     * Getter for player one 'player board controller'.
     * @return PlayerBoardController
     */
    public PlayerBoardController getPlayerOneController() {
        return this.playerOneController;
    }

    /**
     * Getter for player two 'player board controller'.
     * @return PlayerBoardController
     */
    public PlayerBoardController getPlayerTwoController() {
        return this.playerTwoController;
    }

    /**
     * Getter for current active player.
     * @return Player
     */
    public Player getActivePlayer() {
        return this.activePlayer;
    }

    public PlayerBoardController getActivePlayerBoardController() {
        if (this.activePlayer.getName().equals("Player One")) {
            return this.playerOneController;
        } else {
            return this.playerTwoController;
        }
    }

    /**
     * Set player interface such as
     * display player one and player two image,
     * set player health bar and name.
     */
    @FXML
    public void setPlayerInterface() {
        this.leftBoardSlot.getChildren().add(this.playerOneController);
        this.rightBoardSlot.getChildren().add(this.playerTwoController);
    }

    @FXML
    public void setDrawInterface() {
        if (!this.isDrawing && this.activePlayer.getHand().getCards().size() < 5) {
            this.isDrawing = true;
            this.drawSlot.getChildren().clear();
            this.drawController = new DrawCardController(this);
            this.drawSlot.getChildren().add(this.drawController);

        }
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

    public void setIsDrawing(boolean isDrawing) { this.isDrawing = isDrawing; }
}
