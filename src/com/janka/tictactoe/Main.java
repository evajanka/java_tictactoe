package com.janka.tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        System.out.println("Please add the number of rows!");
        int rows = sc.nextInt();
        System.out.println("Please add the number of columns!");
        int cols = sc.nextInt();
        System.out.println("Please add the needed winning scores!");
        int score = sc.nextInt();
        Game game = new Game(rows, cols);
        game.play(score);
    }
}
