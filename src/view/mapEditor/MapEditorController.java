package view.mapEditor;

import javafx.fxml.FXML;
import view.GUIImpl;

/**
 *
 */
public class MapEditorController extends GUIImpl {

    /**
     * Saves the work.
     */
    @FXML
    public void saveButton() {
        //TODO
    }

    /**
     * Returns to main menu whitout saving.
     */
    @FXML
    public void backButton() {
        loadPage("MainMenu");
    }
}
