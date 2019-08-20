package view.animations;

import java.util.ArrayList;
import java.util.List;

import model.player.PlayerColor;

/**
 * This class stores a wake bomb sprites.
 *
 */
public class WakeSprite {

    private final List<Sprite> wake = new ArrayList<>();
    private int toMove;
    private static final int START_RED = 0;
    private static final int START_YELLOW = 4;
    private int start;

    /**
     * Makes the wake animation list.
     * 
     * @param color the color of the player
     * @param sheet the sheet
     */
    public WakeSprite(final PlayerColor color, final SpriteSheet sheet) {

        if (color.equals(PlayerColor.RED)) {
            this.start = START_RED;
            this.toMove = START_YELLOW;
        } else if (color.equals(PlayerColor.YELLOW)) {
            this.start = START_YELLOW;
            this.toMove = (START_YELLOW * 2);
        }

        for (int i = start; i < toMove; i++) {
            this.wake.add(new Sprite(sheet, 0, i));
        }
    }

    /**
     * Gets the explosion sprite list.
     * 
     * @return the list
     */
    public List<Sprite> getWakeEsplosionList() {
        return this.wake;
    }
}
