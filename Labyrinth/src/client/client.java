/*
 * IJA 2015: The Labyrinth
 */
package client;

import client.board.*;
import client.undo.*;
import client.view.*;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author xpospi73, xdress00
 */
public class client {
    
    // Main GAME object representing game board.
    private static MazeBoard game = new MazeBoard();
    
    /* Field object for creating field with [row,col] gived by -sRC command.
     * Field object is required by MazeBoard.shift(MazeField field).
     */
    private static MazeField field = null;
    
    private static undo undo;
    // Size of board. Set to 7 by task assignment.
    private static final int size = 7;
    
    // Main function. Set as sturtup main class.
    public static void main(String[] args) {
        if (args.length != 1){
            gui();
        }
        else if("-tui".equals(args[0])){
            tui();
        }
    }
    
    public static void gui(){
        String path;
        path = System.getProperty("user.dir");
        
        menu gameView;
        gameView = new menu();
        
        /*JLabel contentPane = new JLabel();
        try {
            contentPane.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/Labyrinth.jpg"))));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        gameView.setContentPane(contentPane);*/
        
        gameView.getContentPane().setBackground(Color.DARK_GRAY);
        gameView.pack();
        gameView.setVisible(true);
        gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameView.setSize(1000,700);
        gameView.setLocationRelativeTo(null);
        gameView.setResizable(false);
        
    }
    
    public static void tui(){
        // Call function for printing introduction messages.
        printIntroduction();
        // Creating new Maze Board
        game = MazeBoard.createMazeBoard(size);
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
                    game.newGame();
                    undo = new undo();
                    System.out.println("New game has started!");
                }
                else if (input.matches("q|(-q)") == true){
                    break;
                }
                else if (input.matches("^(s|(-s))([0-9]{2})$") == true){
                    /* Getting [row,col] int values. Creating new field object 
                     * with [row,col]. Shifting game board.
                     */
                    undo.storeCommand(input);
                    rc = input.replaceAll("(s|(-s))", "");
                    field = new MazeField(  Character.getNumericValue(rc.charAt(0)),
                                            Character.getNumericValue(rc.charAt(1)));
                    System.out.println( "Shifting to: " + field.row() + 
                                        " - "           + field.col());
                    game.shift(field);                   
                }
                else if (input.matches("u|(-u)") == true){
                    undoLastCommand();
                }
                else if (input.matches("h|(-h)") == true){
                    // Print help - available commands;
                    printHelp();
                }
                else if (input.matches("^(t|(-t))([0-9]{2})$") == true){
                    undo.storeCommand(input);
                    if(game.get(1, 1).getCard() == null) {
                        System.out.println("Nothing to turn!");
                    }
                    else {
                        rc = input.replaceAll("(t|(-t))", "");
                        int row = Character.getNumericValue(rc.charAt(0));
                        int col = Character.getNumericValue(rc.charAt(1));
                        game.get(row,col).getCard().turnRight();
                        System.out.println("Field [" + row + "," + col + "] turned.");
                    }
                }
                else if (input.matches("^(tl|(-tl))([0-9]{2})$") == true){
                    undo.storeCommand(input);
                    if(game.get(1, 1).getCard() == null) {
                        System.out.println("Nothing to turn!");
                    }
                    else {
                        rc = input.replaceAll("(tl|(-tl))", "");
                        int row = Character.getNumericValue(rc.charAt(0));
                        int col = Character.getNumericValue(rc.charAt(1));
                        game.get(row,col).getCard().turnLeft();
                        System.out.println("Field [" + row + "," + col + "] turned left.");
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
        System.out.println("  p / -p   : Print game board");
        System.out.println("  n / -n   : Start new game");
        System.out.println("  q / -q   : Quit game");
        System.out.println("sRC / -sRC : Insert free card to positions [R,C]");
        System.out.println("tRC / -tRC : Turn right specific card on position [R,C]");
        System.out.println("tlRC / -tlRC : Turn right specific card on position [R,C]");
        System.out.println("  h / -h   : Print help");
    }
    
    // Printing introduction and welcome text messages.
    private static void printIntroduction(){
        System.out.println("Welcome to the our application 3. Homework to IJA");
        printHelp();
        System.out.println("Type your command for getting started.");
        System.out.print(">> ");
    }
    
    // Printing actual game board.
    private static void printBoard() {  
        System.out.println();
        // New game does not created yet.
        if(game.get(1, 1).getCard() == null) {
            System.out.println("Nothing to print!");
        }
        // Print board.
        else {
            for(int i = 1;i <= size;i++) {
               System.out.print("  ");  
               System.out.print(i);  
            }
            
            System.out.println();
            System.out.print("   ");
            
            for(int i = 1;i <= size;i++) {
                System.out.print("─ ");  
            }
            System.out.println();

            for(int i = 1; i <= size;i++) {    
                System.out.print(i);
                System.out.print(" |"); 
                for(int j = 1; j <= size;j++) {
                    printChar(game.get(i, j).getCard());
                }
                System.out.println();
            }
            
            System.out.print("   ");
            
            for(int i = 1;i <= size;i++) {
                System.out.print("─ ");  
            }
            System.out.println();
            
            System.out.print("Free Card: |");
            printChar(game.getFreeCard());
            
            System.out.println();
        }
    }
    
    // Print value of field.
    private static void printChar(MazeCard card) {
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
        String undoCommand,rc;
        if(undo.lastCommand > 0) {
            undoCommand = undo.invertCommand(undo.readLastCommand());
            if(undoCommand.matches("^(tl|(-tl))([0-9]{2})$") == true) {
                rc = undoCommand.replaceAll("(tl|(-tl))", "");
                int row = Character.getNumericValue(rc.charAt(0));
                int col = Character.getNumericValue(rc.charAt(1));
                game.get(row,col).getCard().turnLeft();
            }
            else if(undoCommand.matches("^(t|(-t))([0-9]{2})$") == true) {
                rc = undoCommand.replaceAll("(t|(-t))", "");
                int row = Character.getNumericValue(rc.charAt(0));
                int col = Character.getNumericValue(rc.charAt(1));
                game.get(row,col).getCard().turnRight();
            }
            System.out.println("Command " + undo.readLastCommand() + " undone.");
            undo.commands.remove(undo.lastCommand - 1);
            undo.lastCommand--;
        }
        else {
            System.out.println("Nothing to undo.");
        }
    }
}
