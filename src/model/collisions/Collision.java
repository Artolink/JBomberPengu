package model.collisions;

import java.util.List;

import model.AbstractEntity;
import model.player.Player;
import model.utils.Directions;
import model.utils.Rectangle;

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

    /**
     * Checks if there's a collision with bombs.
     * 
     * @param blocks is the list of all bombs
     * @return true if there's a collision, false otherwise
     */
    boolean bombCollided(List<AbstractEntity> blocks);

    /**
     * Gets a list of hitboxes to check for collisions.
     * 
     * @param player            is the player you want to check for collisions
     * @param direction         is the direction where the player wants to move
     * @return a list of hitboxes to check for collisions
     */
    List<Rectangle> getCollisionBlock(Player player, Directions direction);
}
