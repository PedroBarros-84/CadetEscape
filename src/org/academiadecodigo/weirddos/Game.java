package org.academiadecodigo.weirddos;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Game {

    private final Controller controller;
    private final CodeCadet codeCadet;
    private final Score score;
    private final CollisionDetector summarizers;
    private final Field field;
    private final Lives lives;
    private final AudioSample menuBackgroundMusic;
    private final AudioSample gameBackgroundMusic;
    private final AudioSample randomizerAlarm;
    private final AudioSample randomizerBackgroundMusic;
    private final AudioSample gameOverAudio1;
    private final AudioSample gameOverAudio2;
    private final AudioSample transitionSoundFX;

    private boolean gameHasStarted;
    private boolean gameIsPaused;
    private boolean randomizerAlertMode;
    private boolean randomizerMode;
    private boolean soundON;
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
        summarizers = new CollisionDetector(this, codeCadet);
        controller = new Controller(codeCadet, this, lives);
        controller.init();

        menuBackgroundMusic = new AudioSample("resources/MacQuayle_FuckSociety.wav", true);
        gameBackgroundMusic = new AudioSample("resources/JohnWilliams_BattleOfTheResistance.wav", true);
        randomizerAlarm = new AudioSample("resources/randomizerAlert.wav", false);
        randomizerBackgroundMusic = new AudioSample("resources/randomizerMusic.wav", false);
        gameOverAudio1 = new AudioSample("resources/gameOver1.wav", false);
        gameOverAudio2 = new AudioSample("resources/gameOver2.wav", false);
        transitionSoundFX = new AudioSample("resources/transitionFX.wav", false);
        soundON = true;

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
    public boolean isPaused()          { return gameIsPaused;          }
    public void    setStart()          { gameHasStarted = true;        }
    public void    setPause()          { gameIsPaused = !gameIsPaused; }
    public boolean getRandomizerMode() { return randomizerMode;        }
    public void    toggleSound()       { soundON = !soundON;           }
    public boolean getSoundON()        { return soundON;               }
    public boolean getHasStarted()     { return gameHasStarted;        }



    // Prompts main menu
    public void init() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        // Print main menu
        field.drawMenu();

        // Wait for user to press SPACE key and play music until then
        while(!gameHasStarted) {
            Thread.sleep(100);
            menuBackgroundMusic.play(soundON);
        }

        // Begin game
        menuBackgroundMusic.stop();
        start();

    }


    // Start game
    public void start() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        // Transition audio FX
        transitionSoundFX.stop();
        transitionSoundFX.play(soundON);

        // Start game music
        gameBackgroundMusic.play(soundON);

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
                gameBackgroundMusic.pause();
                randomizerAlarm.pause();
                randomizerBackgroundMusic.pause();
            }

            // Keep music playing after pause
            if (!randomizerMode) { gameBackgroundMusic.play(soundON); }

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
            if (randomizerAlertMode) {
                randomizerAlarm.play(soundON);
                randomizerAlert();
            }

            // Switch to randomizer mode
            if (randomizerMode) {
                randomizerBackgroundMusic.play(soundON);
                randomizer();
            }

            gameCycleCounter++;

        } while (lives.stillHaveLives());

        gameOver();
    }


    public void gameOver() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        // After game ends show game over
        field.drawGameOver();
        gameBackgroundMusic.stop();
        randomizerAlarm.stop();
        randomizerBackgroundMusic.stop();
        menuBackgroundMusic.stop();
        gameOverAudio1.stop();
        gameOverAudio1.play(soundON);
        Thread.sleep(2000);
        gameOverAudio2.stop();
        gameOverAudio2.play(soundON);

        // Reset the game, and go back to start menu
        codeCadet.resetPosition();
        score.resetScore();
        lives.resetNumOfLives();
        Thread.sleep(7000);
        field.clearField();
        delay = 50;
        gameCycleCounter = 1;
        gameHasStarted = false;
        randomizerMode = false;
        randomizerAlertMode = false;

        // Restart game to menu
        init();
    }


    // Prepare user for upcoming randomizer mode and initiate it
    public void randomizerAlert() throws InterruptedException {

        // 110 cycle limit equates to 5.5 seconds to 2.75 seconds alert time
        if (randomizerAlertMode && randomizerAlertCycleCounter <= 110) {

            if (randomizerAlertCycleCounter == 110) {

                // Reset randomizer alert
                field.deleteRandomizerAlert();
                randomizerAlertCycleCounter = 0;
                randomizerAlertMode = false;
                randomizerMode = true;

                // Prepare randomizer
                field.clearField();
                field.drawRandomizer();
                lives.showLivesRemaining();
                score.showScore();
                codeCadet.getPicture().draw();

                randomizerAlarm.stop();
                gameBackgroundMusic.pause();
                randomizerBackgroundMusic.play(soundON);

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
    public void randomizer() throws InterruptedException {

        randomizerCycleCounter++;

        // After 300 animation cycles, revert to standard game mode
        // 300 cycles limit equates from 15 seconds to 7.5 seconds
        if (randomizerCycleCounter > 300) {
            field.clearField();
            field.drawGame();
            lives.showLivesRemaining();
            score.showScore();
            codeCadet.getPicture().draw();
            randomizerCycleCounter = 0;
            randomizerMode = false;
            randomizerBackgroundMusic.stop();
            transitionSoundFX.stop();
            transitionSoundFX.play(soundON);
            gameBackgroundMusic.play(soundON);
        }
    }

    public void soundOFF() {
        menuBackgroundMusic.pause();
        gameBackgroundMusic.pause();
        randomizerAlarm.pause();
        randomizerBackgroundMusic.pause();
    }

}
