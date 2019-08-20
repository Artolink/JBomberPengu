package model;

import model.utils.Rectangle;
import model.utils.Pair;

/**
 * Main interface of the game, used for every generic entity.
 */
public interface Entity {

    /**
     * Gets the initial (relative, map) position of the entity.
     * 
     * @return the initial position of the player
     */
    Pair<Integer, Integer> getInitialPosition();

    /**
     * Gets the position of the entity on screen (absolute, pixel).
     * 
     * @return entity current position on screen
     */
    Pair<Integer, Integer> getPosition();

    /**
     * Sets the position of the entity on screen.
     * Can be used to change an absolute position (that is grid locked because they're a multiple of a value) 
     * to a specific position and place an object everywhere you want
     * 
     * Please check {@link TestEntity} for an in-depth explanation of how this works.
     * 
     * @param position defines the entity position on screen
     */
    void setPosition(Pair<Integer, Integer> position);

    /**
     * Gets the hitbox of the entity (a Rectangle if it's solid, null otherwise).
     * 
     * @return the collision box of the entity.
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
     * Sets the status of the entity (true if destroyed, false otherwise).
     * 
     * @param destroyed defines if the entity has been destroyed or not
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
     * @return true if entity is solid, false otherwise.
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
