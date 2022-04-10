package com.aetherwars.model;

public class Card {
    protected String name;
    protected String description;
    protected int mana;
    protected String imageLoc;
    public Card(){
        this.name = "-";
        this.description = "-";
        this.mana = 0;
        this.imageLoc = "card/error-card.png"
    }
    public Card(String name, String description, int mana, String imageLoc){
        this.name = name;
        this.description = description;
        this.mana = mana;
        this.imageLoc = imageLoc;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public String getName(){
        return this.name;
    }
    public void setDesc(String newDesc){
        this.description = newDesc;
    }
    public String getDesc(){
        return this.description;
    }
    public void setMana(int mana){
        this.mana = mana;
    }
    public int getMana(){
        return this.mana;
    }
    public String getImageLoc(){
        return this.imageLoc;
    }
    public void setImageLoc(String newImageLoc){
        this.imageLoc = newImageLoc;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nDescription: " + this.description;
    }

}
