package com.aetherwars.model;

public class PotionToDeck implements ToDeck {
    @Override
    public Card putCard(String[] row) {
        Potion temp = new Potion(Integer.parseInt(row[0]), row[1], row[2], Integer.parseInt(row[6]), row[3], Integer.parseInt(row[7]), Integer.parseInt(row[4]), Integer.parseInt(row[5]));
        Potion fixPotion = new Potion(temp);
        return fixPotion;
    }
}
