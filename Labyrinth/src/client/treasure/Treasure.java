/*
 * IJA 2015: The Labyrinth
 */
package client.treasure;

import java.io.Serializable;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 * This class represents treasures.
 */
public class Treasure implements Serializable {
   
    /**
     * Card order.
     */
    public final int code;
    /**
     * Actual count of treasures.
     */
    public static int cards;
    /**
     * Picture of the treasure.
     */
    public final int picture;
    
    /**
     * Constructor sets treasures code and picture.
     * @param code represents treasure code.
     * @param pic represents treasure picture.
     */
    public Treasure(int code, int pic){
        this.code = code;
        this.picture = pic;
    }
}