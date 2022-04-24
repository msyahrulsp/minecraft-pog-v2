package com.aetherwars.model;

public class Board extends CardHolder {
    public Board() {
      super();
    }

    public void showBoard() {
      System.out.println("Board: ");
      if (!this.elmt.isEmpty()) {
        for (int i = 0; i < this.elmt.size(); i++) {
          if (i + 1 != this.elmt.size()) {
            System.out.print(this.elmt.get(i).id + ":" + this.elmt.get(i).getName() + ", ");
          } else {
            System.out.println(this.elmt.get(i).id + ":" + this.elmt.get(i).getName());
          }
        }
      } else {
        System.out.println("Empty board");
      }
    }
}
