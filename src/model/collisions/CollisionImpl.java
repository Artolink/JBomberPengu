package model.collisions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.AbstractEntity;
import model.map.GameMap;
import model.player.Player;
import model.utils.Directions;
import model.utils.Rectangle;

/**
 * Implementation of {@link Collision}. 
 *
 */
public final class CollisionImpl implements Collision {

    //private Rectangle entityHitbox;
    private final Player player;
    private final GameMap map;

    /**
     * Collision builder, initialize the entity that needs to be checked and its collision box.
     * 
     * @param player player associated at this collision set
     * @param map is the map of the entire game, used to know all blocks position
     */
    public CollisionImpl(final Player player, final GameMap map) {
        this.player = player;
        this.map = map;
    }

    @Override
    public boolean blocksCollided() {
        return getCollisionBlock(player, player.getDirection()).stream().anyMatch((block) -> player.getCollisionBox().intersectsWith(block));
    }

    /**
     * 
     * @param player            is the player you want to check for collisions
     * @param direction         is the direction where the player wants to move
     * @return a list of hitboxes to check for collisions
     */
    public List<Rectangle> getCollisionBlock(final Player player, final Directions direction) {
        final List<Rectangle> collisionBlock = new ArrayList<Rectangle>();
        final int relativeTLPlayerWidth = player.getPosition().getX() / player.getWidth();
        final int relativeTLPlayerHeight = player.getPosition().getY() / player.getHeight();
        final boolean xRelative = player.getPosition().getX() % player.getWidth() == 0;
        final boolean yRelative = player.getPosition().getY() % player.getHeight() == 0;
        AbstractEntity entity0;
        Rectangle hitBox0;
        if (xRelative && yRelative) {
            try {
                switch (direction) {
                    case DOWN:
                        entity0 = map.getBlock(relativeTLPlayerWidth, relativeTLPlayerHeight + 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        break;
                    case LEFT:
                        entity0 = map.getBlock(relativeTLPlayerWidth - 1, relativeTLPlayerHeight);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        break;
                    case RIGHT:
                        entity0 = map.getBlock(relativeTLPlayerWidth + 1, relativeTLPlayerHeight);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        break;
                    case UP:
                        entity0 = map.getBlock(relativeTLPlayerWidth, relativeTLPlayerHeight - 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        break;
                    default:
                        break;
                }
            } catch (Exception e) { }
        } else if (xRelative && !yRelative) {
            try {
                switch (direction) {
                    case LEFT:
                        entity0 = map.getBlock(relativeTLPlayerWidth - 1, relativeTLPlayerHeight);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        entity0 = map.getBlock(relativeTLPlayerWidth - 1, relativeTLPlayerHeight + 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        break;
                    case RIGHT:
                        entity0 = map.getBlock(relativeTLPlayerWidth + 1, relativeTLPlayerHeight);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        entity0 = map.getBlock(relativeTLPlayerWidth + 1, relativeTLPlayerHeight + 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        break;
                    default:
                        break;
                }
            } catch (Exception e) { }
        } else if (!xRelative && yRelative) {
            try {
                switch (direction) {
                    case UP:
                        entity0 = map.getBlock(relativeTLPlayerWidth, relativeTLPlayerHeight - 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        entity0 = map.getBlock(relativeTLPlayerWidth + 1, relativeTLPlayerHeight - 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        break;
                    case DOWN:
                        entity0 = map.getBlock(relativeTLPlayerWidth, relativeTLPlayerHeight + 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        entity0 = map.getBlock(relativeTLPlayerWidth + 1, relativeTLPlayerHeight + 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                        break;
                    default:
                        break;
                }
            } catch (Exception e) { }
        }
        return collisionBlock;
    }
}
