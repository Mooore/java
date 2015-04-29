/*
 * IJA 2015: The Labyrinth
 */
package client.tui;

import client.board.*;
import client.game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author xpospi73, xdress00
 */
public class Tui {
    public static Game game;
    
    public int size;
    
    public Tui(int n){
        size = n;
    }
    
    public int getSize() {
        return size;
    }
    
    public void tuiRun(){
        printIntroduction();
    
        game = new Game(size, 2, 12);
        
        try {
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(System.in));
            String input, rc;
            
            // Main loop
            while((input = br.readLine()) != null){
                if (input.matches("p|(-p)") == true){
                    System.out.println("Printing game board...");
                    
                    printBoard();
                }
                else if (input.matches("n|(-n)") == true){
                    // Starting new game
                    game.startNewGame();
                    
                    System.out.println("New game has started!");
                }
                else if (input.matches("q|(-q)") == true){
                    break;
                }
                else if (input.matches("^(s|(-s))([0-9]{2})$") == true){
                    /* Getting [row,col] int values. Creating new field object 
                     * with [row,col]. Shifting game board.
                     */                   
                    rc = input.replaceAll("(s|(-s))", "");
                    int r = Character.getNumericValue(rc.charAt(0));
                    int c = Character.getNumericValue(rc.charAt(1));
                    System.out.println( "Shifting to: " + r + 
                                        " - "           + c);
                    game.shift(r, c);
                }
                else if (input.matches("u|(-u)") == true){
                    undoLastCommand();
                }
                else if (input.matches("h|(-h)") == true){
                    // Print help - available commands;
                    printHelp();
                }
                else if (input.matches("^(t|(-t))([0-9]{2})$") == true){
                    if(Game.mazeboard.get(1, 1).getCard() == null) {
                        System.out.println("Nothing to turn!");
                    }
                    else {
                        rc = input.replaceAll("(t|(-t))", "");
                        int r = Character.getNumericValue(rc.charAt(0));
                        int c = Character.getNumericValue(rc.charAt(1));
                        game.turnRight(r, c);
                        System.out.println("Field [" + r + "," + c + "] turned.");
                    }
                }
                else if (input.matches("^(tl|(-tl))([0-9]{2})$") == true){
                    if(Game.mazeboard.get(1, 1).getCard() == null) {
                        System.out.println("Nothing to turn!");
                    }
                    else {
                        rc = input.replaceAll("(tl|(-tl))", "");
                        int r = Character.getNumericValue(rc.charAt(0));
                        int c = Character.getNumericValue(rc.charAt(1));
                        game.turnLeft(r, c);
                        System.out.println("Field [" + r + "," + c + "] turned left.");
                    }
                }
                else {
                    System.out.println("Wrong command. Try again or type h / -h for help.");
                }
                System.out.print(">> ");
            }
        } 
        catch (IOException io){
            io.printStackTrace();
        }
    }
    
    // Printing help - available commands.
    private static void printHelp(){
        System.out.println("Available commands:");
        System.out.println("  p / -p        : Print game board");
        System.out.println("  n / -n        : Start new game");
        System.out.println("  q / -q        : Quit game");
        System.out.println("sRC / -sRC      : Insert free card to positions [R,C]");
        System.out.println("tRC / -tRC      : Turn right specific card on position [R,C]");
        System.out.println("tlRC / -tlRC    : Turn left specific card on position [R,C]");
        System.out.println("  u / -u        : Undo last command");
        System.out.println("  h / -h        : Print help");
    }
    
    // Printing introduction and welcome text messages.
    private static void printIntroduction(){
        System.out.println("Welcome to the our TUI version of project to IJA");
        printHelp();
        System.out.println("Type your command for getting started.");
        System.out.print(">> ");
    }
    
    // Printing actual game board.
    private static void printBoard() {  
        System.out.println();
        // New game does not created yet.
        if(Game.mazeboard.get(1, 1).getCard() == null) {
            System.out.println("Nothing to print!");
        }
        // Print board.
        else {
            for(int i = 1;i <= game.boardSize;i++) {
               System.out.print("  ");  
               System.out.print(i);  
            }
            
            System.out.println();
            System.out.print("   ");
            
            for(int i = 1;i <= game.boardSize;i++) {
                System.out.print("─ ");  
            }
            System.out.println();

            for(int i = 1; i <= game.boardSize;i++) {    
                System.out.print(i);
                System.out.print(" |"); 
                for(int j = 1; j <= game.boardSize;j++) {
                    printChar(Game.mazeboard.get(i, j).getCard());
                }
                System.out.println();
            }
            
            System.out.print("   ");
            
            for(int i = 1;i <= game.boardSize;i++) {
                System.out.print("─ ");  
            }
            System.out.println();
            
            System.out.print("Free Card: |");
            printChar(Game.mazeboard.getFreeCard());
            
            System.out.println();
        }
    }
    
    // Print value of field.
    public static void printChar(MazeCard card) {
        if(card.canGo(MazeCard.CANGO.LEFT) == true) {
            if(card.canGo(MazeCard.CANGO.UP) == false) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        System.out.print("═|");
                    }
                    else if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        System.out.print("╦|");
                    }
                }
                else if(card.canGo(MazeCard.CANGO.RIGHT) == false) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        System.out.print("╗|");
                    }
                }
            }           
            else if(card.canGo(MazeCard.CANGO.UP) == true) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        System.out.print("╩|");
                    }
                }
                else if(card.canGo(MazeCard.CANGO.RIGHT) == false) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        System.out.print("╣|");
                    }
                    else if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        System.out.print("╝|");
                    }
                }
            }
        }
        else if(card.canGo(MazeCard.CANGO.LEFT) == false) {
            if(card.canGo(MazeCard.CANGO.UP) == true) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == false) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        System.out.print("║|");
                    }
                }
                else if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        System.out.print("╠|");
                    }
                    else if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        System.out.print("╚|");
                    }
                }                           
            } 
            else if(card.canGo(MazeCard.CANGO.UP) == false) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        System.out.print("╔|");
                    }
                }
            }
        }
    }   
    
    public static void undoLastCommand() {
        if(Game.undo.lastCommand > 0) {
            String lastCommand = game.undoCommand(true);         // boolean tuiflag
            System.out.println("Command " + lastCommand + " undone.");
        }
        else {
            System.out.println("Nothing to undo.");
        }
    }
}
