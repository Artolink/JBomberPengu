package model.blocks;

import javafx.geometry.Point2D;
import model.AbstractIndestructibleEntity;

/**
 * Final class for the indestructible walls in the game.
 */
public final class IndestructibleBlock extends AbstractIndestructibleEntity {
    /**
     * Wall builder.
     * 
     * @param pos defines the initial position of the wall
     */
    public IndestructibleBlock(final Point2D pos) {
        super(pos, true);
    }
}
