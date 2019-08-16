package model;

import java.util.Optional;
import model.utils.Pair;
import model.utils.Rectangle;

/**
 * Abstract implementation of {@link Entity}.
 */
public abstract class AbstractEntity implements Entity {

    private Optional<Rectangle> hitbox;
    private Pair<Integer, Integer> position;
    private String path;
    private int width;
    private int height;
    private boolean destroyed;

    /**
     * AbstractMovableEntity builder.
     * 
     * @param pos defines the initial position of the entity.
     */
    public AbstractEntity(final Pair<Integer, Integer> pos) {
        this.position = pos;
    }

    @Override
    public final Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public final void setPosition(final Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public final Optional<Rectangle> getCollisionBox() {
        return this.hitbox;
    }

    @Override
    public final void setCollisionBox() {
        this.hitbox = Optional.of(new Rectangle(this.getPosition(), this.getWidth(), this.getHeight()));
    }

    @Override
    public final String getImagePath() {
        return this.path;
    }

    @Override
    public final void setImagePath(final String path) {
        this.path = path;
    }

    @Override
    public final boolean isDestroyed() {
        return this.destroyed;
    }

    @Override
    public final void setStatus(final boolean destroyed) { 
        this.destroyed = destroyed;
    }

    @Override
    public final int getHeight() {
        return this.height;
    }

    @Override
    public final int getWidth() {
        return this.width;
    }

    @Override
    public final void setHeight(final int height) {
        this.height = height;
    }

    @Override
    public final void setWidth(final int width) {
        this.width = width;
    }
}
