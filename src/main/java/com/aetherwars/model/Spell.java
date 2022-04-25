package com.aetherwars.model;

public class Spell extends Card {
    //behaviornya seharusnya Character menggunakan spell jadi, di character ntar bikin metod memakai spell
    protected spellType type;
    public Spell(){
        super();
        this.type = spellType.PTN;
    }
    public Spell(int id, String name, String description, int mana, String imageLoc, spellType type){
        super(id, name, description, mana, imageLoc);
        this.type = type;
    }
    public Spell(Spell s){
        super(s);
        this.type = s.getType();
    }
    public void setType(spellType newType){
        this.type = newType;
    }
    public spellType getType(){
        return this.type;
    }
    public void useOn(Character c){
        System.out.println("Spell " + this.name + " used on " + c.getName());
    };
    @Override
    public String toString(){
        return super.toString() + "\nType: " + this.type;
    }
    //for debug
    public static void main(String[] args) {
        Spell ss = new Spell();
        Spell s = new Spell(ss);
        s.setType(spellType.SWAP);
        System.out.println(s.toString());
        System.out.println(ss);
    }
}

