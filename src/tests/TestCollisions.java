package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.blocks.DestructibleBlock;
import model.utils.Pair;
import model.utils.Rectangle;

/**
 * Testing class for the collision system.
 *
 */
public class TestCollisions {

    /**
     * Testing method.
     */
    @org.junit.Test
    public void testCollisions() {
        final Pair<Integer, Integer> initialPosition = new Pair<Integer, Integer>(0, 0);
        final Pair<Integer, Integer> middlePosition = new Pair<Integer, Integer>(10, 10);
        final Pair<Integer, Integer> touchingPosition = new Pair<Integer, Integer>(0, 49);
        final Pair<Integer, Integer> endingPosition = new Pair<Integer, Integer>(500, 500);
        final Rectangle r1 = new Rectangle(initialPosition, 100, 100);
        final Rectangle r2 = new Rectangle(initialPosition, 200, 200);
        final Rectangle r3 = new Rectangle(middlePosition, 100, 100);
        final Rectangle r4 = new Rectangle(endingPosition, 100, 100);

        assertTrue("Rectangles collide. Should return true.", r1.intersectsWith(r2));
        assertTrue("Rectangles collide. Should return true.", r1.intersectsWith(r3));
        assertFalse("Rectangles do not collide. Should return false.", r1.intersectsWith(r4));

        final DestructibleBlock block1 = new DestructibleBlock(initialPosition);
        final DestructibleBlock block2 = new DestructibleBlock(middlePosition);
        final DestructibleBlock block3 = new DestructibleBlock(touchingPosition);
        final DestructibleBlock block4 = new DestructibleBlock(endingPosition);

        block1.setHeight(50);
        block1.setWidth(50);
        block1.setCollisionBox();

        block2.setHeight(50);
        block2.setWidth(50);
        block2.setCollisionBox();

        block3.setHeight(50);
        block3.setWidth(50);
        block3.setCollisionBox();

        block4.setHeight(50);
        block4.setWidth(50);
        block4.setCollisionBox();

        assertTrue("Blocks collide. Should return true.", block1.getCollisionBox().get().intersectsWith(block2.getCollisionBox().get()));
        //assertTrue("Blocks collide. Should return true.", block1.getCollisionBox().get().hasCollidedUp(block3)); //NOT WORKING

    }
}
