package com.aetherwars.model;

public class Morph extends Spell {
    //dimain seharusnya ada array berisi template semua card, sehingga bisa target id adalah mengarah ke array tersebut (bisa sebagai index atau apa)
    private int target;
    public Morph(){
        super();
        this.type = spellType.MORPH;
        this.target = 0;
    }
    public Morph(String name, String description, int mana, String imageLoc, int target){
        super(name, description, mana, imageLoc, spellType.MORPH);
        this.target = target;
    }
}
