/*
 * IJA 2015: The Labyrinth
 */
package client.board;

/**
 *
 * @author xpospi73, xdress00
 */
public class MazeField {
    
    private final int r, c;
    private MazeCard card = null;
    
    public MazeField(int r, int c){
        this.r = r;
        this.c = c;
    }
        
    public int row(){
        return this.r;
    }
    
    public int col(){
        return this.c;
    }
    
    public MazeCard getCard(){
        return this.card;
    }
    
    public void putCard(MazeCard c){
        this.card = c;
    }
}
