package com.aetherwars.model;

public class Game {
    static int rounds;
    static int capMana;
    private Player[] player;

    public Game(Player player1, Player player2) {
        this.player = new Player[2];
        this.player[0] = player1;
        this.player[1] = player2;
        rounds++;
        capMana++;
    }

    public Player getPlayer(int i) {
        return this.player[i];
    }

    public Phase nextPhase(int i) {
        if (this.player[i].getPhase() == Phase.DRAW) {
            return Phase.PLAN;
        } else if (this.player[i].getPhase() == Phase.PLAN) {
            return Phase.ATTACK;
        } else if (this.player[i].getPhase() == Phase.ATTACK) {
            return Phase.END;
        } else if (this.player[i].getPhase() == Phase.END) {
            if (i == 1) {
                rounds++;
                capMana++;
                if (capMana > 10) {
                    capMana = 10;
                }
                this.player[i].setMana(capMana);
            }
            return Phase.DRAW;
        }
        return null;
    }

//    public static void main(String[] args) {
//        Deck deckPlayerOne = new Deck();
//        Deck deckPlayerTwo = new Deck();
//
//        Hand handPlayerOne = new Hand();
//        Hand handPlayerTwo = new Hand();
//
//        CardHolder addCardPlayerOne = new CardHolder();
//        CardHolder addCardPlayerTwo = new CardHolder();
//
//        Board boardPlayerOne = new Board();
//        Board boardPlayerTwo = new Board();
//
//        Player playerOne = new Player("Player One", 80, 1, deckPlayerOne, handPlayerOne, addCardPlayerOne, boardPlayerOne);
//        Player playerTwo = new Player("Player Two", 80, 1, deckPlayerTwo, handPlayerTwo, addCardPlayerTwo, boardPlayerTwo);
//
//        Game game = new Game(playerOne, playerTwo);
//
//        try {
//            game.player[0].getDeck().loadDeck();
//            game.player[1].getDeck().loadDeck();
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        game.player[0].getAddCard().addElmt(game.player[0].getDeck().getElmt(0));
//        game.player[1].getAddCard().addElmt(game.player[1].getDeck().getElmt(0));
//        System.out.println(game.player[0].getAddCard());
//        System.out.println(game.player[1].getAddCard());
        
    //     System.out.println(game.player[0].getDeck().getSize());
    //     System.out.println(game.player[1].getDeck().getSize());

    //     System.out.println(game.player[0]);
    //     System.out.println(game.player[1]);

    //     game.player[0].getCardToHand("first");
    //     game.player[1].getCardToHand("first");
    //     while (game.nextPhase(0) != Phase.DRAW) {
    //         if (game.player[0].getPhase() == Phase.DRAW) {
    //             for (int i = 0; i < 3; i++) {
    //                 if (i == 0 || i == 2) {
    //                     game.player[0].addCardToHand(1);
    //                     game.player[0].addCardToHand(1);
    //                 } else if (i == 1) {
    //                     game.player[0].throwCardFromHand(0);
    //                     game.player[0].throwCardFromHand(1);
    //                     game.player[0].addCardToHand(2);
    //                 }
    //             }
    //             System.out.println(game.player[0]);
    //             game.player[0].setPhase(game.nextPhase(0));
    //             System.out.println("End of DRAW");
    //         }
    //         else if (game.player[0].getPhase() == Phase.PLAN) {
    //             game.player[0].addCardToBoard(1);
    //             game.player[0].addCardToBoard(0);
    //             System.out.println(game.player[0]);
    //             game.player[0].setPhase(game.nextPhase(0));
    //             System.out.println("End of PLAN");
    //         }
    //         else if (game.player[0].getPhase() == Phase.ATTACK) {
    //             // attack karena belum selesai jadi diskip
    //             game.player[0].setPhase(game.nextPhase(0));
    //             System.out.println("End of ATTACK");
    //         }
    //     }
    //     game.player[0].setPhase(game.nextPhase(0));
    //     System.out.println("End of END");

    //     while (game.nextPhase(1) != Phase.DRAW) {
    //         if (game.player[1].getPhase() == Phase.DRAW) {
    //             for (int i = 0; i < 3; i++) {
    //                 if (i == 0 || i == 2) {
    //                     game.player[1].addCardToHand(1);
    //                     game.player[1].addCardToHand(1);
    //                 } else if (i == 1) {
    //                     game.player[1].throwCardFromHand(0);
    //                     game.player[1].throwCardFromHand(1);
    //                     game.player[1].addCardToHand(2);
    //                 }
    //             }
    //             System.out.println(game.player[1]);
    //             game.player[1].setPhase(game.nextPhase(1));
    //             System.out.println("End of DRAW");
    //         }
    //         else if (game.player[1].getPhase() == Phase.PLAN) {
    //             game.player[1].addCardToBoard(1);
    //             game.player[1].addCardToBoard(0);
    //             System.out.println(game.player[1]);
    //             game.player[1].setPhase(game.nextPhase(1));
    //             System.out.println("End of PLAN");
    //         }
    //         else if (game.player[1].getPhase() == Phase.ATTACK) {
    //             // attack karena belum selesai jadi diskip
    //             game.player[1].setPhase(game.nextPhase(1));
    //             System.out.println("End of ATTACK");
    //         }
    //     }
    //     game.player[1].setPhase(game.nextPhase(1));
    //     System.out.println("End of END");
    // }
//    }

}
