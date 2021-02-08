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
    Clip sample;
    Long currentFrame;
    AudioInputStream audioInputStream;
    String filePath;


    // Constructor
    public AudioSample(String filePath) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        this.filePath = filePath;

        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        sample = AudioSystem.getClip();

        // open audioInputStream to the clip
        sample.open(audioInputStream);

        sample.loop(Clip.LOOP_CONTINUOUSLY);
    }


    public void play() {
        sample.start();
    }


    public void pause() {
        currentFrame = sample.getMicrosecondPosition();
        sample.stop();
    }


    public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        sample.close();
        resetAudioStream();
        sample.setMicrosecondPosition(currentFrame);
        play();
    }


    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        sample.stop();
        sample.close();
        resetAudioStream();
        currentFrame = 0L;
        sample.setMicrosecondPosition(0);
        play();
    }


    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        sample.stop();
        sample.close();
        resetAudioStream();
        currentFrame = 0L;
        sample.setMicrosecondPosition(0);
    }


    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        sample.open(audioInputStream);
        //sample.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
