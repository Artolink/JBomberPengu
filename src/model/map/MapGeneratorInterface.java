package model.map;

/**
 * Generate a GameMap from scratch.
 */
public interface MapGeneratorInterface {

    /**
     * Generate and return a GameMap.
     * 
     * @return a GameMap object with level and dimension specified in constructor
     */
    GameMap get();

}
