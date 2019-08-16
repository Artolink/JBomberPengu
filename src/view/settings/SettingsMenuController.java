package view.settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.language.ApplicationStrings;
import view.PageController;
import javafx.scene.control.ListCell;

/**
 *
 */
public class SettingsMenuController extends PageController {

    @FXML
    private Label pageLabel;

    @FXML
    private Button backBtn; 

    @FXML
    private CheckBox fullscreenCB;

    @FXML
    private ComboBox<String> languageChoiceBox;
    private ObservableList<String> languageComboBoxData = FXCollections.observableArrayList();

    /**
     * 
     */
    public SettingsMenuController() {
        languageComboBoxData.addAll(getTranslator().getAvailableLanguages());
    }

    /**
     * Automatically called from the FXML file.
     */
    @FXML
    private void initialize() {

        fullscreenCB.setSelected(getFullscreenState());

        // Init ComboBox items.
        languageChoiceBox.setItems(languageComboBoxData);
        languageChoiceBox.setValue(getTranslator().getSelectedLanguage());

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
     * Returns to main menu whitout saving.
     */
    @FXML
    public void backButton() {
        getController().actionPerformedBackBtn();
    }

    /**
     * Action executed when language ChoiceBox is pressed.
     */
    @FXML
    private void languageChoiceBoxPressed() {
        getController().actionPerformedLanguageChanged();
    }

    /**
     * 
     */
    @FXML
    public void fullscreenPressed() {
        setFullscreenMode(fullscreenCB.isSelected());
    }
 
    @Override
    public final void translate(final ApplicationStrings t) {
        pageLabel.setText(t.getValueOf("settings"));
        fullscreenCB.setText(t.getValueOf("fullscreen"));
        backBtn.setText(t.getValueOf("back"));
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
