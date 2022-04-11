package com.aetherwars.model;
public class Swap extends tempSpell{
    public Swap(int id, String name, String description, int mana, String imageLoc, int duration){
        super(id, name, description, mana, imageLoc, spellType.SWAP, duration);
    }
    public Swap(Swap s){
        super(s);
    }
    @Override
    public void useOn(Character c){
        System.out.println("Swap " + this.name + " used on " + c.getName());
        if (c.getswapDur() > 0){
            c.setswapDur(c.getswapDur() + this.duration);
        } else {
            c.setswapDur(this.duration);
            int temp = c.getHealth();
            c.setHealth(c.getAttack());
            c.setAttack(temp);
        }
    };
    public static void main(String[] args) {
        Character c1 = new Character(1, "Sepi","sepi nya raju", 3, "-", Type.OVERWORLD,2,2, 3, 4 );
        Character c2 = new Character(2, "laba","laba di loteng", 3, "-", Type.OVERWORLD,3,2, 3, 4 );
        Character ingameCard = new Character(c2);
        System.out.println(ingameCard);
        Swap s = new Swap(0, "Swap", "Swap", 0, "", 5);
        System.out.println(s.toString());
        s.useOn(ingameCard);
        System.out.println(ingameCard);
        Potion p = new Potion(2001,"Jamu kuat", "Supaya tahan lama", 4, "-", 8,10,3);
        Potion pp = new Potion(2002,"Josu", "ExtraJoss Susu mantapp", 4, "-", 4,2,5);
        Potion ingameP = new Potion(p);
        Potion ingamePP = new Potion(pp);
        p.useOn(ingameCard);
        System.out.println(ingameCard);
        ingameCard.decreaseswapDur();
        ingameCard.decreaseBuff();
        ingameCard.decreaseswapDur();
        ingameCard.decreaseBuff();ingameCard.decreaseswapDur();
        ingameCard.decreaseBuff();ingameCard.decreaseswapDur();
        ingameCard.decreaseBuff();ingameCard.decreaseswapDur();
        ingameCard.decreaseBuff();ingameCard.decreaseBuff();
        ingameCard.decreaseswapDur();
        ingameCard.decreaseBuff();ingameCard.decreaseswapDur();
        ingameCard.decreaseBuff();ingameCard.decreaseswapDur();
        ingameCard.decreaseBuff();ingameCard.decreaseswapDur();
        ingameCard.decreaseBuff();
        ingameCard.seeBuff();
        System.out.println(ingameCard); //bug karena memakai potion ketiwa swap dan sisa durasi swap < durasi potion
        // karena pada saat  penggunaan potion, attack = 2 dan health = 3, sehingga menjadi attack = 12 dan health = 6
        // swap habis duluan sehingga attack = 6, health = 12
        // pada saat potion habis dan ingin mengurangsi efek potion, yaitu attackbuff = 10 dan healthbuff = 3
        // hasil pengurangan attack akan menghasilkan negatif
        //? Bug or Game Mechanics?

    }
}
