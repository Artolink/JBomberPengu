package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

import model.AbstractEntity;
import model.Model;
import model.blocks.Bomb;
import model.collisions.CollisionImpl;
import model.language.ApplicationStrings;
import model.map.GameMap;
import model.player.Player;
import model.player.PlayerColor;
import model.utils.Directions;
import model.utils.Pair;
import view.GUI;
import view.GUIImpl;
import view.game.GameController;
import view.game.GameEndedController;

public class ControllerImpl implements Controller {


    private final Model model;
    private final GUIImpl gui;
    private ViewUpdater viewUpdater;
    private GameController gameView;
    private ScoreController scoreController;
    /**
     * 
     * @param model
     * @param gui
     */
    public ControllerImpl(final Model model, final GUIImpl gui) {
        this.model = model;
        this.gui = gui;
        this.gui.setController(this);
    }

    // global data utilities
    /**
     * @return the ApplicationStrings instance
     */
    public ApplicationStrings getTranslator() {
        return this.model.getTranslator();
    }

    /**
     * 
     * @param language - one language taken from getTranslator().getAvailableLanguages()
     */
    public void setLanguage(final String language) {
        this.model.getTranslator().setLanguage(language);
        // this.view.notifyLanguageChanged();
    }


    // main menu controller

    // game controller

    /**
     * 
     * @param controller - the view controller
     */
    public void initGame(final GameController controller) {
        this.model.initGameData();
        final GameMap map = model.getGameMap();
        this.gameView = controller;
        this.viewUpdater = new ViewUpdater();
        scoreController = new ScoreController(model.getPlayers().stream()
                                                                .map( e -> e.getColor())
                                                                .collect(Collectors.toCollection(ArrayList::new)));

        // set game dimensions
        gameView.setDimensions(new Pair<Integer, Integer>(map.getDimensions().getX(), map.getDimensions().getY()));
        gameView.setBlockDimension(Model.BLOCKDIMENSION);
        gameView.setBlockSpacing(Model.BLOCKSPACING);
        gameView.resizeToMap();

        // first render of map in view
        for (int a = 0; a < map.getDimensions().getX(); a++) {
            for (int b = 0; b < map.getDimensions().getY(); b++) {
                final AbstractEntity block = map.getBlock(a, b);
                block.setHeight(Model.BLOCKDIMENSION);
                block.setWidth(Model.BLOCKDIMENSION);
                gameView.draw(block.getImagePath(), a, b);
            }
        }

        // render players
        gameView.drawPlayers(model.getPlayers());
        for (final Player player : model.getPlayers()) {
            player.setCollision(new CollisionImpl(player).setMap(map));
        }
        this.viewUpdater.setModel(this.model);
        this.viewUpdater.setView(gameView);
        new Thread(this.viewUpdater).start();
    }

    /**
     * Move the player specified in the specified direction.
     * 
     * @param player    player to move
     * @param direction direction to move
     */
    public void movePlayer(final Player player, final Directions direction) {
        if (!this.viewUpdater.getDirection(player).equals(direction)) {
            this.viewUpdater.setDirection(player, direction);
        }
    }

    /**
     * Stop to move the specified player in the specified direction.
     * 
     * @param player    player to stop
     * @param direction direction to stop the player moving in
     */
    public void stopPlayer(final Player player, final Directions direction) {
        if (this.viewUpdater.getDirection(player).equals(direction)) {
            this.viewUpdater.setDirection(player, Directions.STATIONARY);
        }
    }


    /**
     * Drop a bomb from player.
     * @param player - who is dropping the bomb
     */
    public void releaseBomb(final Player player) {
        final int bombX = (player.getPosition().getX() + (player.getWidth() / 2)) / player.getWidth();
        final int bombY = (player.getPosition().getY() + (player.getHeight() / 2)) / player.getHeight();
        final Bomb bomb = new Bomb(new Pair<>(bombX, bombY), player);
        this.model.getGameMap().setBlock(bomb, bombX, bombY);
        this.gameView.drawBomb(bomb.getImagePath(), bomb.getInitialPosition().getX(), bomb.getInitialPosition().getY());
        final BombTimer bombTimer = new BombTimer(bomb, this.model.getPlayers(), this.model.getGameMap(), this, this.gameView);
        new Timer().schedule(bombTimer, bomb.getExplosionTime());
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
        System.exit(0); //TODO HOW am I supposed to close it spotbugs???
    }

    @Override
    public final void actionPerformedHTPBtn() {
      this.gui.loadPage(GUI.PageNames.HOWTOPLAY);
      this.gui.getActivePageController().translate(getTranslator());
    }


    /**
     * Called while loading GameEnded.fxml.
     * @param gameEndedController - controller of GameEnded.fxml
     */
    public final void gameEnded(final GameEndedController gameEndedController) {
        if ((scoreController.getAlivePlayers().contains(PlayerColor.RED)) 
                && (scoreController.getAlivePlayers().size() == 1) 
                || (scoreController.getWinnerByScore().isPresent()
                && (scoreController.getWinnerByScore().get().equals(PlayerColor.RED) 
                && (scoreController.getAlivePlayers().size() > 1)))) {

            gameEndedController.redPlayerSet("RED WON!!!", "view/winner.png");
            gameEndedController.yellowPlayerSet("YELLOW LOST...", "view/loser.gif");

        } else if ((scoreController.getAlivePlayers().contains(PlayerColor.YELLOW)) 
                && (scoreController.getAlivePlayers().size() == 1) 
                || (scoreController.getWinnerByScore().isPresent() 
                && (scoreController.getWinnerByScore().get().equals(PlayerColor.RED) 
                && (scoreController.getAlivePlayers().size() > 1)))) {

            gameEndedController.redPlayerSet("RED LOST...", "view/loser.gif");
            gameEndedController.yellowPlayerSet("YELLOW WON!!!", "view/winner.gif"); 
        } else {
            gameEndedController.redPlayerSet("match draw", "view/draw.gif");
            gameEndedController.yellowPlayerSet("match draw", "view/draw.gif");
        }
    }

    public void notifyKilledPlayers(List<Player> killedPlayer) {
        scoreController.killPlayer(killedPlayer.get(killedPlayer.size()-1).getColor());
        /*if(scoreController.getAlivePlayers().size() <= 1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //this.gui.loadPage(GUI.PageNames.GAMENDED);
            //this.gui.getActivePageController().translate(getTranslator());
        }*/
    }


}
