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
    private Clip sample;
    private AudioInputStream audioInputStream;
    private Boolean isLoop;


    // Constructor
    public AudioSample(String filePath, Boolean loop) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        this.isLoop = loop;

        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        sample = AudioSystem.getClip();

        // open audioInputStream to the clip
        sample.open(audioInputStream);

    }

    public void play() {
        sample.start();
        if (isLoop) {
            sample.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void pause() {
        sample.stop();
    }

    public void resume() {
        play();
    }

    public void stop()  {
        sample.stop();
        sample.setMicrosecondPosition(0);
    }


}
