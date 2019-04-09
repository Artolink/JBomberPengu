package model.blocks;

import java.util.Optional;
import javafx.geometry.Point2D;
import model.AbstractEntity;

/**
 * Class for the destructible blocks in the game.
 */
public final class DestructibleBlock extends AbstractEntity {

    private static final double MAX_POWER_UP = 0.5;

    /**
     * Block builder.
     * 
     * @param pos defines the initial position of the block
     */
    public DestructibleBlock(final Point2D pos) {
        super(pos, true, true);
    }
    /**
     * Defines if the block has got a power-up.
     * 
     * @return 1 if has got a power-up, 0 otherwise
     */
    public boolean hasPowerUp() {
        return Math.random() < MAX_POWER_UP;
    }

    /**
     * DA IMPLEMENTARE FACOLTATIVAMENTE.
     * L'OBJECT SAREBBE UN POWERUP GENERICO IN FUTURO IMPLEMENTATO.
     * 
     * @return a possible power-up, or nothing
     */ 
    public Optional<Object> dropPowerUpWhenDestroyed() {
        if (hasPowerUp()) {
            return Optional.of(null); // A CASO
        } else {
            return Optional.empty();
        }
    }
}
