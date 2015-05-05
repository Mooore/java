/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import client.treasure.Treasure;
import client.undo.undo;
import java.awt.Color;

/**
 *
 * @author xpospi73, xdress00
 */
public class Player {
    private int playerNumber;
    private Color playerColor;
    public undo undo;
    public int positionR;
    public int positionC;
    
    private int obtainedTreasures;
    private Treasure assignedTreasure = null;
        
    public boolean turnCommand = false;
    public boolean shiftCommand = false;
    public boolean goCommand = false;
    public boolean undoRight = true;
    
    public Player(int playerNumber) {
        setPlayerNumber(playerNumber);
        setPlayerColor();
        obtainedTreasures = 0;
        undo = new undo();
    }
    
    public int getPlayerNumber() {
        return this.playerNumber;
    }
    
    public Color getPlayerColor() {
        return this.playerColor; 
    }
    
    public int getObtainedTreasures(){
        return obtainedTreasures;
    }
    
    public void icreaseObtainedTreasures(){
        obtainedTreasures++;
    }
    
    public void disposeObtainedTreasures(){
        obtainedTreasures = -1;
    }
    
    private void setPlayerNumber(int number) {
        if ((number > 4) || (number < 1)) {
            throw new IllegalArgumentException("Player's number must be in range 2 to 4.");
        }
        else {
            this.playerNumber = number;
        }
    }
    
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
    
    public void assignTreasure(Treasure tr){
        this.assignedTreasure = tr;
    }
    
    public Treasure getAssignedTreasure(){
        return this.assignedTreasure;
    }
    
    public void removeTreasure(){
        this.assignedTreasure = null;
    }
    
    public void setPosition(int r, int c){
        this.positionR = r;
        this.positionC = c;
    }
    
}
