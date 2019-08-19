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
     * Returns to main menu.
     */
    @FXML
    public void backBtnPressed() {
        getController().actionPerformedBackBtn();
    }

    @Override
    public final void translate(final ApplicationStrings t) {
        backBtn.setText(capitalize(t.getValueOf("main menu")));
    }

}
