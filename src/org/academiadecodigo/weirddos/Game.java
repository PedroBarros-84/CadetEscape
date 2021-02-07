package org.academiadecodigo.weirddos;


import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Shape;

public class Game {


    private Controller controller;
    private CodeCadet codeCadet;
    private AudioLibrary audioLibrary;
    private Score score;
    private CollisionDetector summarizers;
    private Field field;
    private Lives lives;

    private volatile boolean gameHasStarted;
    private boolean gameIsPaused;
    private boolean quitGame;
    private boolean randomizerTime;
    private int randomizerAlertCounter = 0;


    // Constructor
    public Game() {

        lives = new Lives();
        codeCadet = new CodeCadet(lives);
        field = new Field();
        field.drawField();
        score = new Score();
        controller = new Controller(codeCadet, this, lives);
        controller.init();

        gameHasStarted = false;
        randomizerTime = false;
        gameIsPaused = false;
        quitGame = false;

    }

    // Getters & Setters
    public boolean isPaused() { return gameIsPaused; }
    public void setStart()  { gameHasStarted = true; }
    public void setPause()  { gameIsPaused = !gameIsPaused; }
    public void setQuit()   { quitGame = true; }

    public void setRandomizerTime() { randomizerTime = !randomizerTime;}



    // Prompts main menu
    public void init() throws InterruptedException {

        field.drawMenu();

        if (quitGame) {
            System.exit(0);
        }

        while(!gameHasStarted) {
            Thread.sleep(100);
        }

        start();

    }


    // Starts playable game
    public void start() throws InterruptedException {

        field.drawGame();
        codeCadet.getPicture().draw();
        score.showScore();
        lives.showPostIts();
        summarizers = new CollisionDetector(codeCadet);

        do {
            while (gameIsPaused) {
                Thread.sleep(100);
            }

            Thread.sleep(50);

            summarizers.rainAll(score);

            if (randomizerTime) {
                randomizerAlert();
                //randomizer();
            }

            field.getCanvasElements();

        } while (lives.stillHaveLives());

        if (quitGame) {
            System.exit(0);
        }

        field.drawGameOver();

        // Reset the game to start menu
        codeCadet.resetPosition();

        score.resetScore();

        lives.resetNumOfLives();

        Thread.sleep(7000);

        field.clearField();

        gameHasStarted = false;

        init();

    }


    public void randomizerAlert() {
        if (randomizerTime && randomizerAlertCounter <= 110) {
            if (randomizerAlertCounter == 110) {
                field.deleteRandomizerAlert();
                randomizerAlertCounter = 0;
                //randomizerTime = false;
            } else if (randomizerAlertCounter > 100) {
                field.drawRandomizerAlert();
            } else if (randomizerAlertCounter > 90){
                field.deleteRandomizerAlert();
            } else if (randomizerAlertCounter > 80) {
                field.drawRandomizerAlert();
            } else if (randomizerAlertCounter > 70) {
                field.deleteRandomizerAlert();
            } else if (randomizerAlertCounter > 60) {
                field.drawRandomizerAlert();
            } else if (randomizerAlertCounter > 50) {
                field.deleteRandomizerAlert();
            } else if (randomizerAlertCounter > 40){
                field.drawRandomizerAlert();
            } else if (randomizerAlertCounter > 30) {
                field.deleteRandomizerAlert();
            } else if (randomizerAlertCounter > 20) {
                field.drawRandomizerAlert();
            } else if (randomizerAlertCounter > 10) {
                field.deleteRandomizerAlert();
            } else if (randomizerAlertCounter >= 0) {
                field.drawRandomizerAlert();
            }
            randomizerAlertCounter++;
        }
    }

    public void randomizer() {
        field.drawRandomizerAlert();
        field.drawRandomizerAlert();
        field.drawRandomizerAlert();

    }

}
