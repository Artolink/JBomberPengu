package model.blocks;

import model.AbstractEntity;
import model.utils.Pair;

/**
 * Base class for the main terrain where players walk (more terrain types can be added in the future e.g. ice, mud..).
 *
 */
public class Terrain extends AbstractEntity {

    /**
     * Terrain builder.
     * 
     * @param pos defines the initial position of the block
     */
    public Terrain(final Pair<Integer, Integer> pos) {
        super(pos);
        setImagePath("");
    }
}
