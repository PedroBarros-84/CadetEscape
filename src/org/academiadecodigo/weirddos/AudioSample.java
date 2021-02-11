package org.academiadecodigo.weirddos;


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioSample {

    // Properties
    private final Clip sample;
    private final Boolean isLoop;


    // Constructor
    public AudioSample(String filePath, Boolean loop) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        this.isLoop = loop;

        // create AudioInputStream object
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        sample = AudioSystem.getClip();

        // open audioInputStream to the clip
        sample.open(audioInputStream);

    }

    public void play(Boolean soundON) {

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
