package view.game;

import java.io.File;
import java.util.Map;

import controller.game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.utils.Pair;
import view.GUIImpl;

/**
 * Controller of Game.fxml. It draws the game interface.
 */
public class GameController extends GUIImpl {

    private double blockDimension;
    private double blockSpacing;
    private double borderOffset;
    private int velocity;
    private Pair<Integer, Integer> dimensions;

    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private Button button;

    private final Pane canvas = new Pane();
    private Game game;
    private Map<Pair<Integer, Integer>, ImageView> blockMap;
    private ImageView player1;
    private ImageView player2;

    /**
     * Initialize the Game scene.
     */
    public void initialize() {

        this.game = new Game(this);

        // button.setText(getTranslator().getValueOf("give up"));

        anchorPane1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        canvas.prefWidth(anchorPane1.getPrefWidth());
        canvas.prefHeight(anchorPane1.getPrefHeight() - button.getLayoutY());
        anchorPane1.getChildren().add(canvas);
        // getPage("Game").getScene().width

        this.game.init();

        anchorPane1.setOnKeyPressed((key) -> {
            final KeyCode code = key.getCode();
            if (code.equals(KeyCode.UP) || code.equals(KeyCode.DOWN) || code.equals(KeyCode.LEFT)
                    || code.equals(KeyCode.RIGHT)) {
                this.game.moveSecondPlayer(code.toString());
            } else if (code.equals(KeyCode.A) || code.equals(KeyCode.S) || code.equals(KeyCode.D)
                    || code.equals(KeyCode.W)) {
                this.game.moveFirstPlayer(code.toString());
            }
        });

        anchorPane1.setOnKeyReleased((key) -> {
            final KeyCode code = key.getCode();
            if (code.equals(KeyCode.UP) || code.equals(KeyCode.DOWN) || code.equals(KeyCode.LEFT)
                    || code.equals(KeyCode.RIGHT)) {
                this.game.stopSecondPlayer(code.toString());
            } else if (code.equals(KeyCode.A) || code.equals(KeyCode.S) || code.equals(KeyCode.D)
                    || code.equals(KeyCode.W)) {
                this.game.stopFirstPlayer(code.toString());
            }
        });

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
            blockMap.put(new Pair<Integer, Integer>(row, col), view);
            canvas.getChildren().add(view);
        }
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

        computeX = blockDimension * row + 2 * borderOffset;
        computeY = blockDimension * col + 2 * borderOffset;
        return new Pair<Double, Double>(computeX, computeY);
    }

    /**
     * An event occurs when the button is pressed.
     */
    @FXML
    public void buttonPressed() {
        loadPage("MainMenu");
    }

    /**
     * It draws the players on the start point.
     */
    public void drawPlayers() {
        final Image imagePlayer1 = new Image(
                ClassLoader.getSystemClassLoader().getResource("view") + File.separator + "bomba gialla.png",
                blockDimension - blockSpacing, blockDimension - blockSpacing, false, false);
        this.player1 = new ImageView(imagePlayer1);
        this.player1.relocate(borderOffset, borderOffset);
        canvas.getChildren().add(this.player1);

        final Image imagePlayer2 = new Image(
                ClassLoader.getSystemClassLoader().getResource("view") + File.separator + "bomba rossa.png",
                blockDimension - blockSpacing, blockDimension - blockSpacing, false, false);
        this.player2 = new ImageView(imagePlayer2);
        this.player2.relocate(blockDimension * (dimensions.getX() - 1) + borderOffset,
                blockDimension * (dimensions.getX() - 1) + borderOffset);
        canvas.getChildren().add(this.player2);
    }

    /**
     * It moves one player.
     * 
     * @param direction direction to move
     */
    public void moveFirstPlayer(final String direction) {
        switch (direction) {
        case "S":
            player1.relocate(player1.getLayoutX(), player1.getLayoutY() + velocity);
            break;
        case "A":
            player1.relocate(player1.getLayoutX() - velocity, player1.getLayoutY());
            break;
        case "D":
            player1.relocate(player1.getLayoutX() + velocity, player1.getLayoutY());
            break;
        case "W":
            player1.relocate(player1.getLayoutX(), player1.getLayoutY() - velocity);
            break;
        default:
            break;
        }
    }

    /**
     * It moves one player.
     * 
     * @param direction direction to move
     */
    public void moveSecondPlayer(final String direction) {
        switch (direction) {
        case "DOWN":
            player2.relocate(player2.getLayoutX(), player2.getLayoutY() + velocity);
            break;
        case "LEFT":
            player2.relocate(player2.getLayoutX() - velocity, player2.getLayoutY());
            break;
        case "RIGHT":
            player2.relocate(player2.getLayoutX() + velocity, player2.getLayoutY());
            break;
        case "UP":
            player2.relocate(player2.getLayoutX(), player2.getLayoutY() - velocity);
            break;
        default:
            break;
        }
    }

    /**
     * Setter for velocity.
     * 
     * @param velocity velocity
     */
    public void setPlayersVelocity(final int velocity) {
        this.velocity = velocity;
    }
}
