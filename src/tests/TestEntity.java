package tests;

import static org.junit.Assert.*;

import javafx.geometry.Point2D;
import model.player.Player;

/**
 * Test of Entity interface.
 */
public class TestEntity {
    /**
     * Testing method.
     */
    @org.junit.Test
    public void testPlayer() {
        final Player player = new Player("Andrea", new Point2D(0, 0));

        assertEquals(player.getName(), "Andrea");
        assertEquals(player.getScore(), Integer.valueOf(0));
        player.addScore(10);
        assertEquals(player.getScore(), Integer.valueOf(10));
    }
}
