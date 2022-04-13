package com.aetherwars.model;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import com.aetherwars.util.CSVReader;

public class Player<T> {
    private static final String CHARACTER_CSV_FILE_PATH = "../card/data/character.csv";
    private static final String MORPH_CSV_FILE_PATH = "../card/data/spell_morph.csv";
    private static final String PTN_CSV_FILE_PATH = "../card/data/spell_ptn.csv";
    private static final String SWAP_CSV_FILE_PATH = "../card/data/spell_swap.csv";
    private String name;
    private Integer health;
    private Integer mana;
    private ArrayList<Card> deck;
    private ArrayList<T> board;
    private ArrayList<Card> hand;
    private ArrayList<Card> addCard;

    public Player() {
        this.name = "";
        this.health = 80;
        this.mana = 0;
        this.deck = new ArrayList<Card>();
    }

    public Player(String name, Integer health, Integer mana) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.deck = new ArrayList<Card>();
        this.hand = new ArrayList<Card>();
        this.addCard = new ArrayList<Card>();
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
        System.out.println("Player " + this.getName() + " deck list: ");
        System.out.print("[");
        for(int i=0; i<this.deck.size(); i++) {
            if (i+1 != this.deck.size()) {
                System.out.print(deck.get(i).id + ":" + deck.get(i).getName() + ", ");
            } else {
                System.out.println(deck.get(i).id + ":" + deck.get(i).getName() + "]");
            }
        }
    }

    public void showHand() {
        System.out.println("Player " + this.getName() + " hand list: ");
        if (!this.hand.isEmpty()) {
            for(int i=0; i<this.hand.size(); i++) {
                if (i+1 != this.hand.size()) {
                    System.out.print(hand.get(i).id + ":" + hand.get(i).getName() + ", ");
                } else {
                    System.out.println(hand.get(i).id + ":" + hand.get(i).getName() + "]");
                }
            }
        } else {
            System.out.println("Player hand is empty");
        }
    }
    
    @Override
    public String toString() {
        this.showDeck();
        this.showHand();
        return "\nName: " + this.getName() + "\nHealth: " + this.getHealth() + "\nMana: " + this.getMana();
    }

    public List<String[]> loadCards(String path) throws IOException, URISyntaxException {
        File CSVFile = new File(getClass().getResource(path).toURI());
        CSVReader Reader = new CSVReader(CSVFile, "\t");
        Reader.setSkipHeader(true);
        List<String[]> Card = Reader.read();
        return Card;
    }

    public void putCharCardToDeck(String[] row) {
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
    }

    public void putMorphCardToDeck(String[] row) {
        Morph temp = new Morph(Integer.parseInt(row[0]), row[1], row[2], Integer.parseInt(row[5]), row[3], Integer.parseInt(row[4]));
        this.deck.add(temp);
    }

    public void putPtnCardToDeck(String[] row) {
        Potion temp = new Potion(Integer.parseInt(row[0]), row[1], row[2], Integer.parseInt(row[6]), row[3], Integer.parseInt(row[7]), Integer.parseInt(row[4]), Integer.parseInt(row[5]));
        this.deck.add(temp);
    }

    public void putSwapCardToDeck(String[] row) {
        Swap temp = new Swap(Integer.parseInt(row[0]), row[1], row[2], Integer.parseInt(row[5]), row[3], Integer.parseInt(row[4]));
        this.deck.add(temp);
    }
    
    public void loadDeck(List<String[]> deck, List<String[]> deck2, List<String[]> deck3, List<String[]> deck4) {
        // gacha loadDeck?? tapi bener2 random sih ini
        int countCard = 0;
        Random rand = new Random();
        while (countCard < 60) {
            int random = rand.nextInt(11);
            if (random == 0 || random == 5 || random == 6) {
                putCharCardToDeck(deck.get(rand.nextInt(18)));
            } else if (random == 1 || random == 7 || random == 8) {
                putMorphCardToDeck(deck2.get(rand.nextInt(6)));
            } else if (random == 2 || random == 9 || random == 10) {
                putPtnCardToDeck(deck3.get(rand.nextInt(18)));
            } else {
                putSwapCardToDeck(deck4.get(rand.nextInt(10)));
            }
            countCard++;
        }
    }

    public void getCardToHand(String phase) {
        if (phase == "add") {
            for (int i = 0; i < 3; i++) {
                this.addCard.add(this.deck.get(i));
            }
        } else {
            for(int i=0; i<3; i++) {
                this.hand.add(this.deck.get(i));
            }
        }
        for(int i=0; i<3; i++) {
            this.deck.remove(i);
        }
    }
    
    public void returnCardToDeck() {
        Random rand = new Random();
        while (!this.addCard.isEmpty()) {
            this.deck.add(rand.nextInt(this.deck.size()), this.addCard.get(0));
            this.addCard.remove(0);
        }
    }

    public void throwCardFromHand(int choose) {
        this.hand.remove(choose);
    }

    public void addCardToHand(int choose) {
        if (this.hand.size() != 5) {
            this.getCardToHand("add");
            this.hand.add(this.addCard.get(choose));
            this.addCard.remove(choose);
            this.returnCardToDeck();
        } else {
            System.out.println("Hand is full");
        }
    }
    
    public <T> void getCard() {};
    public void getCardInfo(T card) {};
    public void attack() {};
    public void nextPhase() {};

    // for testing
    public static void main(String[] args) {
        Player<Card> playerOne = new Player("kevin", 80, 1);
        try {
            playerOne.loadDeck(playerOne.loadCards(CHARACTER_CSV_FILE_PATH), playerOne.loadCards(MORPH_CSV_FILE_PATH), playerOne.loadCards(PTN_CSV_FILE_PATH), playerOne.loadCards(SWAP_CSV_FILE_PATH));
        } catch (Exception err) {
            System.out.println("Load file error");
        }
        System.out.println(playerOne.deck.size());
        System.out.println(playerOne.toString());
        playerOne.getCardToHand("first");
        System.out.println(playerOne.toString());
        playerOne.hand.remove(1);
        System.out.println(playerOne.toString());
        playerOne.addCardToHand(1);
        playerOne.addCardToHand(2);
        playerOne.addCardToHand(0);
        playerOne.addCardToHand(0);
        System.out.println(playerOne.toString());
        playerOne.throwCardFromHand(1);
        System.out.println(playerOne.toString());
        // try {
        //     System.out.println(playerOne.loadCards().get(0)[2]);
        // } catch (Exception err) {
        //     System.out.println("Load file error");
        // }
    }
}