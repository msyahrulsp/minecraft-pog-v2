package com.aetherwars.model;
public class Swap extends tempSpell{
    public Swap(int id, String name, String description, int mana, String imageLoc, int duration){
        super(id, name, description, mana, imageLoc, spellType.SWAP, duration);
    }
    public Swap(Swap s){
        super(s);
    }
    public static void main(String[] args) {
        Swap s = new Swap(0, "Swap", "Swap", 0, "", 0);
        System.out.println(s.toString());
    }
}
