package com.aetherwars.model;

public class MorphToDeck implements ToDeck {
    @Override
    public Card putCard(String[] row) {
        Morph temp = new Morph(Integer.parseInt(row[0]), row[1], row[2], Integer.parseInt(row[5]), row[3], Integer.parseInt(row[4]));
        Morph fixMorph = new Morph(temp);
        return fixMorph;
    }
}
