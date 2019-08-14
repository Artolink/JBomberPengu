package model.score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.utils.FileWorker;

/**
 * Load and save Score/s.
 */
public class ScoreWorker {

    private static final String FILENAME = "score";

    /**
     * Get the saved list of Scores.
     * @return Array of saved Score 
     * @throws JSONException if something wrong with parsing or inserting
     * @throws IOException if something wrong while reading the file
     */
    public List<Score> getScoreList() throws JSONException, IOException {
        final FileWorker fileWorker = new FileWorker(FILENAME);
        final String text = fileWorker.load();
        final JSONArray scoreList = new JSONArray(text);
        final List<Score> scores = new ArrayList<>();
        JSONObject scoreSaved;
        for (int i = 0; i < scoreList.length(); i++) {
            scoreSaved = scoreList.getJSONObject(i);
            scores.add(new Score().setScore(scoreSaved.getInt("score")).setPlayer(scoreSaved.getString("player"))
                    .setLevel(scoreSaved.getInt("level")).setDate(scoreSaved.getString("date")));
        }
        return scores;
    }

    /**
     * Save a Score.
     * @param score the score to be saved
     * @throws IOException if something wrong while writing to file
     * @throws JSONException if something wrong parsing or inserting in JSON object
     */
    public void saveScore(final Score score) throws IOException, JSONException {
        final JSONObject scoreObject = new JSONObject();
        scoreObject.put("score", score.getScore());
        scoreObject.put("player", score.getPlayer());
        scoreObject.put("level", score.getLevel());
        scoreObject.put("date", score.getDate());
        final FileWorker fileWorker = new FileWorker(FILENAME);
        final String text = fileWorker.load();
        final JSONArray scoreList = new JSONArray(text);
        scoreList.put(scoreObject);
        fileWorker.setContent(scoreList.toString());
        fileWorker.save();
    }

}
