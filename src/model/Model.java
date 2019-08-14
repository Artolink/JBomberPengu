package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.map.MapGenerator;
import controller.map.MapOnFile;
import javafx.geometry.Point2D;
import model.map.GameMap;
import model.player.Player;
import model.utils.Pair;

/**
 * Game controller.
 */
public class Model {

    private static final int MAPROWS = 15;
    private static final int MAPCOLUMN = 15;

    private final Level level;
    private GameMap map;
    private List<Player> players = new ArrayList<Player>();

    /**
     * Constructor initialize level and link to view controller.
     */
    public Model() {
        this.level = new Level();
        this.init();
    }

    private void init() {
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

        this.players.add(new Player("Marco", new Pair<Integer, Integer>(0, 0)));
        this.players.add(new Player("Andrea", 
                new Pair<Integer, Integer>(this.map.getDimensions().getX() - 1, this.map.getDimensions().getY() - 1)));
    }

    private GameMap generateMap() {
        final MapGenerator generator = new MapGenerator(this.level, new Pair<Integer, Integer>(MAPROWS, MAPCOLUMN));
        return generator.get();
    }

    public final GameMap getGameMap() {
        return this.map;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

}
