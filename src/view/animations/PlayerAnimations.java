package view.animations;

import javafx.scene.image.ImageView;
import model.player.Player;
import model.player.PlayerColor;
import model.utils.Directions;

/**
 * Class that run the players animations.
 *
 */
public class PlayerAnimations implements Animation {

    private Player player;
    private ImageView imageView;
    private PlayerSprite sprite;
    private static final int SHEET_ROWS = 1;
    private static final int SHEET_COLUMNS = 38;
    private Directions lastHorizontalDir;
    private final SpriteSheet sheet = new SpriteSheet("/view/sheet.png", SHEET_ROWS, SHEET_COLUMNS);
    private Integer nextFrameTwo = 0;
    private Integer nextFrameThree = 0;
    private boolean isDead;
    private static final int VERTICAL = 300;
    private static final int HORIZONTAL = 120;
    private static final int DEAD = 250;
    private static final int STATIONARY = 280;

    /**
     * Constructor.
     */
    public PlayerAnimations() {
        this.isDead = false;
    }

    /**
     * Runs the animations.
     */
    @Override
    public void run() {

        long timeToSleep = 0;

        while (true) {

            switch (player.getDirection()) {
            case UP:
                this.updateFrame();
                this.imageView.setImage(sprite.getUpSprites().get(nextFrameTwo).getImage());
                timeToSleep = VERTICAL;
                break;
            case RIGHT:
                this.updateFrame();
                this.imageView.setImage(sprite.getRunRightSprites().get(nextFrameThree).getImage());
                lastHorizontalDir = Directions.RIGHT;
                timeToSleep = HORIZONTAL;
                break;
            case LEFT:
                this.updateFrame();
                this.imageView.setImage(sprite.getRunLeftSprites().get(nextFrameThree).getImage());
                lastHorizontalDir = Directions.LEFT;
                timeToSleep = HORIZONTAL;
                break;
            case DOWN:
                this.updateFrame();
                this.imageView.setImage(sprite.getDownSprites().get(nextFrameTwo).getImage());
                timeToSleep = VERTICAL;
                break;
            case STATIONARY:
                if (lastHorizontalDir.equals(Directions.LEFT)) {
                    this.updateFrame();
                    this.imageView.setImage(sprite.getStayLeftSprites().get(nextFrameThree).getImage());

                } else {
                    this.updateFrame();
                    this.imageView.setImage(sprite.getStayRightSprites().get(nextFrameThree).getImage());
                }
                timeToSleep = STATIONARY;
                break;
            default:
                break;
            }

            if (isDead) {
                this.updateFrame();
                this.imageView.setImage(sprite.getLoseSprites().get(nextFrameTwo).getImage());
                timeToSleep = DEAD;
            }

            try {
                Thread.sleep(timeToSleep);
                if (this.nextFrameTwo.equals(2)) {
                    this.nextFrameTwo = 0;
                } else {
                    nextFrameTwo++;
                }
                if (this.nextFrameThree.equals(3)) {
                    this.nextFrameThree = 0;
                } else {
                    nextFrameThree++;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Controls if the player is dead.
     */
    public void playerDead() {
        this.isDead = true;
    }

    /**
     * Sets the current player.
     * 
     * @param player the player
     */
    public void setPlayer(final Player player) {
        this.player = player;
        if (this.player.getID() % 2 == 0) {
            lastHorizontalDir = Directions.RIGHT;
        } else {
            lastHorizontalDir = Directions.LEFT;
        }
        this.setSprite(player);
    }

    /**
     * Sets the current imageView.
     * 
     * @param image the imageView
     */
    public void setImageView(final ImageView image) {
        this.imageView = image;
    }

    private void setSprite(final Player player) {

        if (player.getColor().equals(PlayerColor.RED)) {
            this.sprite = new PlayerSprite(PlayerColor.RED, sheet);
        } else {
            this.sprite = new PlayerSprite(PlayerColor.YELLOW, sheet);
        }
    }

    private void updateFrame() {
        if (this.nextFrameTwo.equals(2)) {
            this.nextFrameTwo = 0;
        }
        if (this.nextFrameThree.equals(3)) {
            this.nextFrameThree = 0;
        }
    }
}
