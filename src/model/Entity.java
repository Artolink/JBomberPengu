package model;

import java.util.Optional;
import model.utils.Pair;
import model.utils.Rectangle;

/**
 * Main interface of the game, used for every generic entity.
 */
public interface Entity {

    /**
     * Gets the position of the entity.
     * 
     * @return entity current position
     */
    Pair<Integer, Integer> getPosition();

    /**
     * Sets the initial position of the entity.
     * 
     * @param position defines the entity position
     */
    void setPosition(Pair<Integer, Integer> position);

    /**
     * Gets the hitbox of the entity (a Rectangle if it's solid, null otherwise.
     * 
     * @return the collision box for each entity.
     */
    Optional<Rectangle> getCollisionBox();

    /**
     * Sets the hitbox position of the entity.
     * 
     */
    void setCollisionBox();

    /**
     * Gets the path of the image that will be used by the view. 
     * 
     * @return the path where the image of the entity is located
     */
    String getImagePath();

    /**
     * Sets the path of the image that will be used by the view.
     * 
     * @param path the path where the image of the entity is located
     */
    void setImagePath(String path);

    /**
     * Method that defines if the entity is destroyed or not.
     * 
     * @return true if destroyed, false otherwise
     */
    boolean isDestroyed();

    /**
     * Sets the status of the entity (1 if destroyed, 0 otherwise).
     * 
     * @param destroyed defines if the Entity has been destroyed or not
     */
    void setStatus(boolean destroyed);

    /**
     * Gets the entity width.
     * 
     * @return entity width
     */
    double getWidth();

    /**
     * Sets the new entity width.
     * 
     * @param width defines the new entity width
     */
    void setWidth(double width);

    /**
     * Gets the entity height.
     * 
     * @return entity height
     */
    double getHeight();

    /**
     * Sets the new entity height.
     * 
     * @param height defines the new entity width
     */
    void setHeight(double height);
}
