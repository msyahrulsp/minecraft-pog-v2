package com.aetherwars.model;
import com.aetherwars.model.Card;

public class Character extends Card{
  private int attack;
  private int attUp;
  private int health;
  private int healthUp;
  private int exp;
  private int lvl;
  public Character(){
    super();
    this.attack = 0;
    this.attUp = 1;
    this.health = 0;
    this.healthUp = 1;
    this.exp = 0;
    this.lvl = 1;
  }
  public Character(String name, String description, Type type, int attack, int health, int attUp, int healthUp){
    super(name, description, type);
    this.attack = attack;
    this.attUp = attUp;
    this.health = health;
    this.healthUp = healthUp;
    this.exp = 0;
    this.lvl = 1;
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
  private void handleLevelup(){
    int lvlxpcap = this.lvl * 2 - 1;
    while (this.exp >= lvlxpcap && this.lvl < 10){
      this.attack += this.attUp;
      this.health += this.healthUp;
      this.lvl += 1;
      this.exp -= lvlxpcap;
      lvlxpcap = this.lvl * 2 - 1;
    }
    if (this.lvl == 10) this.exp = 0;
  }
  public void addExp(int expAmount){
    if (this.lvl < 10){
      this.exp += xpAmount;
      handleLevelup();
    }
  }
  public int getExp(){
    return this.Exp;
  }
  public void setLvl(int newLvl){
    this.lvl = newLvl;
  }
  public int getLvl(){
    return this.lvl;
  }
  @Override
  public String toString() {
    return super.toString() + "\nAttack: " + this.attack + "\nHealth: " + this.health;
  }
}
