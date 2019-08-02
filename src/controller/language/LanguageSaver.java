package controller.language;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.naming.CannotProceedException;

import org.json.JSONException;
import org.json.JSONObject;

import model.language.Traduction;
import controller.utils.FileWorker;

/**
 * Utility class to save a traduction from user.
 */
public class LanguageSaver {

    /**
     * Name of the file to work with.
     */
    public static final String FILENAME = "languages";
    private String languageName;
    private JSONObject traductedStrings;
    private JSONObject loadedTraduction;
    private boolean fromList;

    /**
     * Constructor.
     */
    public LanguageSaver() {
        this.traductedStrings = new JSONObject();
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
     * Insert a traduction into this object.
     * @param key key of traduction
     * @param value traducted value
     * @throws IllegalArgumentException cannot insert this key value couple
     */
    public void insertTraduction(final String key, final String value) throws IllegalArgumentException {
        try {
            //control if the key is correct
            this.traductedStrings.put(key, value);
        } catch (JSONException e) {
            if (this.fromList) {
                this.traductedStrings = new JSONObject();
                this.fromList = false;
            }
            throw new IllegalArgumentException("Cannot insert Traduction of " + key);
        }
    }

    /**
     * Insert a list of traduction into this object.
     * @param traductions list of traduction
     * @throws IllegalArgumentException cannot insert these traduction
     */
    public void insertAllTraduction(final List<Traduction> traductions) throws IllegalArgumentException {
        this.fromList = true;
        final Iterator<Traduction> iterator = traductions.iterator();
        Traduction trad;
        while (iterator.hasNext()) {
            trad = iterator.next();
            this.insertTraduction(trad.getKey(), trad.getTraduction());
        }
        this.fromList = false;
    }

    private void load() throws CannotProceedException {
        try {
            final FileWorker fileWorker = new FileWorker(FILENAME);
            this.loadedTraduction = new JSONObject(fileWorker.load());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CannotProceedException("Error occurred while reading the file or parsing it");
        }

    }

    private int getNumbreOfKeyToTraduct() throws CannotProceedException {
        try {
            this.load();
            final String firstLang = (String) this.loadedTraduction.names().get(0);
            return this.loadedTraduction.getJSONObject(firstLang).names().length();
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Know if you have inserted all keys to be traducted.
     * @return true if you can save, false otherwise
     */
    public boolean canSave() {
        try {
            return this.traductedStrings.names().length() == this.getNumbreOfKeyToTraduct();
        } catch (CannotProceedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Save the inserted traduction into file FILENAME.
     * @return if correctly saved
     * @throws CannotProceedException error parsing file or reading it
     */
    public boolean saveTraductions() throws CannotProceedException {
        if (this.canSave()) {
            try {
                this.loadedTraduction.put(this.languageName, this.traductedStrings);
                final FileWorker fileWorker = new FileWorker(FILENAME);
                fileWorker.setContent(this.loadedTraduction.toString());
                fileWorker.save();
            } catch (JSONException e) {
                e.printStackTrace();
                throw new CannotProceedException();
            } catch (IOException e) {
                e.printStackTrace();
                throw new CannotProceedException("Error reading the file in LanguageSaver.saveTraductions");
            }
            return true;
        } else {
            return false;
        }
    }

}
