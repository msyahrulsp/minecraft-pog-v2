package com.aetherwars.controller;

import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BaseGameController {
    private DeckController deckController;
    private DrawCardController drawController;
    private PlayerBoardController playerOneController;
    private PlayerBoardController playerTwoController;
    private PhaseController phaseController;
    private HandCardController handCardController;
    private BoardCardController boardCardController;

    private Integer clickedBoardCard;

    /**
     * player one used left board
     * player two used right board
     */
    private Player playerOne;
    private Player playerTwo;

    private Player activePlayer;

    private int index;
    private int rounds;
    private int manaCap;
    private boolean isDrawing = false;
    private Phase currentPhase;

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
    @FXML
    private Button nxtPhaseBtn;
    @FXML
    private Label roundText;
    @FXML
    private Label drawPanel;
    @FXML
    private Label planPanel;
    @FXML
    private Label attackPanel;
    @FXML
    private Label endPanel;

    /**
     * Initialize all components when main program is started.
     */
    @FXML
    public void initialize() {
        Deck deckPlayerOne = new Deck();
        Deck deckPlayerTwo = new Deck();

        Hand handPlayerOne = new Hand();
        Hand handPlayerTwo = new Hand();

        CardHolder addCardOne = new CardHolder();
        CardHolder addCardTwo = new CardHolder();

        Board boardPlayerOne = new Board();
        Board boardPlayerTwo = new Board();

        this.playerOne = new Player("Player One", 80, 1, deckPlayerOne, handPlayerOne, addCardOne, boardPlayerOne);
        this.playerTwo = new Player("Player Two", 80, 1, deckPlayerTwo, handPlayerTwo, addCardTwo, boardPlayerTwo);

        this.playerOneController = new PlayerBoardController(this, this.playerOne);
        this.playerTwoController = new PlayerBoardController(this, this.playerTwo);

        this.index = -1;
        this.rounds = 1;
        this.manaCap = 1;
        this.currentPhase = Phase.DRAW;

        try {
            this.playerOne.getDeck().loadDeck();
            this.playerTwo.getDeck().loadDeck();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        this.activePlayer = this.playerOne;
        this.setDeckInterface(this.activePlayer, true);
        //TODO setDrawInterface masih throw null pointer
        this.setPlayerInterface();
        // this.gameState();

        this.drawSec.setOnAction(e -> {
            this.setDrawInterface();
        });

        this.nxtPhaseBtn.setOnAction(e -> {
            this.nextPhase();
        });
    }

    /**
     * Set deck interface such as initialize player deck,
     * get card from player deck to hand,
     * and display the cards on player's hand slol.
     * @param activePlayer
     */
    @FXML
    public void setDeckInterface(Player activePlayer, boolean First) {
        this.roundText.setText(Integer.toString(this.rounds));
        this.deckSlot.getChildren().clear();
        this.deckController = new DeckController(this, activePlayer, First);
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
        if (this.currentPhase == Phase.DRAW) {
            if (!this.isDrawing && this.activePlayer.getHand().getCards().size() < 5) {
                if (this.activePlayer.getDeck().getSize() != 0 || this.activePlayer.getAddCard().getSize() != 0) {
                    this.isDrawing = true;
                    this.drawSlot.getChildren().clear();
                    this.drawController = new DrawCardController(this);
                    this.drawSlot.getChildren().add(this.drawController);
                } else {
                    AlertBox.display("No more card to draw");
                }

            } else if (this.activePlayer.getHand().getCards().size() >= 5) {
                AlertBox.display("Hand can't be more than 5 cards");
            }
        } else {
            AlertBox.display("Can't draw card in this phase");
        }
    }

    public Player getPlayer() {
        return this.playerOne;
    }

    public int getRounds() {
        return this.rounds;
    }

    public int getManaCap() {
        return this.manaCap;
    }

    public Phase getCurrentPhase() {
        return this.currentPhase;
    }

    public void nextPhase() {
        if (isDrawing) {
            AlertBox.display("Please finish drawing card");
        } else {
            if (this.currentPhase == Phase.DRAW) {
                this.currentPhase = Phase.PLAN;
                this.drawPanel.setStyle("-fx-border-color: #000;");
                this.planPanel.setStyle("-fx-border-color: #000; -fx-background-color: #75fc92");
            } else if (this.currentPhase == Phase.PLAN) {
                this.currentPhase = Phase.ATTACK;
                this.planPanel.setStyle("-fx-border-color: #000;");
                this.attackPanel.setStyle("-fx-border-color: #000; -fx-background-color: #75fc92");
            } else if (this.currentPhase == Phase.ATTACK) {
                this.currentPhase = Phase.END;
                this.attackPanel.setStyle("-fx-border-color: #000;");
                this.endPanel.setStyle("-fx-border-color: #000; -fx-background-color: #75fc92");
            } else if (this.currentPhase == Phase.END) {
                this.endPanel.setStyle("-fx-border-color: #000;");
                this.drawPanel.setStyle("-fx-border-color: #000; -fx-background-color: #75fc92");


                if (this.activePlayer.getName().equals("Player One")) {
                    this.activePlayer = this.playerTwo;
                    if (this.rounds == 1) {
                        this.setDeckInterface(this.activePlayer, true);
                    } else {
                        this.setDeckInterface(this.activePlayer, false);
                    }
                } else {
                    this.activePlayer = this.playerOne;
                    this.rounds += 1;
                    this.manaCap += 1;

                    if (this.manaCap > 10) {
                        this.manaCap = 10;
                    }

                    this.playerOne.setMana(this.manaCap);
                    this.playerTwo.setMana(this.manaCap);
                    this.setDeckInterface(this.activePlayer, false);
                }
                for (Card c : this.playerOne.getBoard().getCards()){
                    if (c instanceof Character) {
                        ((Character) c).decreaseBuff();
                    }
                }
                for (Card c : this.playerTwo.getBoard().getCards()){
                    if (c instanceof Character) {
                        ((Character) c).decreaseBuff();
                    }
                }
                this.currentPhase = Phase.DRAW;
            }
            winCondition();
        }
    }

    public int getIndex() { return this.index; }

    public void setIndex(int idx) { this.index = idx; }

    public void setIsDrawing(boolean isDrawing) { this.isDrawing = isDrawing; }

    public boolean getIsDrawing() { return this.isDrawing; }

    public void winCondition() {
        boolean win = false;
        if (this.playerOne.getHealth() <= 0) {
            AlertBox.display("Player Two Win");
            win = true;
        } else if (this.playerTwo.getHealth() <= 0) {
            AlertBox.display("Player One Win");
            win = true;
        } else if (this.playerOne.getDeck().getSize() == 0 && this.playerOne.getAddCard().getSize() == 0) {
            AlertBox.display("Player Two Win");
            win = true;
        } else if (this.playerTwo.getDeck().getSize() == 0 && this.playerTwo.getAddCard().getSize() == 0) {
            AlertBox.display("Player One Win");
            win = true;
        }

        if (win) {
            System.exit(0);
        }
    }

    public void setClickedBoardCard(Integer idx) {
        this.clickedBoardCard = idx;
    }

    public Integer getClickedBoardCard() {
        return this.clickedBoardCard;
    }
}
