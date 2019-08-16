package model.language;

import java.util.List;
import org.json.JSONObject;

/**
 * Class containing all application string saved on file.
 */
public interface ApplicationStringsInterface {

    /**
     * Name of the file to work with.
     */
    String DIRECTORY_NAME = "languages";

    /**
     * Return the list of available languages on file.
     * 
     * @return ArrayList<String> containing available languages
     */
    List<String> getAvailableLanguages();

    /**
     * Set the language to take the strings from.
     * 
     * @param lang String of the language to set
     * @throws IllegalArgumentException if language not present
     */
    void setLanguage(String lang) throws IllegalArgumentException;

    /**
     * Get the selected language.
     * 
     * @return String of selected language, null if not set yet
     */
    String getSelectedLanguage();

    /**
     * Get an object containing key-value of the selected language.
     * 
     * @return JSONObject of the selected language
     */
    JSONObject getSelectedLanguageInfo();

    /**
     * Set the default language.
     */
    void setDefault();

    /**
     * Get the String of the Key value specified.
     * 
     * @param key key value of requested language
     * @return String containing translation of the key value in selected language
     * @throws IllegalArgumentException if the key does not exist
     */
    String getValueOf(String key) throws IllegalArgumentException;

}
