package com.aetherwars.model;

class Spell extends Card {
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
    public void setType(spellType newType){
        this.type = newType;
    }
    public spellType getType(){
        return this.type;
    }
    @Override
    public String toString(){
        return super.toString() + "\nType: " + this.type;
    }
    //for debug
    public static void main(String[] args) {
        Spell s = new Spell();
        System.out.println(s.toString());
        s.setType(spellType.SWAP);
        System.out.println(s.getType());
    }
}
class tempSpell extends Spell{
    protected int duration;
    public tempSpell(){
        super();
        this.duration = 0;
    }
    public tempSpell(int id, String name, String description, int mana, String imageLoc, spellType type, int duration){
        super(id, name, description, mana, imageLoc, type);
        this.duration = duration;
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
        tempSpell s = new tempSpell();
        System.out.println(s.toString());
        s.setDuration(1);
        System.out.println(s.getDuration());
    }
}
