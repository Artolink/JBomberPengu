package controller.map;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Optional;

import javax.naming.CannotProceedException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controller.utils.FileWorker;
import javafx.geometry.Point2D;
import model.utils.Pair;
import model.AbstractIndestructibleEntity;
import model.Level;
import model.map.GameMap;

/**
 * Get and Save map on file.
 */
public class MapOnFile {

    private static final String FILENAME = "maps";
    private JSONObject mainInfo;

    /**
     * Constructor read info from file. Create a {@link JSONObject} where can find
     * info of all maps saved
     */
    public MapOnFile() {
        try {
            final FileWorker fileWorker = new FileWorker(FILENAME);
            final String text = fileWorker.load();
            this.mainInfo = new JSONObject(text.isEmpty() ? "{}" : text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if specified level is present on saved map.
     * 
     * @param level level to check
     * @return true if present, false otherwise
     */
    public boolean containsLevel(final Level level) {
        if (this.mainInfo == null) {
            return false;
        } else {
            try {
                this.mainInfo.getJSONArray(String.valueOf(level.get()));
                return true;
            } catch (JSONException e) {
                return false;
            }
        }
    }

    /**
     * Get a GameMap for the specified Level.
     * 
     * @param level required Level 
     * @return GameMap saved on file or null if the map of specified level is not present
     */
    public Optional<GameMap> getMap(final Level level) {
        try {
            final JSONArray mappa = this.mainInfo.getJSONArray(String.valueOf(level.get()));
            JSONArray riga = mappa.getJSONArray(0);
            final GameMap gameMap = new GameMap(new Pair<Integer, Integer>(riga.length(), mappa.length()));
            for (int a = 0; a < mappa.length(); a++) {
                riga = mappa.getJSONArray(a);
                for (int b = 0; b < riga.length(); b++) {
                    final Constructor<?> constructor = Class.forName(riga.getString(b)).getConstructor(Point2D.class);
                    gameMap.setBlock((AbstractIndestructibleEntity) constructor.newInstance(new Point2D(b, a)), b, a);
                }
            }
            return Optional.of(gameMap);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Save the generated map of a Level on file.
     * @param generated GameMap to save
     * @param level Level of GameMap to save
     * @throws CannotProceedException JSON, IOFile or Level error
     */
    public void save(final GameMap generated, final Level level) throws CannotProceedException {
        if (this.mainInfo == null) {
            throw new CannotProceedException("Error occurred while reading the file or parsing JSON");
        } else if (this.containsLevel(level)) {
            throw new CannotProceedException("Level just exist");
        } else {
            try {
                this.mainInfo.put(String.valueOf(level.get()), this.convertMapIntoJson(generated));
                final FileWorker fileWorker = new FileWorker(FILENAME);
                fileWorker.setContent(this.mainInfo.toString());
                fileWorker.save();
            } catch (JSONException e) {
                throw new CannotProceedException("Error parsing Map to save");
            } catch (IOException e) {
                throw new CannotProceedException("Error saving Map on File");
            }
        }
    }

    private JSONArray convertMapIntoJson(final GameMap map) {
        final JSONArray main = new JSONArray();
        int y, x;
        y = map.getDimensions().getY();
        for (int a = 0; a < y; a++) {
            final JSONArray row = new JSONArray();
            x = map.getDimensions().getX();
            for (int b = 0; b < x; b++) {
                row.put(map.getBlock(b, a).getClass().getCanonicalName());
            }
            main.put(row);
        }
        return main;
    }

}
