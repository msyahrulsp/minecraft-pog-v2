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

    public <T> void getCard() {};
    public void getCardInfo(T card) {};
    public void putCard() {};
    public void attack() {};
    public void nextPhase() {};
}