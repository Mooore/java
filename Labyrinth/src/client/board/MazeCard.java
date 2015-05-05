/*
 * IJA 2015: The Labyrinth
 */
package client.board;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 * MazeCard class represents one rock, which was placed on game field. 
 */
public class MazeCard {
    
    boolean canGoArr[];
    
    /**
     * This enum represents directions, in which can be leave.
     */
    public static enum CANGO {
        LEFT, UP, RIGHT, DOWN;
    }
    
    /**
     * This method creates a new rock of given type.
     * @param type Specific type of rock.
     * @return the created rock.
     */
    public static MazeCard create(String type){
        MazeCard square = new MazeCard();
        
        if (type.contentEquals("C") == true){
            square.canGoArr = new boolean[]{true,true,false,false};
        }
        else if (type.contentEquals("L") == true){
            square.canGoArr = new boolean[]{true,false,true,false};
        }
        else if (type.contentEquals("F") == true){
            square.canGoArr = new boolean[]{true,true,true,false};
        }
        else {
            throw new IllegalArgumentException();
        }
        return square;
    }
    
    /**
     * This method is for checking, if rock can be leave in specific direction.
     * @param dir represents direction.
     * @return true or false.
     */
    public boolean canGo(MazeCard.CANGO dir){
        return canGoArr[dir.ordinal()];
    }
    
    /**
     * This method turns the rock right by 90°.
     */
    public void turnRight(){
        boolean tmp[] = new boolean[4];
        for (int i = 0; i <= 3; i++){
            tmp[i] = canGoArr[i];
        }
        for (int i = 0; i <= 3; i++){
            if (i == 0) {
                canGoArr[i] = tmp[3];
            }
            else {
                canGoArr[i] = tmp[i - 1];
            }
	}
    }
    
    /**
     * This method turns the rock left by 90°.
     */
    public void turnLeft(){
        boolean tmp[] = new boolean[4];
        for (int i = 0; i <= 3; i++){
            tmp[i] = canGoArr[i];
        }
        for (int i = 0; i <= 3; i++){
            if (i == 3) {
                canGoArr[i] = tmp[0];
            }
            else {
                canGoArr[i] = tmp[i + 1];
            }
	}
    }
}
