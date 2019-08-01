package model;

import javafx.geometry.Point2D;

/**
 * Main interface of the game, used for every generic indestructible entity.
 */
public interface IndestructibleEntity {

    /**
     * Gets the position of the entity.
     * 
     * @return entity current position
     */
    Point2D getPosition();

    /**
     * Sets the initial position of the entity.
     * 
     * @param position defines the entity position
     */
    void setPosition(Point2D position);

    /**
     * Defines if the entity is solid (e.g. walls, bombs..) or not.
     * 
     * @return 1 if solid, 0 otherwise
     */
    boolean isSolid();
}
