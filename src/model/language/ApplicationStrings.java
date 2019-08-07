package model.language;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

import controller.utils.FileWorker;

/**
 * Class containing all application string saved on file.
 */
public class ApplicationStrings {

    /**
     * Name of the file to work with.
     */
    public static final String DIRECTORY_NAME = "languages";
    private final List<String> availableLaunguages;
    private JSONObject languageInfo;
    private String selectedLanguage;

    /**
     * Constructor read information from file.
     * 
     * @throws IOException   on file error
     * @throws JSONException on parsing file
     */
    public ApplicationStrings() {
        this.selectedLanguage = "";
        this.availableLaunguages = new ArrayList<>();
        final File folder = new File(DIRECTORY_NAME);
        final Pattern ext = Pattern.compile("(?<=.)\\.[^.]+$");
        final File[] listOfFile = folder.listFiles();
        if (listOfFile != null) {
            for (final File fileEntry : listOfFile) {
                this.availableLaunguages.add(ext.matcher(fileEntry.getName()).replaceAll(""));
            }
        }

    }

    /**
     * Return the list of available languages on file.
     * 
     * @return ArrayList<String> containing available languages
     */
    public List<String> getAvailableLanguages() {
        return this.availableLaunguages;
    }

    /**
     * Set the language to take the strings from.
     * 
     * @param lang String of the language to set
     * @throws IllegalArgumentException if language not present
     */
    public void setLanguage(final String lang) throws IllegalArgumentException {
        if (this.availableLaunguages.contains(lang)) {
            this.selectedLanguage = lang;
            try {
                this.languageInfo = new JSONObject(new FileWorker(DIRECTORY_NAME + File.separator + lang).load());
            } catch (JSONException | IOException e) {
                e.printStackTrace();
                this.languageInfo = new JSONObject();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Get the selected language.
     * 
     * @return String of selected language, null if not setted yet
     */
    public String getSelectedLanguage() {
        return this.selectedLanguage.isEmpty() ? null : this.selectedLanguage;
    }

    /**
     * Get an object containing key-value of the selected language.
     * 
     * @return JSONObject of the selected language
     */
    public JSONObject getSelectedLanguageInfo() {
        return this.languageInfo;
    }

    /**
     * Set the default language.
     */
    public void setDefault() {
        this.setLanguage(this.getAvailableLanguages().get(0));
    }

    /**
     * Get the String of the Key value specified.
     * 
     * @param key key value of requested language
     * @return String containing translation of the key value in selected language
     * @throws IllegalArgumentException if the key does not exist
     */
    public String getValueOf(final String key) throws IllegalArgumentException {
        try {
            return this.languageInfo.getString(key);
        } catch (Exception e) {
            throw new IllegalArgumentException("Parametro key non valido");
        }
    }

}
