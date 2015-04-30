/*
 * IJA 2015: The Labyrinth
 */
package client.treasure;

import java.util.Objects;

/**
 *
 * @author xpospi73, xdress00
 */
public class TreasureCard {
    
    public Treasure card;
    
    public TreasureCard(Treasure tr){
        card = tr;
    }
    
    @Override
    public boolean equals(Object obj){
        return ((obj instanceof TreasureCard) && (((TreasureCard)obj).card == this.card));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.card);
        return hash;
    }
    
}
