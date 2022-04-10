package com.aetherwars.model;

public class Level extends Spell{
    private static int idcounter = 400;
    public Level(){
        super(Level.idcounter++,"XP-Bottle","Menambahkan 1 level pada card Karater yang dipilih", 2, "card/spell/xp_bottle.png", spellType.LVL);
    }
}
