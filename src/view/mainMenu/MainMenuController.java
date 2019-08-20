package view.mainMenu;

import java.util.Locale;

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
    public void singlePlayerBtPressed() {
        getController().actionPerformedSingleplayerBtn();
    }

    /**
     * Action executed when Multiplayer button is pressed.
     */
    @FXML
    public void multiPlayerBtPressed() {
        getController().actionPerformedMultiplayerBtn();
    }
 
    /**
     * Action executed when map editor button is pressed.
     */
    @FXML
    public void mapEditorPressed() {
        getController().actionPerformedMapEditorBtn();
    }

    /**
     * Action executed when language editor button is pressed.
     */
    @FXML
    public void languageEditorPressed() {
        getController().actionPerformedLanguageEditorBtn();
    }

    /**
     * Action executed when Settings button is pressed.
     */
    @FXML
    public void settingsBtPressed() {
        getController().actionPerformedSettingsBtn();
    }

    /**
     * Action executed when howToPlay button is pressed.
     */
    @FXML
    public void howToPlayBtPressed() {
        getController().actionPerformedHTPBtn();
    }

    /**
     * Action executed when Settings button is pressed.
     */
    @FXML
    public void closeGameBtPressed() {
        getController().actionPerformedCloseBtn();
    }

    @Override
    public final void translate(final ApplicationStrings t) {
        spBtn.setText(t.getValueOf("singleplayer").toUpperCase(Locale.getDefault()));
        mpBtn.setText(t.getValueOf("multiplayer").toUpperCase(Locale.getDefault()));
        mapEditorBtn.setText(t.getValueOf("map editor").toUpperCase(Locale.getDefault()));
        langEditorBtn.setText(t.getValueOf("language editor").toUpperCase(Locale.getDefault()));
        settingsBtn.setText(t.getValueOf("settings").toUpperCase(Locale.getDefault()));
        howToPlayBtn.setText(t.getValueOf("howtoplay").toUpperCase(Locale.getDefault()));
        closeBtn.setText(t.getValueOf("close").toUpperCase(Locale.getDefault()));
    }
 
}
