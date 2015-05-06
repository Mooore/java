/*
 * IJA 2015: The Labyrinth
 */
package client.treasure;

import java.io.Serializable;

/**
 *
 * @author xpospi73, xdress00
 */
public class Treasure implements Serializable {
    
    public final int code;
    public static int cards;
    public final int picture;
    
    public Treasure(int code, int pic){
        this.code = code;
        this.picture = pic;
    }
}