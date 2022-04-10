package com.aetherwars.model;
public class Swap extends tempSpell{
    public Swap(String name, String description, int mana, String imageLoc, int duration){
        super(name, description, mana, imageLoc, spellType.SWAP, duration);
    }
}
