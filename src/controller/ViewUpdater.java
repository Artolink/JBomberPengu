package controller;

import model.Model;
import model.player.Player;
import model.utils.Directions;
import model.utils.Pair;
import view.game.GameController;

public class ViewUpdater implements Runnable {

    private static final int TIMETOSLEEP = 100;
    private int velocity;
    private Model model;
    private GameController view;

    @Override
    public void run() {
        while (true) {
            for (Player player : this.model.getPlayers()) {
                final Directions direction = player.getDirection();
                if (!direction.equals(Directions.STATIONARY)) {
                    switch (direction) {
                    case DOWN:
                        // controllo collision
                        player.setPosition(
                                new Pair<>(player.getPosition().getX(), player.getPosition().getY() + velocity));
                        break;
                    case LEFT:
                        // controllo collision
                        player.setPosition(
                                new Pair<>(player.getPosition().getX() - velocity, player.getPosition().getY()));
                        break;
                    case RIGHT:
                        // controllo collision
                        player.setPosition(
                                new Pair<>(player.getPosition().getX() + velocity, player.getPosition().getY()));
                        break;
                    case UP:
                        // controllo collision
                        player.setPosition(
                                new Pair<>(player.getPosition().getX(), player.getPosition().getY() - velocity));
                        break;
                    default:
                        break;
                    }
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

    public void setVelocity(final int velocity) {
        this.velocity = velocity;
    }
}