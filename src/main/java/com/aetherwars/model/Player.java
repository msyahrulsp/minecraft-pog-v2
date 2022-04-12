package com.aetherwars.model;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import com.aetherwars.util.CSVReader;

public class Player<T> {
    private static final String CHARACTER_CSV_FILE_PATH = "../card/data/character.csv";
    private String name;
    private Integer health;
    private Integer mana;
    private ArrayList<Card> deck;
    private ArrayList<T> board;
    private ArrayList<T> hand;

    public Player() {
        this.name = "";
        this.health = 80;
        this.mana = 0;
        this.deck = new ArrayList<Card>();
    }

    public Player(String name, Integer health, Integer mana, ArrayList<Card> card) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.deck = card;
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

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setMana(Integer mana) {
        this.mana = mana;
    }

    public void showDeck() {
        System.out.print("[");
        for(int i=0; i<this.deck.size(); i++) {
            if (i+1 != this.deck.size()) {
                System.out.print(deck.get(i).getName() + ",");
            } else {
                System.out.println(deck.get(i).getName() + "]");
            }
        }
    }
    
    @Override
    public String toString() {
        System.out.println("Player " + this.getName() + " deck list: ");
        this.showDeck();
        return "\nName: " + this.getName() + "\nHealth: " + this.getHealth() + "\nMana: " + this.getMana();
    }
    
    public <T> void getCard() {};
    public void getCardInfo(T card) {};
    public void attack() {};
    public void nextPhase() {};

    public void putCardToDeck(String[] row) {
        Type type;
        if (row[2] == "OVERWORLD") {
            type = Type.OVERWORLD;
        } else if (row[2] == "NETHER") {
            type = Type.NETHER;
        } else {
            type = Type.END;
        }
        Character temp = new Character(Integer.parseInt(row[0]), row[1], row[3], Integer.parseInt(row[7]), row[4], type, Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[8]), Integer.parseInt(row[9]));
        this.deck.add(temp);
    };
    
    public void loadDeck(List<String[]> deck) {
        for(int i=0; i<17; i++) {
            putCardToDeck(deck.get(i));
        }
    }

    public List<String[]> loadCards() throws IOException, URISyntaxException {
        File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
        CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
        characterReader.setSkipHeader(true);
        List<String[]> characterRows = characterReader.read();
        return characterRows;
    }

    public static void main(String[] args) {
        ArrayList<Character> newDeck = new ArrayList<>();
        Player<Card> playerOne = new Player("kevin", 80, 1, newDeck);
        try {
            playerOne.loadDeck(playerOne.loadCards());
        } catch (Exception err) {
            System.out.println("Load file error");
        }
        System.out.println(playerOne.toString());
    }
}