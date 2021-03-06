/*
 * IJA 2015: The Labyrinth
 */
package client.game;

import client.board.*;
import client.gui.Gui;
import static client.gui.Gui.game;
import static client.gui.Gui.gameboard;
import client.gui.Player;
import client.treasure.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 * This class represent main functionality of gameplay.
 */
public class Game implements Serializable {
    /**
     * Main attributes of the game.
     */
    public static int boardSize, numberOfPlayers, numberOfTreasures;
    
    /**
     * Objects for players.
     */
    public static Player player1, player2, player3, player4;
    
    /**
     * Indicates current playing player.
     */
    public static int currentPlayer;
    
    /**
     * List of Treasure objects. Represents pack of treasures in the game.
     */
    public static List<Treasure> pack;
    
    /**
     * Array with saved treasure positions.
     */
    public static int[][] treasuresPositions;
    
    /**
     * Main object of game board.
     */
    public static MazeBoard mazeboard;
    
    /**
     * Field for use as free card for shifting actions.
     */
    public static MazeField field = null;
    
    /**
     * Constructor for sets main attributes of the game.
     * @param size Size of board.
     * @param players Number of players.
     * @param treasures Number of treasures.
     */
    public Game(int size, int players, int treasures){
        boardSize = size;
        numberOfPlayers = players;
        numberOfTreasures = treasures;
        mazeboard = new MazeBoard();
        mazeboard = MazeBoard.createMazeBoard(boardSize);
        createSet(numberOfTreasures);
        placeTreasures();
    }
    
    /**
     * Sets number of players and asigns treasures.
     */
    public void setPlayers() {
        currentPlayer = 1;
        if(numberOfPlayers == 2) {
            player1 = new Player(1);
            player2 = new Player(2);
            player3 = null;
            player4 = null;
            
            player1.setPosition(1, 1);
            player2.setPosition(boardSize, boardSize);
            
            player1.assignTreasure(popTreasure());
            player2.assignTreasure(popTreasure());
        }
        else if(numberOfPlayers == 3) {
            player1 = new Player(1);
            player2 = new Player(2);
            player3 = new Player(3);
            player4 = null;
            
            player1.setPosition(1, 1);
            player2.setPosition(1, boardSize);
            player3.setPosition(boardSize, 1);
            
            player1.assignTreasure(popTreasure());
            player2.assignTreasure(popTreasure());
            player3.assignTreasure(popTreasure());
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
            
            player1.assignTreasure(popTreasure());
            player2.assignTreasure(popTreasure());
            player3.assignTreasure(popTreasure());
            player4.assignTreasure(popTreasure());
        }
    }
    
    /**
     * Starts new game. Sets players and generates game board.
     */
    public void startNewGame(){
        setPlayers();
        mazeboard.newGame();
    }
    
    /**
     * Action - shifts row or column by selected row and column coordinates.
     * @param r Row coordinate.
     * @param c Column coordinate.
     */
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
        
        if(c == 1){                 // shift right
            for (int player = 1; player <= numberOfPlayers; player++){
                switch(player){
                    case 1: for(int i = 1; i <= boardSize; i++){
                                if((player1.positionR == r) && (player1.positionC == i)){
                                    if(i == boardSize){
                                        player1.positionC = 1;
                                    }
                                    else {
                                        player1.positionC++;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 2: for(int i = 1; i <= boardSize; i++){
                                if((player2.positionR == r) && (player2.positionC == i)){
                                    if(i == boardSize){
                                        player2.positionC = 1;
                                    }
                                    else {
                                        player2.positionC++;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 3: for(int i = 1; i <= boardSize; i++){
                                if((player3.positionR == r) && (player3.positionC == i)){
                                    if(i == boardSize){
                                        player3.positionC = 1;
                                    }
                                    else {
                                        player3.positionC++;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 4: for(int i = 1; i <= boardSize; i++){
                                if((player4.positionR == r) && (player4.positionC == i)){
                                    if(i == boardSize){
                                        player4.positionC = 1;
                                    }
                                    else {
                                        player4.positionC++;
                                    }
                                    break;
                                }
                            }
                            break;
                }
            }
        }
        else if(c == boardSize){                 // shift left
            for (int player = 1; player <= numberOfPlayers; player++){
                switch(player){
                    case 1: for(int i = 1; i <= boardSize; i++){
                                if((player1.positionR == r) && (player1.positionC == i)){
                                    if(i == 1){
                                        player1.positionC = boardSize;
                                    }
                                    else {
                                        player1.positionC--;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 2: for(int i = 1; i <= boardSize; i++){
                                if((player2.positionR == r) && (player2.positionC == i)){
                                    if(i == 1){
                                        player2.positionC = boardSize;
                                    }
                                    else {
                                        player2.positionC--;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 3: for(int i = 1; i <= boardSize; i++){
                                if((player3.positionR == r) && (player3.positionC == i)){
                                    if(i == 1){
                                        player3.positionC = boardSize;
                                    }
                                    else {
                                        player3.positionC--;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 4: for(int i = 1; i <= boardSize; i++){
                                if((player4.positionR == r) && (player4.positionC == i)){
                                    if(i == 1){
                                        player4.positionC = boardSize;
                                    }
                                    else {
                                        player4.positionC--;
                                    }
                                    break;
                                }
                            }
                            break;
                }
            }
        }
        else if(r == 1){                 // shift down
            for (int player = 1; player <= numberOfPlayers; player++){
                switch(player){
                    case 1: for(int i = 1; i <= boardSize; i++){
                                if((player1.positionR == i) && (player1.positionC == c)){
                                    if(i == boardSize){
                                        player1.positionR = 1;
                                    }
                                    else {
                                        player1.positionR++;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 2: for(int i = 1; i <= boardSize; i++){
                                if((player2.positionR == i) && (player2.positionC == c)){
                                    if(i == boardSize){
                                        player2.positionR = 1;
                                    }
                                    else {
                                        player2.positionR++;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 3: for(int i = 1; i <= boardSize; i++){
                                if((player3.positionR == i) && (player3.positionC == c)){
                                    if(i == boardSize){
                                        player3.positionR = 1;
                                    }
                                    else {
                                        player3.positionR++;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 4: for(int i = 1; i <= boardSize; i++){
                                if((player4.positionR == i) && (player4.positionC == c)){
                                    if(i == boardSize){
                                        player4.positionR = 1;
                                    }
                                    else {
                                        player4.positionR++;
                                    }
                                    break;
                                }
                            }
                            break;
                }
            }
        }
        else if(r == boardSize){                 // shift up
            for (int player = 1; player <= numberOfPlayers; player++){
                switch(player){
                    case 1: for(int i = 1; i <= boardSize; i++){
                                if((player1.positionR == i) && (player1.positionC == c)){
                                    if(i == 1){
                                        player1.positionR = boardSize;
                                    }
                                    else {
                                        player1.positionR--;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 2: for(int i = 1; i <= boardSize; i++){
                                if((player2.positionR == i) && (player2.positionC == c)){
                                    if(i == 1){
                                        player2.positionR = boardSize;
                                    }
                                    else {
                                        player2.positionR--;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 3: for(int i = 1; i <= boardSize; i++){
                                if((player3.positionR == i) && (player3.positionC == c)){
                                    if(i == 1){
                                        player3.positionR = boardSize;
                                    }
                                    else {
                                        player3.positionR--;
                                    }
                                    break;
                                }
                            }
                            break;
                    case 4: for(int i = 1; i <= boardSize; i++){
                                if((player4.positionR == i) && (player4.positionC == c)){
                                    if(i == 1){
                                        player4.positionR = boardSize;
                                    }
                                    else {
                                        player4.positionR--;
                                    }
                                    break;
                                }
                            }
                            break;
                }
            }
        }
    }
    
    /**
     * Action - turn selected card to right by coordinates.
     * @param r Row coordinate.
     * @param c Column coordinate.
     */
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
    
    /**
     * Action - turn selected card to left by coordinates.
     * @param r Row coordinate.
     * @param c Column coordinate.
     */
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
    
    /**
     * Action - turn free card to right.
     */
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
    
    /**
     * Action - turn free card to left.
     */
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
    
    /**
     * Action - go try to go to new coordinates.
     * @param goX Goal row coordinate.
     * @param goY Goal column coordinate.
     * @param FromX Start row coordinate.
     * @param FromY Start column coordinate.
     * @return Returns false when player cannot move to this coordinates. True otherwise.
     */
    public boolean go(String goX, String goY, int FromX, int FromY) {
        int x, y;
        
        if (currentPlayer == 1) {
            if(canGo(player1, Integer.parseInt(goX), Integer.parseInt(goY))){
                player1.setPosition(Integer.parseInt(goX), Integer.parseInt(goY));
                
                int treasureCode = player1.getAssignedTreasure().code;
                x = Game.treasuresPositions[0][treasureCode];
                y = Game.treasuresPositions[1][treasureCode];
                
                if((player1.positionR == x) && (player1.positionC == y)){
                    player1.icreaseObtainedTreasures();
                    if(pack.size() > 0){
                        player1.assignTreasure(popTreasure());
                    }
                }
                
                player1.undo.storeCommand("go" + goX + ":" + goY + "-" + FromX + ":" + FromY);
                return true;
            }
        }
        else if (currentPlayer == 2) {
            if(canGo(player2, Integer.parseInt(goX), Integer.parseInt(goY))){
                player2.setPosition(Integer.parseInt(goX), Integer.parseInt(goY));
                
                int treasureCode = player2.getAssignedTreasure().code;
                x = Game.treasuresPositions[0][treasureCode];
                y = Game.treasuresPositions[1][treasureCode];
                
                if((player2.positionR == x) && (player2.positionC == y)){
                    player2.icreaseObtainedTreasures();
                    if(pack.size() > 0){
                        player2.assignTreasure(popTreasure());
                    }
                }
                
                player2.undo.storeCommand("go" + goX + ":" + goY + "-" + FromX + ":" + FromY);
                return true;
            }
        }
        else if (currentPlayer == 3) {
            if(canGo(player3, Integer.parseInt(goX), Integer.parseInt(goY))){
                player3.setPosition(Integer.parseInt(goX), Integer.parseInt(goY));
                
                int treasureCode = player3.getAssignedTreasure().code;
                x = Game.treasuresPositions[0][treasureCode];
                y = Game.treasuresPositions[1][treasureCode];
                
                if((player3.positionR == x) && (player3.positionC == y)){
                    player3.icreaseObtainedTreasures();
                    if(pack.size() > 0){
                        player3.assignTreasure(popTreasure());
                    }
                }
                
                player3.undo.storeCommand("go" + goX + ":" + goY + "-" + FromX + ":" + FromY);
                return true;
            }
        }
        else if (currentPlayer == 4) {
            if(canGo(player4, Integer.parseInt(goX), Integer.parseInt(goY))){
                player4.setPosition(Integer.parseInt(goX), Integer.parseInt(goY));
                
                int treasureCode = player4.getAssignedTreasure().code;
                x = Game.treasuresPositions[0][treasureCode];
                y = Game.treasuresPositions[1][treasureCode];
                
                if((player4.positionR == x) && (player4.positionC == y)){
                    player4.icreaseObtainedTreasures();
                    if(pack.size() > 0){
                        player4.assignTreasure(popTreasure());
                    }
                }
                
                player4.undo.storeCommand("go" + goX + ":" + goY + "-" + FromX + ":" + FromY);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method for Action - go. Searching for way to move using stack.
     * @param player Object of player.
     * @param x Row coordinate.
     * @param y Column coordinate.
     * @return Returns false when way doesn't exists. True otherwise.
     */
    public boolean canGo(Player player, int x, int y){
        boolean cango = false, canwrite = true;
        int lastIndex;
        
        List<Integer> coordsX, coordsY, historyX, historyY;
        List<MazeCard.CANGO> direction;
        
        coordsX = new ArrayList<>();
        coordsY = new ArrayList<>();
        historyX = new ArrayList<>();
        historyY = new ArrayList<>();
        direction = new ArrayList<>();
        
        historyX.add(player.positionR);
        historyY.add(player.positionC);
        for(int i = 0; i < 4; i++){
            coordsX.add(player.positionR);
            coordsY.add(player.positionC);
            switch(i){
                case 0: direction.add(MazeCard.CANGO.DOWN);
                        break;
                case 1: direction.add(MazeCard.CANGO.RIGHT);
                        break;
                case 2: direction.add(MazeCard.CANGO.UP);
                        break;
                case 3: direction.add(MazeCard.CANGO.LEFT);
                        break;
            }
        }
                
        int tryX, tryY;
        MazeCard.CANGO tryDir;
        
        while((cango == false) && (coordsX.isEmpty() == false)){
            lastIndex = coordsX.size() - 1;
            tryX = coordsX.remove(lastIndex);
            tryY = coordsY.remove(lastIndex);
            tryDir = direction.remove(lastIndex);
            
            if(mazeboard.get(tryX, tryY).getCard().canGo(tryDir)){
                if(tryDir == MazeCard.CANGO.LEFT){
                    if((tryY - 1) >= 1) {
                        if(mazeboard.get(tryX, tryY - 1).getCard().canGo(MazeCard.CANGO.RIGHT)){
                            int h = 0;
                            while (h < historyX.size()){
                                if ((historyX.get(h) == tryX) && (historyY.get(h) == (tryY - 1))){
                                    canwrite = false;
                                    break;
                                }
                                h++;
                            }
                            if (canwrite) {
                                for (int j = 0; j < 3; j++){
                                    coordsX.add(tryX);
                                    coordsY.add(tryY - 1);
                                    switch(j){
                                        case 0: direction.add(MazeCard.CANGO.DOWN);
                                                break;
                                        case 1: direction.add(MazeCard.CANGO.UP);
                                                break;
                                        case 2: direction.add(MazeCard.CANGO.LEFT);
                                                break;
                                    }
                                }
                            }
                            canwrite = true;
                        }
                    }
                }
                else if(tryDir == MazeCard.CANGO.UP){
                    if((tryX - 1) >= 1) {
                        if(mazeboard.get(tryX - 1, tryY).getCard().canGo(MazeCard.CANGO.DOWN)){
                            int h = 0;
                            while (h < historyX.size()){
                                if ((historyX.get(h) == (tryX - 1)) && (historyY.get(h) == tryY)){
                                    canwrite = false;
                                    break;
                                }
                                h++;
                            }
                            if (canwrite) {
                                for (int j = 0; j < 3; j++){
                                    coordsX.add(tryX - 1);
                                    coordsY.add(tryY);
                                    switch(j){
                                        case 0: direction.add(MazeCard.CANGO.RIGHT);
                                                break;
                                        case 1: direction.add(MazeCard.CANGO.UP);
                                                break;
                                        case 2: direction.add(MazeCard.CANGO.LEFT);
                                                break;
                                    }
                                }
                            }
                            canwrite = true;
                        }
                    }
                }
                else if(tryDir == MazeCard.CANGO.RIGHT){
                    if((tryY + 1) <= boardSize) {
                        if(mazeboard.get(tryX, tryY + 1).getCard().canGo(MazeCard.CANGO.LEFT)){
                            int h = 0;
                            while (h < historyX.size()){
                                if ((historyX.get(h) == tryX) && (historyY.get(h) == (tryY + 1))){
                                    canwrite = false;
                                    break;
                                }
                                h++;
                            }
                            if (canwrite) {
                                for (int j = 0; j < 3; j++){
                                    coordsX.add(tryX);
                                    coordsY.add(tryY + 1);
                                    switch(j){
                                        case 0: direction.add(MazeCard.CANGO.DOWN);
                                                break;
                                        case 1: direction.add(MazeCard.CANGO.RIGHT);
                                                break;
                                        case 2: direction.add(MazeCard.CANGO.UP);
                                                break;
                                    }
                                }
                            }
                            canwrite = true;
                        }
                    }
                }
                else /* if(tryDir == MazeCard.CANGO.DOWN)*/ {
                    if((tryX + 1) <= boardSize) {
                        if(mazeboard.get(tryX + 1, tryY).getCard().canGo(MazeCard.CANGO.UP)){
                            int h = 0;
                            while (h < historyX.size()){
                                if ((historyX.get(h) == (tryX + 1)) && (historyY.get(h) == tryY)){
                                    canwrite = false;
                                    break;
                                }
                                h++;
                            }
                            if (canwrite) {
                                for (int j = 0; j < 3; j++){
                                    coordsX.add(tryX + 1);
                                    coordsY.add(tryY);
                                    switch(j){
                                        case 0: direction.add(MazeCard.CANGO.DOWN);
                                                break;
                                        case 1: direction.add(MazeCard.CANGO.RIGHT);
                                                break;
                                        case 2: direction.add(MazeCard.CANGO.LEFT);
                                                break;
                                    }
                                }
                            }
                            canwrite = true;
                        }
                    }
                }
            }
            
            historyX.add(tryX);
            historyY.add(tryY);
            
            if((x == tryX) && (y == tryY)){
                cango = true;
            }
        }
        
        return cango;
    }
    
    /**
     * Selecting player for undo command.
     */
    public void undo() {
        if(currentPlayer == 1) {
            if(player1.undo.lastCommand > 0) {
                if(player1.undoRight) {
                    undoCommand(false);
                    player1.undoRight = false;
                }
            }
        }
        else if(currentPlayer == 2) {
            if(player2.undo.lastCommand > 0) {
                if(player2.undoRight) {
                    undoCommand(false);
                    player2.undoRight = false;
                }
            }
        }
        else if(currentPlayer == 3) {
            if(player3.undo.lastCommand > 0) {
                if(player3.undoRight) {
                    undoCommand(false);
                    player3.undoRight = false;
                }
            }
        }
        else if(currentPlayer == 4) {
            if(player4.undo.lastCommand > 0) {
                if(player4.undoRight) {
                    undoCommand(false);
                    player4.undoRight = false;
                }
            }
        }
    }
    
    /**
     * Action - undo command.
     * @param tuiflag Old parameter. Used for text user interface.
     * @return 
     */
    public String undoCommand(boolean tuiflag){
        String undoCommand = "",rc;
        int goX, goY, FromX, FromY;
        goX = goY = FromX = FromY = 0;
        System.out.println(undoCommand);
        if (currentPlayer == 1) {
            if(player1.undoRight) {
                undoCommand = player1.undo.invertCommand(player1.undo.readLastCommand());
            }
        }
        else if (currentPlayer == 2) {
            if(player2.undoRight) {
                undoCommand = player2.undo.invertCommand(player2.undo.readLastCommand());
            }
        }
        else if (currentPlayer == 3) {
            if(player3.undoRight) {
                undoCommand = player3.undo.invertCommand(player3.undo.readLastCommand());
            }
        }
        else if (currentPlayer == 4) {
            if(player1.undoRight) {
                undoCommand = player4.undo.invertCommand(player4.undo.readLastCommand());
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
            
            if(currentPlayer == 1) {
                player1.turnCommand = false;
            }
            else if(currentPlayer == 2) {
                player2.turnCommand = false;
            }
            else if(currentPlayer == 3) {
                player3.turnCommand = false;
            }
            else if(currentPlayer == 4) {
                player4.turnCommand = false;
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
            
            if(currentPlayer == 1) {
                player1.turnCommand = false;
            }
            else if(currentPlayer == 2) {
                player2.turnCommand = false;
            }
            else if(currentPlayer == 3) {
                player3.turnCommand = false;
            }
            else if(currentPlayer == 4) {
                player4.turnCommand = false;
            }
            
            mazeboard.get(row,col).getCard().turnRight();
        }
        else if(undoCommand.matches("^(s|(-s))(([0-9])([0-9])?([:])([0-9])([0-9])?)$") == true) {
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
            MazeField fieldToShift = new MazeField(row,col);
            mazeboard.shift(fieldToShift); 
            if(currentPlayer == 1) {
                player1.shiftCommand = false;
            }
            else if(currentPlayer == 2) {
                player2.shiftCommand = false;
            }
            else if(currentPlayer == 3) {
                player3.shiftCommand = false;
            }
            else if(currentPlayer == 4) {
                player4.shiftCommand = false;
            }
        }
        else if(undoCommand.matches("^(tf|(-tf))$") == true) {
            mazeboard.getFreeCard().turnRight();
            if(currentPlayer == 1) {
                player1.turnCommand = false;
            }
            else if(currentPlayer == 2) {
                player2.turnCommand = false;
            }
            else if(currentPlayer == 3) {
                player3.turnCommand = false;
            }
            else if(currentPlayer == 4) {
                player4.turnCommand = false;
            }
        }
        else if(undoCommand.matches("^(tlf|(-tlf))$") == true) {
            mazeboard.getFreeCard().turnLeft();
            if(currentPlayer == 1) {
                player1.turnCommand = false;
            }
            else if(currentPlayer == 2) {
                player2.turnCommand = false;
            }
            else if(currentPlayer == 3) {
                player3.turnCommand = false;
            }
            else if(currentPlayer == 4) {
                player4.turnCommand = false;
            }
        }
        else if(undoCommand.matches("^(go|(-go))([0-9])([0-9])?([:])([0-9])([0-9])?([-])([0-9])([0-9])?([:])([0-9])([0-9])?$") == true) {
            rc = undoCommand.replaceAll("(go|(-go))", "");
            
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
            if(lastcommand.matches("^(go|(-go))([0-9])([0-9])?([:])([0-9])([0-9])?([-])([0-9])([0-9])?([:])([0-9])([0-9])?$") == true) {
                player1.positionR = goY;
                player1.positionC = goX;          
                player1.goCommand = false;
            }
        }
        else if (currentPlayer == 2) {
            lastcommand = player2.undo.readLastCommand();
            player2.undo.commands.remove(player2.undo.lastCommand - 1);
            player2.undo.lastCommand--;
            if(lastcommand.matches("^(go|(-go))([0-9])([0-9])?([:])([0-9])([0-9])?([-])([0-9])([0-9])?([:])([0-9])([0-9])?$") == true) {
                player2.positionR = goY;
                player2.positionC = goX;          
                player2.goCommand = false;
            }
        }
        else if (currentPlayer == 3) {
            lastcommand = player3.undo.readLastCommand();
            player3.undo.commands.remove(player3.undo.lastCommand - 1);
            player3.undo.lastCommand--;
            if(lastcommand.matches("^(go|(-go))([0-9])([0-9])?([:])([0-9])([0-9])?([-])([0-9])([0-9])?([:])([0-9])([0-9])?$") == true) {
                player3.positionR = goY;
                player3.positionC = goX;          
                player3.goCommand = false;
            }
        }
        else if (currentPlayer == 4) {
            lastcommand = player4.undo.readLastCommand();
            player4.undo.commands.remove(player4.undo.lastCommand - 1);
            player4.undo.lastCommand--;
            if(lastcommand.matches("^(go|(-go))([0-9])([0-9])?([:])([0-9])([0-9])?([-])([0-9])([0-9])?([:])([0-9])([0-9])?$") == true) {
                player4.positionR = goY;
                player4.positionC = goX;          
                player4.goCommand = false;
            }
        }   
        
        if(tuiflag){
            return lastcommand;
        }
        
        return lastcommand;
    }
    
    /**
     * Creating set of treasures.
     * @param cards Number of treasures.
     */
    public static void createSet(int cards){
        pack = new ArrayList<>();
        Treasure.cards = cards;
        Random randomGenerator = new Random();
        
        boolean pictures[] = new boolean[31];
        for(int i = 0; i < 31; i++){
            pictures[i] = false;
        }
        
        for (int i = 0; i < Treasure.cards; i++){
            int randomInt;
            do {
                randomInt = randomGenerator.nextInt(31);
            } while((pictures[randomInt] == true) || (randomInt == 0));
            pack.add(new Treasure(i, randomInt));
            pictures[randomInt] = true;
        }
    }
    
    /**
     * Places treasures to random coordinates.
     */
    public static void placeTreasures(){
        Random randomGenerator = new Random();
        Random randomGenerator2 = new Random();
        treasuresPositions = new int[2][numberOfTreasures + 1];
        boolean flag = false;
        
        for (int i = 0; i < numberOfTreasures; i++){
            treasuresPositions[0][i] = 0;
            treasuresPositions[1][i] = 0;
        }
        
        for (int i = 0; i < numberOfTreasures; i++){
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

    /**
     * Pop treasure from pack.
     * @return Object of treasure.
     */
    public static Treasure popTreasure(){
        Treasure tr = pack.remove(Treasure.cards - 1);
        Treasure.cards = pack.size();
        return tr;
    }
    
    /**
     * Function for save game.
     */
    public static void saveGame(){
        File folder = new File(Gui.path + "/examples/");
        File[] listOfFiles = folder.listFiles();
        
        long min = Long.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < 5; i++){
            if(listOfFiles[i].lastModified() < min){
                index = i;
                min = listOfFiles[i].lastModified();
            }
        }
        String filename = listOfFiles[index].getName();
        
        JOptionPane.showMessageDialog(null,"The game has been saved into GAME SAVED " + String.valueOf(index + 1)); 
        
        try {
            FileOutputStream saveFile = new FileOutputStream(Gui.path + "/examples/" + filename);
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(game);
            save.writeObject(currentPlayer);
            save.writeObject(numberOfPlayers);
            save.writeObject(numberOfTreasures);
            save.writeObject(boardSize);
            //save.writeObject(pack);
            save.writeObject(treasuresPositions);
            save.writeObject(gameboard);
            save.writeObject(player1);
            save.writeObject(player2);
            save.writeObject(player3);
            save.writeObject(player4);
            save.writeObject(mazeboard);
            save.writeObject(MazeBoard.field);
            save.writeObject(MazeBoard.freeCard);
            save.writeObject(MazeBoard.size);
            save.writeObject(field);
            save.writeObject(Treasure.cards);
            save.writeObject(player1.undo);
            save.writeObject(player1.assignedTreasure);
            save.writeObject(player2.undo);
            save.writeObject(player2.assignedTreasure);
            if(numberOfPlayers == 3){
                save.writeObject(player3.undo);
                save.writeObject(player3.assignedTreasure);
            }
            if(numberOfPlayers == 4){
                save.writeObject(player3.undo);
                save.writeObject(player3.assignedTreasure);
                save.writeObject(player4.undo);
                save.writeObject(player4.assignedTreasure);
            }
            
            save.writeObject(pack.size());
            for(int i = 0; i < pack.size(); i++){
                save.writeObject(pack.get(i));
            }
                        
            save.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
