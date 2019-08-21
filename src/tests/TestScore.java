package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import model.Level;
import model.player.Player;
import model.player.PlayerColor;
import model.score.Score;
import model.score.ScoreCompute;
import model.utils.Pair;

/**
 *  Test score related methods.
 */
public class TestScore {

    private static final List<Player> PLAYER_LIST = new ArrayList<>();
    static {
        PLAYER_LIST.add(new Player(0, "Mario", new Pair<>(0, 0), PlayerColor.RED));
        PLAYER_LIST.add(new Player(1, "Gianni", new Pair<>(10, 10), PlayerColor.YELLOW));
        PLAYER_LIST.get(0).setStatus(true);
        PLAYER_LIST.get(0).addScore(10);
    }
    private static final Level LEVEL = new Level();
    private static final Date DATE = new Date();


    //TEST Score class
    /**
     * set/get player list .
     */
    @org.junit.Test
    public void testScorePlayers() {
        Score score = new Score();
        score.setPlayers(PLAYER_LIST);
        assertTrue("set/get Players error", score.getPlayers().equals(PLAYER_LIST));
    }

    /**
     * set/get level.
     */
    @org.junit.Test
    public void testScoreLevel() {
        Score score = new Score();
        score.setLevel(LEVEL);
        assertTrue("set/get Level error", score.getLevel() == LEVEL.get());
        score.setLevel(2);
        assertTrue("set/get Level error", score.getLevel() == 2);
    }

    /**
     * set/get date.
     */
    @org.junit.Test
    public void testScoreDate() {
        Score score = new Score();
        score.setDate(DATE.toString());
        assertTrue("set/get Date error", score.getDate().equals(DATE.toString()));
    }


    //TEST ScoreCompute class
    /**
     * getAlivePlayers.
     */
    @org.junit.Test
    public void testScoreComputePlayersAlive() {
        ScoreCompute scoreCompute = new ScoreCompute(PLAYER_LIST, LEVEL);
        List<Player> testList = new ArrayList<>();
        testList.add(PLAYER_LIST.get(1));
        assertTrue("Players error", scoreCompute.getAlivePlayers().equals(testList));
    }

    /**
     * getWinnerByScore.
     */
    @org.junit.Test
    public void testScoreComputeLevelWinnerPoints() {
        ScoreCompute scoreCompute = new ScoreCompute(PLAYER_LIST, LEVEL);
        assertTrue("Winner by points error", scoreCompute.getWinnerByScore().equals(Optional.of(PLAYER_LIST.get(0))));
        PLAYER_LIST.get(1).addScore(200);
        assertTrue("Winner by points error", scoreCompute.getWinnerByScore().equals(Optional.of(PLAYER_LIST.get(1))));
    }

    /**
     * isGameEnded.
     */
    @org.junit.Test
    public void testScoreComputeDate() {
        ScoreCompute scoreCompute = new ScoreCompute(PLAYER_LIST, LEVEL);
        scoreCompute.getAlivePlayers().get(0).setStatus(true);
        assertTrue("set/get Date error", scoreCompute.isGameEnded());
    }
}
