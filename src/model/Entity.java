package model;

import javafx.geometry.Point2D;

/**
 * Interface for every generic entity in the game.
 */
public interface Entity {

    /**
     * Gets the position of the entity.
     * 
     * @return entity current position
     */
    Point2D getPosition();

    /**
     * Sets the INITIAL position of the entity.
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

    /**
     * Defines if the entity is destructible (e.g. blocks, enemies..) or not (e.g. bombs, walls..).
     * 
     * @return 1 if destructible, 0 otherwise
     */
    boolean isDestructible();

    // HITBOX? TILES?
}
