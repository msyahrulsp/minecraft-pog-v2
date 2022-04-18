package com.aetherwars.model;
import java.util.*;

public class CardHolder {
  public ArrayList<Card> elmt;

  public CardHolder() {
    this.elmt = new ArrayList<Card>();
  }

  public Integer getSize() {
    return this.elmt.size();
  }

  public Card getElmt(Integer index) {
    return this.elmt.get(index);
  }

  public void addElmt(Card card) {
    this.elmt.add(card);
  }

  public void addElmtAtIdx(Integer index, Card card) {
    this.elmt.add(index, card);
  }

  public void delElmt(Card card) {
    this.elmt.remove(card);
  }

  public boolean isEmpty() {
    return this.elmt.isEmpty();
  }
}
