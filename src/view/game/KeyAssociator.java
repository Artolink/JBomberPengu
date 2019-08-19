package view.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.scene.input.KeyCode;
import model.player.Player;
import model.utils.Directions;

/**
 * Classes that manage the key input.
 *
 */
public class KeyAssociator {

    private final List<List<KeyCode>> availableControlSet = new ArrayList<>();
    private final Map<KeyCode, Directions> keyDirection = new HashMap<>();
    private final Map<List<KeyCode>, Player> keyPlayer = new HashMap<>();
    private final Iterator<List<KeyCode>> nextControlList;
    private final List<KeyCode> bombKey = new ArrayList<>();

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
        bombKey.add(KeyCode.SPACE);
        this.availableControlSet.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(KeyCode.UP);
        tmp.add(KeyCode.DOWN);
        tmp.add(KeyCode.LEFT);
        tmp.add(KeyCode.RIGHT);
        tmp.add(KeyCode.ADD);
        bombKey.add(KeyCode.ADD);
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
     * Gets the direction of the associate key.
     * 
     * @param code keycode
     * @return the direction
     */
    public Directions getDirection(final KeyCode code) {
        if (code != null) {
            return this.keyDirection.get(code);
        } else {
            return null;
        }
    }

    /**
     * Associates the player to one of the keycode lists.
     * 
     * @param player the player
     * @return true if the association is successful, false otherwise
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
     * 
     * @param code keycode
     * @return true if the map contains the keycode, false otherwise
     */
    public boolean contains(final KeyCode code) {
        if (code != null) {
            for (final List<KeyCode> codeList : availableControlSet) {
                if (codeList.contains(code)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the player key set.
     * 
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

    /**
     * It control if the key pressed is the corret bomb key.
     * @param code kecode
     * @return true if the key is the key to release the bomb, false otherwise
     */
    public boolean isBombControl(final KeyCode code) {
        if (code != null) {
            return this.bombKey.contains(code);
        } else {
            return false;
        }
    }

    private List<KeyCode> getKeySet(final KeyCode code) {
        for (final List<KeyCode> keyCodes : this.availableControlSet) {
            if (keyCodes.contains(code)) {
                return keyCodes;
            }
        }
        return null;
    }

}
