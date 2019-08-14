package model.collisions;

import java.util.Set;
import model.utils.Rectangle;

/**
 * Class that models collisions with different game elements.
 *
 */
public interface Collision {
    /**
     * Checks if there's a collision with blocks.
     * 
     * @param blockSet is the set containing all blocks
     * @return true if there's a collision, false otherwise
     */
    boolean allBlocksCollision(Set<Rectangle> blockSet);

    /**
     * Checks if there's a collision with a bomb.
     * 
     * @param bombSet is the set of active bombs
     * @return true if there's a collision, false otherwise
    */
    boolean bombCollision(Set<Rectangle> bombSet);

    /**
     * Checks if there's a collision with bomb explosions.
     * 
     * @param explosionSet is the set of all explosions on the map
     * @return true if there's a collision, false otherwise
     */
    boolean explosionCollision(Set<Rectangle> explosionSet);
}
