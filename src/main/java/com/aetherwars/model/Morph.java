package com.aetherwars.model;

public class Morph extends Spell {
    //dimain seharusnya ada array berisi template semua card, sehingga bisa target id bisa dicari pake id card
    private int target;
    public Morph(){
        super();
        this.type = spellType.MORPH;
        this.target = 0;
    }
    public Morph(int id, String name, String description, int mana, String imageLoc, int target){
        super(id, name, description, mana, imageLoc, spellType.MORPH);
        this.target = target;
    }
}
