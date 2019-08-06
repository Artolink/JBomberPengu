package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 */
public class MainMenuController extends GuiChild {

    private static final String UNSELECTED_BUTTON_NAME = "view" + File.separator + "Empty_Button.png";
    private static final String SELECTED_BUTTON_NAME = "view" + File.separator + "Empty_Button_Glowing.png";

    private static final int BUTTONS_HEIGHT = 50;
    private static final int BUTTONS_WIDTH = 150;

    private Image unselected;
    private Image selected;
    private ImageView toggleImage;

    @FXML
    private Button spBtn;

    @FXML
    private ComboBox<String> languageChoiceBox;
    private ObservableList<String> languageComboBoxData = FXCollections.observableArrayList();

    //private Timer timer;

    // Add a public no-args constructor
    /**
     * 
     */
    public MainMenuController() {
        try {
            unselected = new Image(UNSELECTED_BUTTON_NAME);
            selected = new Image(SELECTED_BUTTON_NAME);
        } catch (Exception e) {
             System.out.println("ERROR: Image/es not found");
        }
        toggleImage = new ImageView();

        //TODO riempimento da file - da lomba
        languageComboBoxData.add(new String("ita (hardcoded)"));
        languageComboBoxData.add(new String("eng (hardcoded)"));

        //TODO lingua da file - da lomba
    }

    /**
     * 
     */
    @FXML
    private void initialize() {

        try {
            toggleImage.imageProperty().bind(Bindings
                    .when(spBtn.hoverProperty())
                    .then(selected)
                    .otherwise(unselected)
                    );
        } catch (NullPointerException e) {
            System.out.println("ERROR: Images not loaded correctly");
        }
        toggleImage.setPreserveRatio(true);
        toggleImage.setFitHeight(BUTTONS_HEIGHT);
        toggleImage.setFitWidth(BUTTONS_WIDTH);
        spBtn.setGraphic(toggleImage);
        //spBtn.setBackground(null);

        /*
        Background background = new Background(unselected);
        spBtn.setBackground(background);
         */


        // Init ComboBox items.
        languageChoiceBox.setItems(languageComboBoxData);
        languageChoiceBox.setValue(languageComboBoxData.get(0));

        // Define rendering of the list of values in ComboBox drop down. 
        languageChoiceBox.setCellFactory((comboBox) -> {
            return new ListCell<String>() {
                @Override
                protected void updateItem(final String item, final boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
        });


    }

    /**
     * @return null
     * @throws Exception none
     */
    public Scene start() throws Exception {
        return null;
    }

    /**
     * 
     */
    @FXML
    private void singlePlayerBtPressed() {
        System.out.println("Singleplayer button pressed");
        getMainGUI().loadGame();
    }

    /**
     * 
     */
    @FXML
    private void multiPlayerBtPressed() {
        System.out.println("Multiplayer button pressed");
    }

    /**
     * 
     */
    @FXML
    private void settingsBtPressed() {
        System.out.println("Settings button pressed");
    }

    /**
     * 
     */
    @FXML
    private void languageChoiceBoxPressed() {   //ChoiceBox<String>
        System.out.println(languageChoiceBox.getValue());
    }

    /**
     * 
     */
    @FXML
    private void closeGame() {
        System.out.println("Closing game...");
        //System.exit(0);
    }

    /**
     * 
     */
    @FXML
    private List<String> languages() {
        List<String> list = new ArrayList<>();
        list.add("Italiano");
        list.add("English");
        return list;
    }

    /*
     *  To get the items in the choice box:
     *      ObservableList<String> availableChoices = choiceBox.getItems();
     *
     *  To set the items in the choice box:
     *      ObservableList<String> availableChoices = FXCollections.ObservableArrayList("apples", "oranges"); 
     *      choiceBox.setItems(availableChoices);
     *
     *  To get the selected item write:
     *      String selectedChoice = choiceBox.getSelectionModel().getSelectedItem();
     *
     *  To set the selected item write:
     *      choiceBox.getSelectionModel().setSelectedItem("oranges");
     */

}
