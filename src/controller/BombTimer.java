package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import model.AbstractEntity;
import model.ModelImpl;
import model.blocks.Bomb;
import model.blocks.Explosion;
import model.blocks.IndestructibleBlock;
import model.blocks.Terrain;
import model.collisions.CollisionImpl;
import model.map.GameMap;
import model.player.Player;
import model.utils.Pair;
import view.game.GameController;

/**
 * Class that handle a bomb timer and explode the bomb.
 */
public class BombTimer extends TimerTask {

    private final ControllerImpl controller;
    private final GameController view;
    private final Bomb bomb;
    private final GameMap map;
    private final List<Player> players;

    /**
     * Initialize fields.
     * @param bomb bomb to explode
     * @param controller {@link ControllerImpl} to notify the explosion is done
     * @param view controller of the view containing drawn bomb
     */
    public BombTimer(final Bomb bomb, final ControllerImpl controller, final GameController view) {
        super();
        this.controller = controller;
        this.view = view;
        this.bomb = bomb;
        this.map = ModelImpl.getInstance().getGameMap();
        this.players = ModelImpl.getInstance().getPlayers();
    }

    @Override
    public final void run() {
        for (final Player player : players) {
            if (player.isDestroyed()) {
                return;
            }
        }
        final List<AbstractEntity> interestedBlock = this.getExplosionBlocks(bomb.getRange(), bomb.getInitialPosition().getX(), bomb.getInitialPosition().getY());
        interestedBlock.add(bomb);
        //check the player position and kill it if collide
        CollisionImpl collision;
        for (final Player player : players) {
            collision = new CollisionImpl(player);
            if (collision.bombCollided(interestedBlock)) {
                player.setStatus(true);
            }
        }
        //draw explosion on data map
        for (final AbstractEntity block : interestedBlock) {
            this.map.setBlock(
                    new Explosion(new Pair<>(block.getInitialPosition().getX(), block.getInitialPosition().getY())),
                    block.getInitialPosition().getX(), block.getInitialPosition().getY());
            this.map.getBlock(block.getInitialPosition().getX(), block.getInitialPosition().getY()).setHeight(block.getHeight());
            this.map.getBlock(block.getInitialPosition().getX(), block.getInitialPosition().getY()).setWidth(block.getWidth());
        }
        //explosion animation on view
        this.view.explodeBomb(bomb, interestedBlock);
        //attend the view explosion to finish then delete block in the model
        for (final AbstractEntity block : interestedBlock) {
            bomb.getPlayerInfo().addScore(block.getScoreValue());
            this.map.setBlock(
                    new Terrain(new Pair<>(block.getInitialPosition().getX(), block.getInitialPosition().getY())),
                    block.getInitialPosition().getX(), block.getInitialPosition().getY());
            this.map.getBlock(block.getInitialPosition().getX(), block.getInitialPosition().getY()).setHeight(block.getHeight());
            this.map.getBlock(block.getInitialPosition().getX(), block.getInitialPosition().getY()).setWidth(block.getWidth());
        }
        //update available bomb of this player
        this.bomb.getPlayerInfo().bombExploded();
        //notify the controller an explosion is occurred
        this.controller.notifyExplosionDone();
    }

    private List<AbstractEntity> getExplosionBlocks(final int range, final int row, final int column) {
        final List<AbstractEntity> blocks = new ArrayList<>();
        boolean canGoUp = true;
        boolean canGoDown = true;
        boolean canGoLeft = true;
        boolean canGoRight = true;
        AbstractEntity block;
        for (int level = 1; level <= range; level++) {
            try {
                block = map.getBlock(row, column - level);
                if (!block.getClass().getCanonicalName().equals(IndestructibleBlock.class.getCanonicalName())
                        && !block.getClass().getCanonicalName().equals(Bomb.class.getCanonicalName()) && canGoUp) {
                    blocks.add(block);
                } else {
                    canGoUp = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                block = map.getBlock(row, column + level);
                if (!block.getClass().getCanonicalName().equals(IndestructibleBlock.class.getCanonicalName())
                        && !block.getClass().getCanonicalName().equals(Bomb.class.getCanonicalName()) && canGoDown) {
                    blocks.add(block);
                } else {
                    canGoDown = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                block = map.getBlock(row - level, column);
                if (!block.getClass().getCanonicalName().equals(IndestructibleBlock.class.getCanonicalName())
                        && !block.getClass().getCanonicalName().equals(Bomb.class.getCanonicalName()) && canGoLeft) {
                    blocks.add(block);
                } else {
                    canGoLeft = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                block = map.getBlock(row + level, column);
                if (!block.getClass().getCanonicalName().equals(IndestructibleBlock.class.getCanonicalName())
                        && !block.getClass().getCanonicalName().equals(Bomb.class.getCanonicalName()) && canGoRight) {
                    blocks.add(block);
                } else {
                    canGoRight = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return blocks;
    }
}
