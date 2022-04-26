package com.aetherwars.model;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import com.aetherwars.util.CSVReader;

public class Player {
    private String name;
    private Integer health;
    private Integer mana;
    private Deck deck;
    private Board board;
    private Hand hand;
    private CardHolder addCard;
    private Phase phase;

    public Player() {
        this.name = "";
        this.health = 80;
        this.mana = 0;
    }

    public Player(String name, Integer health, Integer mana, Deck deck, Hand hand, CardHolder addCard, Board board) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.deck = deck;
        this.board = board;
        this.hand = hand;
        this.addCard = addCard;
        this.phase = Phase.DRAW;
    }

    public String getName() {
        return this.name;
    }

    public Integer getHealth() {
        return this.health;
    }

    public Integer getMana() {
        return this.mana;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Hand getHand() {
        return this.hand;
    }

    public CardHolder getAddCard() {
        return this.addCard;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setMana(Integer mana) {
        this.mana = mana;
    }

    public Phase getPhase() {
        return this.phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
    
    @Override
    public String toString() {
        this.deck.showDeck();
        this.hand.showHand();
        this.board.showBoard();
        return "\nName: " + this.getName() + "\nHealth: " + this.getHealth() + "\nMana: " + this.getMana();
    }

    public void getCardToHand(String phase) {
        if (phase == "add") {
            for (int i = 0; i < 3; i++) {
                this.addCard.addElmt(this.deck.getElmt(i));
            }
        } else {
            for(int i=0; i<3; i++) {
                this.hand.addElmt(this.deck.getElmt(i));
            }
        }
        for (int i=0; i<3; i++) {
            this.deck.delElmt(0);
        }
    }

    public void throwCardFromHand(int choose) {
        this.hand.delElmt(choose);
    }
    
    public void returnCardToDeck() {
        Random rand = new Random();
        while (!this.addCard.isEmpty()) {
            // System.out.println("out");
            this.deck.addElmtAtIdx(rand.nextInt(this.deck.getSize()), this.addCard.getElmt(0));
            this.addCard.delElmt(0);
        }
    }

    public void addCardToHand(int choose) {
        if (this.hand.getSize() < 5) {
            this.hand.addElmt(this.addCard.getElmt(choose));
            this.addCard.delElmt(choose);
            this.returnCardToDeck();
        } else {
            System.out.println("Hand is full");
        }
    }

    public void addCardToBoard(int choose) {
        if (this.board.getSize() < 5) {
            this.board.addElmt(this.hand.getElmt(choose));
            this.hand.delElmt(choose);
        } else {
            System.out.println("Board is full");
        }
    }

    public void removeCardFromBoard(int choose) {
        this.board.delElmt(choose);
    }

    public void getCardInfo(Card card) {
        System.out.println(card);
    }

    // for testing
    public static void main(String[] args) {
        Deck deckPlayerOne = new Deck();
        Hand handPlayerOne = new Hand();
        CardHolder addCardPlayerOne = new CardHolder();
        Board boardPlayerOne = new Board();
        Player playerOne = new Player("kevin", 80, 1, deckPlayerOne, handPlayerOne, addCardPlayerOne, boardPlayerOne);
        try {
            playerOne.deck.loadDeck();
        } catch (Exception err) {
            System.out.println("Load file error");
        }
        System.out.println(playerOne.deck.getSize());
        System.out.println(playerOne.toString());
        playerOne.getCardToHand("first");
        System.out.println(playerOne.toString());
        playerOne.hand.elmt.remove(1);
        System.out.println(playerOne.toString());
        playerOne.getCardToHand("add");
        playerOne.addCardToHand(1);
        // playerOne.addCardToHand(2);
        // playerOne.addCardToHand(0);
        // playerOne.addCardToHand(0);
        // System.out.println(playerOne.toString());
        playerOne.throwCardFromHand(1);
        System.out.println(playerOne.toString());
        playerOne.addCardToBoard(0);
        playerOne.addCardToBoard(0);
        System.out.println(playerOne.toString());
        // playerOne.addCardToBoard(0);
        playerOne.getCardInfo(playerOne.board.getElmt(0));
        playerOne.getCardInfo(playerOne.board.getElmt(1));
        // playerOne.getCardInfo(playerOne.board.getElmt(2));
        // try {
        //     System.out.println(playerOne.loadCards().get(0)[2]);
        // } catch (Exception err) {
        //     System.out.println("Load file error");
        // }
    }
}