/*
 * IJA 2015: The Labyrinth
 */
package client.board;

import java.util.Random;
/**
 *
 * @author xpospi73, xdress00
 */
public class MazeBoard {
    
    public static MazeField[][] field;
    public static MazeCard freeCard = null;
    public static Random randomGenerator = new Random();
    
    private static int size;
    
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
    
    private MazeCard getNewCard(MazeCard c){
        int randomInt = randomGenerator.nextInt(3);
        switch (randomInt) {
            case 0: c = MazeCard.create("C");
                    break;
            case 1: c = MazeCard.create("L");
                    break;
            case 2: c = MazeCard.create("F");
                    break;
        }
        return c;
    }
    
    public void newGame(){
        for (int r = 1; r <= size; r++){
            for (int c = 1; c <= size; c++){
                MazeCard card = new MazeCard();
                card = getNewCard(card);
                field[r][c].putCard(card);
            }
        }
        freeCard = new MazeCard();
        freeCard = getNewCard(freeCard);
    }
    
    public MazeField get(int r, int c){
        return ((r > size) || (c > size)) ? null : field[r][c];
    }
    
    public MazeCard getFreeCard(){
        return freeCard;
    }
    
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
            System.out.println("Shifting done!");
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
            System.out.println("Shifting done!");
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
            System.out.println("Shifting done!");
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
            System.out.println("Shifting done!");
        }
    }
}
