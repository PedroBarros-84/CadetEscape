package org.academiadecodigo.weirddos.Audio;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;


public class AudioSample {

    private Clip sample;
    private final Boolean isLoop;


    // Constructor
    public AudioSample(String filePath, Boolean loop) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        this.isLoop = loop;

        // Option 1 - Doesn't work in .jar (only if file exists in same parent directory)
        //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // Option 2 - Works in jar
        //InputStream audio = getClass().getResourceAsStream(filePath);
        //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(audio));

        // Option 3 - Works in jar
        URL audio = getClass().getResource(filePath);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);

        // create clip reference
        sample = AudioSystem.getClip();

        // open audioInputStream to the clip
        sample.open(audioInputStream);

    }

    public void play(boolean soundON) {

        if (soundON) {
            sample.start();
            if (isLoop) {
                sample.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }

    }

    public void pause() {
        sample.stop();
    }

    public void stop()  {
        sample.stop();
        sample.setMicrosecondPosition(0);
    }


}
