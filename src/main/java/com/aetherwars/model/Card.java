package com.aetherwars.model;
import java.util.*;
public class Card {
    protected int id;
    protected String name;
    protected String description;
    protected int mana;
    protected String imageLoc;

    public Card() {
        this.id = 0;
        this.name = "-";
        this.description = "-";
        this.mana = 0;
        this.imageLoc = "card/error-card.png";
    }

    public Card(int id, String name, String description, int mana, String imageLoc) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mana = mana;
        this.imageLoc = imageLoc;
    } // hanya dipanggil pada saat membuat card baru (di saat read file csv)

    public Card(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.description = card.getDesc();
        this.mana = card.getMana();
        this.imageLoc = card.getImageLoc();
    } //yang dipanggil di game hanya copy constructor, jadi hanya mengkopi pool card di cardList (yang di cardList akan "Murni")
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    public void setDesc(String newDesc) {
        this.description = newDesc;
    }

    public String getDesc() {
        return this.description;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMana() {
        return this.mana;
    }

    public String getImageLoc() {
        return this.imageLoc;
    }

    public void setImageLoc(String newImageLoc) {
        this.imageLoc = newImageLoc;
    }

    @Override
    public String toString() {
        return "Id: "+ this.id + "\nName: " + this.name + "\nDescription: " + this.description + "\nMana: " + this.mana + "\nImage: " + this.imageLoc;
    }
    
    public static void main(String[] args) {
        Card d = new Card(1, "test", "test", 1, "test");
        Card c = new Card(d);
        c.setName("test2");
        // System.out.println(c.getName());
        c.setDesc("test3");
        // System.out.println(c.getDesc());
        c.setMana(2);
        // System.out.println(c.getMana());
        c.setImageLoc("test4");
        System.out.println(d);
        System.out.println(c);
        // System.out.println(c.getImageLoc());

    }
}
