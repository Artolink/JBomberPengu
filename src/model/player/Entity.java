package model.player;

/**
 * Interface for every generic entity in the game.
 */
public interface Entity {

    /**
     * 
     * @return the entity name
     */
    String getName();

    /**
     * Sets the score of the entity.
     */
    void setPoints();

    /**
     * 
     * @return the score
     */
    Integer getPoints();

}
