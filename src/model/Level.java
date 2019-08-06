package model;

/**
 * Level of the game.
 */
public class Level {

    private int position;

    /**
     * Constructor initialize the level to 0.
     */
    public Level() {
        this.position = 0;
    }

    /**
     * Set the level.
     * @param level integer parameter representing the level
     * @throws IllegalArgumentException throw if we are passing an illegal argument
     */
    public void setLevel(final int level) throws IllegalArgumentException {
        if (level < 0) {
            throw new IllegalArgumentException();
        } else {
            this.position = level;
        }
    }

    /**
     * Get current level.
     * @return current level
     */
    public int get() {
        return this.position;
    }

    /**
     * Increase level.
     * @return increased level
     */
    public int next() {
        return ++this.position;
    }

}
