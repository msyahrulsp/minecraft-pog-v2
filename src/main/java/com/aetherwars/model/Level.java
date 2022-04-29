package com.aetherwars.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Level extends Spell{
    private String modifierType;
    public static ArrayList<Level> levelList = new ArrayList<Level>(); //berisi Level "Murni" tapi seharusnya cuma 1 jenis potion untuk level
    public Level(String modifierType){
        super(401,"XP-Bottle","Menambahkan/mengurangi 1 level pada card Karater yang dipilih", 2, "card/spell/xp_bottle.png", spellType.LVL);
        this.modifierType = modifierType;
        Level.levelList.add(this);
    }
    public Level(Level l){
        super(l);
    }
    public Level getLevel(int id){
        for (Level l : Level.levelList){
            if (l.getId() == id){
                return l;
            }
        }
        return null;
    }
    public String getModifierType(){
        return this.modifierType;
    }
    public void setModifierType(String modifierType){
        this.modifierType = modifierType;
    }
    @Override
    public void useOn(Player caster, Character c){
        if (c.isAbleToBeUsedBy(caster)) {
            if (this.modifierType.equals("LVLUP")){
                if (c.getLvl() < 10) {
                    c.setExp(0);

                    c.addExp(c.getLvl() * 2 - 1);
                    System.out.println("Level up " + c.getName() + " to " + (c.getLvl()));
                } else {
                    System.out.println(c.getName() + " Level is " + c.getLvl() + ", maximum Level reached");
                }
            }
            else if (this.modifierType.equals("LVLDOWN")){
                if (c.getLvl() > 1) {
                    c.setExp(0);
                    c.setLvl(c.getLvl() - 1);
                    System.out.println("Level down " + c.getName() + " to " + (c.getLvl()));
            }
        }
        else{
            System.out.println(c.getName() + " is not able to be used by " + caster.getName());
        }
    }
    public static void main(String[] args) {
        Character c1 = new Character(1, "Sepi","sepi nya raju", 3, "-", Type.OVERWORLD,2,2, 3, 4 );
        Character c2 = new Character(2, "laba","laba di loteng", 3, "-", Type.OVERWORLD,3,2, 3, 4 );
        Character ingameCard = new Character(c2);
        System.out.println(ingameCard);
        Level l = new Level();
        Level ll = new Level(l);
//        ll.useOn(ingameCard);
//        ll.useOn(ingameCard);
//        ll.useOn(ingameCard);
//        ll.useOn(ingameCard);
//        ll.useOn(ingameCard);
//        ll.useOn(ingameCard);
//        ll.useOn(ingameCard);
//        ll.useOn(ingameCard);
//        ll.useOn(ingameCard);
//        ll.useOn(ingameCard);
//        ll.useOn(ingameCard);
        System.out.println(ingameCard);
        // System.out.println(ll);

    }
}
