package model.player;

import java.io.File;

import model.AbstractEntity;
import model.utils.Pair;

/**
 * The player that the user will control.
 */
public final class Player extends AbstractEntity {

    private final String name;
    private Integer score = 0;
    private final Pair<Integer, Integer> initialPosition;
    private Directions direction;

    /**
     * Player builder.
     * 
     * @param name the name of the player
     * @param pos  the initial position of the player
     */
    public Player(final String name, final Pair<Integer, Integer> pos) {
        super(pos);
        this.initialPosition = pos;
        this.name = name;
        this.direction = Directions.STATIONARY;
        this.setImagePath(ClassLoader.getSystemClassLoader().getResource("view") + File.separator + "bomba gialla.png");
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

    public boolean isBeingDestroyed() {
        // QUANDO NON HA PIU' VITE, RETURN TRUE
        return false;
    }

    public Pair<Integer, Integer> getInitialPosition() {
        return this.initialPosition;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public Directions getDirection() {
        return this.direction;
    }

    public enum Directions {
        UP, DOWN, LEFT, RIGHT, STATIONARY;
    }
}
