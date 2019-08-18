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
    private final Sound startGame;

    /**
     * 
     * @param folder
     * @throws LineUnavailableException 
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     */
    public SoundsAssociator() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

            this.bombPlaced = new SoundImpl("/view/sounds/place_bomb.wav");
            this.explosion = new SoundImpl("/view/sounds/bomb_explosion.wav");
            this.takeItem = new SoundImpl("/view/sounds/take_item.wav");
            this.startMatch = new SoundImpl("/view/sounds/start_game.wav");
            this.startGame = new SoundImpl("/view/sounds/start_game_menu.wav");
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
    
    /**
     * Gets start game sound.
     * 
     * @return start match sound
     */
    public Sound getStartGameSound() {
        return this.startGame;
    }
    
    /**
     * stops all the sounds still playing.
     */
    public void stopSounds() {
        if(getBombPlacedSound().isPlaying()) {
            getBombPlacedSound().stop();
        }
        if(getExplosionSound().isPlaying()) {
            getExplosionSound().stop();
        }
        if(getTakeItemSound().isPlaying()) {
            getTakeItemSound().stop();
        }
        if(getStartMatchSound().isPlaying()) {
            getStartMatchSound().stop();
        }
        if(getStartGameSound().isPlaying()) {
            getStartGameSound().stop();
        }
    }
}
