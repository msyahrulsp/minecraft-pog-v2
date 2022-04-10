package com.aetherwars.model;
public class Swap extends tempSpell{
    public Swap(int id, String name, String description, int mana, String imageLoc, int duration){
        super(id, name, description, mana, imageLoc, spellType.SWAP, duration);
    }
}
