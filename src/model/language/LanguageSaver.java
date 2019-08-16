package model.language;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.naming.CannotProceedException;

import org.json.JSONException;
import org.json.JSONObject;

import model.utils.FileWorker;

/**
 * Utility class to save a translation from user.
 */
public class LanguageSaver implements LanguageSaverInterface {

    private String languageName;
    private JSONObject translatedStrings;
    private final JSONObject loadedTranslation;
    private boolean fromList;

    /**
     * Init variable.
     */
    public LanguageSaver() {
        final ApplicationStrings appString = new ApplicationStrings();
        appString.setDefault();
        this.loadedTranslation = appString.getSelectedLanguageInfo();
        this.translatedStrings = new JSONObject();
        this.fromList = false;
    }

    @Override
    public final void setLanguage(final String lang) {
        this.languageName = lang;
    }

    @Override
    public final void insertTranslation(final String key, final String value) throws IllegalArgumentException {
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

    @Override
    public final void insertAllTranslation(final List<Translation> translation) throws IllegalArgumentException {
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

    @Override
    public final boolean canSave() {
        return this.translatedStrings.length() == this.getNumbreOfKeyToTranslate() && this.languageName != null;
    }

    @Override
    public final boolean saveTraductions() throws CannotProceedException {
        if (this.canSave()) {
            try {
                final FileWorker fileWorker = new FileWorker(
                        ApplicationStrings.DIRECTORY_NAME + File.separator + this.languageName);
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

    private boolean contains(final Iterator<?> list, final String key) {
        while (list.hasNext()) {
            if (list.next().equals(key)) {
                return true;
            }
        }
        return false;
    }

}
