package model.score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.player.Player;
import model.player.PlayerColor;
import model.utils.FileWorker;
import model.utils.Pair;

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
        List<Player> playerList;
        JSONObject scoreSaved;
        JSONArray players;
        JSONObject player;
        for (int i = 0; i < scoreList.length(); i++) {
            scoreSaved = scoreList.getJSONObject(i);
            playerList = new ArrayList<>();
            players = scoreSaved.getJSONArray("players");
            for (int p = 0; p < players.length(); p++) {
                player = players.getJSONObject(p);
                final Player pla = new Player(0, player.getString("name"), new Pair<>(0, 0), player.getString("color").equals("RED") ? PlayerColor.RED : PlayerColor.YELLOW);
                pla.setStatus(player.getBoolean("status"));
                pla.setScoreValue(player.getInt("score"));
                playerList.add(pla);
            }
            scores.add(new Score().setDate(scoreSaved.getString("date")).setLevel(scoreSaved.getInt("level")).setPlayers(playerList));
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
        scoreObject.put("level", score.getLevel());
        scoreObject.put("date", score.getDate());
        final JSONArray playerList = new JSONArray();
        final JSONObject playerObj = new JSONObject();
        for (final Player player : score.getPlayers()) {
            playerObj.put("name", player.getName());
            playerObj.put("color", player.getColor().toString());
            playerObj.put("status", player.isDestroyed());
            playerObj.put("score", player.getScoreValue());
            playerList.put(playerObj);
        }
        scoreObject.put("players", playerList);
        final FileWorker fileWorker = new FileWorker(FILENAME);
        final String text = fileWorker.load();
        final JSONArray scoreList = new JSONArray(text.isEmpty() ? "[]" : text);
        scoreList.put(scoreObject);
        fileWorker.setContent(scoreList.toString());
        fileWorker.save();
    }

}
