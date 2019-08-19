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
    private List<Player> changeHistory = new ArrayList<>();

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

    /**
     * 
     * @return a
     */
    public Optional<Player> getLastModified(){
        Optional<Player> oP = Optional.ofNullable(null);
        if (changeHistory.size() > 0) {
            oP = Optional.ofNullable(changeHistory.get(0));
            changeHistory.remove(0);
        }
        return oP; 
    }
    
    public Boolean isGameEnded() {
        return getLastModified().isPresent();
    }

    /**
     * 
     * @param killedPlayer a
     */
    public void killPlayers(final List<Player> killedPlayer) {
        for (Player p: killedPlayer) {
            if (!p.isDestroyed()) {
                p.setStatus(true);
                changeHistory.add(p);
                System.out.println(p.getColor().toString() + " killed");
            }
        }
    }

}
