package view.game;

import java.util.List;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.player.Player;
import model.player.Player.Directions;
import model.utils.Pair;
import view.GUIImpl;
import view.MyPane;

/**
 * Controller of Game.fxml. It draws the game interface.
 */
public class GameController extends GUIImpl {

    private int blockDimension;
    private int blockSpacing;
    private int borderOffset;
    private Pair<Integer, Integer> dimensions;

    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private Button button;
    @FXML
    private GraphicsContext gc;

    private final MyPane canvas = new MyPane();
    private Controller controller;

    /**
     * Initialize the Game scene.
     */
    public void initialize() {

        anchorPane1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        canvas.prefWidth(anchorPane1.getPrefWidth());
        canvas.prefHeight(anchorPane1.getPrefHeight() - button.getLayoutY());
        anchorPane1.getChildren().add(canvas);
        this.controller = new Controller(this);

        anchorPane1.setOnKeyPressed((key) -> {
            switch (key.getCode()) {
                case UP:
                    this.controller.movePlayer(1, Directions.UP);
                    break;
                case DOWN:
                    this.controller.movePlayer(1, Directions.DOWN);
                    break;
                case LEFT:
                    this.controller.movePlayer(1, Directions.LEFT);
                    break;
                case RIGHT:
                    this.controller.movePlayer(1, Directions.RIGHT);
                    break;
                case A:
                    this.controller.movePlayer(0, Directions.LEFT);
                    break;
                case S:
                    this.controller.movePlayer(0, Directions.DOWN);
                    break;
                case D:
                    this.controller.movePlayer(0, Directions.RIGHT);
                    break;
                case W:
                    this.controller.movePlayer(0, Directions.UP);
                    break;
                default:
                    break;
            }
        });

        anchorPane1.setOnKeyReleased((key) -> {
            switch (key.getCode()) {
                case UP:
                    this.controller.stopPlayer(1, Directions.UP);
                    break;
                case DOWN:
                    this.controller.stopPlayer(1, Directions.DOWN);
                    break;
                case LEFT:
                    this.controller.stopPlayer(1, Directions.LEFT);
                    break;
                case RIGHT:
                    this.controller.stopPlayer(1, Directions.RIGHT);
                    break;
                case A:
                    this.controller.stopPlayer(0, Directions.LEFT);
                    break;
                case S:
                    this.controller.stopPlayer(0, Directions.DOWN);
                    break;
                case D:
                    this.controller.stopPlayer(0, Directions.RIGHT);
                    break;
                case W:
                    this.controller.stopPlayer(0, Directions.UP);
                    break;
                default:
                    break;
            }
        });

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
        canvas.prefWidth(width);
        canvas.prefHeight(height);
    }

    /**
     * Fix the dimension of the windows game to the map.
     * @return a pair that contains the correct dimensions fixed with the map
     */
    public Pair<Integer, Integer> resizeToMap() {
        final Pair<Integer, Integer> pair = computeWindowSize(dimensions.getX(), dimensions.getY());
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
    public void setBlockDimension(final int dim) {
        this.blockDimension = dim;
    }

    /**
     * Set the block spacing from one another.
     * 
     * @param spacing spacing
     */
    public void setBlockSpacing(final int spacing) {
        this.blockSpacing = spacing;
    }

    /**
     * Set the border offset from the window border.
     * 
     * @param offset offset
     */
    public void setBorderOffset(final int offset) {
        this.borderOffset = offset;
    }

    private Pair<Integer, Integer> computeWindowSize(final Integer row, final Integer col) {
        return new Pair<Integer, Integer>(blockDimension * row + 2 * borderOffset, blockDimension * col + 2 * borderOffset);
    }

    /**
     * An event occurs when the button is pressed.
     */
    @FXML
    public void buttonPressed() {
        loadPage("MainMenu");
    }

    /**
     * Draw the images on the game area.
     * 
     * @param path image to draw
     * @param row  row to add the image
     * @param col  column to add the image
     */
    public void draw(final String path, final int row, final int col) {
        if (path != null) {
            final Image image = new Image(path, blockDimension - blockSpacing, blockDimension - blockSpacing, false,
                    false);
            final ImageView view = new ImageView(image);
            view.relocate(blockDimension * row + borderOffset, blockDimension * col + borderOffset);
            canvas.addNode(view, row, col);
        }
    }

    /**
     * Draw the players specified in parameter.
     * @param players players to draw
     */
    public void drawPlayers(final List<Player> players) {
        for (final Player player : players) {
            this.draw(player.getImagePath(), player.getInitialPosition().getX(), player.getInitialPosition().getY());
            player.setPosition(new Pair<>(blockDimension * player.getInitialPosition().getX() + borderOffset,
                    blockDimension * player.getInitialPosition().getY() + borderOffset));
        }
    }

    /**
     * Move the specified player in the final specified position.
     * @param player player to move
     * @param row row parameter
     * @param col column parameter
     */
    public void movePlayer(final Player player, final int row, final int col) {
        this.canvas
            .getNode(player.getInitialPosition().getX(), player.getInitialPosition().getY())
            .relocate(row, col);
    }
}
