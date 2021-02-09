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
    private final AudioSample gameBackGroundMusic;
    private final AudioSample randomizerAlarm;
    private final AudioSample randomizerBackgroundMusic;
    private final AudioSample gameOverAudio1;
    private final AudioSample gameOverAudio2;
    private final AudioSample transitionSoundFX1;
    private final AudioSample transitionSoundFX2;
    private final AudioSample transitionSoundFX3;

    private volatile boolean gameHasStarted;
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
        summarizers = new CollisionDetector(codeCadet);
        controller = new Controller(codeCadet, this, lives);
        controller.init();

        gameBackGroundMusic = new AudioSample("resources/JohnWilliams_BattleOfTheResistance.wav", true);
        randomizerAlarm = new AudioSample("resources/randomizerAlert.wav", false);
        randomizerBackgroundMusic = new AudioSample("resources/randomizerMusic.wav", false);
        gameOverAudio1 = new AudioSample("resources/gameOver1.wav", false);
        gameOverAudio2 = new AudioSample("resources/gameOver2.wav", false);
        transitionSoundFX1 = new AudioSample("resources/transition1.wav", false);
        transitionSoundFX2 = new AudioSample("resources/transition2.wav", false);
        transitionSoundFX3 = new AudioSample("resources/transition3.wav", false);

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

    public void setRandomizer() { randomizerAlertMode = true; }


    // Prompts main menu
    public void init() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        // Print main menu
        field.drawMenu();

        // Start background music
        gameBackGroundMusic.play();

        // Wait for user to press SPACE key
        while(!gameHasStarted) {
            Thread.sleep(250);
        }

        // Begin game
        start();

    }


    // Start game
    public void start() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        // Transition audio FX
        transitionSoundFX2.stop();
        transitionSoundFX2.play();

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
            }

            // Create delay for animation
            Thread.sleep(delay);

            // Move all summarizer down once
            summarizers.rainAll(score);

            // Every 200 cycles, summarizers rain faster
            if (gameCycleCounter % 200 == 0 && delay > 25) { delay--; }

            // Create 0.1% chance for randomizer during standard game mode
            if (!randomizerAlertMode && !randomizerMode) {
                double chance = Math.random() * 100;
                if (chance < 0.2) { randomizerAlertMode = true; }
            }

            // Prompt blinking alert before randomizer
            if (randomizerAlertMode) {
                randomizerAlarm.play();
                randomizerAlert();
            }

            // Switch to randomizer mode
            if (randomizerMode) {
                randomizer();
            }

            System.out.println(gameCycleCounter + "   " + delay);

            gameCycleCounter++;

            //field.getCanvasElements();

        } while (lives.stillHaveLives());

        gameOver();
    }


    public void gameOver() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        // After game ends show game over
        field.drawGameOver();
        gameBackGroundMusic.stop();
        randomizerAlarm.stop();
        randomizerBackgroundMusic.stop();
        gameOverAudio1.stop();
        gameOverAudio1.play();
        Thread.sleep(2000);
        gameOverAudio2.stop();
        gameOverAudio2.play();

        // Reset the game, and go back to start menu
        codeCadet.resetPosition();
        score.resetScore();
        lives.resetNumOfLives();
        Thread.sleep(7000);
        field.clearField();
        delay = 50;
        gameCycleCounter = 1;
        gameHasStarted = false;

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
                gameBackGroundMusic.pause();
                randomizerBackgroundMusic.play();

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
        // 250 cycles limit equates from 15 seconds to 7.5 seconds
        if (randomizerCycleCounter > 300) {
            field.clearField();
            field.drawGame();
            lives.showLivesRemaining();
            score.showScore();
            codeCadet.getPicture().draw();
            randomizerCycleCounter = 0;
            randomizerMode = false;
            randomizerBackgroundMusic.stop();
            transitionSoundFX2.stop();
            transitionSoundFX2.play();
            gameBackGroundMusic.resume();
        }
    }

}
