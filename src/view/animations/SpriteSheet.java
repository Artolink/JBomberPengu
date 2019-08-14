package view.animations;

import javafx.scene.image.WritableImage;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

/**
 * 
 * A class that extrapolate the sprites from spritesheet.
 *
 */
public class SpriteSheet {

    private WritableImage image;
    private final int width;
    private final int height;
    private final int row;
    private final int col;
    private BufferedImage sheet;
    private final List<WritableImage> imagesList = new ArrayList<>();
    private static final int OFFSET_SHEET = 13;

    /**
     * Creates a SpriteSheet.
     * @param path the path of the sheet
     * @param rows how many rows are in the sheet
     * @param cols how many cols are in the sheet
     */
    public SpriteSheet(final String path, final int rows, final int cols) {
        try {
            this.sheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.row = rows;
        this.col = cols;

        this.width = this.sheet.getWidth(null) / cols;
        this.height = this.sheet.getHeight(null) / rows;

    }

    /**
     * Give the current width of the sprite to cut.
     * 
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Give the current height of the sprite to cut.
     * 
     * @return height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * A method that gets the specified sprites and converts it from a BufferedImage into JavaFX image.
     * @param x the row
     * @param y the col
     * @return a JavaFX image
     */
    public WritableImage getFxImageFromSheet(final int x, final int y) {

        return SwingFXUtils.toFXImage(getSprite(x, y), image);
    }

    /**
     * It provides a list of JavaFX images with all the Sprites.
     * @return the list
     */
    public List<WritableImage> getSpriteList() {

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < col; j++) {
                imagesList.add(getFxImageFromSheet(i, j));
            }
        }

        return this.imagesList;
    }

    /**
     * It provides a sub image from the original sheet. It takes the sub images as a table.
     * @param x the row
     * @param y the column
     * @return a BufferedImage that is the sub image
     */
    private BufferedImage getSprite(final int x, final int y) {

        return this.sheet.getSubimage(y * getWidth() + OFFSET_SHEET, x * getHeight(), getWidth(), getHeight());
    }
}
