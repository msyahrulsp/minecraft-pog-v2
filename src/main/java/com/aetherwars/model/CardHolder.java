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

  public void addElmt(Card card) {
    this.elmt.add(card);
  }

  public void addElmtAtIdx(int index, Card card) {
    this.elmt.add(index, card);
  }

  public void delElmt(int index) {
    this.elmt.remove(index);
  }

  public void delElmt(Card card) {
    this.elmt.remove(card);
  }

  public boolean isEmpty() {
    return this.elmt.isEmpty();
  }
}
