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
    private Button closeBtn;

    // Private methods -------------------------------------------------------------------------------------------

    /**
     * Action executed when singleplayer button is pressed.
     */
    @FXML
    private void singlePlayerBtPressed() {
        System.out.println("Singleplayer button pressed");  //TODO debug

        getController().actionPerformedSingleplayerBtn();
    }

    /**
     * Action executed when Multiplayer button is pressed.
     */
    @FXML
    private void multiPlayerBtPressed() {
      //TODO
        System.out.println("Multiplayer button pressed");

        getController().actionPerformedMultiplayerBtn();
    }
 
    /**
     * Action executed when map editor button is pressed.
     */
    @FXML
    private void mapEditorPressed() {
      //TODO
        System.out.println("Map editor button pressed");

        getController().actionPerformedMapEditorBtn();
    }

    /**
     * Action executed when language editor button is pressed.
     */
    @FXML
    private void languageEditorPressed() {
      //TODO
        System.out.println("Language editor button pressed");

        getController().actionPerformedEditorBtn();
    }

    /**
     * Action executed when Settings button is pressed.
     */
    @FXML
    private void settingsBtPressed() {
        System.out.println("Settings button pressed");

        getController().actionPerformedSettingsBtn();
    }

    @Override
    public final void translate(final ApplicationStrings t) {
        spBtn.setText(t.getValueOf("singleplayer"));
        mpBtn.setText(t.getValueOf("multiplayer"));
        mapEditorBtn.setText(t.getValueOf("map editor"));
        langEditorBtn.setText(t.getValueOf("language editor"));
        settingsBtn.setText(t.getValueOf("settings"));
        closeBtn.setText(t.getValueOf("close"));
    }
 
}
