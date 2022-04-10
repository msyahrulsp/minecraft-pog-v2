package com.aetherwars.model;

class Spell extends Card {
    //behaviornya seharusnya Character menggunakan spell jadi, di character ntar bikin metod memakai spell
    protected spellType type;
    public Spell(){
        super();
        this.type = spellType.PTN;
    }
    public Spell(String name, String description, int mana, String imageLoc, spellType type){
        super(name, description, mana, imageLoc);
        this.type = type;
    }
    public void setType(spellType newType){
        this.type = newType;
    }
    public spellType getType(){
        return this.type;
    }
}
class tempSpell extends Spell{
    protected int duration;
    public tempSpell(){
        super();
        this.duration = 0;
    }
    public tempSpell(String name, String description, int mana, String imageLoc, spellType type, int duration){
        super(name, description, mana, imageLoc, type);
        this.duration = duration;
    }
}
