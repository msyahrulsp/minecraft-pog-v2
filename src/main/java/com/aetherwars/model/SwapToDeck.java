package com.aetherwars.model;

public class SwapToDeck implements ToDeck {
    @Override
    public Card putCard(String[] row) {
        Swap temp = new Swap(Integer.parseInt(row[0]), row[1], row[2], Integer.parseInt(row[5]), row[3], Integer.parseInt(row[4]));
        Swap fixSwap = new Swap(temp);
        return fixSwap;
    }
}
