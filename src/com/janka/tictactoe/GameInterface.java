package com.janka.tictactoe;

public interface GameInterface {

    void setBoard(int[][] board);
    int[][] getBoard();
    int[] getMove(int player);
    void mark(int player, int row, int col);
    boolean hasWon(int player, int howMany);
    boolean isFull();
    void printBoard();
    void printResult(int player);
    void play(int howMany);
}
