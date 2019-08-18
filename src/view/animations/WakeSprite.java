package view.animations;

import model.player.Player;

/**
 * This class stores a wake bomb sprites.
 *
 */
public class WakeSprite {

    private static final String RED_WAKE = "esplosione rossa.png";
    private static final String YELLOW_WAKE = "esplosione gialla.png";

    /**
     * It takes the right wake image based on the color.
     * 
     * @param player the player
     * @return the wake path
     */
    public String drawWake(final Player player) {
        String path = " ";

        if (player.getID().equals(0)) {
            path = RED_WAKE;
        } else {
            path = YELLOW_WAKE;
        }
        return path;
    }

}
