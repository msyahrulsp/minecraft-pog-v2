package com.aetherwars.model;

public class Character extends Card{
  //handle death di main/player aj karena keregister death nya setiap ganti turn
  private Type type;
  private int attack;
  private int attUp;
  private int health;
  private int healthUp;
  private int exp;
  private int lvl;
  public Character(){
    super();
    this.type = Type.OVERWORLD;
    this.attack = 0;
    this.attUp = 1;
    this.health = 0;
    this.healthUp = 1;
    this.exp = 0;
    this.lvl = 1;
  }
  public Character(int id, String name, String description, int mana, String imageLoc, Type type, int attack, int health, int attUp, int healthUp){
    super(id, name, description, mana, imageLoc);
    this.type = type;
    this.attack = attack;
    this.attUp = attUp;
    this.health = health;
    this.healthUp = healthUp;
    this.exp = 0;
    this.lvl = 1;
  }
  //copy constructor
  public Character(Character c){
    this(c.getId(), c.getName(), c.getDesc(), c.getMana(), c.getImageLoc(), c.getType(), c.getAttack(), c.getHealth(), c.getAttUp(), c.getHealthUp());
  }
  public void setType(Type newType){
    this.type = newType;
  }
  public Type getType(){
    return this.type;
  }
  public void setAttack(int newAttack){
    this.attack = newAttack;
  }
  public int getAttack(){
    return this.attack;
  }
  public void setAttUp(int newAttUp){
    this.attUp = newAttUp;
  }
  public int getAttUp(){
    return this.attUp;
  }
  public void setHealth(int newHealth){
    this.health = newHealth;
  }
  public int getHealth(){
    return this.health;
  }
    public void setHealthUp(int newHealthUp){
    this.healthUp = newHealthUp;
  }
  public int getHealthUp(){
    return this.healthUp;
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
      this.exp += expAmount;
      handleLevelup();
    }
  }
  public int getExp(){
    return this.exp;
  }
  public void setLvl(int newLvl){
    this.lvl = newLvl;
  }
  public int getLvl(){
    return this.lvl;
  }
  @Override
  public String toString() {
    return super.toString() + "\nType: " + this.type + "\nAttack: " + this.attack + "\nHealth: " + this.health;
  }
}
