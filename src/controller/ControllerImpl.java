package controller;

import java.util.Timer;

import javafx.application.Platform;
import model.AbstractEntity;
import model.ModelImpl;
import model.blocks.Bomb;
import model.collisions.CollisionImpl;
import model.language.ApplicationStrings;
import model.map.GameMap;
import model.player.Player;
import model.player.PlayerColor;
import model.score.ScoreCompute;
import model.utils.Directions;
import model.utils.Pair;
import view.GUI;
import view.GUIImpl;
import view.game.GameController;
import view.game.GameEndedController;

/**
 *  Class controller of all Game and windows of it.
 */
public class ControllerImpl implements Controller {

    private static final int TIMEBEFOREGAMEND = 2000;
    private final ModelImpl model;
    private final GUIImpl gui;
    private ViewUpdater viewUpdater;
    private GameController gameView;
    private ScoreCompute scoreCompute;
    private boolean gameEnd;

    /**
     * Sets Model and View references.
     * @param model where to find data
     * @param gui where to load data and game images
     */
    public ControllerImpl(final ModelImpl model, final GUIImpl gui) {
        this.model = model;
        this.gui = gui;
        this.gui.setController(this);
    }

    @Override
    public final ApplicationStrings getTranslator() {
        return this.model.getTranslator();
    }

    @Override
    public final void setLanguage(final String language) {
        this.model.getTranslator().setLanguage(language);
        // this.view.notifyLanguageChanged();
    }


    @Override
    public final void initGame(final GameController controller) {
        gameEnd = false;
        this.model.initGameData();
        final GameMap map = model.getGameMap();
        this.gameView = controller;
        this.viewUpdater = new ViewUpdater();
        scoreCompute = new ScoreCompute(model.getPlayers());
        // set game dimensions
        gameView.setDimensions(new Pair<Integer, Integer>(map.getDimensions().getX(), map.getDimensions().getY()));
        gameView.setBlockDimension(ModelImpl.BLOCKDIMENSION);
        gameView.setBlockSpacing(ModelImpl.BLOCKSPACING);
        gameView.resizeToMap();

        // first render of map in view
        for (int a = 0; a < map.getDimensions().getX(); a++) {
            for (int b = 0; b < map.getDimensions().getY(); b++) {
                final AbstractEntity block = map.getBlock(a, b);
                block.setHeight(ModelImpl.BLOCKDIMENSION);
                block.setWidth(ModelImpl.BLOCKDIMENSION);
                gameView.draw(block.getImagePath(), a, b);
            }
        }

        // render players

        gameView.drawPlayers(model.getPlayers());
        for (final Player player : model.getPlayers()) {
            player.setCollision(new CollisionImpl(player).setMap(map));
            gameView.showAvailableBombs(player);
            gameView.showScore(player);
        }
        this.viewUpdater.setView(gameView);
        new Thread(this.viewUpdater).start();
    }

    @Override
    public final void movePlayer(final Player player, final Directions direction) {
        if (!player.getDirection().equals(direction)) {
            player.setDirection(direction);
        }
    }

    @Override
    public final void stopPlayer(final Player player, final Directions direction) {
        if (player.getDirection().equals(direction)) {
            player.setDirection(Directions.STATIONARY);
        }
    }

    @Override
    public final void releaseBomb(final Player player) {
        if (player.canPlaceBomb()) {
            final int bombX = (player.getPosition().getX() + (player.getWidth() / 2)) / player.getWidth();
            final int bombY = (player.getPosition().getY() + (player.getHeight() / 2)) / player.getHeight();
            final Bomb bomb = new Bomb(new Pair<>(bombX, bombY), player);
            this.model.getGameMap().setBlock(bomb, bombX, bombY);
            this.model.getGameMap().getBlock(bombX, bombY).setWidth(player.getWidth());
            this.model.getGameMap().getBlock(bombX, bombY).setHeight(player.getHeight());
            this.gameView.drawBomb(bomb.getImagePath(), bomb.getInitialPosition().getX(), bomb.getInitialPosition().getY());
            final BombTimer bombTimer = new BombTimer(bomb, this, this.gameView);
            new Timer().schedule(bombTimer, bomb.getExplosionTime());
            player.placeBomb();
            gameView.showAvailableBombs(player);
        }
    }

    @Override
    public final void actionPerformedSingleplayerBtn() {
        //this.gui.loadPage(GUI.PageNames.GAME);
        //this.gui.getActivePageController().translate(getTranslator());
    }

    @Override
    public final void actionPerformedBackBtn() {
        this.gui.loadPage(GUI.PageNames.MAINMENU);
        this.gui.getActivePageController().translate(getTranslator());
    }

    @Override
    public final void actionPerformedMultiplayerBtn() {
        this.gui.loadPage(GUI.PageNames.GAME);
        this.gui.getActivePageController().translate(getTranslator());
    }

    @Override
    public final void actionPerformedMapEditorBtn() {
        //this.gui.loadPage(GUI.PageNames.MAPEDITOR);
        //this.gui.getActivePageController().translate(getTranslator());
    }

    @Override
    public final void actionPerformedLanguageEditorBtn() {
        this.gui.loadPage(GUI.PageNames.LANGUAGEDITOR);
        this.gui.getActivePageController().translate(getTranslator());
    }

    @Override
    public final void actionPerformedSettingsBtn() {
        this.gui.loadPage(GUI.PageNames.SETTINGS);
        this.gui.getActivePageController().translate(getTranslator());
    }

    @Override
    public final void actionPerformedSaveBtn() {
        //this.gui.loadPage(GUI.PageNames.GAME);
    }

    @Override
    public final void actionPerformedLanguageChanged(final String language) {
        setLanguage(language);
        this.gui.getActivePageController().translate(getTranslator());
    }

    @Override
    public final void actionPerformedLoseBtn() {
        this.gui.loadPage(GUI.PageNames.GAMENDED);
        this.gui.getActivePageController().translate(getTranslator());
    }

    @Override
    public final void actionPerformedCloseBtn() {
        System.out.println("Closing application...");
        this.gui.stop();
        Platform.exit(); //TODO HOW am I supposed to close it spotbugs???
    }

    @Override
    public final void actionPerformedHTPBtn() {
      this.gui.loadPage(GUI.PageNames.HOWTOPLAY);
      this.gui.getActivePageController().translate(getTranslator());
    }


    @Override
    public final void gameEnded(final GameEndedController gameEndedController) {
        viewUpdater.stop();
        if ((scoreCompute.getAlivePlayers().stream().map(e -> e.getColor()).anyMatch(e -> e.compareTo(PlayerColor.RED) == 0)) 
                && (scoreCompute.getAlivePlayers().size() == 1) 
                || (scoreCompute.getWinnerByScore().isPresent()
                && (scoreCompute.getWinnerByScore().get().getColor().equals(PlayerColor.RED) 
                && (scoreCompute.getAlivePlayers().size() > 1)))) {

            gameEndedController.redPlayerSet("RED WON!!!", "view/winner.gif");
            gameEndedController.yellowPlayerSet("YELLOW LOST...", "view/loser.gif");

        } else if ((scoreCompute.getAlivePlayers().stream().map(e -> e.getColor()).anyMatch(e -> e.compareTo(PlayerColor.YELLOW) == 0))
                && (scoreCompute.getAlivePlayers().size() == 1) 
                || (scoreCompute.getWinnerByScore().isPresent() 
                && (scoreCompute.getWinnerByScore().get().getColor().equals(PlayerColor.RED) 
                && (scoreCompute.getAlivePlayers().size() > 1)))) {

            gameEndedController.redPlayerSet("RED LOST...", "view/loser.gif");
            gameEndedController.yellowPlayerSet("YELLOW WON!!!", "view/winner.gif"); 
        } else {
            gameEndedController.redPlayerSet("match draw", "view/draw.gif");
            gameEndedController.yellowPlayerSet("match draw", "view/draw.gif");
        }
    }

    @Override
    public final void notifyKilledPlayers() {
        this.gameEnd = true;
        System.out.println("GAME ENDED NOW!!!");
        try {
            Thread.sleep(TIMEBEFOREGAMEND);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.gui.loadPage(GUI.PageNames.GAMENDED);
        this.gui.getActivePageController().translate(getTranslator());
    }

    @Override
    public final void notifyExplosionDone() {
        if (!gameEnd) {
            for (final Player player : this.model.getPlayers()) {
                gameView.showAvailableBombs(player);
                gameView.showScore(player);
                if (player.isDestroyed()) {
                    notifyKilledPlayers();
                    return;
                }
            }
        }
    }


}
