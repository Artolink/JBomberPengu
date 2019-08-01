package controller.map;

import javax.naming.CannotProceedException;

import model.Level;
import model.map.GameMap;
import model.utils.BiDimension;

/**
 * Generate a GameMap from scratch.
 *
 */
public class MapGenerator {

    private final Level level;
    private final BiDimension dimensions;

    /**
     * Initialize the level and dimensions of the GameMap to be generated. 
     * @param level level to generate
     * @param dimensions dimension of the GameMap
     */
    public MapGenerator(final Level level, final BiDimension dimensions) {
        this.level = level;
        this.dimensions = dimensions;
    }

    /**
     * Generate and return a  GameMap.
     * @return a GameMap object with level and dimension specified in constructor
     */
    public GameMap get() {
        final GameMap generata = this.generate();
        try {
            final MapOnFile mapOnFile = new MapOnFile();
            if (!mapOnFile.containsLevel(this.level)) {
                mapOnFile.save(generata, this.level);
            }
        } catch (CannotProceedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generata;
    }

    private GameMap generate() {
        final GameMap map = new GameMap(this.dimensions);
        map.setAllEmpty();
        // TODO generazione
        return map;

    }

}
