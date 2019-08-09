package view.sounds;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * A class that implements the interface Sound.
 *
 */
public class SoundImpl implements Sound {

    private final Clip clip;
    private static final int SONG_FRAME = 0;


    /**
     * SoundImpl constructor, it creates an audioInputStream to manage the clip.
     * @param path path
     * @throws UnsupportedAudioFileException uafe
     * @throws IOException ioe
     * @throws LineUnavailableException lue
     */
    public SoundImpl(final String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        final AudioInputStream ais = AudioSystem.getAudioInputStream(this.getClass().getResource(path));
        this.clip = AudioSystem.getClip();
        this.clip.open(ais);
    }

    /**
     * It plays the clip.
     */
    @Override
    public void play() {

        this.clip.stop();
        this.clip.setFramePosition(SONG_FRAME);
        this.clip.start();
    }

    /**
     * It stops the clip.
     */
    @Override
    public void stop() {

        this.clip.stop();

    }

    @Override
    public final boolean isPlaying() {

        return this.clip.isRunning();
    }
}
