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

    public ScoreCompute(List<Player> players){
        setPlayers(players);
    }
    
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getAlivePlayers() {
        return players.stream()
                .filter(e -> e.isDestroyed() == false)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<Player> getWinnerByScore() {
        return players.stream()
                .max((e,r)->Integer.compare(e.getScore(), r.getScore()));
    }
    
}
