package view.mainMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import model.language.ApplicationStrings;
import view.FxmlFileLoader;
import view.GUIImpl;

/**
 *  The controller of MainMenu.fxml .
 */
public class MainMenuController extends GUIImpl {
 
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
    /**
     * 
     */
    public MainMenuController() {
        languageComboBoxData.addAll(getTranslator().getAvailableLanguages());
    }

    // Private methods -------------------------------------------------------------------------------------------

    /**
     * Automatically called from the FXML file.
     */
    @FXML
    private void initialize() {

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

     changeNames();

    } 

    /**
     * Action executed when singleplayer button is pressed.
     */
    @FXML
    private void singlePlayerBtPressed() {
        System.out.println("Singleplayer button pressed");
        //addPage(new GameGUI()).load();
        getDimensionsMultipliers(); //TODO

    }

    /**
     * Action executed when Multiplayer button is pressed.
     */
    @FXML
    private void multiPlayerBtPressed() {
        System.out.println("Multiplayer button pressed");
        addPage(new FxmlFileLoader("view", "Game")).load();
    }
 
    /**
     * Action executed when map editor button is pressed.
     */
    @FXML
    private void mapEditorPressed() {
        System.out.println("Map editor button pressed");
        addPage(new FxmlFileLoader("view\\mapEditor\\MapEditor.fxml")).load();
    }

    /**
     * Action executed when language editor button is pressed.
     */
    @FXML
    private void languageEditorPressed() {
        System.out.println("Language editor button pressed");
        addPage(new FxmlFileLoader("view\\multiLang\\MultilangView.fxml")).load();
    }

    /**
     * Action executed when Settings button is pressed.
     */
    @FXML
    private void settingsBtPressed() {
        System.out.println("Settings button pressed");
        addPage(new FxmlFileLoader("view\\settings\\SettingsMenu.fxml")).load();
    }
 
    /**
     * Action executed when language ChoiceBox is pressed.
     */
    @FXML
    private void languageChoiceBoxPressed() {
        getTranslator().setLanguage(languageChoiceBox.getValue());
        changeNames();
    }

 
    /**
     * Methods that changes the language to the selected one. Triggered when LanguageChoiceBox is Pressed.
     */
    private void changeNames() {
        try {
            ApplicationStrings t = getTranslator();
            spBtn.setText(t.getValueOf("singleplayer"));
            mpBtn.setText(t.getValueOf("multiplayer"));
            settingsBtn.setText(t.getValueOf("settings"));
            closeBtn.setText(t.getValueOf("close"));
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED while translating");
        } 
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
