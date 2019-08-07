package model.language;

/**
 * Associate a key value of a game string to its translation in a user chosen
 * language.
 */
public class Translation {

    private String key;
    private String translated;

    /**
     * Constructor with key value and its translation.
     * @param key key value of a game string
     * @param translated string translated
     */
    public Translation(final String key, final String translated) {
        this.key = key;
        this.translated = translated;
    }

    /**
     * Get key of the translation.
     * @return string of the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Set the key of this translation.
     * @param key key to be setted
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Get the translation value.
     * @return string of the translation
     */
    public String getTranslation() {
        return translated;
    }

    /**
     * Set the translation.
     * @param translated string of the translation
     */
    public void setTranslation(final String translated) {
        this.translated = translated;
    }

}
