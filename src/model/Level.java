package model;

/**
 * Level of the game.
 */
public class Level {

    private int position;
    private static final int MINIMUMLEVEL = 1;

    /**
     * Constructor initialize the level to 0.
     */
    public Level() {
        this.position = MINIMUMLEVEL;
    }

    /**
     * Set the level.
     * @param level integer parameter representing the level
     * @throws IllegalArgumentException throw if we are passing an illegal argument
     */
    public void setLevel(final int level) throws IllegalArgumentException {
        if (level < MINIMUMLEVEL) {
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
