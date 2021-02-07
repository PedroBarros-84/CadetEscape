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


    // Constructor
    public Game() {

        lives = new Lives();
        codeCadet = new CodeCadet(lives);
        field = new Field();
        field.drawField();
        score = new Score();
        controller = new Controller(codeCadet, this);
        controller.init();

        gameHasStarted = false;
        gameIsPaused = false;
        quitGame = false;

    }

    // Getters & Setters
    public boolean isPaused() { return gameIsPaused; }
    public void setStart()  { gameHasStarted = true; }
    public void setPause()  { gameIsPaused = !gameIsPaused; }
    public void setQuit()   { quitGame = true; }



    // Prompts main menu
    public void init() throws InterruptedException {

        field.drawMenu();

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

            //field.getCanvasElements();

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

}
