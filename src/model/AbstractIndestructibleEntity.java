package model;

import javafx.geometry.Point2D;

/**
 * Abstract implementation of {@link IndestructibleEntity}.
 */
public abstract class AbstractIndestructibleEntity implements IndestructibleEntity {

    private final boolean solid;
    private Point2D position;

    /**
     * AbstractIndestructibleEntity builder.
     * 
     * @param pos          defines the initial position of the entity
     * @param solid        defines the entity is solid or not
     */
    public AbstractIndestructibleEntity(final Point2D pos, final boolean solid) {
        this.position = pos;
        this.solid = solid;
    }

    @Override
    public final Point2D getPosition() {
        return position;
    }

    @Override
    public final void setPosition(final Point2D position) {
        this.position = position;

    }

    @Override
    public final boolean isSolid() {
        return this.solid;
    }
}
