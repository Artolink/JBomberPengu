package model.blocks;

import javafx.geometry.Point2D;
import model.AbstractDestructibleEntity;

/**
 * Base class for the bomb (more bomb types can be added in the future).
 *
 */
public class Bomb extends AbstractDestructibleEntity {

    private static final int BASE_RANGE = 5;

    private int range;

    /**
     * Bomb builder.
     * 
     * @param pos defines the initial position of the bomb
     */
    public Bomb(final Point2D pos) {
        super(pos, true);
        setStatus(false); //sets the entity status (destroyed or not) to false
        this.range = BASE_RANGE;
    }

    /**
     * Gets bomb's range of explosion.
     * 
     * @return bomb's range of the explosion
     */
    public int getRange() {
        return this.range;
    }

    /**
     * Sets the new range of explosion.
     * 
     * @param range defines bomb's range of explosion
     * @throws IllegalArgumentException if range is <= 0
     */
    public void setRange(final int range) throws IllegalArgumentException {
        if (range <= 0) { 
            throw new IllegalArgumentException("Range must be a positive number");
        }
        this.range = range;
    }

    /**
     * Gets bomb's status (exploded or not).
     * 
     * @return true if it's exploded, false otherwise
     */

    @Override
    public final boolean isBeingDestroyed() {
        //UNA VOLTA CHE HO LA CLASSE TIMER PASSARLA E FARE METODO CHE QUANDO SCADE TIMER EXPLODED = TRUE
        return false;
    }
}
