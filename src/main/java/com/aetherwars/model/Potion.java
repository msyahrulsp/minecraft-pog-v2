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
    public int getHealthBuff(){
        return this.healthBuff;
    };
    @Override
    public String toString() {
        return super.toString() + "\nAttack Buff: " + this.attackBuff + "\nHealth Buff: " + this.healthBuff;
    }
    public static void main(String[] args) {
        Potion p = new Potion();
        System.out.println(p.toString());
        p.setAttackBuff(1);
        System.out.println(p.getAttackBuff());
        p.setHealthBuff(1);
        System.out.println(p.getHealthBuff());
    }
}
