package model.language;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
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
    public static final String FILENAME = "languages";
    private final List<String> availableLaunguages;
    private final JSONObject mainInfo;
    private JSONObject languageInfo;
    private String selectedLanguage;

    /**
     * Constructor read information from file.
     * @throws IOException on file error
     * @throws JSONException on parsing file
     */
    public ApplicationStrings() throws IOException, JSONException {
        this.availableLaunguages = new ArrayList<>();
        final FileWorker fileWorker = new FileWorker(FILENAME);
        this.mainInfo = new JSONObject(fileWorker.load());
        final JSONArray keys = this.mainInfo.names();
        for (int i = 0; i < keys.length(); i++) {
            this.availableLaunguages.add(keys.getString(i));
        }
    }

    /**
     * Return the list of available languages on file.
     * @return ArrayList<String> containing available languages
     */
    public List<String> getAvailableLanguages() {
        return this.availableLaunguages;
    }

    /**
     * Set the language to take the strings from.
     * @param lang String of the language to set
     * @throws IllegalArgumentException if language not present
     */
    public void setLanguage(final String lang) throws IllegalArgumentException {
        try {
            this.languageInfo = this.mainInfo.getJSONObject(lang);
            this.selectedLanguage = lang;
        } catch (JSONException e) {
            throw new IllegalArgumentException(lang);
        }
    }

    /**
     * Get the selected language.
     * @return String of selected language
     */
    public String getSelectedLanguage() {
        return this.selectedLanguage;
    }

    /**
     * Get the String of the Key value specified.
     * @param key key value of requested language
     * @return String containing traduction of the key value in selected language
     * @throws IllegalArgumentException if the kay does not exist
     */
    public String getValueOf(final String key) throws IllegalArgumentException {
        try {
            return this.languageInfo.getString(key);
        } catch (JSONException e) {
            throw new IllegalArgumentException(key);
        }
    }

}
