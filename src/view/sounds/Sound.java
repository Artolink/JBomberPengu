package view.sounds;

/**
 * 
 * A class that manages a sound.
 *
 */
public interface Sound {

    /**
     * Plays a sound.
     */
    void play();

    /**
     * Stops a sound.
     */
    void stop();

    /**
     * Gets the sound state.
     *
     * @return true if sound is playing, false it's not
     */
    boolean isPlaying();
}
