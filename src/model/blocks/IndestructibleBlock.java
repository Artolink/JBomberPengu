package model.blocks;

import javafx.geometry.Point2D;
import model.Block;

/**
 * Class for the indestructible walls in the game.
 */
public final class IndestructibleBlock extends Block {
    /**
     * Wall builder.
     * 
     * @param pos defines the initial position of the wall
     */
    public IndestructibleBlock(final Point2D pos) {
        super(pos, true, false);
    }
}
