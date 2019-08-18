package view.animations;

import javafx.scene.image.ImageView;
import model.player.Player;

/**
 * It manages the sprite animations.
 *
 */
public interface Animation extends Runnable {

    /**
     * Sets the current player.
     * 
     * @param player is the current player
     */
    void setPlayer(Player player);

    /**
     * Sets the current imageView.
     * 
     * @param view the image view
     */
    void setImageView(ImageView view);
}
