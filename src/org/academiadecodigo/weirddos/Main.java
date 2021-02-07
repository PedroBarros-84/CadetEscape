package org.academiadecodigo.weirddos;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.academiadecodigo.simplegraphics.pictures.Picture;



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
