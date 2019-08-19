package controller;

import java.util.List;
import java.util.TimerTask;

import model.AbstractEntity;
import model.blocks.Bomb;
import model.blocks.Terrain;
import model.map.GameMap;
import model.utils.Pair;
import view.game.GameController;

public class BombTimer extends TimerTask {

    private final GameController view;
    private final Bomb bomb;
    private final List<AbstractEntity> interestedBlock;
    private final GameMap map;

    public BombTimer(final Bomb bomb, final List<AbstractEntity> interestedBlock, final GameMap map,
            final GameController view) {
        this.map = map;
        this.view = view;
        this.bomb = bomb;
        this.interestedBlock = interestedBlock;
    }

    @Override
    public void run() {
        this.view.explodeBomb(bomb, interestedBlock);
        for (AbstractEntity block : interestedBlock) {
            this.map.setBlock(
                    new Terrain(new Pair<>(block.getInitialPosition().getX(), block.getInitialPosition().getY())),
                    block.getInitialPosition().getX(), block.getInitialPosition().getY());
        }
    }
}
