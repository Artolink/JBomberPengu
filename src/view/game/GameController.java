package view.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.ControllerImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import model.AbstractEntity;
import model.blocks.Bomb;
import model.language.ApplicationStrings;
import model.player.Player;
import model.utils.Pair;
import view.PageController;
import view.animations.PlayerAnimations;
import view.animations.WakeAnimations;

/**
 * Controller of Game.fxml. It draws the game interface.
 */
public class GameController extends PageController {

    private static final long EXPLOSIONSDELAY = 15;
    private int blockDimension;
    private int blockSpacing;
    private Pair<Integer, Integer> dimensions;
    private final KeyAssociator associator = new KeyAssociator();
    private final List<PlayerAnimations> playerAnimations = new ArrayList<>();

    private String scoreString;

    @FXML
    private MyPane canvas;
    @FXML
    private Button button;
    @FXML
    private Label plRedScore;
    @FXML
    private HBox bombCounterR;
    @FXML
    private Label plYellScore;
    @FXML
    private HBox bombCounterY;

    private final Alert alert = new Alert(AlertType.CONFIRMATION);
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
     * 
     * @param event the keyPressed event
     */
    @FXML
    public void keyPressed(final KeyEvent event) {
        final KeyCode code = event.getCode();
        if (associator.contains(code) && !associator.isBombControl(code)) {
            this.controller.movePlayer(associator.getPlayer(code), associator.getDirection(code));
        }
    }

    /**
     * It stops moving while the key is pressed.
     * 
     * @param event the keyRelased event
     */
    @FXML
    public void keyReleased(final KeyEvent event) {
        final KeyCode code = event.getCode();
        if (associator.contains(code)) {
            if (associator.isBombControl(code)) {
                this.controller.releaseBomb(associator.getPlayer(code));
            } else {
                this.controller.stopPlayer(associator.getPlayer(code), associator.getDirection(code));
            }
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
        return new Pair<Integer, Integer>(blockDimension * row, blockDimension * col);
    }

    /**
     * Draw the images on the game area.
     * 
     * @param path image to draw
     * @param row  row to add the image
     * @param col  column to add the image
     */
    public void draw(final String path, final int row, final int col) {
        ImageView view = null;
        if (!path.isEmpty()) {
            final Image image = new Image(path, blockDimension - blockSpacing, blockDimension - blockSpacing, false,
                    false);
            view = new ImageView(image);
        } else {
            view = new ImageView();
        }
        view.relocate(blockDimension * row, blockDimension * col);
        canvas.addNode(view, row, col);
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
            view.relocate(blockDimension * player.getInitialPosition().getX(),
                    blockDimension * player.getInitialPosition().getY());
            canvas.addPlayer(view, player.getInitialPosition().getX(), player.getInitialPosition().getY());
            final PlayerAnimations animation = new PlayerAnimations();
            animation.setImageView(view);
            animation.setPlayer(player);
            playerAnimations.add(animation);
            new Thread(animation).start();
            associator.associatePlayer(player);
        }
    }

    /**
     * stop all players animation.
     */
    public void stopPlayerAnimations() {
        for (final PlayerAnimations a: playerAnimations) {
            a.stop();
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
        this.canvas.getPlayer(player.getInitialPosition().getX(), player.getInitialPosition().getY()).relocate(row, col);
    }

    /**
     * Draw the bomb on the game area.
     * 
     * @param path image to draw
     * @param row  row to add the bomb
     * @param col  column to add the bomb
     */
    public void drawBomb(final String path, final int row, final int col) {
        if (!path.isEmpty()) {
            final Image image = new Image(path, blockDimension - blockSpacing, blockDimension - blockSpacing, false,
                    false);
            ImageView view = (ImageView) canvas.getNode(row, col);
            if (view == null) {
                view = new ImageView(image);
                canvas.addNode(view, row, col);
            } else {
                view.setImage(image);
            }
            view.relocate(blockDimension * row, blockDimension * col);
            if (isSoundEnabled()) {
                getSounds().getBombPlacedSound().play();
            }
        }
    }

    /**
     * Draw the Explosion on the game area.
     * 
     * @param bomb bomb to explode
     * @param interestedBlocks 
     */
    public void explodeBomb(final Bomb bomb, final List<AbstractEntity> interestedBlocks) {
        final List<Thread> threads = new ArrayList<>();
        for (final AbstractEntity block : interestedBlocks) {
            final WakeAnimations animation = new WakeAnimations();
            animation.setPlayer(bomb.getPlayerInfo());
            final ImageView image = (ImageView) canvas.getNode(block.getInitialPosition().getX(), block.getInitialPosition().getY());
            image.setFitHeight(blockDimension - blockSpacing);
            image.setFitWidth(blockDimension - blockSpacing);
            animation.setImageView(image);
            threads.add(new Thread(animation));
        }
        if (isSoundEnabled()) {
            getSounds().getExplosionSound().play();
        }
        for (final Thread thread : threads) {
            thread.start();
            try {
                Thread.sleep(EXPLOSIONSDELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (final Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (final AbstractEntity block : interestedBlocks) {
            canvas.removeNode(block.getInitialPosition().getX(), block.getInitialPosition().getY());
        }
        canvas.removeNode(bomb.getInitialPosition().getX(), bomb.getInitialPosition().getY());
    }

    /**
     * An event occurs when the button is pressed.
     */
    @FXML
    public void buttonPressed() {
        alert.setTitle("AAA");
        alert.setHeaderText("do you really want to give up?");
        alert.setContentText("umh...");
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            getController().actionPerformedLoseBtn();
        } else {
            alert.close();
        }
    }

    /**
     * Changes the score visualized for specified player.
     * @param player player to take the score from
     */
    public void showScore(final Player player) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (player.getColor()) {
                    case RED:
                        plRedScore.setText(scoreString + ": " + player.getScore());
                        break;
                    case YELLOW:
                        plYellScore.setText(scoreString + ": " + player.getScore());
                        break;
                    default:
                }
            }
        });
    }

    /**
     * 
     * @param player 
     */
    public void showAvailableBombs(final Player player) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (player.getColor()) {
                    case RED:
                        bombCounterR.getChildren().clear();
                        for (int i = 0; i < player.getBombNumber(); i++) {
                            final ImageView img = new ImageView(new Image("/view/bomba_rossa.png"));
                            img.setFitWidth(blockDimension);
                            img.setFitHeight(blockDimension);
                            bombCounterR.getChildren().add(img);
                        }
                        break;
                    case YELLOW:
                        bombCounterY.getChildren().clear();
                        for (int i = 0; i < player.getBombNumber(); i++) {
                            final ImageView img = new ImageView(new Image("/view/bomba_gialla.png"));
                            img.setFitWidth(blockDimension);
                            img.setFitHeight(blockDimension);
                            bombCounterY.getChildren().add(img);
                        }
                        break;
                    default:
                }
            }
        });
    }

    @Override
    public final void translate(final ApplicationStrings t) {
        this.scoreString = t.getValueOf("score");
        button.setText(t.getValueOf("give up"));
    }
}

