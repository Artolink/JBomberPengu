package view.game;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.utils.Pair;

/**
 * Added a custom features to a Pane to interact with nodes.
*/
public class MyPane extends Pane {

    private final Map<Pair<Integer, Integer>, Node> gridMap = new HashMap<>();
    private final Map<Pair<Integer, Integer>, Node> players = new HashMap<>();

    /**
     * Adds a node in the general Pane.
     * @param child node to add to the pane
     * @param row row
     * @param column column
     */
    public void addNode(final Node child, final int row, final int column) {
        getChildren().add(child);
        gridMap.put(new Pair<>(row, column), child);
    }

    /**
     * Gets the node in specified position.
     * @param row row
     * @param column column
     * @return Node associated to specified parameters, null if not present
     */
    public Node getNode(final int row, final int column) {
        return gridMap.get(new Pair<Integer, Integer>(row, column));
    }

    /**
     * Removes the Node from the View.
     * @param row row
     * @param column column
     */
    public void removeNode(final int row, final int column) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                final ImageView image = (ImageView) gridMap.get(new Pair<>(row, column));
                image.setImage(null);
            }
        });
    }

    /**
     * Adds a Bomb in the general Pane.
     * @param child node to add to the pane
     * @param row row
     * @param column column
     */
    public void addPlayer(final Node child, final int row, final int column) {
        this.getChildren().add(child);
        players.put(new Pair<>(row, column), child);
    }

    /**
     * Gets the node in specified position.
     * @param row row
     * @param column column
     * @return Node associated to specified parameters, null if not present
     */
    public Node getPlayer(final int row, final int column) {
        return players.get(new Pair<Integer, Integer>(row, column));
    }

}
