//import java.awt.Graphics;
//import java.awt.Color;
//import java.util.Scanner;
//import java.io.*;
//import java.util.Random;
//
//public class app {
//    public static void main(String[] args) {
//        Board board = new Board();
//        int x, y;
//        System.out.println("Welcome to Omok");
//        System.out.println("Type 1 for single player vs computer.\nType 2 for 2 local players.\nType 3 to quit.\nGame Mode:");
//        Scanner sc = new Scanner(System.in);
//        int mode = sc.nextInt();
//        if (mode == 1) {
//            Human player1 = new Human(1);
//            Human player2 = new Human(0);
//            Computer comp = new Computer(0);
//        }
//        else if (mode == 2) {
//            Human player1 = new Human(1);
//            Computer comp = new Computer(0);
//        } else
//            return;
//        board.drawBoard();
//        int color = 1;
//        Random rand = new Random();
//        switch (mode){
//            case (1):
//                while (true) {
//                    System.out.print("\nEnter coordinates for stone (x y):");
//                    x = sc.nextInt();
//                    y = sc.nextInt();
//                    if (!(board.validMovement(x, y, color))) {
//                        System.out.println("Invalid input.");
//                        continue;
//                    }
//                    board.drawBoard();
//                    if (color == 1)
//                        color = 0;
//                    else
//                        color = 1;
//                }
//               // break;
//            case(2):
//                while(true){
//                    System.out.print("\nEnter coordinates (x y):");
//                    int i = 0;
//                    int e = rand.nextInt(15);
//                    int h = rand.nextInt(15);
//                    while(!(board.validMovement(e, h, color)) && i < 10) {
//                        e = rand.nextInt(15);
//                        h = rand.nextInt(15);
//                        i++;
//                    }
//                    if(i > 9){
//                        for(int k = 7; k < 15; k++){
//
//                        }
//                    }
//
//
//                }
//        }
//    }
//}
//class Stone{
//    private int x;
//    private int y;
//    private int size = 10;
//    private int color;
//
//    public Stone(){ }
//    public Stone(int x, int y, int color){
//        this.x = x;
//        this.y = y;
//        this.size = this.size;
//        this.color = color;
//    }
//
////    public boolean validMovement(int x, int y, int color) {
////        if (board[x][y] == true) //if board[x][y] is true, position is not valid
////            return false;
////        board[x][y] = true; //position is valid, assign coordinate with true
////        stones[x][y] = color; //keeps track of which player is positioning stone
////        return true;
////    }
////    public void draw(Graphics g){
////        g.setColor(color);
////        g.fillOval(x * 20 - 5, y * 21 - 5, 10, 10);
////    }
//}
//
//class Board {
//    private boolean[][] board = new boolean[15][15]; // when value is true, location is taken
//    private int[][] stones = new int[15][15]; //1 for black, 0 for red
//
////    public Board(boolean[][], int[][]) {
////
////    }
//
//    public boolean validMovement(int x, int y, int color) {
//        if(x < 0 || x > 14 || y < 0 || y > 14)
//            return false;
//        if (board[x][y] == true) //if board[x][y] is true, position is not valid
//            return false;
//        board[x][y] = true; //position is valid, assign coordinate with true
//        stones[x][y] = color; //keeps track of which player is positioning stone
//        return true;
//    }
//
//    public void drawBoard() {
////        g.setColor(new Color(102, 255, 102));
////        g.fillRect(0, 0, 320, 336);
////        g.setColor(Color.BLACK);
////        int x = 0, y = 0;
////        for (x = 0; x <= 320; x += 20) {
////            g.drawLine(x, y, x, 336);
////        }
////        for (y = 0; y <= 336; y += 21) {
////            g.drawLine(x, y, 320, y);
////        }
////        for (x = 1; x < 16; x++) { //iterating through array in order to print all stones placed
////            for (y = 1; y < 16; y++) {
////                if (board[x][y] == true) {
////                    if (stones[x][y] == 1) {
////                        g.setColor(Color.BLACK);
////                        g.fillOval(x * 20 - 5, y * 21 - 5, 10, 10);
////                    } else {
////                        g.setColor(Color.RED);
////                        g.fillOval(x * 20 - 5, y * 21 - 5, 10, 10);
////                    }
////                }
////            }
////        }
//        System.out.println("    | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10| 11| 12| 13| 14|");
//        System.out.println("----------------------------------------------------------------");
//        for(int i = 0; i < 15; i++){
//            if(i < 10)
//                System.out.print("  " + i + " |");
//            else
//                System.out.print(" " + i + " |");
//            for(int j = 0; j < 15; j++){
//                if (board[i][j] == true){
//                    if(stones[i][j] == 1)
//                        System.out.print(" X |");
//                    else
//                        System.out.print(" 0 |");
//                }
//                else
//                    System.out.print("   |");
//            }
//            System.out.println();
//        //    System.out.println("\n-----------------------------------------------------------------");
//        }
//    }
////    public boolean whiteWon(){
////        return false;
////    }
//}
//
////abstract class Player{
////    private int x;
////    private int y;
////    private int color;
////    public Player(){ }
////    public Player(int x, int y, int color){
////        this.x = x;
////        this.y = y;
////        this.color = color; //1 for black, 0 for red
////    }
////    public abstract void createMove(int color, Board board);
////    public int getX(){return x;}
////    public int getY(){return y;}
////    public int getColor(){return color;}
////}
////class Human extends Player{
////    private int x;
////    private int y;
////    private int color;
////    public Human(int color){
////        this.color = color;
////    }
////    @Override
////    public void createMove(int color, Board board) {
////        int x,y;
////        while(true) {
////            Scanner s = new Scanner(System.in);
////            System.out.print("Enter numbers from 1-15,separate x form y by space char (x y)\nEnter coordinates for stone:");
////            x = s.nextInt();
////            y = s.nextInt();
////            if (!(board.validMovement(x, y, color))) ;
////                continue;
////        }
////    }
////}
//class Computer extends Player{
//    private int x;
//    private int y;
//    private int color;
//
//    public Computer(int color){
//        this.color = color;
//    }
//
//    public void createMove(int color, Board board){
//
//    }
//}
