package com.aetherwars.model;
import java.util.*;

public class CardHolder {
  protected ArrayList<Card> elmt;

  public CardHolder() {
    this.elmt = new ArrayList<Card>();
  }

  public int getSize() {
    return this.elmt.size();
  }

  public Card getElmt(int index) {
    return this.elmt.get(index);
  }

  public ArrayList<Card> getCards() {
    return this.elmt;
  }

  public void addElmt(Card card) {
    this.elmt.add(card);
  }

  public void addElmtAtIdx(int index, Card card) {
    this.elmt.add(index, card);
  }

  public void delElmt(int index) {
    this.elmt.remove(index);
  }

  public int findElmt(Card card) {
    for (int i = 0; i < this.elmt.size(); i++) {
      if (this.elmt.get(i).equals(card)) {
        return i;
      }
    }
    return -1;
  }

  public void delElmt(Card card) {
    this.elmt.remove(card);
  }

  public boolean isEmpty() {
    return this.elmt.isEmpty();
  }
}
