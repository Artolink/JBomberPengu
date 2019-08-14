package view.game;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.utils.Pair;

/**
 * Added a custom features to a Pane to interact with nodes.
*/
public class MyPane extends Pane {

    private final Map<Pair<Integer, Integer>, Node> gridMap = new HashMap<>();

    /**
     * Add a node in the general Pane.
     * @param child node to add to the pane
     * @param row row
     * @param column column
     */
    public void addNode(final Node child, final int row, final int column) {
        this.getChildren().add(child);
        gridMap.put(new Pair<>(row, column), child);
    }

    /**
     * Get the node in specified position.
     * @param row row
     * @param column column
     * @return Node associated to specified parameters, null if not present
     */
    public Node getNode(final int row, final int column) {
        return gridMap.get(new Pair<Integer, Integer>(row, column));
    }

    /**
     * Remove the Node from the View.
     * @param row row
     * @param column column
     */
    public void removeNode(final int row, final int column) {
        try {
            this.getChildren().remove(this.getNode(row, column));
            this.gridMap.remove(new Pair<>(row, column));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
