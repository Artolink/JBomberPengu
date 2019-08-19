package view.animations;

import javafx.scene.image.ImageView;
import model.player.Player;
import model.player.PlayerColor;

/**
 * Class that run the bomb wake animations.
 *
 */
public class WakeAnimations implements Animation {

    private static final int SHEET_ROWS = 1;
    private static final int SHEET_COLUMNS = 8;
    private final SpriteSheet sheet = new SpriteSheet("/view/explosion_sheet.png", SHEET_ROWS, SHEET_COLUMNS);
    private WakeSprite sprite;
    private Integer nextFrame = 0;
    private Player player;
    private ImageView imageView;
    private static final int WAKE_TIME = 250;

    /**
     * Runs the bomb animations.
     */
    @Override
    public void run() {

        long timeToSleep = 0;
        boolean run = true;

        while (run) {

            this.imageView.setImage(sprite.getWakeEsplosionList().get(nextFrame).getImage());
            timeToSleep = WAKE_TIME;

            try {
                Thread.sleep(timeToSleep);
                if (nextFrame.equals(3)) {
                    run = false;
                } else {
                    nextFrame++;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the current player.
     * 
     * @param player the player
     */
    public void setPlayer(final Player player) {
        this.player = player;
        this.setSprite();
    }

    /**
     * Sets the current imageView.
     * 
     * @param image the imageView
     */
    public void setImageView(final ImageView image) {
        this.imageView = image;
    }

    private void setSprite() {

        if (this.player.getColor().equals(PlayerColor.RED)) {
            this.sprite = new WakeSprite(PlayerColor.RED, sheet);
        } else {
            this.sprite = new WakeSprite(PlayerColor.YELLOW, sheet);
        }
    }
}
