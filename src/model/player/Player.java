package model.player;

import model.Entity;

/**
 * Class for the player that the user will control.
 */
public class Player implements Entity {

    private final String playerName;
    private Integer points = 0;

    /**
     * 
     * @param playerName the name of the player
     */
    public Player(final String playerName) {
        this.playerName = playerName;
    }

    /**
     * Returns player name.
     */
    @Override
    public String getName() {
        return this.playerName;
    }

    /**
     * Adds points to the player.
     * 
     * @param points the score of the player
     */
    @Override
    public void addPoints(final Integer points) {
        this.points += points;
    }

    /**
     * Returns the score of the player.
     */
    @Override
    public Integer getPoints() {
        return this.points;
    }
}
