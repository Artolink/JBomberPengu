package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.AbstractEntity;
import model.Model;
import model.language.ApplicationStrings;
import model.map.GameMap;
import model.player.Player;
import model.utils.Directions;
import model.utils.Pair;
import view.GUI;
import view.GUIImpl;
import view.game.GameController;

public class ControllerImpl implements Controller {

    public static final int VELOCITY = 5;

    private final Model model;
    private final GUIImpl gui;
    private ViewUpdater viewUpdater;

    public ControllerImpl(Model model, GUIImpl gui) {
        this.model = model;
        this.gui = gui;
        gui.setController(this);
    }

    // global data utilities
    public ApplicationStrings getTranslator() {
        return this.model.getTranslator();
    }

    public void setLanguage(final String language) {
        this.model.getTranslator().setLanguage(language);
        // this.view.notifyLanguageChanged();
    }

    // main menu controller

    // game controller

    public void initGame(final GameController controller) {
        this.model.initGameData();
        final GameMap map = model.getGameMap();
        final GameController view = controller;
        this.viewUpdater = new ViewUpdater();

        // set game dimensions
        view.setDimensions(new Pair<Integer, Integer>(map.getDimensions().getX(), map.getDimensions().getY()));
        view.setBlockDimension(Model.BLOCKDIMENSION);
        view.setBlockSpacing(Model.BLOCKSPACING);
        view.resizeToMap();

        // first render of map in view
        for (int a = 0; a < map.getDimensions().getX(); a++) {
            for (int b = 0; b < map.getDimensions().getY(); b++) {
                final AbstractEntity block = map.getBlock(b, a);
                view.draw(block.getImagePath(), b, a);
            }
        }

        // render players
        view.drawPlayers(model.getPlayers());

        this.viewUpdater.setModel(this.model);
        this.viewUpdater.setView(view);
        this.viewUpdater.setVelocity(VELOCITY);
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

    @Override
    public void actionPerformedSingleplayerBtn() {
        //this.gui.loadPage(GUI.PageNames.GAME);
    }

    @Override
    public void actionPerformedLanguageChanged() {
        //this.gui.loadPage(GUI.PageNames.GAME);
    }

    @Override
    public void actionPerformedBackBtn() {
        //this.gui.loadPage(GUI.PageNames.GAME);
    }

    @Override
    public void actionPerformedMultiplayerBtn() {
        this.gui.loadPage(GUI.PageNames.GAME);
    }

    @Override
    public void actionPerformedMapEditorBtn() {
        this.gui.loadPage(GUI.PageNames.MAPEDITOR);
    }

    @Override
    public void actionPerformedEditorBtn() {
        //this.gui.loadPage(GUI.PageNames);
    }

    @Override
    public void actionPerformedSettingsBtn() {
        this.gui.loadPage(GUI.PageNames.SETTINGS);
    }

    @Override
    public void actionPerformedSaveBtn() {
        //this.gui.loadPage(GUI.PageNames.GAME);
    }

}
