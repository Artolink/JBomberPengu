package model;

import java.util.Optional;
import com.sun.javafx.geom.Rectangle;
import javafx.geometry.Point2D;

/**
 * Main interface of the game, used for every generic entity.
 */
public interface Entity {

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
    void setPosition(Point2D position); //FORSE INUTILE

    /**
     * Gets the hitbox of the entity (a Rectangle if it's solid, null otherwise.
     * 
     * @return the collision box for each entity.
     */
    Optional<Rectangle> getCollisionBox();

    /**
     * Sets the hitbox position of the entity.
     * 
     * @param position defines the entity position
     * @param width defines the default width of the sprite to match the rectangle
     * @param height defines the default height of the sprite to match the rectangle
     */
    void setCollisionBox(Point2D position, int width, int height);

    /**
     * Sets the hitbox to a Optional.empty().
     * This is useful when a entity is not solid and it doesn't need an hitbox
     */
    void setCollisionBox(); 
}
