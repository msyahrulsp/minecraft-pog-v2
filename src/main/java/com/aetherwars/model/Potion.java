package com.aetherwars.model;

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
    public void setAttackBuff(int newAttackBuff){
        this.attackBuff = newAttackBuff;
    }
    public int getAttackBuff(){
        return this.attackBuff;
    };
    public void setHealthBuff(int newHealthBuff){
        this.healthBuff = newHealthBuff;
    };
}
