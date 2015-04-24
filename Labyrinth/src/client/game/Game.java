/*
 * IJA 2015: The Labyrinth
 */
package client.game;

/**
 *
 * @author xpospi73, xdress00
 */
public class Game {
    public int boardSize, numberOfPlayers, numberOfTreasures;
    
    public Game(int size, int players, int treasures){
        boardSize = size;
        numberOfPlayers = players;
        numberOfTreasures = treasures;
    }
}
