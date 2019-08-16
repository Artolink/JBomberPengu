package model.language;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

import model.utils.FileWorker;

/**
 * Class containing all application string saved on file.
 */
public class ApplicationStrings implements ApplicationStringsInterface {

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

    @Override
    public final List<String> getAvailableLanguages() {
        return this.availableLaunguages;
    }


    @Override
    public final void setLanguage(final String lang) throws IllegalArgumentException {
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


    @Override
    public final String getSelectedLanguage() {
        return this.selectedLanguage.isEmpty() ? null : this.selectedLanguage;
    }


    @Override
    public final JSONObject getSelectedLanguageInfo() {
        return this.languageInfo;
    }


    @Override
    public final void setDefault() {
        this.setLanguage(this.getAvailableLanguages().get(0));
    }


    @Override
    public final String getValueOf(final String key) throws IllegalArgumentException {
        try {
            return this.languageInfo.getString(key);
        } catch (Exception e) {
            throw new IllegalArgumentException("Parametro key non valido");
        }
    }

}
