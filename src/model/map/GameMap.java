package model.map;

import model.AbstractEntity;
import model.utils.BiDimension;

/**
 * Map of the Game. Contains Blocks e dimensions.
 */
public class GameMap {
    private final BiDimension dimensions;
    private AbstractEntity[][] map;

    /**
     * Constructor set dimensions of GameMap.
     * @param dimensions dimensions of Map
     * @throws IllegalArgumentException 
     */
    public GameMap(final BiDimension dimensions) throws IllegalArgumentException {
        if (dimensions.getX() <= 0 && dimensions.getY() <= 0) {
            throw new IllegalArgumentException("Incorrect dimension/s");
        }
        this.dimensions = dimensions;
        this.map = new AbstractEntity[dimensions.getX()][dimensions.getY()];
    }

    /**
     * Get Dimension2 object with dimension of the GameMap.
     * @return Dimension2 dimensions of GameMap
     */
    public BiDimension getDimensions() {
        return dimensions;
    }

    /**
     * Load the map with empty blocks.
     */
    public void setAllEmpty() {
        for (int a = 0; a < this.dimensions.getX(); a++) {
            for (int b = 0; b < this.dimensions.getY(); b++) {
                // TODO this.map[a][b] = new AbstractEntity();
            }
        }
    }

    /**
     * Set a Block in a specified position.
     * 
     * @param block Block to insert in a specified position
     * @param x horizontal position
     * @param y vertical position
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    public void setBlock(final AbstractEntity block, final int x, final int y) throws IllegalArgumentException {
        if (this.isDimensionsCorrect(x, y)) {
            throw new IllegalArgumentException();
        }
        this.map[x][y] = block;
    }

    /**
     * Get Block at specified position.
     * @param x horizontal position
     * @param y vertical position
     * @return AbstractEntity on specified position
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    public AbstractEntity getBlock(final int x, final int y) throws IllegalArgumentException {
        if (this.isDimensionsCorrect(x, y)) {
            throw new IllegalArgumentException();
        }
        return this.map[x][y];
    }

    /**
     * Get Block at specified position.
     * @param dim dimensions
     * @return AbstractEntity on specified position
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    public AbstractEntity getBlock(final BiDimension dim) throws IllegalArgumentException {
        return this.getBlock(dim.getX(), dim.getY());
    }

    /**
     * Set a Block in a specified position.
     * 
     * @param block Block to insert in a specified position
     * @param dim dimensions
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    public void setBlock(final AbstractEntity block, final BiDimension dim) throws IllegalArgumentException {
        this.setBlock(block, dim.getX(), dim.getY());
    }

    private boolean isDimensionsCorrect(final int x, final int y) {
        return this.dimensions.getX() > x && this.dimensions.getY() > y;
    }
}
