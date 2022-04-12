package com.aetherwars.model;

import java.util.Arrays;
import java.util.ArrayList;

public class Morph extends Spell {
    //dimain seharusnya ada array berisi template semua card, sehingga bisa target id bisa dicari pake id card
    public static ArrayList<Morph> morphList = new ArrayList<Morph>(); //berisi Morph "Murni"
    private int target;
    public Morph(){
        super();
        this.type = spellType.MORPH;
        this.target = 0;
        Morph.morphList.add(this);
    }
    public Morph(int id, String name, String description, int mana, String imageLoc, int target){
        super(id, name, description, mana, imageLoc, spellType.MORPH);
        this.target = target;
        Morph.morphList.add(this);
    }
    public Morph(Morph m){
        super(m);
        this.target = m.getTarget();
    }
    public void setTarget(int newTarget){
        this.target = newTarget;
    }
    public int getTarget(){
        return this.target;
    }
    public Morph getMorph(int id){
        for(Morph m : Morph.morphList){
            if(m.getId() == id){
                return m;
            }
        }
        return null;
    }
    @Override
    public void useOn(Character c){
        Character targetRef = Character.getCharacter(this.target);
        if (targetRef != null){
            System.out.println("Morph " + this.name + " used on " + c.getName() + " and morph to " + targetRef.getName());
            c.changeTo(targetRef);
        }
        else{
            System.out.println("Target not found");
        }
    }
    @Override
    public String toString() {
        return super.toString() + "\nTarget: " + this.target;
    }
    public static void main(String[] args) {
        Character c1 = new Character(1, "Sepi","sepi nya raju", 3, "-", Type.OVERWORLD,2,2, 3, 4 );
        Character c2 = new Character(2, "laba","laba di loteng", 3, "-", Type.OVERWORLD,3,2, 3, 4 );
        Character ingameCard = new Character(c2);
        System.out.println(ingameCard);
        Morph m = new Morph(1001,"Morphing to Sapi", "haloo", 4, "-", 1);
        Morph mm = new Morph(m);
        mm.useOn(ingameCard);
        System.out.println(ingameCard);
    }
}
