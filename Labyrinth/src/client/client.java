/*
 * IJA 2015: Homework 3
 */
package client;

import client.board.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    
    // Size of board. Set to 7 by task assignment.
    private static final int size = 7;
    
    // Main function. Set as sturtup main class.
    public static void main(String[] args) {
        // Call function for printing introduction messages.
        printIntroduction();
        // Creating new Maze Board
        game = MazeBoard.createMazeBoard(size);
        try {
            // Buffered Reader and strings for reading from STDIN
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input, rc;
            
            // Main loop
            while((input = br.readLine()) != null){
                if (input.matches("p|(-p)") == true){
                    System.out.println("Printing game board...");
                    
                    // TODO: Print actual game board.
                    printBoard();

                }
                else if (input.matches("n|(-n)") == true){
                    // Starting new game
                    game.newGame();
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
                    field = new MazeField(  Character.getNumericValue(rc.charAt(0)),
                                            Character.getNumericValue(rc.charAt(1)));
                    System.out.println( "Shifting to: " + field.row() + 
                                        " - "           + field.col());
                    game.shift(field);
                }
                else if (input.matches("h|(-h)") == true){
                    // Print help - available commands;
                    printHelp();
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
        System.out.println("  h / -h   : Print help");
    }
    
    // Printing introduction and welcome text messages.
    private static void printIntroduction(){
        System.out.println("Welcome to the our application 3. Homework to IJA");
        printHelp();
        System.out.println("Type your command for getting started.");
        System.out.print(">> ");
    }
    
    private static void printBoard() {
      //  System.out.println("board");
        
        System.out.println();
        if(game.get(1, 1).getCard() == null) {
            System.out.println("Nothing to print!");
        }
        else {
            for(int i = 1; i <= size;i++) {
                for(int j = 1; j <= size;j++) {
                    if(game.get(i, j).getCard().canGo(MazeCard.CANGO.LEFT) == true) {
                        if(game.get(i, j).getCard().canGo(MazeCard.CANGO.UP) == false) {
                            if(game.get(i, j).getCard().canGo(MazeCard.CANGO.RIGHT) == true) {
                                if(game.get(i, j).getCard().canGo(MazeCard.CANGO.DOWN) == false) {
                                    System.out.print("═");
                                }
                                else if(game.get(i, j).getCard().canGo(MazeCard.CANGO.DOWN) == true) {
                                    System.out.print("╦");
                                }
                            }
                            else if(game.get(i, j).getCard().canGo(MazeCard.CANGO.RIGHT) == false) {
                                if(game.get(i, j).getCard().canGo(MazeCard.CANGO.DOWN) == true) {
                                    System.out.print("╗");
                                }
                            }
                        }           
                        else if(game.get(i, j).getCard().canGo(MazeCard.CANGO.UP) == true) {
                            if(game.get(i, j).getCard().canGo(MazeCard.CANGO.RIGHT) == true) {
                                if(game.get(i, j).getCard().canGo(MazeCard.CANGO.DOWN) == false) {
                                    System.out.print("╩");
                                }
                            }
                            else if(game.get(i, j).getCard().canGo(MazeCard.CANGO.RIGHT) == false) {
                                if(game.get(i, j).getCard().canGo(MazeCard.CANGO.DOWN) == true) {
                                    System.out.print("╣");
                                }
                                else if(game.get(i, j).getCard().canGo(MazeCard.CANGO.DOWN) == false) {
                                    System.out.print("╝");
                                }
                            }
                        }
                    }
                    else if(game.get(i, j).getCard().canGo(MazeCard.CANGO.LEFT) == false) {
                        if(game.get(i, j).getCard().canGo(MazeCard.CANGO.UP) == true) {
                            if(game.get(i, j).getCard().canGo(MazeCard.CANGO.RIGHT) == false) {
                                if(game.get(i, j).getCard().canGo(MazeCard.CANGO.DOWN) == true) {
                                    System.out.print("║");
                                }
                            }
                            else if(game.get(i, j).getCard().canGo(MazeCard.CANGO.RIGHT) == true) {
                                if(game.get(i, j).getCard().canGo(MazeCard.CANGO.DOWN) == true) {
                                    System.out.print("╠");
                                }
                                else if(game.get(i, j).getCard().canGo(MazeCard.CANGO.DOWN) == false) {
                                    System.out.print("╚");
                                }
                            }                           
                        } 
                        else if(game.get(i, j).getCard().canGo(MazeCard.CANGO.UP) == false) {
                            if(game.get(i, j).getCard().canGo(MazeCard.CANGO.RIGHT) == true) {
                                if(game.get(i, j).getCard().canGo(MazeCard.CANGO.DOWN) == true) {
                                    System.out.print("╔");
                                }
                            }
                        }
                    }
                }
                System.out.println();
            }
            System.out.println();           

            if(game.getFreeCard().canGo(MazeCard.CANGO.LEFT) == true) {
                if(game.getFreeCard().canGo(MazeCard.CANGO.UP) == false) {
                    if(game.getFreeCard().canGo(MazeCard.CANGO.RIGHT) == true) {
                        if(game.getFreeCard().canGo(MazeCard.CANGO.DOWN) == false) {
                            System.out.print("Free Card ═");
                        }
                        else if(game.getFreeCard().canGo(MazeCard.CANGO.DOWN) == true) {
                            System.out.print("Free Card ╦");
                        }
                    }
                    else if(game.getFreeCard().canGo(MazeCard.CANGO.RIGHT) == false) {
                        if(game.getFreeCard().canGo(MazeCard.CANGO.DOWN) == true) {
                            System.out.print("Free Card ╗");
                        }
                    }
                }           
                else if(game.getFreeCard().canGo(MazeCard.CANGO.UP) == true) {
                    if(game.getFreeCard().canGo(MazeCard.CANGO.RIGHT) == true) {
                        if(game.getFreeCard().canGo(MazeCard.CANGO.DOWN) == false) {
                            System.out.print("Free Card ╩");
                        }
                    }
                    else if(game.getFreeCard().canGo(MazeCard.CANGO.RIGHT) == false) {
                        if(game.getFreeCard().canGo(MazeCard.CANGO.DOWN) == true) {
                            System.out.print("Free Card ╣");
                        }
                        else if(game.getFreeCard().canGo(MazeCard.CANGO.DOWN) == false) {
                            System.out.print("Free Card ╝");
                        }
                    }
                }
            }
            else if(game.getFreeCard().canGo(MazeCard.CANGO.LEFT) == false) {
                if(game.getFreeCard().canGo(MazeCard.CANGO.UP) == true) {
                    if(game.getFreeCard().canGo(MazeCard.CANGO.RIGHT) == false) {
                        if(game.getFreeCard().canGo(MazeCard.CANGO.DOWN) == true) {
                            System.out.print("Free Card ║");
                        }
                    }
                    else if(game.getFreeCard().canGo(MazeCard.CANGO.RIGHT) == true) {
                        if(game.getFreeCard().canGo(MazeCard.CANGO.DOWN) == true) {
                            System.out.print("Free Card ╠");
                        }
                        else if(game.getFreeCard().canGo(MazeCard.CANGO.DOWN) == false) {
                            System.out.print("Free Card ╚");
                        }
                    }                          
                } 
                else if(game.getFreeCard().canGo(MazeCard.CANGO.UP) == false) {
                    if(game.getFreeCard().canGo(MazeCard.CANGO.RIGHT) == true) {
                        if(game.getFreeCard().canGo(MazeCard.CANGO.DOWN) == true) {
                            System.out.print("Free Card ╔");
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}
