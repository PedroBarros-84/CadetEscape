package org.academiadecodigo.weirddos;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class Main {

    public static  void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        Game game = new Game();
        game.init();

        /*String filePath = "resources/JohnWilliams_BattleOfTheResistance.wav";
        //String filePath = "resources/hitFloor1.wav";
        AudioLibrary audioLibrary = new AudioLibrary(filePath);
        audioLibrary.play();*/

        /*SimpleAudioPlayer simpleAudio = new SimpleAudioPlayer();
        simpleAudio.play();*/

    }
}
