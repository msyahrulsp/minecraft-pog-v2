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
    public void setTarget(int newTarget){
        this.target = newTarget;
    }
    public int getTarget(){
        return this.target;
    }
    @Override
    public String toString() {
        return super.toString() + "\nTarget: " + this.target;
    }
    public static void main(String[] args) {
        Morph m = new Morph();
        System.out.println(m.toString());
        m.setTarget(1);
        System.out.println(m.getTarget());
    }
}
