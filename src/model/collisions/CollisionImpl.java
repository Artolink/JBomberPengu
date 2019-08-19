package model.collisions;

import java.util.ArrayList;
import java.util.List;

import model.AbstractEntity;
import model.map.GameMap;
import model.player.Player;
import model.utils.Directions;
import model.utils.Pair;
import model.utils.Rectangle;

/**
 * Implementation of {@link Collision}.
 *
 */
public final class CollisionImpl implements Collision {

    // private Rectangle entityHitbox;
    private final Player player;
    private GameMap map;

    /**
     * Collision builder, initialize the entity that needs to be checked and its
     * collision box.
     * 
     * @param player player associated at this collision set
     */
    public CollisionImpl(final Player player) {
        this.player = player;
    }
    /**
     * Sets the map.
     * 
     * @param map is the game map containing all the blocks
     * @return the class
     */
    public CollisionImpl setMap(final GameMap map) {
        this.map = map;
        return this;
    }

    @Override
    public boolean blocksCollided() {
        return getCollisionBlock(player, player.getDirection()).stream()
                .anyMatch((block) -> player.getCollisionBox().intersectsWith(block));
    }

    @Override
    public boolean bombCollided(final List<AbstractEntity> blocks) {
        return blocks.stream().anyMatch((block) -> player.getCollisionBox().intersectsWithBomb(block.getCollisionBox()));
    }

    @Override
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
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth, relativeTLPlayerHeight + 1);
                        hitBox0 = entity0.getCollisionBox();

                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>(relativeTLPlayerWidth * player.getWidth(),
                                        (relativeTLPlayerHeight + 1) * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    break;
                case LEFT:
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth - 1, relativeTLPlayerHeight);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>((relativeTLPlayerWidth - 1) * player.getWidth(),
                                        relativeTLPlayerHeight * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    break;
                case RIGHT:
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth + 1, relativeTLPlayerHeight);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>((relativeTLPlayerWidth + 1) * player.getWidth(),
                                        relativeTLPlayerHeight * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    break;
                case UP:
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth, relativeTLPlayerHeight - 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>(relativeTLPlayerWidth * player.getWidth(),
                                        (relativeTLPlayerHeight - 1) * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    break;
                default:
                    break;
                }
            } catch (Exception e) {

            }
        } else if (xRelative && !yRelative) {
            try {
                switch (direction) {
                case LEFT:
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth - 1, relativeTLPlayerHeight);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>((relativeTLPlayerWidth - 1) * player.getWidth(),
                                        relativeTLPlayerHeight * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth - 1, relativeTLPlayerHeight + 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>((relativeTLPlayerWidth - 1) * player.getWidth(),
                                        (relativeTLPlayerHeight + 1) * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    break;
                case RIGHT:
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth + 1, relativeTLPlayerHeight);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>((relativeTLPlayerWidth + 1) * player.getWidth(),
                                        relativeTLPlayerHeight * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth + 1, relativeTLPlayerHeight + 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>((relativeTLPlayerWidth + 1) * player.getWidth(),
                                        (relativeTLPlayerHeight + 1) * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    break;
                default:
                    break;
                }
            } catch (Exception e) {

            }
        } else if (!xRelative && yRelative) {
            try {
                switch (direction) {
                case UP:
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth, relativeTLPlayerHeight - 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>(relativeTLPlayerWidth * player.getWidth(),
                                        (relativeTLPlayerHeight - 1) * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth + 1, relativeTLPlayerHeight - 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>((relativeTLPlayerWidth + 1) * player.getWidth(),
                                        (relativeTLPlayerHeight - 1) * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    break;
                case DOWN:
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth, relativeTLPlayerHeight + 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>(relativeTLPlayerWidth * player.getWidth(),
                                        (relativeTLPlayerHeight + 1) * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    try {
                        entity0 = map.getBlock(relativeTLPlayerWidth + 1, relativeTLPlayerHeight + 1);
                        hitBox0 = entity0.getCollisionBox();
                        if (entity0.isSolid()) {
                            collisionBlock.add(hitBox0);
                        }
                    } catch (Exception e) {
                        collisionBlock.add(new Rectangle(
                                new Pair<Integer, Integer>((relativeTLPlayerWidth + 1) * player.getWidth(),
                                        (relativeTLPlayerHeight + 1) * player.getHeight()),
                                player.getWidth(), player.getHeight()));
                    }
                    break;
                default:
                    break;
                }
            } catch (Exception e) {

            }
        }
        return collisionBlock;
    }
}
