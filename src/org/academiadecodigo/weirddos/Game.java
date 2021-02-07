package org.academiadecodigo.weirddos;


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
        summarizers = new CollisionDetector(codeCadet);

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
        controller.init();
        while(!gameHasStarted) {
        }
        start();
    }


    // Starts playable game
    public void start() throws InterruptedException {

        field.drawGame();
        codeCadet.getPicture().draw();
        score.showScore();
        lives.showPostIts();

        do {
            while (gameIsPaused) {
                Thread.sleep(100);
            }

            Thread.sleep(50);

            summarizers.rainAll(score);

            //field.getCanvasElements();

        } while (!quitGame);

        System.exit(0);

    }

}
