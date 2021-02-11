package org.academiadecodigo.weirddos;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.academiadecodigo.weirddos.Audio.AudioLibrary;
import org.academiadecodigo.weirddos.Audio.Sample;

public class Game {

    private final Controller controller;
    private final CodeCadet codeCadet;
    private final Score score;
    private final CollisionDetector summarizers;
    private final Field field;
    private final Lives lives;
    private final AudioLibrary audioLibrary;

    private boolean gameHasStarted;
    private boolean gameIsPaused;
    private boolean randomizerAlertMode;
    private boolean randomizerMode;
    private int randomizerAlertCycleCounter;
    private int randomizerCycleCounter;
    private int delay;
    private int gameCycleCounter;


    // Constructor
    public Game() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        lives = new Lives();
        codeCadet = new CodeCadet(lives);
        field = new Field();
        field.drawField();
        score = new Score();
        audioLibrary = new AudioLibrary();
        summarizers = new CollisionDetector(this, codeCadet, audioLibrary);
        controller = new Controller(codeCadet, this, lives);

        delay = 50;
        gameCycleCounter = 1;
        randomizerAlertCycleCounter = 0;
        randomizerCycleCounter = 0;
        gameHasStarted = false;
        randomizerAlertMode = false;
        randomizerMode = false;
        gameIsPaused = false;

    }


    // Getters & Setters
    public boolean isPaused()          { return gameIsPaused; }
    public void    setStart()          { gameHasStarted = true; }
    public void    setPause()          { gameIsPaused = !gameIsPaused; }
    public boolean getRandomizerMode() { return randomizerMode; }
    public void    toggleSound()       { audioLibrary.toggleSound(); }
    public void    setSoundOFF()       { audioLibrary.setSoundOFF(); }
    public boolean getHasStarted()     { return gameHasStarted; }



    // Prompts main menu
    public void init() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        // Print main menu
        field.drawMenu();

        // Wait for user to press SPACE key and play music until then
        while(!gameHasStarted) {
            Thread.sleep(100);
            audioLibrary.play(Sample.MENU_MUSIC);
        }

        // Begin game
        audioLibrary.stop(Sample.MENU_MUSIC);
        start();

    }


    // Start game
    public void start() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        // Transition audio FX
        audioLibrary.replay(Sample.GAME_MODE_TRANSITION);

        // Start game music
        audioLibrary.play(Sample.GAME_MUSIC);

        // Print all game components on screen
        field.drawGame();
        codeCadet.getPicture().draw();
        score.showScore();
        lives.showAllPostIts();
        summarizers.resetAllSummarizers();

        do {

            // Keep game paused if necessary
            while (gameIsPaused) {
                Thread.sleep(100);
                audioLibrary.pauseGameAudio();
            }

            // Keep music playing after pause
            if (!randomizerMode) {
                audioLibrary.play(Sample.GAME_MUSIC);
            }

            // Create delay for animation
            Thread.sleep(delay);

            // Move all summarizer down once
            summarizers.rainAll(score);

            // Every 200 cycles, summarizers rain faster
            if (gameCycleCounter % 200 == 0 && delay > 25) { delay--; }

            // Create 0.2% chance for randomizer during standard game mode
            if (!randomizerAlertMode && !randomizerMode) {
                double chance = Math.random() * 100;
                if (chance < 0.2) { randomizerAlertMode = true; }
            }

            // Prompt blinking alert before randomizer
            if (randomizerAlertMode) { randomizerAlert(); }

            // Switch to randomizer mode
            if (randomizerMode) { randomizer(); }

            gameCycleCounter++;

        } while (lives.stillHaveLives());

        gameOver();
    }


    public void gameOver() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        // After game ends show game over
        field.drawGameOver();
        audioLibrary.endOfGame();

        // Reset the game, and go back to start menu
        codeCadet.resetPosition();
        score.resetScore();
        lives.resetNumOfLives();
        Thread.sleep(6000);
        delay = 50;
        gameCycleCounter = 1;
        gameHasStarted = false;
        randomizerMode = false;
        randomizerAlertMode = false;

        // Restart game to menu
        init();
    }


    // Prepare user for upcoming randomizer mode and initiate it
    public void randomizerAlert() {

        audioLibrary.play(Sample.RANDOMIZER_ALARM);

        // 110 cycle limit equates to 5.5 seconds to 2.75 seconds alert time
        if (randomizerAlertMode && randomizerAlertCycleCounter <= 110) {

            if (randomizerAlertCycleCounter == 110) {

                // Reset randomizer alert
                field.deleteRandomizerAlert();
                randomizerAlertCycleCounter = 0;
                randomizerAlertMode = false;
                randomizerMode = true;

                // Prepare randomizer
                field.drawRandomizer();
                lives.showLivesRemaining();
                score.showScore();
                codeCadet.getPicture().draw();

                audioLibrary.endOfRandomizerAlert();

            } else if ((randomizerAlertCycleCounter / 10) % 2 == 0) {
                field.deleteRandomizerAlert();
                field.drawRandomizerAlert();
            } else if ((randomizerAlertCycleCounter / 10) % 2 != 0) {
                field.deleteRandomizerAlert();
            }

            randomizerAlertCycleCounter++;
        }
    }

    // Check if randomizer should be over and finish it
    public void randomizer() {

        randomizerCycleCounter++;
        audioLibrary.play(Sample.RANDOMIZER_MUSIC);

        // After 300 animation cycles, revert to standard game mode
        // 300 cycles limit equates from 15 seconds to 7.5 seconds
        if (randomizerCycleCounter > 300) {
            field.drawGame();
            lives.showLivesRemaining();
            score.showScore();
            codeCadet.getPicture().draw();
            randomizerCycleCounter = 0;
            randomizerMode = false;
            audioLibrary.endOfRandomizer();
        }
    }

}
