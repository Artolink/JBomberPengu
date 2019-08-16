package view.mapEditor;

import javafx.fxml.FXML;
import model.language.ApplicationStrings;
import view.PageController;

/**
 *
 */
public class MapEditorController extends PageController {

    /**
     * Saves the work.
     */
    @FXML
    public void saveButton() {
        getController().actionPerformedSaveBtn();
    }

    /**
     * Returns to main menu whitout saving.
     */
    @FXML
    public void backButton() {
        getController().actionPerformedBackBtn();
    }

    @Override
    public void translate(final ApplicationStrings translator) {
        // TODO Auto-generated method stub

    }
}
