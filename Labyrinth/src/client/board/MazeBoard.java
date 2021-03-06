/*
 * IJA 2015: The Labyrinth
 */
package client.board;

import java.io.Serializable;
import java.util.Random;
/**
 *
 * @author xpospi73, xdress00
 */

/**
 * MazeBoard class represents a game board. The game board is compound by fields.
 * Also enable a new game generation and fields shifting.
 */
public class MazeBoard implements Serializable {
    
    /** 
     * Field of game fields.
     */
    public static MazeField[][] field;
    
    /**
     * Represents a freecard.
     */
    public static MazeCard freeCard = null;
    
    /**
     * For number generation.
     */
    public static Random randomGenerator = new Random();
    
    public static int size;
    
    /**
     * This method creates a game board.
     * @param n specify a game board size.
     * @return the game board.
     */
    public static MazeBoard createMazeBoard(int n){
        MazeBoard board = new MazeBoard();
        size = n;
        field = new MazeField[size + 1][size + 1];
        for (int r = 1; r <= size; r++){
            for (int c = 1; c <= size; c++){
                field[r][c] = new MazeField(r,c);
            }
        }
        return board;
    }
    
    /**
     * This method generates a new rock of random type and rotation.
     */
    private MazeCard getRandomNewCard(MazeCard c){
        int randomInt = randomGenerator.nextInt(3);
        switch (randomInt) {
            case 0: c = MazeCard.create("C");
                    for (int i = 0; i < randomGenerator.nextInt(5); i++){
                        c.turnRight();
                    }
                    break;
            case 1: c = MazeCard.create("L");
                    for (int i = 0; i < randomGenerator.nextInt(3); i++){
                        c.turnRight();
                    }
                    break;
            case 2: c = MazeCard.create("F");
                    for (int i = 0; i < randomGenerator.nextInt(5); i++){
                        c.turnRight();
                    }
                    break;
        }
        return c;
    }
    
    /**
     * This method generates a new rock of specific type and rotation.
     */
    private MazeCard getSpecificNewCard(MazeCard c, String type){
        switch (type) {
            case "C": c = MazeCard.create("C");
                    break;
            case "L": c = MazeCard.create("L");
                    break;
            case "F": c = MazeCard.create("F");
                    break;
        }
        return c;
    }
    
    /**
     * This method creates a new game and generates fields, rocks and free rock.  
     */
    public void newGame(){
        for (int r = 1; r <= size; r++){
            for (int c = 1; c <= size; c++){
                MazeCard card = new MazeCard();
                if ((r == 1) && (c == 1)){
                    card = getSpecificNewCard(card, "C");
                    card.turnLeft();
                    card.turnLeft();
                }
                else if ((r == 1) && (c == size)){
                    card = getSpecificNewCard(card, "C");
                    card.turnLeft();
                }
                else if ((r == size) && (c == 1)){
                    card = getSpecificNewCard(card, "C");
                    card.turnRight();
                }
                else if ((r == size) && (c == size)){
                    card = getSpecificNewCard(card, "C");
                }
                else if ((r % 2 == 1) && (c % 2 == 1)){
                    card = getSpecificNewCard(card, "F");
                    if (r == 1) {
                        card.turnRight();
                        card.turnRight();
                    }
                    else if (c == 1) {
                        card.turnRight();
                    }
                    else if (c == size) {
                        card.turnLeft();
                    }
                    else if (r != size){
                        for (int i = 0; i < randomGenerator.nextInt(4); i++){
                            card.turnRight();
                        }
                    }
                }
                else {
                    card = getRandomNewCard(card);
                }
                field[r][c].putCard(card);
            }
        }
        freeCard = new MazeCard();
        freeCard = getRandomNewCard(freeCard);
    }
    
    /**
     * This method is for getting a spefic field.
     * @param r represents row of the field.
     * @param c represents column of the field.
     * @return the field or null (error).
     */
    public MazeField get(int r, int c){
        return ((r > size) || (c > size)) ? null : field[r][c];
    }
    
    /**
     * This method is for getting a free rock.
     * @return the free rock.
     */
    public MazeCard getFreeCard(){
        return freeCard;
    }
   
    /**
     * This method is for field shifting.
     * @param mf represents a position of the field, which is going to be shifted.
     */
    public void shift(MazeField mf){
        if ((mf.row() == 1) && ((mf.col() > 0) && (mf.col() < size)) && (mf.col() % 2 == 0)){
            // Prvni radek [1, mf.col()]
            MazeField[] tmpfield = new MazeField[size + 1];
            for (int r = 1; r <= size; r++){
                tmpfield[r] = new MazeField(0,0);
                tmpfield[r].putCard(field[r][mf.col()].getCard());
            }
            field[1][mf.col()].putCard(freeCard);
            freeCard = tmpfield[size].getCard();
            for (int r = 1; r < size; r++){
                field[r + 1][mf.col()].putCard(tmpfield[r].getCard());
            }
            //System.out.println("Shifting done!");
        }
        else if ((mf.row() == size) && ((mf.col() > 0) && (mf.col() < size)) && (mf.col() % 2 == 0)){
            // Posledni radek [size, mf.col()]
            MazeField[] tmpfield = new MazeField[size + 1];
            for (int r = size; r >= 1; r--){
                tmpfield[r] = new MazeField(0,0);
                tmpfield[r].putCard(field[r][mf.col()].getCard());
            }
            field[size][mf.col()].putCard(freeCard);
            freeCard = tmpfield[1].getCard();
            for (int r = size; r > 1; r--){
                field[r - 1][mf.col()].putCard(tmpfield[r].getCard());
            }
            //System.out.println("Shifting done!");
        }
        else if ((mf.col() == 1) && ((mf.row() > 0) && (mf.row() < size)) && (mf.row() % 2 == 0)){
            // Prvni sloupec [mf.row(), 1]
            MazeField[] tmpfield = new MazeField[size + 1];
            for (int c = 1; c <= size; c++){
                tmpfield[c] = new MazeField(0,0);
                tmpfield[c].putCard(field[mf.row()][c].getCard());
            }
            field[mf.row()][1].putCard(freeCard);
            freeCard = tmpfield[size].getCard();
            for (int c = 1; c < size; c++){
                field[mf.row()][c + 1].putCard(tmpfield[c].getCard());
            }
            //System.out.println("Shifting done!");
        }
        else if ((mf.col() == size) && ((mf.row() > 0) && (mf.row() < size)) && (mf.row() % 2 == 0)){
            // Posledni sloupec [mf.row(), size]
            MazeField[] tmpfield = new MazeField[size + 1];
            for (int c = size; c >= 1; c--){
                tmpfield[c] = new MazeField(0,0);
                tmpfield[c].putCard(field[mf.row()][c].getCard());
            }
            field[mf.row()][size].putCard(freeCard);
            freeCard = tmpfield[1].getCard();
            for (int c = size; c > 1; c--){
                field[mf.row()][c - 1].putCard(tmpfield[c].getCard());
            }
            //System.out.println("Shifting done!");
        }
    }
}
