package view.animations;

import javafx.scene.image.Image;

/**
 * 
 * A class that creates the sprites.
 *
 */
public class Sprite {

    private final Image image;
    private double width;
    private double height;

    /**
     * It creates a sprite.
     * @param spriteSheet the sheet
     * @param x the row
     * @param y the column
     */
    public Sprite(final SpriteSheet spriteSheet, final int x, final int y) {
        this.image = spriteSheet.getFxImageFromSheet(x, y);
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    /**
     * Gets the sprite image.
     *
     * @return the sprite {@link Image}
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Gets the sprite width.
     * @return the sprite width
     */
    public double getSpriteWidth() {
        return this.width;
    }

    /**
     * Gets the sprite height.
     * @return the sprite height
     */
    public double getSpriteHeight() {
        return this.height;
    }

    /**
     * Set the sprite width.
     * @param width width
     */
    public void setSpriteWidth(final double width) {
        this.width = width;
    }

    /**
     * Gets the sprite height.
     * @param height height
     */
    public void setSpriteHeight(final double height) {
        this.height = height;
    }
}
