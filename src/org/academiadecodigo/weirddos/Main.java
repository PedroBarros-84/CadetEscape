package org.academiadecodigo.weirddos;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class Main {

    public static int highScore = 0;

    public static  void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        Game game = new Game();
        game.init();

        highScore++;
        System.out.println(highScore);

    }
}
