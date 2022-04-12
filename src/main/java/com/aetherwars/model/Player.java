package com.aetherwars.model;
import java.util.*;

public class Player<T> {
    private String name;
    private Integer health;
    private Integer mana;
    private ArrayList<T> deck;

    public Player() {
        this.name = "";
        this.health = 0;
        this.mana = 0;
        this.deck = new ArrayList<T>();
    }

    public Player(String name, Integer health, Integer mana, ArrayList<T> card) {
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
        for(int i=0; i<this.deck.size(); i++) {
            System.out.println(deck.get(i).toString());
        }
    }

    @Override
    public String toString() {
        System.out.println("Player deck list: ");
        this.showDeck();
        return "\nName: " + this.getName() + "\nHealth: " + this.getHealth() + "\nMana: " + this.getMana();
    }

    public <T> void getCard() {};
    public void getCardInfo(T card) {};
    public void putCard() {};
    public void attack() {};
    public void nextPhase() {};

    public static void main(String[] args) {
        Character d = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
        Character e = new Character(2, "test", "test", 2, "test", Type.OVERWORLD, 1, 1, 1, 1);
        ArrayList<Character> newDeck = new ArrayList<>();
        newDeck.add(d);
        newDeck.add(e);
        Player<Card> one = new Player("kevin", 80, 1, newDeck);
        System.out.println(one.toString());
    }
}