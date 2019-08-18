package view.game;

import controller.ControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.language.ApplicationStrings;
import view.PageController;

/**
 *
 */
public class GameEndedController extends PageController {

    @FXML
    private Label playerNameLabel1;
    @FXML
    private Label playerNameLabel2;
    @FXML
    private Button mpBtn;
    @FXML
    private Button backBtn;
    @FXML
    private ImageView playerImage1;
    @FXML
    private ImageView playerImage2;
    
    
    private ControllerImpl controller;

    public void initialize() {
        this.controller = (ControllerImpl) getController();
        this.controller.GameEnded(this);
    }

    public void leftPlayerSet(String text, String path) {
        playerNameLabel1.setText(text);
        playerImage1.setImage(new Image(path));
    }

    public void rightPlayerSet(String text, String path) {
        playerNameLabel2.setText(text);
        playerImage2.setImage(new Image(path));
    }

    /**
     * Action executed when Multiplayer button is pressed.
     */
    @FXML
    private void multiPlayerBtPressed() {
        System.out.println("Multiplayer button pressed");   //TODO debug
        getController().actionPerformedMultiplayerBtn();
    }

    /**
     * Returns to main menu whitout saving.
     */
    @FXML
    private  void backBtPressed() {
        System.out.println("Back button pressed");  //TODO debug
        getController().actionPerformedBackBtn();
    }

    @Override
    public final void translate(final ApplicationStrings t) {
        mpBtn.setText(t.getValueOf("multiplayer"));
        backBtn.setText(t.getValueOf("back"));
    }

}
