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
        final Pair<Integer, Integer> touchingUpPosition = new Pair<Integer, Integer>(0, 49); //Touches up with initialPosition (and also middlePosition)
        final Pair<Integer, Integer> endingPosition = new Pair<Integer, Integer>(500, 500);
        final Pair<Integer, Integer> touchingLeftPosition = new Pair<Integer, Integer>(549, 500); //Touches left with endingPosition

        final DestructibleBlock block1 = new DestructibleBlock(initialPosition);
        final DestructibleBlock block2 = new DestructibleBlock(middlePosition);
        final DestructibleBlock block3 = new DestructibleBlock(touchingUpPosition);
        final DestructibleBlock block4 = new DestructibleBlock(endingPosition);
        final DestructibleBlock block5 = new DestructibleBlock(touchingLeftPosition);

        block1.setHeight(50);
        block1.setWidth(50);

        block2.setHeight(50);
        block2.setWidth(50);

        block3.setHeight(50);
        block3.setWidth(50);

        block4.setHeight(50);
        block4.setWidth(50);

        block5.setHeight(50);
        block5.setWidth(50);

        final Rectangle r1 = new Rectangle(initialPosition, block1.getWidth(), block1.getHeight());
        final Rectangle r2 = new Rectangle(initialPosition, block2.getWidth(), block2.getHeight());
        final Rectangle r3 = new Rectangle(middlePosition, block3.getWidth(), block3.getHeight());
        final Rectangle r4 = new Rectangle(endingPosition, block4.getWidth(), block4.getHeight());

        //Rectangles collision test
        assertTrue("Rectangles collide. Should return true.", r1.intersectsWith(r2));
        assertTrue("Rectangles collide. Should return true.", r1.intersectsWith(r3));
        assertFalse("Rectangles do not collide. Should return false.", r1.intersectsWith(r4));

        //Blocks collision test
        assertTrue("Blocks generically collide. Should return true.", block1.getCollisionBox().intersectsWith(block2.getCollisionBox()));
        assertTrue("Blocks collide up. Should return true.", block3.getCollisionBox().hasCollidedUp(block1.getCollisionBox()));
        assertFalse("Blocks do not collide up. Should return false.", block2.getCollisionBox().hasCollidedUp(block3.getCollisionBox()));

        assertTrue("Blocks collide down. Should return true.", block1.getCollisionBox().hasCollidedDown(block3.getCollisionBox()));
        assertFalse("Blocks do not collide down. Should return false.", block3.getCollisionBox().hasCollidedDown(block2.getCollisionBox()));

        assertTrue("Blocks collide left. Should return true.", block5.getCollisionBox().hasCollidedLeft(block4.getCollisionBox()));
        assertFalse("Blocks do not collide left. Should return false.", block4.getCollisionBox().hasCollidedLeft(block5.getCollisionBox()));

        assertTrue("Blocks collide right. Should return true.", block4.getCollisionBox().hasCollidedRight(block5.getCollisionBox()));
        assertFalse("Blocks do not collide right. Should return false.", block5.getCollisionBox().hasCollidedRight(block4.getCollisionBox()));
    }
}
