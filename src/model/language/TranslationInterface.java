package model.language;

/**
 * Associate a key value of a game string to its translation in a user chosen
 * language.
 */
public interface TranslationInterface {

    /**
     * Get key of the translation.
     * @return string of the key
     */
    String getKey();

    /**
     * Set the key of this translation.
     * @param key key to be setted
     */
    void setKey(String key);

    /**
     * Get the translation value.
     * @return string of the translation
     */
    String getTranslation();

    /**
     * Set the translation.
     * @param translated string of the translation
     */
    void setTranslation(String translated);

}
