package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import model.AbstractEntity;
import model.blocks.Bomb;
import model.blocks.IndestructibleBlock;
import model.blocks.Terrain;
import model.collisions.CollisionImpl;
import model.map.GameMap;
import model.player.Player;
import model.utils.Pair;
import view.game.GameController;

public class BombTimer extends TimerTask {

    private final ControllerImpl controller;
    private final GameController view;
    private final Bomb bomb;
    private final GameMap map;
    private final List<Player> players;
    private List<Player> killedPlayer;

    public BombTimer(final Bomb bomb, final List<Player> players, final GameMap map, final ControllerImpl controller, final GameController view) {
        this.controller = controller;
        this.view = view;
        this.bomb = bomb;
        this.map = map;
        this.players = players;
        this.killedPlayer = new ArrayList<>();
    }

    @Override
    public void run() {
        final List<AbstractEntity> interestedBlock = this.getExplosionBlocks(bomb.getRange(), bomb.getInitialPosition().getX(), bomb.getInitialPosition().getY());

        this.view.explodeBomb(bomb, interestedBlock);
        //attend the view explosion to finish then delete block in the model
        interestedBlock.add(bomb);
        for (final AbstractEntity block : interestedBlock) {
            this.map.setBlock(
                    new Terrain(new Pair<>(block.getInitialPosition().getX(), block.getInitialPosition().getY())),
                    block.getInitialPosition().getX(), block.getInitialPosition().getY());
            this.map.getBlock(block.getInitialPosition().getX(), block.getInitialPosition().getY()).setHeight(block.getHeight());
            this.map.getBlock(block.getInitialPosition().getX(), block.getInitialPosition().getY()).setWidth(block.getWidth());
        }
        CollisionImpl collision;
        for (final Player player : players) {
            collision = new CollisionImpl(player);
            if (collision.bombCollided(interestedBlock)) {
                player.setStatus(true);
            }
        }
        this.controller.notifyKilledPlayers(); 
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
            }
        }
        return blocks;
    }
}
