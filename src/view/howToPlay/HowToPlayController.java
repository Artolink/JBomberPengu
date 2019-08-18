package view.howToPlay;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.language.ApplicationStrings;
import view.PageController;

/**
 *
 */
public class HowToPlayController extends PageController {

    @FXML
    private Button backBtn;


    /**
     * Automatically called from the FXML file.
     */
    @FXML
    private void initialize() {


    }

    /**
     * Returns to main menu whitout saving.
     */
    @FXML
    public void backButton() {
        getController().actionPerformedBackBtn();
    }

    @Override
    public final void translate(final ApplicationStrings t) {
        backBtn.setText(t.getValueOf("back"));
    }

}
