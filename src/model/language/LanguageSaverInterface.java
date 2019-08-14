package model.language;

import java.util.List;

import javax.naming.CannotProceedException;

/**
 * Utility class to save a translation from user.
 */
public interface LanguageSaverInterface {

    /**
     * Set language to insert.
     * @param lang language to insert
     */
    void setLanguage(String lang);

    /**
     * Insert a translation into this object.
     * 
     * @param key   key of translation
     * @param value translated value
     * @throws IllegalArgumentException cannot insert this key value couple
     */
    void insertTranslation(String key, String value) throws IllegalArgumentException;

    /**
     * Insert a list of translation into this object.
     * 
     * @param translation list of translation
     * @throws IllegalArgumentException cannot insert these translation
     */
    void insertAllTranslation(List<Translation> translation) throws IllegalArgumentException;

    /**
     * Know if you have inserted all keys to be translated.
     * 
     * @return true if you can save, false otherwise
     */
    boolean canSave();

    /**
     * Save the inserted translation into file FILENAME.
     * 
     * @return if correctly saved
     * @throws CannotProceedException error accessing file
     */
    boolean saveTraductions() throws CannotProceedException;

}
