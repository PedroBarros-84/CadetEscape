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
    private boolean randomizerAlertTime;
    private boolean randomizerTime;
    private int randomizerAlertCounter;
    private int randomizerCounter;
    private int delay;
    private int gameCycleCounter;


    // Constructor
    public Game() {

        lives = new Lives();
        codeCadet = new CodeCadet(lives);
        field = new Field();
        field.drawField();
        score = new Score();
        summarizers = new CollisionDetector(codeCadet);
        controller = new Controller(codeCadet, this, lives);
        controller.init();

        delay = 50;
        gameCycleCounter = 1;
        randomizerAlertCounter = 0;
        randomizerCounter = 0;
        gameHasStarted = false;
        randomizerAlertTime = false;
        randomizerTime = false;
        gameIsPaused = false;

    }


    // Getters & Setters
    public boolean isPaused() { return gameIsPaused; }
    public void setStart()  { gameHasStarted = true; }
    public void setPause()  { gameIsPaused = !gameIsPaused; }
    public boolean getRandomizerTime() { return randomizerTime; }
    public void setRandomizerAlertTime() {randomizerAlertTime = !randomizerAlertTime; }


    // Prompts main menu
    public void init() throws InterruptedException {

        // Print main menu
        field.drawMenu();

        // Wait for user to press SPACE key
        while(!gameHasStarted) {
            Thread.sleep(100);
        }

        // Begin game
        start();

    }


    // Start game
    public void start() throws InterruptedException {

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

            // Every 250 cycles, summarizers rain faster
            if (gameCycleCounter % 200 == 0 && delay > 25) { delay--; }

            // Create 0.2% chance for randomizer during standard game mode
            if (!randomizerAlertTime && !randomizerTime) {
                double chance = Math.random() * 100;
                if (chance < 0.2) { randomizerAlertTime = true; }
            }

            // Prompt blinking alert before randomizer
            if (randomizerAlertTime) { randomizerAlert(); }

            // Switch to randomizer mode
            if (randomizerTime) { randomizer(); }

            //System.out.println(gameCycleCounter + "   " + delay);

            // Move all summarizer down once
            summarizers.rainAll(score);

            gameCycleCounter++;
            //field.getCanvasElements();

        } while (lives.stillHaveLives());

        gameOver();
    }


    public void gameOver() throws InterruptedException {

        // After game ends show game over
        field.drawGameOver();

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
    public void randomizerAlert() {
        if (randomizerAlertTime && randomizerAlertCounter <= 110) {
            if (randomizerAlertCounter == 110) {

                // Reset randomizer alert
                field.deleteRandomizerAlert();
                randomizerAlertCounter = 0;
                randomizerAlertTime = false;
                randomizerTime = true;

                // Prepare randomizer
                field.clearField();
                field.drawRandomizer();
                lives.showLivesRemaining();
                score.showScore();
                codeCadet.getPicture().draw();

            } else if ((randomizerAlertCounter / 10) % 2 == 0) {
                field.drawRandomizerAlert();
            } else if ((randomizerAlertCounter / 10) % 2 != 0) {
                field.deleteRandomizerAlert();
            }

            randomizerAlertCounter++;
        }
    }

    // Check if randomizer should be over and finish it
    public void randomizer() {

        randomizerCounter++;

        // After 250 animation cycles, revert to standard game mode
        if (randomizerCounter > 250) {
            field.clearField();
            field.drawGame();
            lives.showLivesRemaining();
            score.showScore();
            codeCadet.getPicture().draw();
            randomizerCounter = 0;
            randomizerTime = false;
        }
    }

}
