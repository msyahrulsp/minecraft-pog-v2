package com.aetherwars.model;

public class Card {
    protected String name;
    protected String description;
    protected Type type;
    public Card(){
        this.name = "-";
        this.description = "-";
        this.type = Type.OVERWORLD;
    }
    public Card(String name, String description, Type type){
        this.name = name;
        this.description = description;
        this.type = type;
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
    public void setType(Type newType){
        this.type = newType;
    }
    public Type getType(){
        return this.type;
    }
    @Override
    public String toString() {
        return "Name: " + this.name + "\nDescription: " + this.description + "\nType: " + this.type;
    }

}
