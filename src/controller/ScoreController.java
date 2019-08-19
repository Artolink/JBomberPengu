package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.player.PlayerColor;

/**
 *
 */
public class ScoreController {
    private HashMap<PlayerColor, Integer> scores = new HashMap<>();
    private HashMap<PlayerColor, Boolean> players = new HashMap<>();

    /**
     * 
     * @param players
     */
    ScoreController(final List<PlayerColor> players) {
        for (PlayerColor p: players) {
            this.players.put(p, true);
        }
    }

    /**pull
     * 
     * @param p
     */
    public void killPlayer(final PlayerColor p) {
        players.put(p, false);
    }

    /**
     * 
     * @return
     */
    public List<PlayerColor> getAlivePlayers(){
        return players.entrySet().stream()
                                .filter(e -> e.getValue().equals(true))
                                .map(e -> e.getKey())
                                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * 
     * @param pl
     * @param valueAdded
     */
    public final void scoreUpdater(final PlayerColor pl, final Integer valueAdded ) {
        Optional<Integer> value = Optional.ofNullable(scores.get(pl));
        if (value.isPresent()) {
            value = Optional.of(value.get() + valueAdded);
        } else {
            value = Optional.of(valueAdded);
        }
        scores.put(pl, value.get());
    }

    /**
     * 
     * @return the playerColor who is winning
     */
    public final Optional<PlayerColor> getWinnerByScore() {
        return scores.keySet().stream().max((e, l) -> Integer.compare(scores.get(e), scores.get(l)));    
    }
}
