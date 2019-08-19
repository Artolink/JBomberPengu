package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.language.ApplicationStrings;
import model.map.GameMap;
import model.map.MapGenerator;
import model.map.MapOnFile;
import model.player.Player;
import model.player.PlayerColor;
import model.utils.Pair;

/**
 * Game model.
 */
public class Model {

    public static final int VELOCITY = 10;
    public static final int BLOCKDIMENSION = 40;
    public static final int BLOCKSPACING = 0;
    public static final int PANEOFFSET = 10;
    private static final int MAPROWS = 15;
    private static final int MAPCOLUMN = 15;

    private final Level level;
    private final ApplicationStrings translator;
    private GameMap map;
    private final List<Player> players = new ArrayList<Player>();

    /**
     * Constructor initialize level and link to view controller.
     */
    public Model() {
        this.level = new Level();
        this.translator = new ApplicationStrings();
        this.translator.setDefault();
    }

    public void initGameData() {
        // check if map is saved on file or generate it (and save it into file)
        final MapOnFile onFile = new MapOnFile();
        if (onFile.containsLevel(level)) {
            final Optional<GameMap> optionalMap = onFile.getMap(level);
            if (optionalMap.isPresent()) {
                this.map = optionalMap.get();
            } else {
                this.map = new MapGenerator(this.level, new Pair<Integer, Integer>(MAPROWS, MAPCOLUMN)).get();
            }
        } else {
            this.map = new MapGenerator(this.level, new Pair<Integer, Integer>(MAPROWS, MAPCOLUMN)).get();
        }

        Player player = new Player(0, "Marco", new Pair<Integer, Integer>(0, 0), PlayerColor.RED);
        player.setHeight(BLOCKDIMENSION);
        player.setWidth(BLOCKDIMENSION);
        player.setVelocity(VELOCITY);
        this.players.add(player);
        Player player2 = new Player(1, "Andrea", new Pair<Integer, Integer>(this.map.getDimensions().getX() - 1, this.map.getDimensions().getY() - 1), PlayerColor.YELLOW);
        player2.setHeight(BLOCKDIMENSION);
        player2.setWidth(BLOCKDIMENSION);
        player2.setVelocity(VELOCITY);
        this.players.add(player2);
    }

    /**
     * Get the {@link GameMap} of this level.
     * @return {@link GameMap} object
     */
    public final GameMap getGameMap() {
        return this.map;
    }

    /**
     * Get the players.
     * @return players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Get the global translator.
     * @return {@link ApplicationStrings} object
     */
    public ApplicationStrings getTranslator() {
        return this.translator;
    }

}
