package com.aetherwars.model;

public class DeckFactory {
    public ToDeck getDeck(String deckType) {
        if (deckType.equals("Potion")) {
            return new PotionToDeck();
        } else if (deckType.equals("Swap")) {
            return new SwapToDeck();
        } else if (deckType.equals("Morph")) {
            return new MorphToDeck();
        } else if (deckType.equals("Character")) {
            return new CharToDeck();
        } else {
            return null;
        }
    }
}
