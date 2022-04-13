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
    
    public List<String[]> loadCharCards() throws IOException, URISyntaxException {
        File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
        CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
        characterReader.setSkipHeader(true);
        List<String[]> characterCard = characterReader.read();
        return characterCard;
    }

    public List<String[]> loadMorCards() throws IOException, URISyntaxException {
        File morphCSVFile = new File(getClass().getResource(MORPH_CSV_FILE_PATH).toURI());
        CSVReader morphReader = new CSVReader(morphCSVFile, "\t");
        morphReader.setSkipHeader(true);
        List<String[]> morphCard = morphReader.read();
        return morphCard;
    }

    public List<String[]> loadPtnCard() throws IOException, URISyntaxException {
        File ptnCSVFile = new File(getClass().getResource(PTN_CSV_FILE_PATH).toURI());
        CSVReader ptnReader = new CSVReader(ptnCSVFile, "\t");
        ptnReader.setSkipHeader(true);
        List<String[]> ptnCard = ptnReader.read();
        return ptnCard;
    }

    public List<String[]> loadSwapCard() throws IOException, URISyntaxException {
        File swapCSVFile = new File(getClass().getResource(SWAP_CSV_FILE_PATH).toURI());
        CSVReader swapReader = new CSVReader(swapCSVFile, "\t");
        swapReader.setSkipHeader(true);
        List<String[]> swapCard = swapReader.read();
        return swapCard;
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

    public void putMorCardToDeck(String[] row) {
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
        // for(int i=0; i<18; i++) {
        //     putCharCardToDeck(deck.get(i));
        // }
        // for(int i=0; i<6; i++) {
        //     putMorCardToDeck(deck2.get(i));
        // }
        // for(int i=0; i<18; i++) {
        //     putPtnCardToDeck(deck3.get(i));
        // }
        // for(int i=0; i<10; i++) {
        //     putSwapCardToDeck(deck4.get(i));
        // }
        // gacha loadDeck?? tapi bener2 random sih ini
        int countCard = 0;
        Random rand = new Random();
        while (countCard < 60) {
            int random = rand.nextInt(4);
            if (random == 0) {
                putCharCardToDeck(deck.get(rand.nextInt(18)));
            } else if (random == 1) {
                putMorCardToDeck(deck2.get(rand.nextInt(6)));
            } else if (random == 2) {
                putPtnCardToDeck(deck3.get(rand.nextInt(18)));
            } else {
                putSwapCardToDeck(deck4.get(rand.nextInt(10)));
            }
            countCard++;
        }
    }

    public void getTopThreeCard() {
        for(int i=0; i<3; i++) {
            this.hand.add(this.deck.get(i));
        }
        for(int i=0; i<3; i++) {
            this.deck.remove(i);
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
            playerOne.loadDeck(playerOne.loadCharCards(), playerOne.loadMorCards(), playerOne.loadPtnCard(), playerOne.loadSwapCard());
        } catch (Exception err) {
            System.out.println("Load file error");
        }
        System.out.println(playerOne.deck.size());
        System.out.println(playerOne.toString());
        playerOne.getTopThreeCard();
        System.out.println(playerOne.toString());
        // try {
        //     System.out.println(playerOne.loadCards().get(0)[2]);
        // } catch (Exception err) {
        //     System.out.println("Load file error");
        // }
    }
}