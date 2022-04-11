package com.aetherwars.model;

import java.util.ArrayList;
import java.util.Arrays;
import com.aetherwars.util.Triplet;
public class Character extends Card{
  //handle death di main/player aj karena keregister death nya setiap ganti turn
  public static ArrayList<Character> characterList = new ArrayList<Character>(); //berisi Character "Murni"
  private Type type;
  private int attack;
  private int attUp;
  private int health;
  private int healthUp;
  private int exp;
  private int lvl;
  private int swapDuration;
  private ArrayList<Triplet<Integer, Integer, Integer>> buffList;
  public Character(){
    super();
    this.type = Type.OVERWORLD;
    this.attack = 0;
    this.attUp = 1;
    this.health = 0;
    this.healthUp = 1;
    this.exp = 0;
    this.lvl = 1;
    Character.characterList.add(this);
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
    Character.characterList.add(this);
  }
  //copy constructor
  public Character(Character c){
    super(c);
    this.type = c.getType();
    this.attack = c.getAttack();
    this.attUp = c.getAttUp();
    this.health = c.getHealth();
    this.healthUp = c.getHealthUp();
    this.exp = c.getExp();
    this.lvl = c.getLvl();
    this.buffList = new ArrayList<Triplet<Integer, Integer, Integer>>();
  }//yang hanya punya bufflist hanya character yang dimainkan (yang dibuat dengan copy constructor)
  public void changeTo(Character c){ // buat swap
    this.id = c.getId();
    this.name = c.getName();
    this.description = c.getDesc();
    this.mana = c.getMana();
    this.imageLoc = c.getImageLoc();
    this.type = c.getType();
    this.attack = c.getAttack();
    this.attUp = c.getAttUp();
    this.health = c.getHealth();
    this.healthUp = c.getHealthUp();
    this.exp = c.getExp();
    this.lvl = c.getLvl();
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
  public void setExp(int newExp){
    this.exp = newExp;
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
  public void addBuff(int duration, int attBuff, int healthBuff){
    if (duration >0){
      this.buffList.add(new Triplet<Integer, Integer, Integer>(duration, attBuff, healthBuff));

    } // kalo durasi = 0 buff permanen jadi gaperlu di monitor
    this.attack += attBuff;
    this.health += healthBuff;
  }
  // sumur https://stackoverflow.com/questions/14231688/how-to-remove-element-from-arraylist-by-checking-its-value
  public void decreaseBuff(){
    ArrayList<Triplet<Integer, Integer, Integer>> deleteList = new ArrayList<Triplet<Integer, Integer, Integer>>();
    for (Triplet<Integer, Integer, Integer> buff : this.buffList){
      buff.setValue0(buff.getValue0() - 1);
      if (buff.getValue0() == 0){
        this.attack -= buff.getValue1();
        this.health -= buff.getValue2();
        deleteList.add(buff);
      }
    }
    for (Triplet<Integer, Integer, Integer> buff : deleteList){
      this.buffList.remove(buff);
    }
  }
  //for debug
  public void seeBuff(){
    System.out.println(Arrays.deepToString(this.buffList.toArray()));
  }
  public static Character getCharacter(int id){
    for (Character c : Character.characterList){
      if (c.getId() == id) return c;
    }
    return null;
  }
  public void setswapDur(int dur){
    this.swapDuration = dur;
  }
  public int getswapDur(){
    return this.swapDuration;
  }
  public void decreaseswapDur(){
    if (this.swapDuration > 0) {
      this.swapDuration -= 1;
      //jika setelah di decrease, durasi = 0, maka swap akan dihentikan
      if(this.swapDuration == 0){
        int temp = this.attack;
        this.attack = this.health;
        this.health = temp;
      } 
    }
  };
  @Override
  public String toString() {
    return super.toString() + "\nType: " + this.type + "\nAttack: " + this.attack + "\nHealth: " + this.health + "\nLevel: " + this.lvl + "\nExp: " + this.exp;
  }
  public static void main(String[] args) {
    Character d = new Character(1, "test", "test", 1, "test", Type.OVERWORLD, 1, 1, 1, 1);
    Character c = new Character(d);
    c.setType(Type.END);
    // System.out.println(c.getType());
    c.addExp(10);
    // System.out.println(c);
    c.setAttack(10);
    // System.out.println(c.getAttack());
    c.setHealth(10);
    // System.out.println(c.getHealth());
    c.setAttUp(10);
    // System.out.println(c.getAttUp());
    c.setHealthUp(10);
    // System.out.println(c.getHealthUp());
    System.out.println(d);
    System.out.println(c);
  }
}
