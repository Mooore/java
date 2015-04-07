/*
 * IJA 2015: Homework 3
 */
package board;

import java.util.Random;
/**
 *
 * @author xpospi73
 */
public class MazeBoard {
    
    public static MazeField[][] field;
    public static MazeCard free_card = null;
    public static Random randomGenerator = new Random();
    
    private static int size;
    
    public static MazeBoard createMazeBoard(int n){
        MazeBoard board = new MazeBoard();
        MazeBoard.size = n;
        MazeBoard.field = new MazeField[MazeBoard.size + 1][MazeBoard.size + 1];
        for (int r = 1; r <= MazeBoard.size; r++){
            for (int c = 1; c <= MazeBoard.size; c++){
                MazeBoard.field[r][c] = new MazeField(r,c);
            }
        }
        return board;
    }
    
    public MazeCard getNewCard(MazeCard c){
        int randomInt = randomGenerator.nextInt(2);
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
        for (int r = 1; r <= MazeBoard.size; r++){
            for (int c = 1; c <= MazeBoard.size; c++){
                MazeCard card = new MazeCard();
                card = getNewCard(card);
                MazeBoard.field[r][c].putCard(card);
            }
        }
        MazeBoard.free_card = new MazeCard();
        MazeBoard.free_card = getNewCard(MazeBoard.free_card);
    }
    
    public MazeField get(int r, int c){
        return ((r > MazeBoard.size) || (c > MazeBoard.size)) ? null : MazeBoard.field[r][c];
    }
    
    public MazeCard getFreeCard(){
        return MazeBoard.free_card;
    }
    
    public void shift(MazeField mf){
        if ((mf.row() == 1) && ((mf.col() > 0) && (mf.col() < MazeBoard.size)) && (mf.col() % 2 == 0)){
            // Prvni radek [1, mf.col()]
            MazeField[] tmpfield = new MazeField[MazeBoard.size + 1];
            for (int r = 1; r <= MazeBoard.size; r++){
                tmpfield[r] = new MazeField(0,0);
                tmpfield[r].putCard(MazeBoard.field[r][mf.col()].getCard());
            }
            MazeBoard.field[1][mf.col()].putCard(MazeBoard.free_card);
            MazeBoard.free_card = tmpfield[MazeBoard.size].getCard();
            for (int r = 1; r < MazeBoard.size; r++){
                MazeBoard.field[r + 1][mf.col()].putCard(tmpfield[r].getCard());
            }
        }
        else if ((mf.row() == MazeBoard.size) && ((mf.col() > 0) && (mf.col() < MazeBoard.size)) && (mf.col() % 2 == 0)){
            // Posledni radek [size, mf.col()]
            MazeField[] tmpfield = new MazeField[MazeBoard.size + 1];
            for (int r = MazeBoard.size; r >= 1; r--){
                tmpfield[r] = new MazeField(0,0);
                tmpfield[r].putCard(MazeBoard.field[r][mf.col()].getCard());
            }
            MazeBoard.field[MazeBoard.size][mf.col()].putCard(MazeBoard.free_card);
            MazeBoard.free_card = tmpfield[1].getCard();
            for (int r = MazeBoard.size; r > 1; r--){
                MazeBoard.field[r - 1][mf.col()].putCard(tmpfield[r].getCard());
            }
        }
        else if ((mf.col() == 1) && ((mf.row() > 0) && (mf.row() < MazeBoard.size)) && (mf.row() % 2 == 0)){
            // Prvni sloupec [mf.row(), 1]
            MazeField[] tmpfield = new MazeField[MazeBoard.size + 1];
            for (int c = 1; c <= MazeBoard.size; c++){
                tmpfield[c] = new MazeField(0,0);
                tmpfield[c].putCard(MazeBoard.field[mf.row()][c].getCard());
            }
            MazeBoard.field[mf.row()][1].putCard(MazeBoard.free_card);
            MazeBoard.free_card = tmpfield[MazeBoard.size].getCard();
            for (int c = 1; c < MazeBoard.size; c++){
                MazeBoard.field[mf.row()][c + 1].putCard(tmpfield[c].getCard());
            }
        }
        else if ((mf.col() == MazeBoard.size) && ((mf.row() > 0) && (mf.row() < MazeBoard.size)) && (mf.row() % 2 == 0)){
            // Posledni sloupec [mf.row(), size]
            MazeField[] tmpfield = new MazeField[MazeBoard.size + 1];
            for (int c = MazeBoard.size; c >= 1; c--){
                tmpfield[c] = new MazeField(0,0);
                tmpfield[c].putCard(MazeBoard.field[mf.row()][c].getCard());
            }
            MazeBoard.field[mf.row()][MazeBoard.size].putCard(MazeBoard.free_card);
            MazeBoard.free_card = tmpfield[1].getCard();
            for (int c = MazeBoard.size; c > 1; c--){
                MazeBoard.field[mf.row()][c - 1].putCard(tmpfield[c].getCard());
            }
        }
    }
}
