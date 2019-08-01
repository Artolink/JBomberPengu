package model;

import javafx.geometry.Point2D;

/**
 * Abstract implementation of {@link DestructibleEntity}. Also extends {@link AbstractIndestructibleEntity}
 */
public abstract class AbstractDestructibleEntity extends AbstractIndestructibleEntity implements DestructibleEntity {

    private boolean destroyed;

    /**
     * AbstractDestructibleEntity builder.
     * 
     * @param pos          defines the initial position of the entity
     * @param solid        defines the entity is solid or not
     */
    public AbstractDestructibleEntity(final Point2D pos, final boolean solid) {
        super(pos, solid);
        this.destroyed = false;
    }

    /**
     * Defines if the entity is destroyed or not.
     * 
     * @return 1 if destroyed, 0 otherwise
     */
    public boolean isDestroyed() {
        return this.destroyed;
    }

    /**
     * Sets the status of the entity (1 if destroyed, 0 otherwise).
     * 
     * @param destroyed defines if the Entity has been destroyed or not
     */
    public void setStatus(final boolean destroyed) {
        this.destroyed = destroyed;
    }

    @Override
    public abstract boolean isBeingDestroyed();
}
