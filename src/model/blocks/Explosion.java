package model.blocks;

import model.AbstractEntity;
import model.utils.Pair;

/**
 * Final class for the explosions in the game, after a bomb is exploded.
 */
public final class Explosion extends AbstractEntity {

    /**
     * Explosion builder.
     * 
     * @param pos defines the initial position of the explosion
     */
    public Explosion(final Pair<Integer, Integer> pos) {
        super(pos, true);
        this.setImagePath("");
    }
}
