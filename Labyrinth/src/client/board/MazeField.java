/*
 * IJA 2015: The Labyrinth
 */
package client.board;

import java.io.Serializable;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 * This class represents game field. Each field have to be identify by row and column.
 * Rocks are placed on the fields.
 */
public class MazeField implements Serializable {
    
    private final int r, c;
    private MazeCard card = null;
    
    /**
     * Constructor sets row and column of the field.
     * @param r represents number of row.
     * @param c represents number of column.
     */
    public MazeField(int r, int c){
        this.r = r;
        this.c = c;
    }
    
    /**
     * This method is for getting row of the field.
     * @return number of the row.
     */
    public int row(){
        return this.r;
    }
    
    /**
     * This method is for getting column of the field.
     * @return number of the column.
     */
    public int col(){
        return this.c;
    }
    
    /**
     * This method is for getting the rock on the field.
     * @return the rock which was placed on the field.
     */
    public MazeCard getCard(){
        return this.card;
    }
    
    /**
     * This method puts the rock on the field.
     * @param c represents a rock, which is placing on the field.
     */
    public void putCard(MazeCard c){
        this.card = c;
    }
}
