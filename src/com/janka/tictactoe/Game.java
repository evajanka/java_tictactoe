package com.janka.tictactoe;

import java.util.Arrays;
import java.util.Scanner;


public class Game implements GameInterface {
    private int[][] board;

    public Game(int Rows, int Cols) {
        int[][] board = new int[Rows][Cols];
        this.setBoard(board);
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getMove(int player) {
        Scanner scanner = new Scanner(System.in);
        String input1;
        do {
            System.out.println("Player " + player + ", please add the letter of your move: ");
            input1 = scanner.nextLine();

        } while (input1.length() != 1 || !input1.matches("[a-zA-Z]+") || (Character.getNumericValue(input1.charAt(0)) - 10) >= getBoard().length);

        String input2;
        do {
            System.out.println("Player " + player + ", please add the number of your move: ");
            input2 = scanner.nextLine();

        } while (input2.length() != 1 || !input2.matches("[0-9]+") || Integer.parseInt(input2) > getBoard()[0].length);

        char c = input1.charAt(0);
        int row = Character.getNumericValue(c) - 10;
        int column = Integer.parseInt(input2) - 1;
        int[] step = { row, column };
        System.out.println(Arrays.toString(step));
        return step;
    }

    public void mark(int player, int row, int col) {
        if (this.board[row][col] == 0) {
            this.getBoard()[row][col] = player;
        } else {
            printBoard();
            System.out.println("Cell occupied :,(");
            int[] playerStep = getMove(player);
            mark(player, playerStep[0], playerStep[1]);
        }

    }

    public boolean hasWon(int player, int howMany) {
        int count = 1;
        int[][] board = getBoard();

        //check horizontal

        for (int i = 0; i < board.length; i++) {
            count = 1;
            for (int j = 0; j < board[i].length - 1; j++) {
                if (board[i][j] == player && board[i][j + 1] == player) {
                    count++;
                    if (count == howMany) {
                        return true;
                    }
                }
            }
        }

        //check vertical

        for (int i = 0; i < board[0].length; i++) {
            count = 1;
            for (int j = 0; j < board.length - 1; j++) {

                if (board[j][i] == player && board[j + 1][i] == player) {
                    count++;
                    if (count == howMany) {
                        return true;
                    }
                }
            }
        }

        //check diagonal left

        for (int i = 0; i < board.length; i++) {
            for (int j = board[i].length - 1; j >= 0; j--) {
                count = 1;
                if (board[i][j] == player) {
                    if (j > howMany) {
                        for (int k = 1; k <= howMany; k++) {
                            if ((i + k < board.length) && board[i + k][j - k] == player) {
                                count++;
                                if (count == howMany) {
                                    return true;
                                }
                                System.out.println(count);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }

        //check diagonal right

        for (int i = 0; i < board.length - howMany; i++) {
            for (int j = 0; j < board[i].length; j++) {
                count = 1;
                if (board[i][j] == player) {
                    if (j < howMany - 1) {
                        for (int k = 1; k <= howMany; k++) {
                            if ((i + k < board.length) && board[i + k][j + k] == player) {
                                count++;
                                if (count == howMany) {
                                    return true;
                                }
                                System.out.println(count);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        char[] alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String[][] boardToPrint = new String[board.length + 1][board[0].length + 1];

        boardToPrint[boardToPrint.length - 1][0] = alphabet[board.length] + "  ";
        for (int i = 0; i < boardToPrint.length - 1; i++) {
            for (int j = 0; j < boardToPrint[i].length - 1; j++) {
                boardToPrint[i][0] = alphabet[i] + "  ";
                boardToPrint[0][j + 1] = Integer.toString(j + 1) + "  ";
                if (board[i][j] != 0) {
                    if (board[i][j] == 1) {
                        boardToPrint[i + 1][j + 1] = " " + "X" + " ";
                    } else {
                        boardToPrint[i + 1][j + 1] = " " + "O" + " ";
                    }
                } else {
                    boardToPrint[i + 1][j + 1] = " . ";
                }
            }
        }
        for (int i = 0; i < boardToPrint.length; i++) {
            for (int j = 0; j < boardToPrint[i].length; j++) {
                System.out.print(boardToPrint[i][j]);
            }
            System.out.println();
        }
    }

    public void printResult(int player) {
        System.out.println("Player " + player + " has won!");
    }

    public void play(int howMany) {
        int player = 1;
        while (!isFull()) {
            printBoard();
            int[] playerStep = getMove(player);
            mark(player, playerStep[0], playerStep[1]);
            printBoard();
            if (hasWon(player, howMany)) {
                break;
            } else {
                player = player == 1 ? 2 : 1;
            }
        }
        if (hasWon(player, howMany)) {
            printResult(player);
        } else {
            System.out.println("It's a tie! :(");
        }

    }
}


