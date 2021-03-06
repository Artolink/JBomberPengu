package model.blocks;

import java.io.File;

import model.AbstractEntity;
import model.player.Player;
import model.player.PlayerColor;
import model.utils.Pair;

/**
 * Base class for the bomb (more bomb types can be added in the future).
 *
 */
public class Bomb extends AbstractEntity { 

    private static final int DEFAULT_RANGE = 2;
    private static final long EXPLOSION_TIME = 3000L;

    private final long explosionTime;
    private final Player playerInfo;
    private int range;

    /**
     * Bomb builder.
     * 
     * @param pos       defines the initial position of the bomb
     * @param pinfo     defines all the player's informations associated with the bomb 
     */
    public Bomb(final Pair<Integer, Integer> pos, final Player pinfo) {
        super(pos, true);
        this.playerInfo = pinfo;
        this.setStatus(false);
        this.range = DEFAULT_RANGE;
        this.explosionTime = EXPLOSION_TIME;
        if (pinfo.getColor().equals(PlayerColor.RED)) {
            this.setImagePath(ClassLoader.getSystemClassLoader().getResource("view") + File.separator + "bomba_rossa.png");
        } else if (pinfo.getColor().equals(PlayerColor.YELLOW)) {
            this.setImagePath(ClassLoader.getSystemClassLoader().getResource("view") + File.separator + "bomba_gialla.png");
        }
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

    /**
     * Gets the bomb explosion time.
     * 
     * @return bomb explosion time
     */
    public final long getExplosionTime() {
        return this.explosionTime;
    }

    @Override
    public final String toString() {
        return new StringBuilder().append("BOMB -  ")
                .append("Planted by: ")
                .append(this.playerInfo.getName())
                .append(" - Range is: ")
                .append(this.getRange())
                .append(" - Status (destroyed/not) is: ")
                .append(this.isDestroyed())
                .append("\n")
                .append(super.toString())
                .toString();
    }
}
