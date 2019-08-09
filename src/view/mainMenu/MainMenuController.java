package view.mainMenu;

import java.io.File;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.language.ApplicationStrings;
import view.GUIImpl;

public class MainMenuController extends GUIImpl{

    private static final String UNSELECTED_BUTTON_NAME = "view" + File.separator + "Empty_Button.png";
    private static final String SELECTED_BUTTON_NAME = "view" + File.separator + "Empty_Button_Glowing.png";
 
    private static final int BUTTONS_HEIGHT = 50;
    private static final int BUTTONS_WIDTH = 150;
 
    private Image unselected;
    private Image selected;
    //private ImageView toggleImage;
 
 
    @FXML
    private Button spBtn;
    @FXML
    private Button mpBtn;
    @FXML
    private Button settingsBtn;
    @FXML
    private Button closeBtn;
 
    @FXML
    private ComboBox<String> languageChoiceBox;
    private ObservableList<String> languageComboBoxData = FXCollections.observableArrayList();
 
    // Add a public no-args constructor
    public MainMenuController() {

     try {
     unselected = new Image(UNSELECTED_BUTTON_NAME);
     selected = new Image(SELECTED_BUTTON_NAME);
        } catch (Exception e) {
      System.out.println("ERROR: Image/es not found");
        }

     languageComboBoxData.addAll(getTranslator().getAvailableLanguages());
    }

    // Private methods -------------------------------------------------------------------------------------------

    /**
     * Automatically called from the FXML file.
     */
    @FXML
    private void initialize() {

     setButtonGraphics(spBtn, unselected, selected);
     setButtonGraphics(mpBtn, unselected, selected);
     setButtonGraphics(settingsBtn, unselected, selected);
        setButtonGraphics(closeBtn, unselected, selected);

     /*
     Background background = new Background(toggleImage);
     spBtn.setBackground(value);
     */

     // Init ComboBox items.
     languageChoiceBox.setItems(languageComboBoxData);
     languageChoiceBox.setValue(languageComboBoxData.get(0));

     // Define rendering of the list of values in ComboBox drop down. 
     languageChoiceBox.setCellFactory((comboBox) -> {

         return new ListCell<String>() {

       @Override
       protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty); 
        if (item == null || empty) {
         setText(null);
        } else {
         setText(item);
        }
       }

      };

     });

     
    //changeNames(); //TODO

    } 

    /**
     * Action executed when singleplayer button is pressed.
     */
    @FXML
    private void singlePlayerBtPressed() {
        System.out.println("Singleplayer button pressed");
        //addPage(new GameGUI()).load();
        System.out.println(getDimensionsMultipliers().toString());
    }

    /**
     * Action executed when Multiplayer button is pressed.
     */
    @FXML
    private void multiPlayerBtPressed() {
        System.out.println("Multiplayer button pressed");
    }
 
    /**
     * Action executed when map editor button is pressed.
     */
    @FXML
    private void mapEditorPressed() {
        System.out.println("Map editor button pressed");
    }

    /**
     * Action executed when language editor button is pressed.
     */
    @FXML
    private void languageEditorPressed() {
        System.out.println("Language editor button pressed");
    }

    /**
     * Action executed when Settings button is pressed.
     */
    @FXML
    private void settingsBtPressed() {
        System.out.println("Settings button pressed");
    }
 
    /**
     * Action executed when language ChoiceBox is pressed.
     */
    @FXML
    private void languageChoiceBoxPressed() {
        getTranslator().setLanguage(languageChoiceBox.getValue());
        //changeNames(); //TODO
    }

 
    /**
     * Methods that changes the language to the selected one. Triggered when LanguageChoiceBox is Pressed.
     */
    private void changeNames() {
        ApplicationStrings t = getTranslator();
        spBtn.setText(t.getValueOf("singleplayer"));
        mpBtn.setText(t.getValueOf("multiplayer"));
        settingsBtn.setText(t.getValueOf("settings"));
        closeBtn.setText(t.getValueOf("close"));
    }
 
    /**
     * Changes the aspect of a button.
     * @param button - the button you want to modify
     * @param toggleImage 
     */
    private void setButtonGraphics(Button button, ImageView toggleImage) {
        button.setGraphic(toggleImage);
    }
 

    private void setButtonGraphics(Button button, Image unselected, Image selected) {
        ImageView toggleImage = new ImageView();
        try {
            toggleImage.imageProperty().bind(Bindings
                  .when(button.hoverProperty())
                    .then(selected)
                    .otherwise(unselected)
            );
        } catch (NullPointerException e) {
            System.out.println("ERROR: Images not loaded correctly");
        }
        toggleImage.setPreserveRatio(true);
        toggleImage.setFitHeight(BUTTONS_HEIGHT);
        toggleImage.setFitWidth(BUTTONS_WIDTH);
        setButtonGraphics(button, toggleImage);
            
         //TODO translate to css
    }
 
 /*
  *  To get the items in the choice box:
   ObservableList<String> availableChoices = choiceBox.getItems();

  To set the items in the choice box:
   ObservableList<String> availableChoices = FXCollections.ObservableArrayList("apples", "oranges"); 
   choiceBox.setItems(availableChoices);

  To get the selected item write:
   String selectedChoice = choiceBox.getSelectionModel().getSelectedItem();

  To set the selected item write:
   choiceBox.getSelectionModel().setSelectedItem("oranges");

  */
 
}
