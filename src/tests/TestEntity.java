package tests;

import java.util.HashSet;
import java.util.Set;
import model.utils.Pair;
import model.utils.Rectangle;
import model.player.Player;
import model.player.PlayerColor;

/**
 * Test of Entity interface.
 */
public class TestEntity {
    /**
     * Testing method.
     */
    @org.junit.Test
    public void testPlayer() {
        final Player player = new Player(0, "Andrea", new Pair<Integer, Integer>(50, 50), PlayerColor.RED);

        player.setWidth(50);
        player.setHeight(50);
        player.getCollisionBox();

        final Set<Rectangle> blockSet = new HashSet<Rectangle>();
        blockSet.add(new Rectangle(new Pair<Integer, Integer>(0, 0), 50, 50));
        blockSet.add(new Rectangle(new Pair<Integer, Integer>(30, 30), 50, 50));

        final Set<Rectangle> bombSet = new HashSet<Rectangle>();
        bombSet.add(new Rectangle(new Pair<Integer, Integer>(0, 0), 50, 50));
        bombSet.add(new Rectangle(new Pair<Integer, Integer>(0, 0), 50, 50));

        final Set<Rectangle> explosionSet = new HashSet<Rectangle>();
        explosionSet.add(new Rectangle(new Pair<Integer, Integer>(0, 0), 50, 50));
        explosionSet.add(new Rectangle(new Pair<Integer, Integer>(0, 0), 50, 50));
    }
}
