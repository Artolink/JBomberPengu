package model;

import model.utils.Rectangle;
import model.utils.Pair;

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
    Rectangle getCollisionBox();

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
    int getWidth();

    /**
     * Sets the new entity width.
     * 
     * @param width defines the new entity width
     */
    void setWidth(int width);

    /**
     * Gets the entity height.
     * 
     * @return entity height
     */
    int getHeight();

    /**
     * Sets the new entity height.
     * 
     * @param height defines the new entity width
     */
    void setHeight(int height);

    /**
     * Return the state of the block, if it is solid or not.
     * @return {@link Boolean} associated at the state of solidity
     */
    boolean isSolid();

    /**
     * Sets the score value of the entity.
     * 
     * @param scoreValue defines the value that will be given (added) to players score when the entity is destroyed
     */
    void setScoreValue(int scoreValue);

    /**
     * Gets the score value of the entity.
     * 
     * @return a score value that defines the value of the entity
     */
    int getScoreValue();
}
