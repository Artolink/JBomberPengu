package controller.game;

import java.util.Optional;

import controller.map.MapGenerator;
import controller.map.MapOnFile;
import model.AbstractIndestructibleEntity;
import model.Level;
import model.map.GameMap;
import model.utils.Pair;
import view.game.GameController;

/**
 * Game controller.
 */
public class Game {

    private static final int MAPROWS = 15;
    private static final int MAPCOLUMN = 15;
    private static final double BLOCKDIMENSION = 40.0;
    private static final double BLOCKSPACING = 0;
    private static final double PANEOFFSET = 10.0;
    private final GameController viewController;
    private final Level level;
    private GameMap map;
    private PlayerMover firstPlayerMover;
    private PlayerMover secondPlayerMover;

    /**
     * Constructor initialize level and link to view controller.
     * @param controller view controller
     */
    public Game(final GameController controller) {
        this.viewController = controller;
        this.level = new Level();
    }

    /**
     * Init game.
     * Draw map associated at first level, draw players and handle their moving in map
     */
    public void init() {
        // check if map is saved on file or generate it (and save it into file)
        final MapOnFile onFile = new MapOnFile();
        if (onFile.containsLevel(level)) {
            final Optional<GameMap> optionalMap = onFile.getMap(level);
            if (optionalMap.isPresent()) {
                this.map = optionalMap.get();
            } else {
                this.map = generateMap();
            }
        } else {
            this.map = generateMap();
        }

        // set container and sizes
        this.viewController
                .setDimensions(new Pair<Integer, Integer>(map.getDimensions().getX(), map.getDimensions().getY()));
        this.viewController.setBlockDimension(BLOCKDIMENSION);
        this.viewController.setBlockSpacing(BLOCKSPACING);
        this.viewController.setBorderOffset(PANEOFFSET);
        this.viewController.resizeToMap();

        // render map on view
        for (int a = 0; a < map.getDimensions().getX(); a++) {
            for (int b = 0; b < map.getDimensions().getY(); b++) {
                final AbstractIndestructibleEntity block = this.map.getBlock(b, a);
                this.viewController.draw(block.getImagePath(), b, a);
            }
        }

        // players

        this.viewController.drawPlayers();
        this.viewController.setPlayersVelocity(10);

        // set first (top-left) player control in thread
        this.firstPlayerMover = new PlayerMover();
        this.firstPlayerMover.setPlayer(true);
        this.firstPlayerMover.setDirection("");
        this.firstPlayerMover.setController(viewController);
        new Thread(firstPlayerMover).start();
        // set second (bottom-right) player control in thread
        this.secondPlayerMover = new PlayerMover();
        this.secondPlayerMover.setPlayer(false);
        this.secondPlayerMover.setDirection("");
        this.secondPlayerMover.setController(viewController);
        new Thread(secondPlayerMover).start();
    }

    /**
     * Move first player in the specified direction.
     * 
     * @param direction direction to move
     */
    public void moveFirstPlayer(final String direction) {
        if (!this.firstPlayerMover.getDirection().equals(direction)) {
            this.firstPlayerMover.setDirection(direction);
        }
    }

    /**
     * Stop moving player in the specified direction if it moving in the specified
     * direction. Otherwise it keep moving in the actual direction set
     * 
     * @param direction direction to stop moving
     */
    public void stopFirstPlayer(final String direction) {
        if (this.firstPlayerMover.getDirection().equals(direction)) {
            this.firstPlayerMover.setDirection("");
        }
    }

    /**
     * Move second player in the specified direction.
     * 
     * @param direction direction to move
     */
    public void moveSecondPlayer(final String direction) {
        if (!this.secondPlayerMover.getDirection().equals(direction)) {
            this.secondPlayerMover.setDirection(direction);
        }
    }

    /**
     * Stop moving player in the specified direction if it moving in the specified
     * direction. Otherwise it keep moving in the actual direction set
     * 
     * @param direction direction to stop moving
     */
    public void stopSecondPlayer(final String direction) {
        if (this.secondPlayerMover.getDirection().equals(direction)) {
            this.secondPlayerMover.setDirection("");
        }
    }

    private GameMap generateMap() {
        final MapGenerator generator = new MapGenerator(this.level, new Pair<Integer, Integer>(MAPROWS, MAPCOLUMN));
        return generator.get();
    }

    /**
     * Controller of players moving.
     */
    private class PlayerMover implements Runnable {

        private String direction;
        private boolean firstPlayer;
        private static final int TIMETOSLEEP = 100;
        private GameController controller;

        @Override
        public void run() {
            while (true) {
                if (haveToMove()) {
                    if (firstPlayer) {
                        controller.moveFirstPlayer(direction);
                    } else {
                        controller.moveSecondPlayer(direction);
                    }
                }
                try {
                    Thread.sleep(TIMETOSLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean haveToMove() {
            return !this.direction.isEmpty();
        }

        public void setPlayer(final boolean firstPlayer) {
            this.firstPlayer = firstPlayer;
        }

        public void setDirection(final String direction) {
            this.direction = direction;
        }

        public String getDirection() {
            return this.direction == null ? "" : this.direction;
        }

        public void setController(final GameController controller) {
            this.controller = controller;
        }

    }

}
