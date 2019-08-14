package controller.map;

import java.util.Optional;

import javax.naming.CannotProceedException;

import model.Level;
import model.map.GameMap;

/**
 * Get and Save map on file.
 */
public interface MapOnFileInterface {

    /**
     * Check if specified level is present on saved map.
     * 
     * @param level level to check
     * @return true if present, false otherwise
     */
    boolean containsLevel(Level level);

    /**
     * Get a GameMap for the specified Level.
     * 
     * @param level required Level 
     * @return GameMap saved on file or null if the map of specified level is not present
     */
    Optional<GameMap> getMap(Level level);

    /**
     * Save the generated map of a Level on file.
     * @param generated GameMap to save
     * @param level Level of GameMap to save
     * @throws CannotProceedException JSON, IOFile or Level error
     */
    void save(GameMap generated, Level level) throws CannotProceedException;

}
