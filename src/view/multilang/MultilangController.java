package view.multilang;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.CannotProceedException;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.language.ApplicationStrings;
import model.language.LanguageSaver;
import model.language.Translation;
import view.PageController;

/**
 * Controller of res.view.MultilangView.fxm.
 */
public class MultilangController extends PageController{

    private ObservableList<String> languageList;
    private JSONArray languageKey;
    private JSONObject languageInfo;
    private ObservableList<TranslationBlock> translationBlocks;
    private ApplicationStrings appString;
    private static final String SELECT = "Select language...";

    @FXML
    private VBox mainContainer;

    @FXML
    private ComboBox<String> langCombo;

    @FXML
    private TextField userLanguage;

    @FXML
    private Button saveButton;

    /**
     * Initialize view.
     */
    @FXML
    public void initialize() {
        appString = new ApplicationStrings();
        appString.setLanguage(appString.getAvailableLanguages().get(0));
        setLanguageInCombo(appString.getAvailableLanguages());
    }

    /**
     * ComboBox listener. Update view information on language changes.
     */
    @FXML
    public void selectionChange() {
        this.languageList.remove(SELECT);
        appString.setLanguage(this.getSelectedLanguage());
        setTranslationInfo(appString.getSelectedLanguageInfo());
        renderTranslation();
    }

    @FXML
    private  void backBtPressed() {
        System.out.println("Back button pressed");  //TODO debug
        getController().actionPerformedBackBtn();
    }
    
    /**
     * Button save. Save translation into file.
     */
    @FXML
    public void saveTraduction() {
        if (translationBlocks == null) {
            JOptionPane.showMessageDialog(null, appString.getValueOf("multilangViewMessage1"));
            this.langCombo.requestFocus();
        } else if (this.userLanguage.getText().length() < 3) {
            JOptionPane.showMessageDialog(null, appString.getValueOf("multilangViewMessage2"));
            this.userLanguage.requestFocus();
        } else {
            final List<Translation> translated = translationBlocks.stream()
                    .filter(trad -> (!trad.getTranslation().equals("")) && (trad.getTranslation() != null)
                            && (trad.getTranslation().matches("^[a-zA-Z]*$")))
                    .map((trad) -> new Translation(trad.getTranslationKey(), trad.getTranslation()))
                    .collect(Collectors.toList());
            try {
                final LanguageSaver saver = new LanguageSaver();
                saver.setLanguage(this.userLanguage.getText());
                saver.insertAllTranslation(translated);
                if (!saver.saveTraductions()) {
                    JOptionPane.showMessageDialog(null, appString.getValueOf("multilangViewMessage3"));
                }
            } catch (CannotProceedException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    /**
     * Set languages in ComboBox.
     * @param languages list of available languages
     */
    public void setLanguageInCombo(final List<String> languages) {
        languages.add(SELECT);
        this.languageList = FXCollections.observableArrayList(languages);
        this.langCombo.setItems(this.languageList);
        this.langCombo.getSelectionModel().select(SELECT);
    }

    /**
     * Set listener on ComboBox observing language changes.
     * @param action Action to perform on ComboBox content changes
     */
    public void setLanguageChangedListener(final EventHandler<ActionEvent> action) {
        this.langCombo.setOnAction(action);
    }

    /**
     * Get the selected language in ComboBox.
     * @return string of selected language
     */
    public String getSelectedLanguage() {
        return this.langCombo.getSelectionModel().getSelectedItem();
    }

    /**
     * Set the translation info in this object.
     * @param mainInfo JSONObject containing key value of all translation to be translated
     * @throws IllegalArgumentException if argument is null or empty
     */
    public void setTranslationInfo(final JSONObject mainInfo) throws IllegalArgumentException {
        if (mainInfo == null || mainInfo.names() == null) {
            throw new IllegalArgumentException("Null argument");
        } else {
            this.languageKey = mainInfo.names();
            this.languageInfo = mainInfo;
        }
    }

    /**
     * Render translation block in view.
     */
    public void renderTranslation() {
        this.mainContainer.getChildren().clear();
        translationBlocks = FXCollections.observableArrayList();
        TranslationBlock block;
        for (int i = 0; i < this.languageKey.length(); i++) {
            try {
                block = new TranslationBlock(new Translation(this.languageKey.getString(i),
                        this.languageInfo.getString(this.languageKey.getString(i))));
                translationBlocks.add(block);
                this.mainContainer.getChildren().add(block.getMainNode());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private class TranslationBlock {

        private final HBox mainLayout = new HBox();
        private final Label keyInSelectedLanguage = new Label();
        private final Label translationLabel = new Label();
        private final TextField translatedText = new TextField();
        private final String translationKey;

        TranslationBlock(final Translation translation) {
            this.keyInSelectedLanguage.setText(translation.getTranslation());
            this.translationKey = translation.getKey();

            this.translationLabel.setText("Translation ->");
            this.translationLabel.setPadding(new Insets(0, 10, 0, 0));
            this.mainLayout.setPadding(new Insets(0, 0, 10, 0));
            this.mainLayout.setAlignment(Pos.CENTER);

            mainLayout.getChildren().add(0, this.keyInSelectedLanguage);
            mainLayout.getChildren().add(1, this.translationLabel);
            mainLayout.getChildren().add(2, this.translatedText);
        }

        public Node getMainNode() {
            return this.mainLayout;
        }

        public String getTranslationKey() {
            return this.translationKey;
        }

        public String getTranslation() {
            return this.translatedText.getText();
        }
    }

    @Override
    public void translate(ApplicationStrings t) {
        // TODO Auto-generated method stub
        
    }

}
