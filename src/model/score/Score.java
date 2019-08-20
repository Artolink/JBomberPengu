package model.score;

import java.util.ArrayList;
import java.util.List;

import model.Level;
import model.player.Player;

/**
 * Class representing Score of a Player.
 *
 */
public class Score {

    private List<Player> player = new ArrayList<>();
    private String date;
    private int level;

    /**
     * Get the player associated to this score.
     * @return the name of the player
     */
    public List<Player> getPlayers() {
        return player;
    }

    /**
     * Set the player.
     * @param player player
     * @return this Score object
     */
    public Score setPlayer(final Player player) {
        this.player.add(player);
        return this;
    }

    /**
     * Set the players.
     * @param players players
     * @return this Score object
     */
    public Score setPlayers(final List<Player> players) {
        this.player = players;
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
     * @param date date to be set
     * @return this Score object
     */
    public Score setDate(final String date) {
        this.date = date;
        return this;
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
     * @param level level to be set
     * @return this Score object
     */
    public Score setLevel(final Level level) {
        return this.setLevel(level.get());
    }

    /**
     * Set the level associated with actual score.
     * @param level level to be set
     * @return this Score object
     */
    public Score setLevel(final int level) {
        this.level = level;
        return this;
    }

}
