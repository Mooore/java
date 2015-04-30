/*
 * IJA 2015: The Labyrinth
 */
package client.treasure;

/**
 *
 * @author xpospi73, xdress00
 */
public class Treasure {
    
    public final int code;
    private static int cards;
    
    public static Treasure[] set;

    private Treasure(int code){
        this.code = code;
    }
    
    public static void createSet(int cards){
        Treasure.cards = cards;
        Treasure.set = new Treasure[Treasure.cards];
        for (int i = 0; i < Treasure.cards; i++){
            Treasure.set[i] = new Treasure(i);
        }
    }
    
    public static Treasure getTreasure(int code){
        return ((0 <= code) && (code < Treasure.cards)) ? Treasure.set[code] : null;
    }
    
}