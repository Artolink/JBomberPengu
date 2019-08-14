package view.animations;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages dynamic sprites.
 */
public abstract class AbstractDynamicSprites {

    private final List<Sprite> stayLeft;
    private final List<Sprite> stayRight;
    private final List<Sprite> runLeft;
    private final List<Sprite> runRight;
    private final List<Sprite> down;
    private final List<Sprite> up;
    private final List<Sprite> lose;

    /**
     * It creates the lists.
     */
    public AbstractDynamicSprites() {

        this.stayLeft = new ArrayList<>();
        this.stayRight = new ArrayList<>();
        this.runLeft = new ArrayList<>();
        this.runRight = new ArrayList<>();
        this.down = new ArrayList<>();
        this.up = new ArrayList<>();
        this.lose = new ArrayList<>();
    }

    /**
     * Gets the stay left movement sprites.
     * 
     * @return the stay left list
     */
    public List<Sprite> getStayLeftSprites() {
        return this.stayLeft;
    }

    /**
     * Gets the stay right movement sprites.
     * 
     * @return the stay right list
     */
    public List<Sprite> getStayRightSprites() {
        return this.stayRight;
    }

    /**
     * Gets the run left movement sprites.
     * 
     * @return the run left list
     */
    public List<Sprite> getRunLeftSprites() {
        return this.runLeft;
    }

    /**
     * Gets the run right movement sprites.
     * 
     * @return the run right list
     */
    public List<Sprite> getRunRightSprites() {
        return this.runRight;
    }

    /**
     * Gets the down movement sprites.
     * 
     * @return the down list
     */
    public List<Sprite> getDownSprites() {
        return this.down;
    }

    /**
     * Gets the up movement sprites.
     * 
     * @return the up list
     */
    public List<Sprite> getUpSprites() {
        return this.up;
    }

    /**
     * Gets the lose movement sprites.
     * 
     * @return the lose list
     */
    public List<Sprite> getLoseSprites() {
        return this.lose;
    }

    /**
     * Gets how many sprites are in the list.
     * 
     * @return the number of sprites
     */
    public abstract int getSpritesToMove();
}
