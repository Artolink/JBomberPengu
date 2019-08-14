package controller.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.naming.CannotProceedException;

import model.Level;
import model.blocks.DestructibleBlock;
import model.blocks.IndestructibleBlock;
import model.map.GameMap;
import model.utils.Pair;

/**
 * Generate a GameMap from scratch.
 *
 */
public class MapGenerator implements MapGeneratorInterface {

    private final Level level;
    private final Pair<Integer, Integer> dimensions;
    private final List<Pair<Integer, Integer>> illegalPosition;

    /**
     * Initialize the level and dimensions of the GameMap to be generated.
     * 
     * @param level      level to generate
     * @param dimensions dimension of the GameMap
     */
    public MapGenerator(final Level level, final Pair<Integer, Integer> dimensions) {
        this.level = level;
        this.dimensions = dimensions;
        this.illegalPosition = new ArrayList<Pair<Integer, Integer>>();
        this.illegalPosition.add(new Pair<Integer, Integer>(0, 0));
        this.illegalPosition.add(new Pair<Integer, Integer>(0, 1));
        this.illegalPosition.add(new Pair<Integer, Integer>(1, 0));
        this.illegalPosition.add(new Pair<Integer, Integer>(dimensions.getX() - 1, dimensions.getY() - 1));
        this.illegalPosition.add(new Pair<Integer, Integer>(dimensions.getX() - 2, dimensions.getY() - 1));
        this.illegalPosition.add(new Pair<Integer, Integer>(dimensions.getX() - 1, dimensions.getY() - 2));
    }

    @Override
    public final GameMap get() {
        final GameMap generata = this.generate();
        try {
            final MapOnFile mapOnFile = new MapOnFile();
            if (!mapOnFile.containsLevel(this.level)) {
                mapOnFile.save(generata, this.level);
            }
        } catch (CannotProceedException e) {
            e.printStackTrace();
        }
        return generata;
    }

    private GameMap generate() {
        final GameMap map = new GameMap(this.dimensions);
        map.setAllEmpty();
        final int totaleNumberOfBlock = this.dimensions.getX() * this.dimensions.getY();
        final int numberIndestructibelBlock = (totaleNumberOfBlock / 3) - totaleNumberOfBlock / (9 + this.level.get());
        final int numberDestructibleBlock = ((totaleNumberOfBlock * 4) / 9) - totaleNumberOfBlock / (15 + this.level.get());
        for (int i = 0; i < numberIndestructibelBlock; i++) {
            while (true) {
                final Pair<Integer, Integer> whereToSet = this.getPosition();
                if (map.getBlock(whereToSet).getClass().equals(IndestructibleBlock.class)) {
                    continue;
                } else {
                    map.setBlock(new IndestructibleBlock(whereToSet), whereToSet);
                    break;
                }
            }
        }
        for (int i = 0; i < numberDestructibleBlock; i++) {
            while (true) {
                final Pair<Integer, Integer> whereToSet = this.getPosition();
                if (map.getBlock(whereToSet).getClass().equals(IndestructibleBlock.class)
                        || map.getBlock(whereToSet).getClass().equals(DestructibleBlock.class)) {
                    continue;
                } else {
                    map.setBlock(new DestructibleBlock(whereToSet), whereToSet);
                    break;
                }
            }
        }
        return map;
    }

    private Pair<Integer, Integer> getPosition() {
        Pair<Integer, Integer> position;
        while (true) {
            final Random rand = new Random();
            rand.setSeed(System.currentTimeMillis());
            position = new Pair<Integer, Integer>(rand.nextInt(this.dimensions.getX()),
                    rand.nextInt(this.dimensions.getY()));
            if (this.illegalPosition.contains(position)) {
                continue;
            } else {
                break;
            }
        }
        return position;
    }

}
