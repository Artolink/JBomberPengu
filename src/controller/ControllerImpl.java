package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import model.AbstractEntity;
import model.Model;
import model.blocks.Bomb;
import model.blocks.IndestructibleBlock;
import model.collisions.CollisionImpl;
import model.language.ApplicationStrings;
import model.map.GameMap;
import model.player.Player;
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
    final Timer timer;

    /**
     * 
     * @param model
     * @param gui
     */
    public ControllerImpl(final Model model, final GUIImpl gui) {
        this.model = model;
        this.gui = gui;
        this.gui.setController(this);
        this.timer = new Timer();
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
            player.setCollision(new CollisionImpl(player, map));
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
        Bomb bomb = new Bomb(new Pair<>(bombX, bombY), player);
        this.model.getGameMap().setBlock(bomb, bombX, bombY);
        this.gameView.drawBomb(bomb.getImagePath(), bomb.getInitialPosition().getX(), bomb.getInitialPosition().getY());
        BombTimer bombTimer = new BombTimer(bomb, getExplosionBlocks(bomb.getRange(), bombX, bombY), this.model.getGameMap(), this.gameView);
        this.timer.schedule(bombTimer, bomb.getExplosionTime());
    }

    private List<AbstractEntity> getExplosionBlocks(final int range, final int row, final int column) {
        final List<AbstractEntity> blocks = new ArrayList<>();
        boolean canGoUp = true;
        boolean canGoDown = true;
        boolean canGoLeft = true;
        boolean canGoRight = true;
        AbstractEntity block;
        for (int level = 1; level <= range; level++) {
            try {
                block = this.model.getGameMap().getBlock(row, column - level);
                if (!block.getClass().getCanonicalName().equals(IndestructibleBlock.class.getCanonicalName()) && canGoUp) {
                    blocks.add(block);
                } else {
                    canGoUp = false;
                }
            } catch (Exception e) { }
            try {
                block = this.model.getGameMap().getBlock(row, column + level);
                if (!block.getClass().getCanonicalName().equals(IndestructibleBlock.class.getCanonicalName()) && canGoDown) {
                    blocks.add(block);
                } else {
                    canGoDown = false;
                }
            } catch (Exception e) { }
            try {
                block = this.model.getGameMap().getBlock(row - level, column);
                if (!block.getClass().getCanonicalName().equals(IndestructibleBlock.class.getCanonicalName()) && canGoLeft) {
                    blocks.add(block);
                } else {
                    canGoLeft = false;
                }
            } catch (Exception e) { }
            try {
                block = this.model.getGameMap().getBlock(row + level, column);
                if (!block.getClass().getCanonicalName().equals(IndestructibleBlock.class.getCanonicalName()) && canGoRight) {
                    blocks.add(block);
                } else {
                    canGoRight = false;
                }
            } catch (Exception e) { }
        }
        return blocks;
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
        // TODO use score to determine who won
        if (true) {
            gameEndedController.leftPlayerSet("RED WON!!!", "view/iu.gif");
            gameEndedController.rightPlayerSet("YELLOW Lost...", "view/loser.gif");
        } else {
            gameEndedController.leftPlayerSet("RED Lost...", "view/loser.gif");
            gameEndedController.rightPlayerSet("YELLOW WON!!!", "view/iu.gif"); 
        }
    }

}
