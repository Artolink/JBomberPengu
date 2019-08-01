package model.score;

import model.AbstractDestructibleEntity;
import model.Level;

/**
 * Class representing Score of a Player.
 *
 */
public class Score {

    private int points;
    private String player;
    private String date;
    private int level;

    /**
     * Get the actual score.
     * @return actual score
     */
    public int getScore() {
        return points;
    }

    /**
     * Set a score.
     * @param score score to be setted
     * @return this Score object
     */
    public Score setScore(final int score) {
        this.points = score;
        return this;
    }

    /**
     * Get the player associated to this score.
     * @return the name of the player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Set the player of this score.
     * @param player name of the player
     * @return this Score object
     */
    public Score setPlayer(final String player) {
        this.player = player;
        return this;
    }

    /**
     * Get the date of the score.
     * @return the date of the score
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of the score.
     * @param date date to be setted
     * @return this Score object
     */
    public Score setDate(final String date) {
        this.date = date;
        return this;
    }

    /**
     * Apply a score from a block.
     * @param block block to take points
     * @return a boolean representing a positive score with true or negative with false.
     */
    public boolean applyScore(final AbstractDestructibleEntity block) {
        // this.points += block.getPoints();
        if (!this.isScorePositive()) {
            this.points = 0;
            return false;
        }
        return true;
    }

    /**
     * Check if score is major than 0.
     * @return true if positive, false if negative
     */
    public boolean isScorePositive() {
        return this.points > 0;
    }

    /**
     * Get the level.
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the level associated with actual score.
     * @param level level to be setted
     * @return this Score object
     */
    public Score setLevel(final Level level) {
        return this.setLevel(level.get());
    }

    /**
     * Set the level associated with actual score.
     * @param level level to be setted
     * @return this Score object
     */
    public Score setLevel(final int level) {
        this.level = level;
        return this;
    }

}
