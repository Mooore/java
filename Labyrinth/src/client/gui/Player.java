/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import client.treasure.Treasure;
import client.undo.undo;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 * This class represents a game players.
 */
public class Player implements Serializable {
    private int playerNumber;
    private Color playerColor;
    /**
     * Undo class for undo command.
     */
    public undo undo;
    /**
     * Row of player's position.
     */
    public int positionR;
    /**
     * Column of player's position.
     */
    public int positionC;
    
    /**
     * Count of obtained treasures.
     */
    private int obtainedTreasures;
    /**
     * Treasure which player have to find next.
     */
    public Treasure assignedTreasure = null;
    
    /**
     * Represents, if player has already turned in his turn.
     */
    public boolean turnCommand = false;
    /**
     * Represents, if player has already shifted in his turn.
     */
    public boolean shiftCommand = false;
    /**
     * Represents, if player has already used go in his turn.
     */
    public boolean goCommand = false;
    /**
     * Represents, if player can make undo command.
     */
    public boolean undoRight = true;
    
    /**
     * Constructor initialize a new player.
     * @param playerNumber represents player's number.
     */
    public Player(int playerNumber) {
        setPlayerNumber(playerNumber);
        setPlayerColor();
        obtainedTreasures = 0;
        undo = new undo();
    }
    
    /**
     * This method gets the player number.
     * @return number of the player.
     */
    public int getPlayerNumber() {
        return this.playerNumber;
    }
    
    /**
     * This method gets the player color.
     * @return player's color.
     */
    public Color getPlayerColor() {
        return this.playerColor; 
    }
    
    /**
     * This method gets the number of obtained treasures.
     * @return number of obtained treasures.
     */
    public int getObtainedTreasures(){
        return obtainedTreasures;
    }
    
    /**
     * This method inrease number of obtained treasures.
     */
    public void icreaseObtainedTreasures(){
        obtainedTreasures++;
    }
   
    /**
     * This method sets the player number.
     * @param number player's number.
     */
    private void setPlayerNumber(int number) {
        if ((number > 4) || (number < 1)) {
            throw new IllegalArgumentException("Player's number must be in range 2 to 4.");
        }
        else {
            this.playerNumber = number;
        }
    }
    
    /**
     * This method sets the player's color.
     */
    private void setPlayerColor() {
        int number = getPlayerNumber();
        
        if(number == 1) {
            this.playerColor = Color.GREEN;
        }
        else if(number == 2) {
            this.playerColor = Color.RED;
        }
        else if(number == 3) {
            this.playerColor = Color.CYAN;
        }
        else if(number == 4) {
            this.playerColor = Color.YELLOW;
        }
    }
    
    /**
     * This method is for assign treasure to player.
     * @param tr assigned treasure.
     */
    public void assignTreasure(Treasure tr){
        this.assignedTreasure = tr;
    }
    
    /**
     * This method gets assigned treasure of player.
     * @return assigned treasure.
     */
    public Treasure getAssignedTreasure(){
        return this.assignedTreasure;
    }
    
    /**
     * This method removes assigned treasure of player.
     */
    public void removeTreasure(){
        this.assignedTreasure = null;
    }
    
    /**
     * This method sets the player position.
     * @param r represents row of position.
     * @param c represents column of position.
     */
    public void setPosition(int r, int c){
        this.positionR = r;
        this.positionC = c;
    }
    
}
