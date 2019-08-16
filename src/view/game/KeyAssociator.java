package view.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.scene.input.KeyCode;
import model.player.Player;
import model.player.Player.Directions;

/**
 * Classes that manage the key input.
 *
 */
public class KeyAssociator {

    private final List<List<KeyCode>> availableControlSet = new ArrayList<>();
    private final Map<KeyCode, Directions> keyDirection = new HashMap<>();
    private final Map<List<KeyCode>, Player> keyPlayer = new HashMap<>();
    private final Iterator<List<KeyCode>> nextControlList;

    /**
     * KeyAssociator stored.
     */
    public KeyAssociator() {
        List<KeyCode> tmp = new ArrayList<>();
        tmp.add(KeyCode.A);
        tmp.add(KeyCode.S);
        tmp.add(KeyCode.D);
        tmp.add(KeyCode.W);
        tmp.add(KeyCode.SPACE);
        this.availableControlSet.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(KeyCode.UP);
        tmp.add(KeyCode.DOWN);
        tmp.add(KeyCode.LEFT);
        tmp.add(KeyCode.RIGHT);
        tmp.add(KeyCode.PLUS);
        this.availableControlSet.add(tmp);
        this.keyDirection.put(KeyCode.UP, Directions.UP);
        this.keyDirection.put(KeyCode.DOWN, Directions.DOWN);
        this.keyDirection.put(KeyCode.LEFT, Directions.LEFT);
        this.keyDirection.put(KeyCode.RIGHT, Directions.RIGHT);
        this.keyDirection.put(KeyCode.A, Directions.LEFT);
        this.keyDirection.put(KeyCode.S, Directions.DOWN);
        this.keyDirection.put(KeyCode.D, Directions.RIGHT);
        this.keyDirection.put(KeyCode.W, Directions.UP);
        this.nextControlList = this.availableControlSet.iterator();
    }

    /**
     * Gets the direction.
     * @param code keycode
     * @return keycode
     */
    public Directions getDirection(final KeyCode code) {
        if (code != null) {
            return this.keyDirection.get(code);
        } else {
            return null;
        }
    }

    /**
     * Associates the player to the keycode.
     * @param player player
     * @return keycode
     */
    public boolean associatePlayer(final Player player) {
        if (this.nextControlList.hasNext()) {
            this.keyPlayer.put(this.nextControlList.next(), player);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Controls that the map contains the keycode.
     * @param code keycode
     * @return true if the map contains the keycode otherwise false
     */
    public boolean contains(final KeyCode code) {
        if (code != null) {
            return this.keyDirection.containsKey(code);
        } else {
            return false;
        }
    }

    /**
     * Gets the player key set.
     * @param code keycode
     * @return the player keycode
     */
    public Player getPlayer(final KeyCode code) {
        final List<KeyCode> tmp = this.getKeySet(code);
        if (tmp != null) {
            return this.keyPlayer.get(tmp);
        } else {
            return null;
        }
    }

    private List<KeyCode> getKeySet(final KeyCode code) {
        for (final List<KeyCode> keyCodes : this.availableControlSet) {
            System.out.println(keyCodes.toString());
            if (keyCodes.contains(code)) {
                return keyCodes;
            }
        }
        return null;
    }

}
