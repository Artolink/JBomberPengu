package view.game;

import java.util.List;

import controller.ControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.player.Player;
import model.utils.Pair;
import view.GUIImpl;
import view.animations.PlayerAnimations;

/**
 * Controller of Game.fxml. It draws the game interface.
 */
public class GameController extends GUIImpl {

    private int blockDimension;
    private int blockSpacing;
    private Pair<Integer, Integer> dimensions;
    private final KeyAssociator associator = new KeyAssociator();

    @FXML
    private MyPane canvas;

    private ControllerImpl controller;

    /**
     * Initialize the Game scene.
     */
    public void initialize() {
        this.controller = (ControllerImpl) getController();
        this.controller.initGame(this);
    }

    /**
     * It keeps moving while the key is pressed.
     * @param event the keyPressed event
     */
    @FXML
    public void keyPressed(final KeyEvent event) {
        final KeyCode code = event.getCode();
        if (associator.contains(code)) {
            this.controller.movePlayer(associator.getPlayer(code), associator.getDirection(code));
        }
    }

    /**
     * It stops moving while the key is pressed.
     * @param event the keyRelased event
     */
    @FXML
    public void keyReleased(final KeyEvent event) {
        final KeyCode code = event.getCode();
        if (associator.contains(code)) {
            this.controller.stopPlayer(associator.getPlayer(code), associator.getDirection(code));
        }
    }

    /**
     * Change the dimensions of the canvas, anchorpane and scene.
     * 
     * @param width  width to change
     * @param height height to change
     */
    public void setWindowSize(final double width, final double height) {
        canvas.prefWidth(width);
        canvas.prefHeight(height);
    }

    /**
     * Fix the dimension of the windows game to the map.
     * 
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

    private Pair<Integer, Integer> computeWindowSize(final Integer row, final Integer col) {
        return new Pair<Integer, Integer>(blockDimension * row,
                blockDimension * col);
    }

    /**
     * Draw the images on the game area.
     * 
     * @param path image to draw
     * @param row  row to add the image
     * @param col  column to add the image
     */
    public void draw(final String path, final int row, final int col) {
        if (!path.isEmpty()) {
            final Image image = new Image(path, blockDimension - blockSpacing, blockDimension - blockSpacing, false,
                    false);
            final ImageView view = new ImageView(image);
            view.relocate(blockDimension * row, blockDimension * col);
            canvas.addNode(view, row, col);
        }
    }

    /**
     * Draw the players specified in parameter.
     * 
     * @param players players to draw
     */
    public void drawPlayers(final List<Player> players) {
        for (final Player player : players) {
            player.setWidth(blockDimension - blockSpacing);
            player.setHeight(blockDimension - blockSpacing);
            final ImageView view = new ImageView();
            view.setFitHeight(blockDimension - blockSpacing);
            view.setFitWidth(blockDimension - blockSpacing);
            view.relocate(blockDimension * player.getInitialPosition().getX(), blockDimension * player.getInitialPosition().getY());
            canvas.addNode(view, player.getInitialPosition().getX(), player.getInitialPosition().getY());
            final PlayerAnimations animation = new PlayerAnimations();
            animation.setImageView(view);
            animation.setPlayer(player);
            new Thread(animation).start();
            player.setPosition(new Pair<>(blockDimension * player.getInitialPosition().getX(),
                    blockDimension * player.getInitialPosition().getY()));
            associator.associatePlayer(player);
        }
    }

    /**
     * Move the specified player in the final specified position.
     * 
     * @param player player to move
     * @param row    row parameter
     * @param col    column parameter
     */
    public void movePlayer(final Player player, final int row, final int col) {
        this.canvas.getNode(player.getInitialPosition().getX(), player.getInitialPosition().getY()).relocate(row, col);
    }
}
