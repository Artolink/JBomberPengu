package model;

import java.util.List;
import model.language.ApplicationStrings;
import model.map.GameMap;
import model.player.Player;

/**
 * Interface of model. Containing the principal methods to access game data.
 */
public interface Model {

    /**
     * Velocity of the players.
     */
    int VELOCITY = 10;
    /**
     * Dimensions of blocks.
     */
    int BLOCKDIMENSION = 40;
    /**
     * Spacing between blocks in view.
     */
    int BLOCKSPACING = 0;

    /**
     * Init all game data.
     */
    void initGameData();

    /**
     * Get the {@link GameMap} of this level.
     * @return {@link GameMap} object
     */
    GameMap getGameMap();

    /**
     * Get the players.
     * @return players
     */
    List<Player> getPlayers();

    /**
     * Get the global translator.
     * @return {@link ApplicationStrings} object
     */
    ApplicationStrings getTranslator();

}
