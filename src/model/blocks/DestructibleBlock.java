package model.blocks;

import java.io.File;
import java.util.Optional;
import model.AbstractEntity;
import model.utils.Pair;

/**
 * Final class for the destructible blocks in the game.
 * 
 */
public final class DestructibleBlock extends AbstractEntity {

    private static final double POWER_UP_CHANCE = 0.5;
    private static final int SCORE_VALUE = 25;

    private final Optional<Object> powerup;

    /**
     * Block builder.
     * 
     * @param pos defines the initial position of the block
     */
    public DestructibleBlock(final Pair<Integer, Integer> pos) {
        super(pos, true);
        this.powerup = this.hasPowerUp();
        this.setScoreValue(SCORE_VALUE);
        this.setImagePath(ClassLoader.getSystemClassLoader().getResource("view") + File.separator + "destructible_block.png");

    }

    private Optional<Object> hasPowerUp() {
        return Math.random() < POWER_UP_CHANCE ? Optional.of(1) : Optional.empty(); //optional.of(1) has to change with enum of powerup block type
    }

    /**
     * Defines if have to drop a power up when destroying this block.
     * @return if have to drop
     */
    public boolean dropPowerUpWhenDestroyed() {
        return this.powerup.isPresent();
    }
}
