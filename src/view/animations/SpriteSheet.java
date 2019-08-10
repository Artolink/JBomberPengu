package view.animations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/**
 * 
 * A class that extrapolate the sprites from spritesheet.
 *
 */
public class SpriteSheet {

    private Image image;
    private double width;
    private double height;
    private List<Image> imageList = new ArrayList<>();

    /**
     * Get a sprite sheet.
     * @param image image
     */
    public SpriteSheet(final Image image) {

        this.image = image;
    }

    /**
     * Give the current width of the sprite to cut.
     * 
     * @return width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Give the current height of the sprite to cut.
     * 
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

}
