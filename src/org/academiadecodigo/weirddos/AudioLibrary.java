package org.academiadecodigo.weirddos;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioLibrary {

    // Properties
    Clip gameAudio;
    AudioInputStream audioInputStream;


    // Constructor
    public AudioLibrary(String filePath) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        gameAudio = AudioSystem.getClip();

        // open audioInputStream to the clip
        gameAudio.open(audioInputStream);

    }


    public void play() throws InterruptedException {

        gameAudio.start();
        gameAudio.loop(Clip.LOOP_CONTINUOUSLY);
        Thread.sleep((int)Double.POSITIVE_INFINITY);
        //gameAudio.stop();

    }


}
