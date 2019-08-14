package model.collisions;

import java.util.Optional;
import java.util.Set;
import model.Entity;
import model.utils.Rectangle;

/**
 * Implementation of {@link Collision}. 
 *
 */
public final class CollisionImpl implements Collision {

    private final Optional<Rectangle> entityHitbox;

    /**
     * Collision builder, initialize the entity that needs to be checked and its collision box.
     * 
     * @param entity The entity
     */
    public CollisionImpl(final Entity entity) {
        this.entityHitbox = entity.getCollisionBox();
    }

    @Override
    public boolean allBlocksCollision(final Set<Rectangle> blockSet) {
        return blockSet.stream().anyMatch((block) -> this.entityHitbox.get().intersectsWith(block));
    }

    @Override
    public boolean bombCollision(final Set<Rectangle> bombSet) {
        return bombSet.stream().anyMatch((bomb) -> this.entityHitbox.get().intersectsWith(bomb));
    }

    @Override
    public boolean explosionCollision(final Set<Rectangle> explosionSet) {
        return explosionSet.stream().anyMatch((explosion) -> this.entityHitbox.get().intersectsWith(explosion));
    }
}
