package view.animations;

import javafx.scene.image.ImageView;
import model.player.Player;
import model.utils.Directions;

/**
 * Class that run the animations.
 *
 */
public class Animations implements Runnable {

    private Player player;
    private ImageView imageView;
    private PlayerSprite sprite;
    private static final int SHEET_ROWS = 1;
    private static final int SHEET_COLUMNS = 38;
    private Directions lastHorizontalDir;
    private final SpriteSheet sheet = new SpriteSheet("/view/sheet.png", SHEET_ROWS, SHEET_COLUMNS);
    private Integer nextFrame = 0;
    private long timeToSleep;
    private boolean isDead = false;

    /**
     * Runs the animations.
     */
    @Override
    public void run() {

        while (true) {

            switch (player.getDirection()) {
            case UP:
                this.updateFrame(sprite.getUpSprites().size());
                this.imageView.setImage(sprite.getUpSprites().get(nextFrame).getImage());
                timeToSleep = 300;
                break;
            case RIGHT:
                this.updateFrame(sprite.getRunRightSprites().size());
                this.imageView.setImage(sprite.getRunRightSprites().get(nextFrame).getImage());
                lastHorizontalDir = Directions.RIGHT;
                timeToSleep = 120;
                break;
            case LEFT:
                this.updateFrame(sprite.getRunLeftSprites().size());
                this.imageView.setImage(sprite.getRunLeftSprites().get(nextFrame).getImage());
                lastHorizontalDir = Directions.LEFT;
                timeToSleep = 120;
                break;
            case DOWN:
                this.updateFrame(sprite.getDownSprites().size());
                this.imageView.setImage(sprite.getDownSprites().get(nextFrame).getImage());
                timeToSleep = 300;
                break;
            case STATIONARY:
                if (lastHorizontalDir.equals(Directions.LEFT)) {
                    this.updateFrame(sprite.getStayLeftSprites().size());
                    this.imageView.setImage(sprite.getStayLeftSprites().get(nextFrame).getImage());

                } else {
                    this.updateFrame(sprite.getStayRightSprites().size());
                    this.imageView.setImage(sprite.getStayRightSprites().get(nextFrame).getImage());
                }
                timeToSleep = 280;
                break;
            default:
                break;
            }

            if (isDead) {
                this.updateFrame(sprite.getLoseSprites().size());
                this.imageView.setImage(sprite.getLoseSprites().get(nextFrame).getImage());
                timeToSleep = 250;
            }

            try {
                Thread.sleep(timeToSleep);
                if (nextFrame == 2) {
                    nextFrame = 0;
                } else {
                    nextFrame++; 
                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
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

        if (player.getID().equals(0)) {
            this.sprite = new PlayerSprite("red", sheet);
        } else {
            this.sprite = new PlayerSprite("yellow", sheet);
        }
    }

    private void updateFrame(final int size) {
        if (this.nextFrame.equals(size)) {
            this.nextFrame = 0;
        }
    }
}
