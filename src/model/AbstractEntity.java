package model;

import model.utils.Rectangle;
import model.utils.Pair;

/**
 * Abstract implementation of {@link Entity}.
 */
public abstract class AbstractEntity implements Entity {

    private String path;
    private int width;
    private int height;
    private int scoreValue;
    private final boolean solid;
    private boolean destroyed;
    private final Pair<Integer, Integer> initialPosition;
    private Pair<Integer, Integer> realPosition;

    /**
     * AbstractMovableEntity builder.
     * 
     * @param pos defines the INITIAL (relative) position of the entity. (the same positions that the game map uses)
     * @param isSolid defines if the entity is solid
     */
    public AbstractEntity(final Pair<Integer, Integer> pos, final boolean isSolid) {
        this.initialPosition = pos;
        this.solid = isSolid;
    }

    @Override
    public final void setPosition(final Pair<Integer, Integer> position) {
        this.realPosition = position;
    }

    /**
     * This method can be overridden by dynamic entities like players to get a more specific hitbox.
     */
    @Override
    public Rectangle getCollisionBox() {
        return new Rectangle(new Pair<Integer, Integer>(getPosition().getX(), getPosition().getY()), getWidth(), getHeight());
    }

    @Override
    public final Pair<Integer, Integer> getPosition() {
        if (this.realPosition == null) {
            this.realPosition = new Pair<Integer, Integer>(this.getInitialPosition().getX() * this.getWidth(), this.getInitialPosition().getY() * this.getHeight());
        }
        return this.realPosition;
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

    @Override
    public final boolean isSolid() {
        return this.solid;
    }

    @Override
    public final void setScoreValue(final int scoreValue) {
        this.scoreValue = scoreValue;
    }

    @Override
    public final int getScoreValue() {
        return scoreValue;
    }

    /**
     * Gets the initial (relative, map) position of the entity.
     * 
     * @return the initial position of the player
     */
    public Pair<Integer, Integer> getInitialPosition() {
        return this.initialPosition;
    }
}
