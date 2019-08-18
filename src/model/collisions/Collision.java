package model.collisions;

/**
 * Class that models collisions with different game elements.
 *
 */
public interface Collision {
    /**
     * Checks if there's a collision with blocks.
     * 
     * @return true if there's a collision, false otherwise
     */
    boolean blocksCollided();

}
