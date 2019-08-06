package model;

import javafx.geometry.Point2D;

/**
 * Abstract implementation of {@link DestructibleEntity}. Also extends {@link AbstractIndestructibleEntity}
 */
public abstract class AbstractDestructibleEntity extends AbstractIndestructibleEntity implements DestructibleEntity {

    private boolean destroyed; //NON SO ANCORA SE SERVE

    /**
     * AbstractDestructibleEntity builder.
     * 
     * @param pos defines the initial position of the entity
     */
    public AbstractDestructibleEntity(final Point2D pos) {
        super(pos);
        this.destroyed = false;
    }

    @Override
    public final boolean isDestroyed() {
        return this.destroyed;
    }

    @Override
    public final void setStatus(final boolean destroyed) { //forse da far overraidare in più casi (es. player 0 vite..)
        this.destroyed = destroyed;
    }
}
