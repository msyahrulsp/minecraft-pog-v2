package com.aetherwars.model;

import java.util.Arrays;

public class Level extends Spell{
    private static int idcounter = 400;
    public Level(){
        super(Level.idcounter++,"XP-Bottle","Menambahkan 1 level pada card Karater yang dipilih", 2, "card/spell/xp_bottle.png", spellType.LVL);
    }
    public Level(Level l){
        super(l);
    }
    @Override
    public void useOn(Character c){
        if (c.getLvl() < 10){
            c.setExp(0);
            
            c.addExp(c.getLvl()*2 - 1);
            System.out.println("Level up " + c.getName() + " to " + (c.getLvl()));
        }
        else{
            System.out.println(c.getName() +" Level is " + c.getLvl() + ", maximum Level reached");
        }
    }
    public static void main(String[] args) {
        Character c1 = new Character(1, "Sepi","sepi nya raju", 3, "-", Type.OVERWORLD,2,2, 3, 4 );
        Character c2 = new Character(2, "laba","laba di loteng", 3, "-", Type.OVERWORLD,3,2, 3, 4 );
        Character ingameCard = new Character(c2);
        System.out.println(ingameCard);
        Level l = new Level();
        Level ll = new Level(l);
        ll.useOn(ingameCard);
        ll.useOn(ingameCard);
        ll.useOn(ingameCard);
        ll.useOn(ingameCard);
        ll.useOn(ingameCard);
        ll.useOn(ingameCard);
        ll.useOn(ingameCard);
        ll.useOn(ingameCard);
        ll.useOn(ingameCard);
        ll.useOn(ingameCard);
        ll.useOn(ingameCard);
        System.out.println(ingameCard);
        // System.out.println(ll);

    }
}
