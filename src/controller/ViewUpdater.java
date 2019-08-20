package controller;

import model.ModelImpl;
import model.player.Player;
import model.utils.Directions;
import view.game.GameController;

/**
 * Player position updater on the view.
 */
public class ViewUpdater implements Runnable {

    private static final int TIMETOSLEEP = 100;
    private GameController view;
    private boolean canRun = true;

    @Override
    public final void run() {
        while (canRun) {
            for (final Player player : ModelImpl.getInstance().getPlayers()) {
                final Directions direction = player.getDirection();
                if (!direction.equals(Directions.STATIONARY) && player.move(direction)) {
                    this.view.movePlayer(player, player.getPosition().getX(), player.getPosition().getY());
                }
            }
            try {
                Thread.sleep(TIMETOSLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * stops update thread, calls stop on player animations.
     */
    public void stop() {
        canRun = false;
        view.stopPlayerAnimations();
    }

    /**
     * Sets the view to move the player.
     * @param view view
     */
    public void setView(final GameController view) {
        this.view = view;
    }
}
