package com.aetherwars.model;

public class TempSpell extends Spell{
    protected int duration;
    public TempSpell(){
        super();
        this.duration = 0;
    }
    public TempSpell(int id, String name, String description, int mana, String imageLoc, spellType type, int duration){
        super(id, name, description, mana, imageLoc, type);
        this.duration = duration;
    }
    public TempSpell(TempSpell s){
        super(s);
        this.duration = s.getDuration();
    }
    public void setDuration(int newDuration){
        this.duration = newDuration;
    }
    public int getDuration(){
        return this.duration;
    }
    @Override
    public String toString() {
        return super.toString() + "\nDuration: " + this.duration;
    }
    public static void main(String[] args) {
        TempSpell s = new TempSpell();
        System.out.println(s.toString());
        s.setDuration(1);
        System.out.println(s.getDuration());
    }
}