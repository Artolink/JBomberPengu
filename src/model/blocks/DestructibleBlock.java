package model.blocks;

import java.util.Optional;
import javafx.geometry.Point2D;
import model.AbstractDestructibleEntity;

/**
 * Final class for the destructible blocks in the game.
 * 
 */
public final class DestructibleBlock extends AbstractDestructibleEntity {

    private static final double POWER_UP_CHANCE = 0.5;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private final Optional<Object> powerup; //sostituire con enum di powerup

    /**
     * Block builder.
     * 
     * @param pos defines the initial position of the block
     */
    public DestructibleBlock(final Point2D pos) {
        super(pos);
        this.powerup = this.hasPowerUp();
        setCollisionBox(pos, WIDTH, HEIGHT);
    }

    private Optional<Object> hasPowerUp() {
        return Math.random() < POWER_UP_CHANCE ? Optional.of(1) : Optional.of(null); //sostituire 1 con enum di powerup
    }

    /**
     * VOID SAREBBE DA SOSTITUIRE CON UN POWERUP GENERICO DA RILASCIARE.
     * 
     */ 
    public void dropPowerUpWhenDestroyed() {
        if (this.powerup.isPresent()) {
            return;
        }
    }
}
