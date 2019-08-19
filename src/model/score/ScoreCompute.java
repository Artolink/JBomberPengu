package model.score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import model.player.Player;
import model.player.PlayerColor;

/**
 *
 */
public class ScoreCompute {

    private List<Player> players;

    /**
     * 
     * @param players - list of player to evaluate
     */
    public ScoreCompute(final List<Player> players){
        setPlayers(players);
    }

    /**
     * 
     * @param players - list of player to evaluate
     */
    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

    /**
     * 
     * @return List<Player> alive
     */
    public List<Player> getAlivePlayers() {
        return players.stream()
                .filter(e -> !e.isDestroyed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * 
     * @return Optional<Player> winner
     */
    public Optional<Player> getWinnerByScore() {
        return players.stream()
                .max((e, r) -> Integer.compare(e.getScore(), r.getScore()));
    }

}
