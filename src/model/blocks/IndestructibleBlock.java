package model.blocks;

import java.io.File;

import model.AbstractEntity;
import model.utils.Pair;

/**
 * Final class for the indestructible walls in the game.
 */
public final class IndestructibleBlock extends AbstractEntity {

    /**
     * Wall builder.
     *
     * @param pos defines the initial position of the block
     */
    public IndestructibleBlock(final Pair<Integer, Integer> pos) {
        super(pos, true);
        this.setImagePath(ClassLoader.getSystemClassLoader().getResource("view") + File.separator + "indestructible_block.png");
    }

}
