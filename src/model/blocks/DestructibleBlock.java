package model.blocks;

import java.util.Optional;
import javafx.geometry.Point2D;
import model.Block;

/**
 * Class for the destructible blocks in the game.
 */
public final class DestructibleBlock extends Block {

    private static final double MAX_POWER_UP = 0.5;

    /**
     * Block builder.
     * 
     * @param pos defines the initial position of the block
     */
    public DestructibleBlock(final Point2D pos) {
        super(pos, true, true);
    }

    public boolean hasPowerUp() {
        return Math.random() < MAX_POWER_UP;
    }

    // L'OBJECT SAREBBE UN POWERUP GENERICO IN FUTURO IMPLEMENTATO
    public Optional<Object> releasePowerUpWhenDestroyed() {
        if (hasPowerUp()) {
            return Optional.of(null); // A CASO
        } else {
            return Optional.empty();
        }
    }
}
