package view.game;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import model.utils.Pair;
import view.GUIImpl;

/**
 * Controller of Game.fxml. It draws the game interface.
 */
public class GameController extends GUIImpl {

    private double blockDimension;
    private double blockSpacing;
    private double borderOffset;

    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private Button button;
    @FXML
    private GraphicsContext gc;

    private final Canvas canvas = new Canvas();
    private Pair<Integer, Integer> dimensions;

    /**
     * Initialize the Game scene.
     */
    public void initialize() {

        //button.setText(getTranslator().getValueOf("give up"));

        anchorPane1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        canvas.setWidth(anchorPane1.getPrefWidth());
        canvas.setHeight(anchorPane1.getPrefHeight() - button.getLayoutY());
        gc = canvas.getGraphicsContext2D();
        anchorPane1.getChildren().add(canvas);
        // getPage("Game").getScene().width

    }

    /**
     * Draw the images on the game area.
     * 
     * @param image image to draw
     * @param row   row to add the image
     * @param col   column to add the image
     */
    public void draw(final Image image, final int row, final int col) {

        gc.drawImage(image, blockDimension * row + borderOffset, blockDimension * col + borderOffset,
                blockDimension - blockSpacing, blockDimension - blockSpacing);
    }

    /**
     * Change the dimensions of the canvas, anchorpane and scene.
     * 
     * @param width  width to change
     * @param height height to change
     */
    public void setWindowSize(final double width, final double height) {

        anchorPane1.setPrefWidth(width);
        anchorPane1.setPrefHeight(height);
        canvas.setWidth(width);
        canvas.setHeight(height - button.getLayoutY());
    }

    /**
     * Fix the dimension of the windows game to the map.
     * 
     * @return a pair that contains the correct dimensions fixed with the map
     */
    public Pair<Double, Double> resizeToMap() {

        Pair<Double, Double> pair;
        pair = computeWindowSize(dimensions.getX(), dimensions.getY());
        setWindowSize(pair.getX(), pair.getY());
        return pair;
    }

    /**
     * Controller give me the map size.
     * 
     * @param dim map size (row and columns)
     */
    public void setDimensions(final Pair<Integer, Integer> dim) {

        this.dimensions = dim;
    }

    /**
     * Get size of the panel game.
     * 
     * @return a pair that contains the dimensions of the panel game
     */
    @Override
    public Pair<Double, Double> getSizes() {

        return new Pair<Double, Double>(canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Set the block dimension.
     * 
     * @param dim dimension
     */
    public void setBlockDimension(final Double dim) {

        this.blockDimension = dim;
    }

    /**
     * Set the block spacing from one another.
     * 
     * @param spacing spacing
     */
    public void setBlockSpacing(final Double spacing) {

        this.blockSpacing = spacing;
    }

    /**
     * Set the border offset from the window border.
     * 
     * @param offset offset
     */
    public void setBorderOffset(final Double offset) {

        this.borderOffset = offset;
    }

    private Pair<Double, Double> computeWindowSize(final Integer row, final Integer col) {

        double computeX = 0;
        double computeY = 0;

        computeX = blockDimension * row;
        computeY = blockDimension * col;
        return new Pair<Double, Double>(computeX, computeY);
    }


    /**
     * An event occurs when the button is pressed.
     */
    @FXML
    public void buttonPressed() {
        loadPage("MainMenu");
    }
}
