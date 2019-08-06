package model.player;

import javafx.geometry.Point2D;
import model.AbstractDestructibleEntity;

/**
 * The player that the user will control.
 */
public final class Player extends AbstractDestructibleEntity {

    private final String name;
    private Integer score = 0;

    /**
     * Player builder.
     * 
     * @param name the name of the player
     * @param pos  the initial position of the player
     */
    public Player(final String name, final Point2D pos) {
        super(pos, true);
        this.name = name;
    }

    /**
     * Returns the name of the player.
     * 
     * @return player name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the score of the player.
     * 
     * @return player score
     */
    public Integer getScore() {
        return this.score;
    }

    /**
     * Adds the score to the player.
     * 
     * @param score The score of the player
     */
    public void addScore(final Integer score) {
        this.score += score;
    }

    @Override
    public boolean isBeingDestroyed() {
        // QUANDO NON HA PIU' VITE, RETURN TRUE
        return false;
    }
}
