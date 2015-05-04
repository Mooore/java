/*
 * IJA 2015: The Labyrinth
 */
package client.game;

import client.board.*;
import client.gui.Player;
import client.treasure.*;
import java.util.Random;

/**
 *
 * @author xpospi73, xdress00
 */
public class Game {
    public static int boardSize, numberOfPlayers, numberOfTreasures;
    
    public static Player player1, player2, player3, player4;
    public static int currentPlayer;
    
    public static Treasure[] pack;
    public static int[][] treasuresPositions;
    
    // Main GAME object representing game board.
    public static MazeBoard mazeboard;
    
    /* Field object for creating field with [row,col] gived by -sRC command.
     * Field object is required by MazeBoard.shift(MazeField field).
     */
    private static MazeField field = null;
    //public static undo undo;
    
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
            
            player1.setPosition(1, 1);
            player2.setPosition(boardSize, boardSize);
        }
        else if(numberOfPlayers == 3) {
            player1 = new Player(1);
            player2 = new Player(2);
            player3 = new Player(3);
            player4 = null;
            
            player1.setPosition(1, 1);
            player2.setPosition(1, boardSize);
            player3.setPosition(boardSize, 1);
        }
        else {
            player1 = new Player(1);
            player2 = new Player(2);
            player3 = new Player(3);
            player4 = new Player(4);
            
            player1.setPosition(1, 1);
            player2.setPosition(1, boardSize);
            player3.setPosition(boardSize, 1);
            player4.setPosition(boardSize, boardSize);
        }
    }
    
    public void startNewGame(){
       // undo = new undo();
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
            
            if (currentPlayer == 1) {
                player1.undo.storeCommand("s" + r + ":" + c);
            }
            else if (currentPlayer == 2) {
                player2.undo.storeCommand("s" + r + ":" + c);
            }
            else if (currentPlayer == 3) {
                player3.undo.storeCommand("s" + r + ":" + c);
            }
            else if (currentPlayer == 4) {
                player4.undo.storeCommand("s" + r + ":" + c);
            }
        }  
    }
    
    public void turnRight(int r, int c){
        if (currentPlayer == 1) {
            player1.undo.storeCommand("t" + r + ":" + c);
        }
        else if (currentPlayer == 2) {
            player2.undo.storeCommand("t" + r + ":" + c);
        }
        else if (currentPlayer == 3) {
            player3.undo.storeCommand("t" + r + ":" + c);
        }
        else if (currentPlayer == 4) {
            player4.undo.storeCommand("t" + r + ":" + c);
        }
        
        mazeboard.get(r, c).getCard().turnRight();
    }
    
    public void turnLeft(int r, int c){
        if (currentPlayer == 1) {
            player1.undo.storeCommand("tl" + r + ":" + c);
        }
        else if (currentPlayer == 2) {
            player2.undo.storeCommand("tl" + r + ":" + c);
        }
        else if (currentPlayer == 3) {
            player3.undo.storeCommand("tl" + r + ":" + c);
        }
        else if (currentPlayer == 4) {
            player4.undo.storeCommand("tl" + r + ":" + c);
        }
        mazeboard.get(r, c).getCard().turnLeft();
    }
    
    public void turnRightFreeCard(){
        if (currentPlayer == 1) {
            player1.undo.storeCommand("tf");
        }
        else if (currentPlayer == 2) {
            player2.undo.storeCommand("tf");
        }
        else if (currentPlayer == 3) {
            player3.undo.storeCommand("tf");
        }
        else if (currentPlayer == 4) {
            player4.undo.storeCommand("tf");
        }
        mazeboard.getFreeCard().turnRight();
    }
    
    public void turnLeftFreeCard(){
        if (currentPlayer == 1) {
            player1.undo.storeCommand("tlf");
        }
        else if (currentPlayer == 2) {
            player2.undo.storeCommand("tlf");
        }
        else if (currentPlayer == 3) {
            player3.undo.storeCommand("tlf");
        }
        else if (currentPlayer == 4) {
            player4.undo.storeCommand("tlf");
        }
        mazeboard.getFreeCard().turnLeft();
    }
    
    public void go(String goX, String goY, int FromX, int FromY) {
        if (currentPlayer == 1) {
            player1.undo.storeCommand("go" + goX + ":" + goY + "-" + FromX + ":" + FromY);
        }
        else if (currentPlayer == 2) {
            player2.undo.storeCommand("go" + goX + ":" + goY + "-" + FromX + ":" + FromY);
        }
        else if (currentPlayer == 3) {
            player3.undo.storeCommand("go" + goX + ":" + goY + "-" + FromX + ":" + FromY);
        }
        else if (currentPlayer == 4) {
            player4.undo.storeCommand("go" + goX + ":" + goY + "-" + FromX + ":" + FromY);
        }
    }
    
    public void undo() {
        undoCommand(false);
 
    }
    public String undoCommand(boolean tuiflag){
        String undoCommand = "",rc;
        System.out.println(undoCommand);
        if (currentPlayer == 1) {
            if(player1.undoRight) {
                undoCommand = player1.undo.invertCommand(player1.undo.readLastCommand());
                player1.undoRight = false;
            }
        }
        else if (currentPlayer == 2) {
            if(player2.undoRight) {
                undoCommand = player2.undo.invertCommand(player2.undo.readLastCommand());
                player2.undoRight = false;
            }
        }
        else if (currentPlayer == 3) {
            if(player3.undoRight) {
                undoCommand = player3.undo.invertCommand(player3.undo.readLastCommand());
                player3.undoRight = false;
            }
        }
        else if (currentPlayer == 4) {
            if(player1.undoRight) {
                undoCommand = player4.undo.invertCommand(player4.undo.readLastCommand());
                player4.undoRight = false;
            }
        }
        
        if(undoCommand.matches("^(tl|(-tl))(([0-9])([0-9])?([:])([0-9])([0-9])?)$") == true) {
            rc = undoCommand.replaceAll("(tl|(-tl))", "");
            int row, col;
            if(rc.length() == 3){
                row = Character.getNumericValue(rc.charAt(0));
                col = Character.getNumericValue(rc.charAt(2));
            }
            else if(rc.length() == 5){
                row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                col = Character.getNumericValue(rc.charAt(3) + rc.charAt(4));
            }
            else {
                if(":".equals(String.valueOf(rc.charAt(1)))){
                    row = Character.getNumericValue(rc.charAt(0));
                    col = Character.getNumericValue(rc.charAt(2) + rc.charAt(3));
                }
                else {
                    row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                    col = Character.getNumericValue(rc.charAt(3));
                }
            }
            mazeboard.get(row,col).getCard().turnLeft();
        }
        else if(undoCommand.matches("^(t|(-t))(([0-9])([0-9])?([:])([0-9])([0-9])?)$") == true) {
            rc = undoCommand.replaceAll("(t|(-t))", "");
            int row, col;
            if(rc.length() == 3){
                row = Character.getNumericValue(rc.charAt(0));
                col = Character.getNumericValue(rc.charAt(2));
            }
            else if(rc.length() == 5){
                row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                col = Character.getNumericValue(rc.charAt(3) + rc.charAt(4));
            }
            else {
                if(":".equals(String.valueOf(rc.charAt(1)))){
                    row = Character.getNumericValue(rc.charAt(0));
                    col = Character.getNumericValue(rc.charAt(2) + rc.charAt(3));
                }
                else {
                    row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                    col = Character.getNumericValue(rc.charAt(3));
                }
            }
            mazeboard.get(row,col).getCard().turnRight();
        }
        else if(undoCommand.matches("^(s|(-s))(([0-9])([0-9])?([:])([0-9])([0-9])?)$") == true) {
            //System.out.println(undoCommand);
            rc = undoCommand.replaceAll("(s|(-s))", "");
            int row, col;
            if(rc.length() == 3){
                row = Character.getNumericValue(rc.charAt(0));
                col = Character.getNumericValue(rc.charAt(2));
            }
            else if(rc.length() == 5){
                row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                col = Character.getNumericValue(rc.charAt(3) + rc.charAt(4));
            }
            else {
                if(":".equals(String.valueOf(rc.charAt(1)))){
                    row = Character.getNumericValue(rc.charAt(0));
                    col = Character.getNumericValue(rc.charAt(2) + rc.charAt(3));
                }
                else {
                    row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                    col = Character.getNumericValue(rc.charAt(3));
                }
            }
            mazeboard.shift(field); 
        }
        else if(undoCommand.matches("^(tf|(-tf))$") == true) {
            mazeboard.getFreeCard().turnRight();
        }
        else if(undoCommand.matches("^(tlf|(-tlf))$") == true) {
            mazeboard.getFreeCard().turnLeft();
        }
        else if(undoCommand.matches("^(go|(-go))([0-9])([0-9])?([:])([0-9])([0-9])?([-])([0-9])([0-9])?([:])([0-9])([0-9])?$") == true) {
            rc = undoCommand.replaceAll("(go|(-go))", "");
            int goX, goY, FromX, FromY;
            goX = goY = FromX = FromY = 0;
            String go[] = rc.split("-");
            for(int i = 0; i < 2; i++){
                if (go[i].equals("-") != true){
                    String part[] = go[i].split(":");
                    for(int j = 0; j < 2; j++){
                        if((i == 0) && (j == 0)){
                            goX = Integer.parseInt(part[j]);
                        }
                        else if((i == 0) && (j == 1)){
                            goY = Integer.parseInt(part[j]);
                        }
                        else if((i == 1) && (j == 0)){
                            FromX = Integer.parseInt(part[j]);
                        }
                        else if((i == 1) && (j == 1)){
                            FromY = Integer.parseInt(part[j]);
                        }
                    }
                }
            }         
        }
        
        String lastcommand = "";
        if (currentPlayer == 1) {
            lastcommand = player1.undo.readLastCommand();
            player1.undo.commands.remove(player1.undo.lastCommand - 1);
            player1.undo.lastCommand--;
        }
        else if (currentPlayer == 2) {
            lastcommand = player2.undo.readLastCommand();
            player2.undo.commands.remove(player2.undo.lastCommand - 1);
            player2.undo.lastCommand--;
        }
        else if (currentPlayer == 3) {
            lastcommand = player3.undo.readLastCommand();
            player3.undo.commands.remove(player3.undo.lastCommand - 1);
            player3.undo.lastCommand--;
        }
        else if (currentPlayer == 4) {
            lastcommand = player4.undo.readLastCommand();
            player4.undo.commands.remove(player4.undo.lastCommand - 1);
            player4.undo.lastCommand--;
        }
        
        if(tuiflag){
            return lastcommand;
        }
        
        //return null;
        return lastcommand;
    }
    
    public static void createSet(int cards){
        Treasure.cards = cards;
        Random randomGenerator = new Random();
        
        pack = new Treasure[Treasure.cards + 1];
        
        boolean pictures[] = new boolean[31];
        for(int i = 0; i < 31; i++){
            pictures[i] = false;
        }
        
        for (int i = 1; i <= Treasure.cards; i++){
            int randomInt;
            do {
                randomInt = randomGenerator.nextInt(31);
            } while((pictures[randomInt] == true) || (randomInt == 0));
            pack[i] = new Treasure(i, randomInt);
            pictures[randomInt] = true;
        }
    }
    
    public static void placeTreasures(){
        Random randomGenerator = new Random();
        Random randomGenerator2 = new Random();
        treasuresPositions = new int[2][numberOfTreasures + 1];
        boolean flag = false;
        
        for (int i = 1; i <= numberOfTreasures; i++){
            treasuresPositions[0][i] = 0;
            treasuresPositions[1][i] = 0;
        }
        
        for (int i = 1; i <= numberOfTreasures; i++){
            int randomInt, randomInt2;
            do {
                randomInt = randomGenerator.nextInt(boardSize + 1);
                randomInt2 = randomGenerator2.nextInt(boardSize + 1);
                
                if ((randomInt > 0) && (randomInt2 > 0)){
                    for(int j = 0; j <= i; j++){
                        if (treasuresPositions[0][j] == randomInt){
                            if (treasuresPositions[1][j] == randomInt2){
                                flag = false;
                                break;
                            }
                            else {
                                flag = true;
                            }
                        }
                        else {
                            flag = true;
                        }
                    }
                }
            } while((flag == false) || (randomInt == 0) || (randomInt2 == 0));
            treasuresPositions[0][i] = randomInt;
            treasuresPositions[1][i] = randomInt2;
            flag = false;
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
