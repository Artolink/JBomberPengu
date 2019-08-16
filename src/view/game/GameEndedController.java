package view.game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.language.ApplicationStrings;
import view.PageController;

/**
 *
 */
public class GameEndedController extends PageController {

    @FXML
    private Label statusLabel;
    @FXML
    private Button mpBtn;
    @FXML
    private Button backBtn;

    /**
     * 
     * @param phrase - The phrase you want to display
     */
    public void setLabelText(final String phrase) {
        statusLabel.setText(phrase);
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
     * Returns to main menu whitout saving.
     */
    @FXML
    private  void backBtPressed() {
        System.out.println("Back button pressed");

        getController().actionPerformedBackBtn();
    }

    @Override
    public final void translate(final ApplicationStrings t) {
        mpBtn.setText(t.getValueOf("multiplayer"));
        backBtn.setText(t.getValueOf("back"));
    }

}
