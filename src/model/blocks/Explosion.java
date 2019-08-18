package model.blocks;

import model.AbstractEntity;
import model.utils.Pair;

/**
 * 
 */
public class Explosion extends AbstractEntity {

    /**
     * Explosion builder.
     * 
     * @param pos defines the initial position of the explosion
     */
    public Explosion(final Pair<Integer, Integer> pos) {
        super(pos);
        this.setImagePath("");
    }
}
