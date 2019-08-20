package controller;

import model.language.ApplicationStrings;
import model.player.Player;
import model.utils.Directions;
import view.game.GameController;
import view.game.GameEndedController;

/**
 *
 */
public interface Controller {

    /**
     * @return the ApplicationStrings instance
     */
    ApplicationStrings getTranslator();

    /**
     * 
     * @param language - one language taken from getTranslator().getAvailableLanguages()
     */
    void setLanguage(String language);

    /**
     * 
     * @param controller - the view controller
     */
    void initGame(GameController controller);

    /**
     * Move the player specified in the specified direction.
     * 
     * @param player    player to move
     * @param direction direction to move
     */
    void movePlayer(Player player, Directions direction);

    /**
     * Stop to move the specified player in the specified direction.
     * 
     * @param player    player to stop
     * @param direction direction to stop the player moving in
     */
    void stopPlayer(Player player, Directions direction);


    /**
     * Drop a bomb from player.
     * @param player - who is dropping the bomb
     */
    void releaseBomb(Player player);

    /**
     * Single player button pressed. Start the game in singlePlayer mode 
     */
    void actionPerformedSingleplayerBtn();

    /**
     * Back button pressed. Return to the MainMenu window.
     */
    void actionPerformedBackBtn();

    /**
     * Multiplayer button pressed. Start the game in MultiPlayer mode.
     */
    void actionPerformedMultiplayerBtn();

    /**
     * Map editor button pressed. Load the map editor window.
     */
    void actionPerformedMapEditorBtn();

    /**
     * Multilang button pressed. Load the translation window.
     */
    void actionPerformedLanguageEditorBtn();

    /**
     * Settings button pressed. Load the settings window.
     */
    void actionPerformedSettingsBtn();

    /**
     * Save button pressed.
     */
    void actionPerformedSaveBtn();

    /**
     * Language changed. Change it in the all game.
     * @param language language to change
     */
    void actionPerformedLanguageChanged(String language);

    /**
     * Lose button pressed.
     */
    void actionPerformedLoseBtn();

    /**
     * Close button pressed. End the game.
     */
    void actionPerformedCloseBtn();

    /**
     * How to play button pressed. Load its window.
     */
    void actionPerformedHTPBtn();

    /**
     * Called while loading GameEnded.fxml.
     * @param gameEndedController - controller of GameEnded.fxml
     */
    void gameEnded(GameEndedController gameEndedController);

    /**
     * Stops control bomb explosion and start game ended gui.
     */
    void notifyKilledPlayers();

    /**
     * {@link BombTimer} notify the controller that a bomb is exploded.
     */
    void notifyExplosionDone();

}
