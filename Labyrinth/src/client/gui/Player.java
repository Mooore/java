/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import java.awt.Color;

/**
 *
 * @author xpospi73, xdress00
 */
public class Player {
    private int playerNumber;
    private Color playerColor;
    private int obtainedTreasures;
    //private Treasure
    
    public Player(int playerNumber) {
        setPlayerNumber(playerNumber);
        setPlayerColor();
        obtainedTreasures = 0;
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
            this.playerColor = Color.BLUE;
        }
        else if(number == 4) {
            this.playerColor = Color.YELLOW;
        }
    }
}
