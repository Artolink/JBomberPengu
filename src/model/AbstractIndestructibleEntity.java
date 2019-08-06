package model;

import java.util.Optional;
import com.sun.javafx.geom.Rectangle;
import javafx.geometry.Point2D;

/**
 * Abstract implementation of {@link IndestructibleEntity}.
 */
public abstract class AbstractIndestructibleEntity implements Entity {

    private Point2D position;
    private Optional<Rectangle> hitbox;

    /**
     * AbstractIndestructibleEntity builder.
     * 
     * @param pos defines the initial position of the entity
     */
    public AbstractIndestructibleEntity(final Point2D pos) {
        this.position = pos;
    }

    @Override
    public final Point2D getPosition() {
        return this.position;
    }

    @Override
    public final void setPosition(final Point2D position) {
        this.position = position;
    }

    @Override
    public final Optional<Rectangle> getCollisionBox() {
        return this.hitbox;
    }

    @Override
    public final void setCollisionBox(final Point2D position, final int width, final int height) {
        this.hitbox = Optional.of(new Rectangle((int) position.getX(), (int) position.getY(), width, height));
    }

    @Override
    public final void setCollisionBox() {
        this.hitbox = Optional.empty();
    }
}
