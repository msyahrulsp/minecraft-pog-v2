package com.aetherwars.model;
import com.aetherwars.util.CSVReader;
import java.util.*;
import java.io.IOException;
import java.io.File;
import java.net.URISyntaxException;


public class Deck extends CardHolder {
    private static final String CHARACTER_CSV_FILE_PATH = "../card/data/character.csv";
    private static final String MORPH_CSV_FILE_PATH = "../card/data/spell_morph.csv";
    private static final String PTN_CSV_FILE_PATH = "../card/data/spell_ptn.csv";
    private static final String SWAP_CSV_FILE_PATH = "../card/data/spell_swap.csv";
    
    public Deck() {
        super();
    }

    public List<String[]> loadCards(String path) throws IOException, URISyntaxException {
      File CSVFile = new File(getClass().getResource(path).toURI());
      CSVReader Reader = new CSVReader(CSVFile, "\t");
      Reader.setSkipHeader(true);
      List<String[]> Card = Reader.read();
      return Card;
    }

    public void loadDeck() throws IOException, URISyntaxException {
        List<String[]> deck = this.loadCards(CHARACTER_CSV_FILE_PATH);
        List<String[]> deck2 = this.loadCards(MORPH_CSV_FILE_PATH);
        List<String[]> deck3 = this.loadCards(PTN_CSV_FILE_PATH);
        List<String[]> deck4 = this.loadCards(SWAP_CSV_FILE_PATH);
        int countCard = 0;
        Random rand = new Random();
        DeckFactory factory = new DeckFactory();
        while (countCard < 60) {
          int random = rand.nextInt(11);
          if (random == 0 || random == 5 || random == 6) {
            ToDeck todeck = factory.getDeck("Character");
            this.elmt.add(todeck.putCard(deck.get(rand.nextInt(18))));
          } else if (random == 1 || random == 7 || random == 8) {
            ToDeck todeck = factory.getDeck("Morph");
            this.elmt.add(todeck.putCard(deck2.get(rand.nextInt(6))));
          } else if (random == 2 || random == 9 || random == 10) {
            ToDeck todeck = factory.getDeck("Potion");
            this.elmt.add(todeck.putCard(deck3.get(rand.nextInt(18))));
          } else {
            ToDeck todeck = factory.getDeck("Swap");
            this.elmt.add(todeck.putCard(deck4.get(rand.nextInt(10))));
          }
          countCard++;
        }
    }

    public void showDeck() {
      System.out.println("Deck : ");
      System.out.printf("[");
      for (int i = 0; i < this.elmt.size(); i++) {
        if (i + 1 != this.elmt.size()) {
          System.out.print(elmt.get(i).id + ":" + elmt.get(i).name + ", ");
        } else {
          System.out.println(elmt.get(i).id + ":" + elmt.get(i).name + "]");
        }
      }
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        try {
            deck.loadDeck();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        deck.showDeck();
    }
}
