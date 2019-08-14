package view.sounds;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * A class that stores the game sounds.
 *
 */
public class SoundsAssociator {

    private final Sound bombPlaced;
    private final Sound explosion;
    private final Sound takeItem;
    private final Sound startMatch;

    /**
     * 
     * @param folder
     * @throws LineUnavailableException 
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     */
    public SoundsAssociator() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

            this.bombPlaced = new SoundImpl("");
            this.explosion = new SoundImpl("");
            this.takeItem = new SoundImpl("");
            this.startMatch = new SoundImpl("");
    }

    /**
     * Gets bomb placed sound.
     * 
     * @return bomb placed sound
     */
    public Sound getBombPlacedSound() {
        return this.bombPlaced;
    }

    /**
     * Gets bomb explosion sound.
     * 
     * @return bomb explosion sound
     */
    public Sound getExplosionSound() {
        return this.explosion;
    }

    /**
     * Gets take item sound.
     * 
     * @return take item sound
     */
    public Sound getTakeItemSound() {
        return this.takeItem;
    }

    /**
     * Gets start match sound.
     * 
     * @return start match sound
     */
    public Sound getStartMatchSound() {
        return this.startMatch;
    }
}