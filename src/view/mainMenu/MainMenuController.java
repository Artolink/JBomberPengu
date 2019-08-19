package view.mainMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.language.ApplicationStrings;
import view.PageController;

/**
 *  The controller of MainMenu.fxml .
 */
public class MainMenuController extends PageController {
 
    @FXML
    private Button spBtn;
    @FXML
    private Button mpBtn;
    @FXML
    private Button mapEditorBtn;
    @FXML
    private Button langEditorBtn;
    @FXML
    private Button settingsBtn;
    @FXML
    private Button howToPlayBtn;
    @FXML
    private Button closeBtn;

    // Private methods -------------------------------------------------------------------------------------------

    /**
     * Action executed when singleplayer button is pressed.
     */
    @FXML
    private void singlePlayerBtPressed() {
        getController().actionPerformedSingleplayerBtn();
    }

    /**
     * Action executed when Multiplayer button is pressed.
     */
    @FXML
    private void multiPlayerBtPressed() {
        getController().actionPerformedMultiplayerBtn();
    }
 
    /**
     * Action executed when map editor button is pressed.
     */
    @FXML
    private void mapEditorPressed() {
        getController().actionPerformedMapEditorBtn();
    }

    /**
     * Action executed when language editor button is pressed.
     */
    @FXML
    private void languageEditorPressed() {
        getController().actionPerformedLanguageEditorBtn();
    }

    /**
     * Action executed when Settings button is pressed.
     */
    @FXML
    private void settingsBtPressed() {
        getController().actionPerformedSettingsBtn();
    }

    /**
     * Action executed when howToPlay button is pressed.
     */
    @FXML
    private void howToPlayBtPressed() {
        getController().actionPerformedHTPBtn();
    }

    /**
     * Action executed when Settings button is pressed.
     */
    @FXML
    private void closeGameBtPressed() {
        getController().actionPerformedCloseBtn();
    }

    @Override
    public final void translate(final ApplicationStrings t) {
        spBtn.setText(t.getValueOf("singleplayer").toUpperCase());
        mpBtn.setText(t.getValueOf("multiplayer").toUpperCase());
        mapEditorBtn.setText(t.getValueOf("map editor").toUpperCase());
        langEditorBtn.setText(t.getValueOf("language editor").toUpperCase());
        settingsBtn.setText(t.getValueOf("settings").toUpperCase());
        howToPlayBtn.setText(t.getValueOf("howtoplay").toUpperCase());
        closeBtn.setText(t.getValueOf("close").toUpperCase());
    }
 
}
