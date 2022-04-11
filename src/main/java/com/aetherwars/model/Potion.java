package com.aetherwars.model;

import java.util.Arrays;

public class Potion extends tempSpell{
    private int attackBuff;
    private int healthBuff;
    public Potion(){
        super();
        this.type =spellType.PTN;
        this.attackBuff =0;
        this.healthBuff = 0;
    }
    public Potion(int id, String name, String description, int mana, String imageLoc, int duration, int attackBuff, int healthBuff){
        super(id, name, description, mana, imageLoc, spellType.PTN, duration);
        this.attackBuff = attackBuff;
        this.healthBuff = healthBuff;
    }
    public Potion(Potion p){
        super(p);
        this.attackBuff = p.getAttackBuff();
        this.healthBuff = p.getHealthBuff();
    }
    public void setAttackBuff(int newAttackBuff){
        this.attackBuff = newAttackBuff;
    }
    public int getAttackBuff(){
        return this.attackBuff;
    };
    public void setHealthBuff(int newHealthBuff){
        this.healthBuff = newHealthBuff;
    };
    public int getHealthBuff(){
        return this.healthBuff;
    };
    @Override
    public void useOn(Character c){
        System.out.println("Potion " + this.name + " used on " + c.getName());
        c.addBuff(this.duration, this.attackBuff, this.healthBuff);
    };
    @Override
    public String toString() {
        return super.toString() + "\nAttack Buff: " + this.attackBuff + "\nHealth Buff: " + this.healthBuff;
    }
    public static void main(String[] args) {
        
        Character c1 = new Character(1, "Sepi","sepi nya raju", 3, "-", Type.OVERWORLD,2,2, 3, 4 );
        Character c2 = new Character(2, "laba","laba di loteng", 3, "-", Type.OVERWORLD,3,2, 3, 4 );
        Character ingameCard = new Character(c2);
        System.out.println(ingameCard);
        Potion p = new Potion(2001,"Jamu kuat", "Supaya tahan lama", 4, "-", 3,10,3);
        Potion pp = new Potion(2002,"Josu", "ExtraJoss Susu mantapp", 4, "-", 4,2,5);
        Potion ingameP = new Potion(p);
        Potion ingamePP = new Potion(pp);
        ingameCard.seeBuff();
        ingameP.useOn(ingameCard);
        System.out.println(ingameCard);
        ingameCard.decreaseBuff();
        ingamePP.useOn(ingameCard);
        ingameCard.seeBuff();
        ingameCard.decreaseBuff();
        ingameCard.decreaseBuff();
        System.out.println(ingameCard);
        ingameCard.seeBuff();



    }
}
