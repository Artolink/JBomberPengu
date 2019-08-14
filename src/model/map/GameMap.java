package model.map;

import model.AbstractIndestructibleEntity;
import model.blocks.Terrain;
import model.utils.Pair;

/**
 * Map of the Game. Contains Blocks e dimensions.
 */
public class GameMap implements GameMapInterface {

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

    @Override
    public final Pair<Integer, Integer> getDimensions() {
        return dimensions;
    }

    @Override
    public final void setAllEmpty() {
        for (int a = 0; a < this.dimensions.getX(); a++) {
            for (int b = 0; b < this.dimensions.getY(); b++) {
                this.map[a][b] = new Terrain(new Pair<Integer, Integer>(0, 0));
            }
        }
    }

    @Override
    public final void setBlock(final AbstractIndestructibleEntity block, final int row, final int column) throws IllegalArgumentException {
        if (!this.isDimensionsCorrect(row, column)) {
            throw new IllegalArgumentException();
        }
        this.map[row][column] = block;
    }


    @Override
    public final AbstractIndestructibleEntity getBlock(final int row, final int column) throws IllegalArgumentException {
        if (!this.isDimensionsCorrect(row, column)) {
            throw new IllegalArgumentException();
        }
        return this.map[row][column];
    }

    @Override
    public final AbstractIndestructibleEntity getBlock(final Pair<Integer, Integer> dim) throws IllegalArgumentException {
        return this.getBlock(dim.getX(), dim.getY());
    }

    @Override
    public final void setBlock(final AbstractIndestructibleEntity block, final Pair<Integer, Integer> dim) throws IllegalArgumentException {
        this.setBlock(block, dim.getX(), dim.getY());
    }

    private boolean isDimensionsCorrect(final int x, final int y) {
        return this.dimensions.getX() > x && this.dimensions.getY() > y;
    }
}
