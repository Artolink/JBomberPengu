package model.score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONException;

import model.Level;
import model.player.Player;

/**
 *
 */
public class ScoreCompute {

    private final Score score = new Score();

    /**
     * Construct the score managing system.
     * @param players list of players
     * @param level current level
     */
    public ScoreCompute(final List<Player> players, final Level level) {
        score.setLevel(level);
        players.stream().forEach(e -> score.setPlayer(e));
        score.setDate(new Date().toString());
    }
    /**
     * 
     * @return List<Player> alive
     */
    public List<Player> getAlivePlayers() {
        return score.getPlayers().stream()
                .filter(e -> !e.isDestroyed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * 
     * @return Optional<Player> winner
     */
    public Optional<Player> getWinnerByScore() {
        return score.getPlayers().stream()
                .max((e, r) -> Integer.compare(e.getScore(), r.getScore()));
    }

    /**
     * 
     * @return if match has ended
     */
    public Boolean isGameEnded() {
        return score.getPlayers().stream().anyMatch(e -> e.isDestroyed());
    }

    /**
     * Save current score, if match has ended.
     */
    public void saveScores() {
        if (isGameEnded()) {
            final ScoreWorker scoreWorker = new ScoreWorker();
              try {
                scoreWorker.saveScore(score);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
