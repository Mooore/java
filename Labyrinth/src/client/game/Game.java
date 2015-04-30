/*
 * IJA 2015: The Labyrinth
 */
package client.game;

import client.board.*;
import client.gui.Player;
import client.undo.undo;
import client.treasure.*;
import java.util.Random;

/**
 *
 * @author xpospi73, xdress00
 */
public class Game {
    public static int boardSize, numberOfPlayers, numberOfTreasures;
    
    public static Player player1, player2, player3, player4;
    public int currentPlayer;
    
    public static Treasure[] pack;
    
    // Main GAME object representing game board.
    public static MazeBoard mazeboard;
    
    /* Field object for creating field with [row,col] gived by -sRC command.
     * Field object is required by MazeBoard.shift(MazeField field).
     */
    private static MazeField field = null;
    public static undo undo;
    
    public Game(int size, int players, int treasures){
        boardSize = size;
        numberOfPlayers = players;
        numberOfTreasures = treasures;
        mazeboard = new MazeBoard();
        mazeboard = MazeBoard.createMazeBoard(boardSize);
        createSet(numberOfTreasures);
    }
    
    public void setPlayers() {
        currentPlayer = 1;
        if(numberOfPlayers == 2) {
            player1 = new Player(1);
            player2 = new Player(2);
            player3 = null;
            player4 = null;
        }
        else if(numberOfPlayers == 3) {
            player1 = new Player(1);
            player2 = new Player(2);
            player3 = new Player(3);
            player4 = null;
        }
        else {
            player1 = new Player(1);
            player2 = new Player(2);
            player3 = new Player(3);
            player4 = new Player(4);
        }
    }
    
    public void startNewGame(){
        undo = new undo();
        setPlayers();
        mazeboard.newGame();
    }
    
    public void shift(int r, int c){
        field = new MazeField(r, c);
        mazeboard.shift(field);      
        
        if (((field.row() == 1) && ((field.col() > 0) && (field.col() < boardSize)) && (field.col() % 2 == 0)) ||
           ((field.row() == boardSize) && ((field.col() > 0) && (field.col() < boardSize)) && (field.col() % 2 == 0)) ||
           ((field.col() == 1) && ((field.row() > 0) && (field.row() < boardSize)) && (field.row() % 2 == 0)) ||
           ((field.col() == boardSize) && ((field.row() > 0) && (field.row() < boardSize)) && (field.row() % 2 == 0))) {
            
            undo.storeCommand("s" + r + c);
        }  
    }
    
    public void turnRight(int r, int c){
        undo.storeCommand("t" + r + c);
        mazeboard.get(r, c).getCard().turnRight();
    }
    
    public void turnLeft(int r, int c){
        undo.storeCommand("tl" + r + c);
        mazeboard.get(r, c).getCard().turnLeft();
    }
    
    public void turnRightFreeCard(){
        undo.storeCommand("tf");
        mazeboard.getFreeCard().turnRight();
    }
    
    public void turnLeftFreeCard(){
        undo.storeCommand("tlf");
        mazeboard.getFreeCard().turnLeft();
    }
    
    public String undoCommand(boolean tuiflag){
        String undoCommand,rc;
        
        undoCommand = undo.invertCommand(undo.readLastCommand());
        if(undoCommand.matches("^(tl|(-tl))([0-9]{2})$") == true) {
            rc = undoCommand.replaceAll("(tl|(-tl))", "");
            int row = Character.getNumericValue(rc.charAt(0));
            int col = Character.getNumericValue(rc.charAt(1));
            mazeboard.get(row,col).getCard().turnLeft();
        }
        else if(undoCommand.matches("^(t|(-t))([0-9]{2})$") == true) {
            rc = undoCommand.replaceAll("(t|(-t))", "");
            int row = Character.getNumericValue(rc.charAt(0));
            int col = Character.getNumericValue(rc.charAt(1));
            mazeboard.get(row,col).getCard().turnRight();
        }
        else if(undoCommand.matches("^(s|(-s))([0-9]{2})$") == true) {
            //System.out.println(undoCommand);
            rc = undoCommand.replaceAll("(s|(-s))", "");
            field = new MazeField(  Character.getNumericValue(rc.charAt(0)),
                                    Character.getNumericValue(rc.charAt(1)));
            mazeboard.shift(field); 
        }
        else if(undoCommand.matches("^(tf|(-tf))$") == true) {
            mazeboard.getFreeCard().turnRight();
        }
        else if(undoCommand.matches("^(tlf|(-tlf))$") == true) {
            mazeboard.getFreeCard().turnLeft();
        }
        
        String lastcommand = undo.readLastCommand();
        undo.commands.remove(undo.lastCommand - 1);
        undo.lastCommand--;
        
        if(tuiflag){
            return lastcommand;
        }
        
        return null;
    }
    
    public static void createSet(int cards){
        Treasure.cards = cards;
        Random randomGenerator = new Random();
        
        pack = new Treasure[Treasure.cards];
        
        boolean pictures[] = new boolean[31];
        for(int i = 0; i < 31; i++){
            pictures[i] = false;
        }
        
        for (int i = 0; i < Treasure.cards; i++){
            int randomInt;
            do {
                randomInt = randomGenerator.nextInt(31);
            } while((pictures[randomInt] == true) || (randomInt == 0));
            pack[i] = new Treasure(i, randomInt);
            pictures[randomInt] = true;
            //System.out.println(Integer.toString(i) + " : " + Integer.toString(randomInt));
        }
    }
        
    public static Treasure popTreasure(){
        Treasure tr = pack[0];              // pocet karet - 1 (indexace v poli od 0)
        
        for (int i = 0; i < Treasure.cards; i++){                    
            if ((i + 1) < Treasure.cards){
                pack[i] = pack[i + 1];
            }
        }
        Treasure.cards--;
        
        return tr;
    }
}
