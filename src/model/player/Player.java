package model.player;

import java.io.File;

import model.utils.Rectangle;
import model.AbstractEntity;
import model.collisions.CollisionImpl;
import model.utils.Directions;
import model.utils.Pair;

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
    private int velocity;
    private final PlayerColor color;
    private CollisionImpl collision;

    /**
     * Player builder.
     * 
     * @param id   the ID of the player
     * @param name the name of the player
     * @param pos  the initial position of the player
     * @param color color of this player
     */
    public Player(final Integer id, final String name, final Pair<Integer, Integer> pos, final PlayerColor color) {
        super(pos, true);
        this.id = id;
        this.name = name;
        this.score = 0;
        this.bombNumber = INITIAL_BOMB_NUMBER;
        this.direction = Directions.STATIONARY;
        this.color = color;
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
     * @param direction is the enumeration containing all the possible directions to
     *                  move
     */
    public void setDirection(final Directions direction) {
        this.direction = direction;
    }

    /**
     * Gets the directions enumeration.
     * @return the
     */
    public Directions getDirection() {
        return this.direction;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public PlayerColor getColor() {
        return color;
    }

    @Override
    public Rectangle getCollisionBox() {
        return new Rectangle(
                new Pair<Integer, Integer>(getPosition().getX(), getPosition().getY()),
                getWidth(),
                getHeight());
    }
    
    public void setCollision(final CollisionImpl collision) {
        this.collision = collision;
    }

    public boolean move(final Directions direction) {
        if(collision.blocksCollided()) {
            return false;
        } else {
            int x = 0;
            int y = 0;
            switch (direction) {
                case DOWN:
                    x = getPosition().getX();
                    y = getPosition().getY() + getVelocity();
                    break;
                case LEFT:
                    x = getPosition().getX() - getVelocity();
                    y = getPosition().getY();
                    break;
                case RIGHT:
                    x = getPosition().getX() + getVelocity();
                    y = getPosition().getY();
                    break;
                case UP:
                    x = getPosition().getX();
                    y = getPosition().getY() - getVelocity();
                    break;
                default:
                    break;
            }
            this.setPosition(new Pair<>(x, y));
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
