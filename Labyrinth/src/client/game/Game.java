/*
 * IJA 2015: The Labyrinth
 */
package client.game;

import client.board.*;
import client.undo.undo;

/**
 *
 * @author xpospi73, xdress00
 */
public class Game {
    public int boardSize, numberOfPlayers, numberOfTreasures;
    
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
    }
    
    public void startNewGame(){
        //TODO vytvorit hrace - vytvorit tridu pro jednoho hrace ...
        mazeboard.newGame();
        undo = new undo();
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
            System.out.println(undoCommand);
            rc = undoCommand.replaceAll("(s|(-s))", "");
            field = new MazeField(  Character.getNumericValue(rc.charAt(0)),
                                    Character.getNumericValue(rc.charAt(1)));
            mazeboard.shift(field); 
        }
        String lastcommand = undo.readLastCommand();
        undo.commands.remove(undo.lastCommand - 1);
        undo.lastCommand--;
        
        if(tuiflag){
            return lastcommand;
        }
        
        return null;
    }
}
