package model.map;

import javafx.geometry.Point2D;
import model.AbstractIndestructibleEntity;
import model.blocks.Terrain;
import model.utils.Pair;

/**
 * Map of the Game. Contains Blocks e dimensions.
 */
public class GameMap {
    private final Pair<Integer, Integer> dimensions;
    private AbstractIndestructibleEntity[][] map;

    /**
     * Constructor set dimensions of GameMap.
     * @param dimensions dimensions of Map
     * @throws IllegalArgumentException 
     */
    public GameMap(final Pair<Integer, Integer> dimensions) throws IllegalArgumentException {
        if (dimensions.getX() <= 0 && dimensions.getY() <= 0) {
            throw new IllegalArgumentException("Incorrect dimension/s");
        }
        this.dimensions = dimensions;
        this.map = new AbstractIndestructibleEntity[dimensions.getX()][dimensions.getY()];
    }

    /**
     * Get Dimension2 object with dimension of the GameMap.
     * @return Dimension2 dimensions of GameMap
     */
    public Pair<Integer, Integer> getDimensions() {
        return dimensions;
    }

    /**
     * Load the map with empty blocks.
     */
    public void setAllEmpty() {
        for (int a = 0; a < this.dimensions.getX(); a++) {
            for (int b = 0; b < this.dimensions.getY(); b++) {
                this.map[a][b] = new Terrain(new Point2D(0, 0)); /////////???????????
            }
        }
    }

    /**
     * Set a Block in a specified position.
     * 
     * @param block Block to insert in a specified position
     * @param row horizontal position
     * @param column vertical position
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    public void setBlock(final AbstractIndestructibleEntity block, final int row, final int column) throws IllegalArgumentException {
        if (!this.isDimensionsCorrect(row, column)) {
            throw new IllegalArgumentException();
        }
        this.map[row][column] = block;
    }

    /**
     * Get Block at specified position.
     * @param row horizontal position
     * @param column vertical position
     * @return AbstractEntity on specified position
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    public AbstractIndestructibleEntity getBlock(final int row, final int column) throws IllegalArgumentException {
        if (!this.isDimensionsCorrect(row, column)) {
            throw new IllegalArgumentException();
        }
        return this.map[row][column];
    }

    /**
     * Get Block at specified position.
     * @param dim dimensions
     * @return AbstractEntity on specified position
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    public AbstractIndestructibleEntity getBlock(final Pair<Integer, Integer> dim) throws IllegalArgumentException {
        return this.getBlock(dim.getX(), dim.getY());
    }

    /**
     * Set a Block in a specified position.
     * 
     * @param block Block to insert in a specified position
     * @param dim dimensions
     * @throws IllegalArgumentException throws if the argument is not correct
     */
    public void setBlock(final AbstractIndestructibleEntity block, final Pair<Integer, Integer> dim) throws IllegalArgumentException {
        this.setBlock(block, dim.getX(), dim.getY());
    }

    private boolean isDimensionsCorrect(final int x, final int y) {
        return this.dimensions.getX() > x && this.dimensions.getY() > y;
    }
}
