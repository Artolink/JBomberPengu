package model.map;

import model.AbstractIndestructibleEntity;
import model.utils.Pair;

/**
 * Map of the Game. Contains Blocks e dimensions.
 */
public interface GameMapInterface {

    /**
     * Get Dimension2 object with dimension of the GameMap.
     * @return Dimension2 dimensions of GameMap
     */
    Pair<Integer, Integer> getDimensions();

    /**
     * Load the map with empty blocks.
     */
    void setAllEmpty();

    /**
     * Set a Block in a specified position.
     * 
     * @param block Block to insert in a specified position
     * @param row horizontal position
     * @param column vertical position
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    void setBlock(AbstractIndestructibleEntity block, int row, int column) throws IllegalArgumentException;

    /**
     * Get Block at specified position.
     * @param row horizontal position
     * @param column vertical position
     * @return AbstractEntity on specified position
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    AbstractIndestructibleEntity getBlock(int row, int column) throws IllegalArgumentException;

    /**
     * Get Block at specified position.
     * @param dim dimensions
     * @return AbstractEntity on specified position
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    AbstractIndestructibleEntity getBlock(Pair<Integer, Integer> dim) throws IllegalArgumentException;

    /**
     * Set a Block in a specified position.
     * 
     * @param block Block to insert in a specified position
     * @param dim dimensions
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    void setBlock(AbstractIndestructibleEntity block, Pair<Integer, Integer> dim) throws IllegalArgumentException;
}
