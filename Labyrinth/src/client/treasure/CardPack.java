/*
 * IJA 2015: The Labyrinth
 */
package client.treasure;

import static client.treasure.Treasure.createSet;
import static client.treasure.Treasure.getTreasure;
import java.util.Random;

/**
 *
 * @author xpospi73, xdress00
 */
public class CardPack {
    
    public TreasureCard[] pack;
    
    private int size;
    
    public CardPack(int maxSize, int initSize){
        createSet(initSize);
        if (maxSize >= initSize){
            this.pack = new TreasureCard[maxSize];
            for (int i = 0; i < initSize; i++){
                this.pack[i] = new TreasureCard(getTreasure(i));
            }
            this.size = initSize;
        }
    }
    
    public TreasureCard popCard(){
        TreasureCard tr = this.pack[0];              // pocet karet - 1 (indexace v poli od 0)
        
        for (int i = 0; i < this.size; i++){                    
            if ((i + 1) < this.size){
                this.pack[i] = this.pack[i + 1];
            }
        }
        this.size--;
        
        return tr;
    }
    
    public int size(){
        return this.size;
    }
    
    public void shuffle(){
        Random rnd = new Random();
        for (int i = this.size - 1; i > 0; i--){
            int index = rnd.nextInt(i + 1);
            
            TreasureCard tr = this.pack[index];
            this.pack[index] = this.pack[i];
            this.pack[i] = tr;
        }
    }
    
}
