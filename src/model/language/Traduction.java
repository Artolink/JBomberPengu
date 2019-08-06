package model.language;

/**
 * Associate a key value of a game string to its traduction in a user chosed
 * language.
 */
public class Traduction {

    private String key;
    private String traducted;

    /**
     * Constructor with key value and its traduction.
     * @param key key value of a game string
     * @param traducted string traduted
     */
    public Traduction(final String key, final String traducted) {
        this.key = key;
        this.traducted = traducted;
    }

    /**
     * Get key of the traduction.
     * @return string of the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Set the key of this traduction.
     * @param key key to be setted
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Get the traduction value.
     * @return string of the traduction
     */
    public String getTraduction() {
        return traducted;
    }

    /**
     * Set the traduction.
     * @param traducted string of the traduction
     */
    public void setTraduction(final String traducted) {
        this.traducted = traducted;
    }

}
