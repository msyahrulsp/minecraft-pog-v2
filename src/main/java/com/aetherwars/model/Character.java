package com.aetherwars.model;
import com.aetherwars.model.Card;

public class Character extends Card{
  private int attack;
  private int health;
  public Character(){
    super();
    this.attack = 0;
    this.health = 0;
  }
  public Character(String name, String description, Type type, int attack, int health){
    super(name, description, type);
    this.attack = attack;
    this.health = health;
  }
  public void setAttack(int newAttack){
    this.attack = newAttack;
  }
  public int getAttack(){
    return this.attack;
  }
  public void setHealth(int newHealth){
    this.health = newHealth;
  }
  public int getHealth(){
    return this.health;
  }
  @Override
  public String toString() {
    return super.toString() + "\nAttack: " + this.attack + "\nHealth: " + this.health;
  }
}
