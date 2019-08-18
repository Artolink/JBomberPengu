package model.player;

import java.io.File;
import java.util.Set;

import model.AbstractEntity;
import model.collisions.Collision;
import model.collisions.CollisionImpl;
import model.utils.Directions;
import model.utils.Pair;
import model.utils.Rectangle;

/**
 * The player that the user will control.
 */
public final class Player extends AbstractEntity {

    private static final int INITIAL_BOMB_NUMBER = 1;

    private Integer id;
    private Integer score;
    private Integer bombNumber;
    private final String name;
    private Directions direction;
    private final Collision playerCollisions;
    private final Pair<Integer, Integer> initialPosition;

    /**
     * Player builder.
     * 
     * @param id   the ID of the player
     * @param name the name of the player
     * @param pos  the initial position of the player
     */
    public Player(final Integer id, final String name, final Pair<Integer, Integer> pos) {
        super(pos);
        this.id = id;
        this.name = name;
        this.score = 0;
        this.initialPosition = pos;
        this.bombNumber = INITIAL_BOMB_NUMBER;
        this.direction = Directions.STATIONARY;
        this.playerCollisions = new CollisionImpl(this);
        this.setImagePath(ClassLoader.getSystemClassLoader().getResource("view") + File.separator + "bomba gialla.png");
    }

    /**
     * Gets the ID of the player.
     * 
     * @return player ID
     */
    public Integer getID() {
        return this.id;
    }

    /**
     * Sets the ID of the player.
     * 
     * @param id is the id of the player
     */
    public void setID(final Integer id) {
        this.id = id;
    }

    /**
     * Returns the name of the player.
     * 
     * @return player name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the score of the player.
     * 
     * @return player score
     */
    public Integer getScore() {
        return this.score;
    }

    /**
     * Adds the score to the player.
     * 
     * @param score The score of the player
     */
    public void addScore(final Integer score) {
        this.score += score;
    }

    /**
     * Gets the initial position of the player, useful for the view.
     * 
     * @return the initial position of the player
     */
    public Pair<Integer, Integer> getInitialPosition() {
        return this.initialPosition;
    }

    /**
     * Gets the number of bombs the player can place.
     * 
     * @return the number of bombs player can place.
     */
    public Integer getBombNumber() {
        return this.bombNumber;
    }

    /**
     * Sets the number of bombs the player can place.
     * 
     * @param bombNumber defines the number of bombs player can place on the map
     */
    public void setBombNumber(final Integer bombNumber) {
        this.bombNumber = bombNumber;
    }

    /**
     * Sets the directions to move.
     * 
     * @param direction is the direction where the player wants to move

     */
    public void setDirection(final Directions direction) {
        this.direction = direction;
    }

    /**
     * Gets the directions where the player wants to move.
     * 
     * @return the direction where the player wants to move
     */
    public Directions getDirection() {
        return this.direction;
    }

    /**
     * Method that checks if the player can move in a particular direction.
     * 
     * @param dir          is the direction where the player wants to move
     * @param blockSet     is the set of blocks surrounding the player
     * @param bombSet      is the set of bombs surrounding the player
     * @param explosionSet is the set of explosions in the game map
     * @return a position
     */
    public boolean canMove(final Directions dir, final Set<Rectangle> blockSet, final Set<Rectangle> bombSet,
            final Set<Rectangle> explosionSet) {
        this.setCollisionBox();

        if (this.playerCollisions.explosionCollision(explosionSet)) {
            this.setStatus(true);
            return false;
        }

        switch (dir) {
        case UP:
            if (blockSet.stream().anyMatch((block) -> this.getCollisionBox().get().hasCollidedUp(block)) 
                    && bombSet.stream().anyMatch((bomb) -> this.getCollisionBox().get().hasCollidedUp(bomb))) {
                return false;
            }
            return true;
        case DOWN:
            if (blockSet.stream().anyMatch((block) -> this.getCollisionBox().get().hasCollidedDown(block)) 
                    && bombSet.stream().anyMatch((bomb) -> this.getCollisionBox().get().hasCollidedDown(bomb))) {
                return false;
            }
            return true;
        case LEFT:
            if (blockSet.stream().anyMatch((block) -> this.getCollisionBox().get().hasCollidedLeft(block)) 
                    && bombSet.stream().anyMatch((bomb) -> this.getCollisionBox().get().hasCollidedLeft(bomb))) {
                return false;
            }
            return true;
        case RIGHT:
            if (blockSet.stream().anyMatch((block) -> this.getCollisionBox().get().hasCollidedRight(block)) 
                    && bombSet.stream().anyMatch((bomb) -> this.getCollisionBox().get().hasCollidedRight(bomb))) {
                return false;
            }
            return true;
        default: 
            return true;
        }
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Player n.")
                .append(this.getID())
                .append(" at position: ")
                .append(this.getPosition().getX())
                .append(", ")
                .append(this.getPosition().getY())
                .append(".\n")
                .append(super.toString())
                .toString();
    }
}
