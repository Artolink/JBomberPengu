package view.game;

<<<<<<< HEAD
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
=======
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
>>>>>>> 45d0a3e8936d7801c5148a405a6ab30d27e1fa05
import model.utils.Pair;
import view.GUIImpl;
import view.MyPane;

/**
 * Controller of Game.fxml. It draws the game interface.
 */
public class GameController extends GUIImpl {

<<<<<<< HEAD
    private int blockDimension;
    private int blockSpacing;
    private int borderOffset;
=======
    private double blockDimension;
    private double blockSpacing;
    private double borderOffset;
    private int velocity;
>>>>>>> 45d0a3e8936d7801c5148a405a6ab30d27e1fa05
    private Pair<Integer, Integer> dimensions;

    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private Button button;

<<<<<<< HEAD
    private final MyPane canvas = new MyPane();
    private Controller controller;
=======
    private final Pane canvas = new Pane();
    private Game game;
    private Map<Pair<Integer, Integer>, ImageView> blockMap;
    private ImageView player1;
    private ImageView player2;
>>>>>>> 45d0a3e8936d7801c5148a405a6ab30d27e1fa05

    /**
     * Initialize the Game scene.
     */
    public void initialize() {

<<<<<<< HEAD
=======
        this.game = new Game(this);

        // button.setText(getTranslator().getValueOf("give up"));

>>>>>>> 45d0a3e8936d7801c5148a405a6ab30d27e1fa05
        anchorPane1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        canvas.prefWidth(anchorPane1.getPrefWidth());
        canvas.prefHeight(anchorPane1.getPrefHeight() - button.getLayoutY());
        anchorPane1.getChildren().add(canvas);
<<<<<<< HEAD
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
=======
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
>>>>>>> 45d0a3e8936d7801c5148a405a6ab30d27e1fa05
            }
        });

        anchorPane1.setOnKeyReleased((key) -> {
<<<<<<< HEAD
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

=======
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
>>>>>>> 45d0a3e8936d7801c5148a405a6ab30d27e1fa05
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

<<<<<<< HEAD
    private Pair<Integer, Integer> computeWindowSize(final Integer row, final Integer col) {
        return new Pair<Integer, Integer>(blockDimension * row + 2 * borderOffset, blockDimension * col + 2 * borderOffset);
=======
    private Pair<Double, Double> computeWindowSize(final Integer row, final Integer col) {

        double computeX = 0;
        double computeY = 0;

        computeX = blockDimension * row + 2 * borderOffset;
        computeY = blockDimension * col + 2 * borderOffset;
        return new Pair<Double, Double>(computeX, computeY);
>>>>>>> 45d0a3e8936d7801c5148a405a6ab30d27e1fa05
    }

    /**
     * An event occurs when the button is pressed.
     */
    @FXML
    public void buttonPressed() {
        loadPage("MainMenu");
    }

    /**
<<<<<<< HEAD
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
=======
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
>>>>>>> 45d0a3e8936d7801c5148a405a6ab30d27e1fa05
        }
    }

    /**
<<<<<<< HEAD
     * Draw the players specified in parameter.
     * @param players players to draw
     */
    public void drawPlayers(final List<Player> players) {
        for (final Player player : players) {
            this.draw(player.getImagePath(), player.getInitialPosition().getX(), player.getInitialPosition().getY());
            player.setPosition(new Pair<>(blockDimension * player.getInitialPosition().getX() + borderOffset,
                    blockDimension * player.getInitialPosition().getY() + borderOffset));
=======
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
>>>>>>> 45d0a3e8936d7801c5148a405a6ab30d27e1fa05
        }
    }

    /**
<<<<<<< HEAD
     * Move the specified player in the final specified position.
     * @param player player to move
     * @param row row parameter
     * @param col column parameter
     */
    public void movePlayer(final Player player, final int row, final int col) {
        this.canvas
            .getNode(player.getInitialPosition().getX(), player.getInitialPosition().getY())
            .relocate(row, col);
=======
     * Setter for velocity.
     * 
     * @param velocity velocity
     */
    public void setPlayersVelocity(final int velocity) {
        this.velocity = velocity;
>>>>>>> 45d0a3e8936d7801c5148a405a6ab30d27e1fa05
    }
}
