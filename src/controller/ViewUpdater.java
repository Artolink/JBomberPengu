package controller;



import model.Model;
import model.map.GameMap;
import model.player.Player;
import model.utils.Directions;
import view.game.GameController;

public class ViewUpdater implements Runnable {

    private static final int TIMETOSLEEP = 75;
    private int velocity;
    private Model model;
    private GameController view;
    private GameMap map;

    @Override
    public void run() {
        while (true) {
            for (Player player : this.model.getPlayers()) {
                final Directions direction = player.getDirection();
                if (!direction.equals(Directions.STATIONARY)) {
                    if(player.move(direction)) {
                        this.view.movePlayer(player, player.getPosition().getX(), player.getPosition().getY());
                    }
                }
            }
            try {
                Thread.sleep(TIMETOSLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Directions getDirection(final Player player) {
        return this.model.getPlayers().get(model.getPlayers().indexOf(player)).getDirection();
    }

    public void setModel(final Model model) {
        this.model = model;
    }

    public void setDirection(final Player player, final Directions direction) {
        this.model.getPlayers().get(model.getPlayers().indexOf(player)).setDirection(direction);
    }

    public void setView(final GameController view) {
        this.view = view;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}