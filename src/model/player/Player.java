package model.player;

import model.AbstractEntity;
import model.collisions.CollisionImpl;
import model.utils.Directions;
import model.utils.Pair;

/**
 * The player that the users will control.
 */
public final class Player extends AbstractEntity {

    private static final int INITIAL_BOMB_NUMBER = 1;
    private static final int SCORE_VALUE = 200;

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
        this.setScoreValue(SCORE_VALUE);
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
     * Return if the player can place bombs.
     * 
     * @return true if a bomb can be placed, false otherwise.
     */
    public boolean canPlaceBomb() {
        return this.bombNumber > 0;
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
     * Gets the number of bombs the player can place.
     * 
     * @return number of available bomb
     */
    public Integer getBombNumber() {
        return this.bombNumber;
    }

    /**
     * Decrease the available bomb number of this player.
     */
    public void placeBomb() {
        this.bombNumber--;
    }

    /**
     * Increase the available bomb number of this player.
     */
    public void bombExploded() {
        this.bombNumber++;
    }

    /**
     * Sets the directions to move.
     * 
     * @param direction is the enumeration containing all the possible directions to move
     */
    public void setDirection(final Directions direction) {
        this.direction = direction;
    }

    /**
     * Gets the direction to move.
     * 
     * @return the player desired direction 
     */
    public Directions getDirection() {
        return this.direction;
    }

    /**
     * Gets the player velocity.
     * 
     * @return player speed.
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     * Sets the player velocity. 
     * 
     * @param velocity defines the player speed
     */
    public void setVelocity(final int velocity) {
        this.velocity = velocity;
    }

    /**
     * Gets the color of the player.
     * 
     * @return RED if player is red, YELLOW if it's yellow
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Sets the player collision system. Called by controller when the game starts.
     * 
     * @param collision defines the system containing all the collisions
     */
    public void setCollision(final CollisionImpl collision) {
        this.collision = collision;
    }

    /**
     * Method that defines if the player can move.
     * 
     * @param direction is the player direction where he wants to move
     * @return true if player can move, false if it collides with explosions, blocks, bombs or if he is dead
     */
    public boolean move(final Directions direction) {
        if (collision.explosionCollided()) {
            setStatus(true);
            return false;
        } else if (collision.blocksCollided() || isDestroyed()) {
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
