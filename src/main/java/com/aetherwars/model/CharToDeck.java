package com.aetherwars.model;

public class CharToDeck implements ToDeck {
    @Override
    public Card putCard(String[] row) {
        Type type;
        if (row[2] == "OVERWORLD") {
            type = Type.OVERWORLD;
        } else if (row[2] == "NETHER") {
            type = Type.NETHER;
        } else {
            type = Type.END;
        }
        Character temp = new Character(Integer.parseInt(row[0]), row[1], row[3], Integer.parseInt(row[7]), row[4], type, Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[8]), Integer.parseInt(row[9]));
        Character fixChar = new Character(temp);
        return fixChar;
    }
}
