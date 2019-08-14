package model.player;

import java.io.File;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import model.AbstractDestructibleEntity;
import model.utils.Pair;

/**
 * The player that the user will control.
 */
public final class Player extends AbstractDestructibleEntity {

    private final String name;
    private Integer score = 0;
    private Pair<Integer, Integer> initialPosition;
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

    public String getImagePath() {
        // TODO Auto-generated method stub
        return ClassLoader.getSystemClassLoader().getResource("view") + File.separator + "bomba gialla.png";
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
