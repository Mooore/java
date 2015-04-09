/*
 * IJA 2015: Homework 3
 */
package client.board;

/**
 *
 * @author xpospi73, xdress00
 */
public class MazeCard {
    
    boolean canGoArr[];
    
    public static enum CANGO {
        LEFT, UP, RIGHT, DOWN;
    }
    
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
    
    public boolean canGo(MazeCard.CANGO dir){
        return canGoArr[dir.ordinal()];
    }
    
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
}
