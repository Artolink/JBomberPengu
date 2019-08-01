package model.blocks;

import javafx.geometry.Point2D;
import model.AbstractIndestructibleEntity;

/**
 * Base class for the main terrain where players walk (more terrain types can be added in the future e.g. ice, mud..).
 *
 */
public class Terrain extends AbstractIndestructibleEntity {

    /**
     * Terrain builder.
     * 
     * @param pos defines the initial position of the block
     */
    public Terrain(final Point2D pos) {
        super(pos, false);
    }
}
