package model;

import javafx.geometry.Point2D;

/**
 * Abstract implementation of {@link Entity}.
 */
public abstract class AbstractEntity implements Entity {

    private final boolean solid;
    private final boolean destructible;
    private Point2D position;

    /**
     * AbstractEntity builder.
     * 
     * @param pos          defines the initial position of the entity
     * @param solid        defines the entity is solid or not
     * @param destructible defines if the entity is destructible or not
     */
    public AbstractEntity(final Point2D pos, final boolean solid, final boolean destructible) {
        this.position = pos;
        this.solid = solid;
        this.destructible = destructible;
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

    @Override
    public final boolean isDestructible() {
        return this.destructible;
    }
}
