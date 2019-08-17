package view.animations;

/**
 * 
 * This class store a player sprites.
 *
 */
public class PlayerSprite extends AbstractDynamicSprites {

    private int start;
    private int toMove;
    private static final int START_RED = 0;
    private static final int START_YELLOW = 18;
    private static final int SPRITES_TO_STAY = 3;
    private static final int SPRITES_TO_RUN = 4;
    private static final int SPRITES_TO_UP = 2;
    private static final int SPRITES_TO_LOSE = 36;

    /**
     * It creates the sprite lists of the player.
     * 
     * @param color color of the player
     * @param sheet the sheet
     */
    public PlayerSprite(final String color, final SpriteSheet sheet) {

        super();
        if (color.equals("red")) {
            this.start = START_RED;
            this.toMove = START_YELLOW;
        } else if (color.equals("yellow")) {
            this.start = START_YELLOW;
            this.toMove = START_YELLOW + start;
        }

        final int cont = start + SPRITES_TO_STAY;

        for (int i = start; i <= toMove; i++) {
            if (i < cont) {
                super.getStayLeftSprites().add(new Sprite(sheet, 0, i));
            }
            if (i >= cont && (i < (cont + SPRITES_TO_STAY))) {
                super.getStayRightSprites().add(new Sprite(sheet, 0, i));
            }
            if (i > (cont + SPRITES_TO_STAY) && (i < (cont + SPRITES_TO_RUN + SPRITES_TO_STAY))) {
                super.getRunLeftSprites().add(new Sprite(sheet, 0, i));
            }
            if (i > (cont + SPRITES_TO_STAY + SPRITES_TO_RUN)
                    && (i < (cont + (SPRITES_TO_RUN * 2) + SPRITES_TO_STAY))) {
                super.getRunRightSprites().add(new Sprite(sheet, 0, i));
            }
            if (i >= (cont + (SPRITES_TO_RUN * 2) + SPRITES_TO_STAY)
                    && (i < (cont + (SPRITES_TO_RUN * 2) + SPRITES_TO_STAY + SPRITES_TO_UP))) {
                super.getUpSprites().add(new Sprite(sheet, 0, i));
            }
            if (i >= (cont + (SPRITES_TO_RUN * 2) + SPRITES_TO_STAY + SPRITES_TO_UP)
                    && (i < (cont + (SPRITES_TO_RUN * 2) + SPRITES_TO_STAY + (SPRITES_TO_UP * 2)))) {
                super.getDownSprites().add(new Sprite(sheet, 0, i));
            }
        }

        super.getLoseSprites().add(new Sprite(sheet, 0, SPRITES_TO_LOSE));
        super.getLoseSprites().add(new Sprite(sheet, 0, SPRITES_TO_LOSE + 1));
    }

    /**
     * Gets sprites to move.
     */
    @Override
    public int getSpritesToMove() {
        return this.toMove;
    }

}
