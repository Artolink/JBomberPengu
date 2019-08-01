package model;

/**
 * Second interface of the game, used for every generic destructible entity, extends {@link IndestructibleEntity}.
 */
public interface DestructibleEntity extends IndestructibleEntity {

    /**
     * Method that defines if the entity is being destroyed.
     * 
     * @return true if the entity has been destroyed, false otherwise
     */
    boolean isBeingDestroyed();
}
