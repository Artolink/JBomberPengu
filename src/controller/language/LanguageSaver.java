package controller.language;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.naming.CannotProceedException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.language.ApplicationStrings;
import model.language.Translation;
import controller.utils.FileWorker;

/**
 * Utility class to save a translation from user.
 */
public class LanguageSaver {

    /**
     * Name of the file to work with.
     */
    private String languageName;
    private JSONObject translatedStrings;
    private JSONObject loadedTranslation;
    private boolean fromList;

    /**
     * Init variable.
     */
    public LanguageSaver() {
        ApplicationStrings appString = new ApplicationStrings();
        appString.setDefault();
        this.loadedTranslation = appString.getSelectedLanguageInfo();
        this.translatedStrings = new JSONObject();
        this.fromList = false;
    }

    /**
     * Set language to insert.
     * @param lang language to insert
     */
    public void setLanguage(final String lang) {
        this.languageName = lang;
    }

    /**
     * Insert a translation into this object.
     * @param key key of translation
     * @param value translated value
     * @throws IllegalArgumentException cannot insert this key value couple
     */
    public void insertTranslation(final String key, final String value) throws IllegalArgumentException {
        try {
            if (this.contains(this.loadedTranslation.keys(), key)) {
                this.translatedStrings.put(key, value);
            } else {
                if (this.fromList) {
                    this.translatedStrings = new JSONObject();
                    this.fromList = false;
                }
                throw new IllegalArgumentException("Cannot insert Translation of " + key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a list of translation into this object.
     * @param translation list of translation
     * @throws IllegalArgumentException cannot insert these translation
     */
    public void insertAllTranslation(final List<Translation> translation) throws IllegalArgumentException {
        this.fromList = true;
        final Iterator<Translation> iterator = translation.iterator();
        Translation trad;
        while (iterator.hasNext()) {
            trad = iterator.next();
            this.insertTranslation(trad.getKey(), trad.getTranslation());
        }
        this.fromList = false;
    }

    private int getNumbreOfKeyToTranslate() {
        return this.loadedTranslation.length();
    }

    /**
     * Know if you have inserted all keys to be translated.
     * @return true if you can save, false otherwise
     */
    public boolean canSave() {
        return this.translatedStrings.length() == this.getNumbreOfKeyToTranslate() && this.languageName != null;
    }

    /**
     * Save the inserted translation into file FILENAME.
     * @return if correctly saved
     * @throws CannotProceedException error accessing file
     */
    public boolean saveTraductions() throws CannotProceedException {
        if (this.canSave()) {
            try {
                final FileWorker fileWorker = new FileWorker(ApplicationStrings.DIRECTORY_NAME + File.separator + this.languageName);
                fileWorker.setContent(this.translatedStrings.toString());
                fileWorker.save();
            } catch (IOException e) {
                e.printStackTrace();
                throw new CannotProceedException("Error reading the file in LanguageSaver.saveTraductions");
            }
            return true;
        } else {
            return false;
        }
    }
    
    private boolean contains(final Iterator<?> list, String key) {
        while (list.hasNext()) {
            if (list.next().equals(key)) {
                return true;
            }
        }
        return false;
    }

}
