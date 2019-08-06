package model.blocks;

import javafx.geometry.Point2D;
import model.AbstractDestructibleEntity;
import model.player.Player;

/**
 * Base class for the bomb (more bomb types can be added in the future).
 *
 */
public class Bomb extends AbstractDestructibleEntity {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private final Player playerInfo;
    private int range;

    /**
     * Bomb builder.
     * 
     * @param pos       defines the initial position of the bomb
     * @param pinfo     defines all the player's informations associated with the bomb 
     * @param range     defines the explosion's dimension
     */
    public Bomb(final Point2D pos, final Player pinfo, final int range) {
        super(pos);
        setCollisionBox(pos, WIDTH, HEIGHT);
        this.playerInfo = pinfo;
        this.range = range;
    }

    /**
     * Gets the player informations associated with the bomb.
     * 
     * @return a Player
     */
    public Player getPlayerInfo() {
        return this.playerInfo;
    }

    /**
     * Gets bomb's range of explosion.
     * 
     * @return bomb's range of the explosion
     */
    public int getRange() {
        return this.range;
    }

    /**
     * Sets the new range of explosion.
     * 
     * @param range defines bomb's range of explosion
     * @throws IllegalArgumentException if range is <= 0
     */
    public void setRange(final int range) throws IllegalArgumentException {
        if (range <= 0) { 
            throw new IllegalArgumentException("Range must be a positive number");
        }
        this.range = range;
    }
}
