package tests;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import model.utils.Pair;
import model.utils.Rectangle;
import model.blocks.DestructibleBlock;
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

        //When an entity is first created, its position is relative (the same as the map, e.g. (10,10), (30,20)..)
        //then, when entity has to be printed on screen, its position and collisionBox become absolute (e.g. (10x50, 10x50), (30x50, 20x50)..)
        //(a multiple of a value (40 in our case, and 50 in this Test) to work with pixels)

        //used to check a precise position collision (not grid-locked)
        final Pair<Integer, Integer> initialPosition = new Pair<Integer, Integer>(0, 0); 

        //used to check directly the pixel position collisions (absolute, grid-locked)
        final Pair<Integer, Integer> absolutePosition = new Pair<Integer, Integer>(49, 49); 
        final Pair<Integer, Integer> middlePosition = new Pair<Integer, Integer>(50, 50);

        //player will initially be tested in a absolute position, then we'll change the absolute position value to a precise position
        final Player player = new Player(0, "Andrea", middlePosition, PlayerColor.RED);


        //this block will be changed to a precise position. 
        //To test that, you just need to call ".setPosition".
        //by doing this, objects can be overlapped, even on screen.
        final DestructibleBlock block1 = new DestructibleBlock(initialPosition);

        //this block will be tested in an absolute position. 
        //please note that, in this way, everything on screen that is absolute (without you changing that with ".setPosition") will be a multiple of a value so they'll be grid-aligned.
        //(objects can't be overlapped in this way)
        final DestructibleBlock block2 = new DestructibleBlock(absolutePosition);

        player.setWidth(50);
        player.setHeight(50);

        block2.setHeight(50);
        block2.setWidth(50);

        //note that the REAL (pixel) position of this block is (2450, 2450), even tho the relative starting position (map) is (49,49)
        System.out.println("Test with absolute positions:\n" + block2.getPosition());
        System.out.println(player.getPosition() + "\n\n"); //and the player position is 2500, so they collide in pixel

        assertTrue("Player REAL position collide with block REAL position, shoul return true", player.getCollisionBox().intersectsWith(block2.getCollisionBox()));

        //now, with this command we set the real position (pixel) of the player to a precise position. 
        //We can use this command to place an object everywhere we want precisely on the monitor.
        player.setPosition(middlePosition); 

        block1.setHeight(50);
        block1.setWidth(50);
        block1.setPosition(initialPosition); //same thing for block1

        System.out.println("Test with relative positions:\n" + block1.getPosition());
        System.out.println(player.getPosition() + "\n\nPlease see also TestCollisions for an in-depth look of how collisions (general, up/down/left/right) work.");

        assertTrue("Player RELATIVE position collide with block RELATIVE position, should return true", player.getCollisionBox().intersectsWith(block1.getCollisionBox()));
    }
}
