package model.language;

/**
 * Associate a key value of a game string to its translation in a user chosen
 * language.
 */
public class Translation implements TranslationInterface {

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

    @Override
    public final String getKey() {
        return key;
    }

    @Override
    public final void setKey(final String key) {
        this.key = key;
    }

    @Override
    public final String getTranslation() {
        return translated;
    }
    
    @Override
    public final void setTranslation(final String translated) {
        this.translated = translated;
    }

}
