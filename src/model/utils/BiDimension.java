package model.utils;

/**
 * Class representing two dimensions.
 */
public class BiDimension {

    private int x;
    private int y;

    /**
     * Constructor initialize the two dimensions.
     * 
     * @param x first dimension
     * @param y second dimension
     */
    public BiDimension(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get first dimension.
     * 
     * @return first dimension
     */
    public int getX() {
        return x;
    }

    /**
     * Set the fist dimension.
     * 
     * @param x first dimension to be setted
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Get the second dimension.
     * 
     * @return second dimension
     */
    public int getY() {
        return y;
    }

    /**
     * Set the second dimension.
     * 
     * @param y second dimension to be setted
     */
    public void setY(final int y) {
        this.y = y;
    }

}
