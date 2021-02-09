package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Lives {

    private final Picture[] lives;
    private int numOfLives;
    private boolean stillHaveLives;


    // Constructor
    public Lives() {

        lives = new Picture[5];
        lives[0] = new Picture(790 - 63, 560, "resources/heart.png");
        for (int i = 1; i < lives.length; i++) {
            lives[i] = new Picture(lives[i-1].getX() - lives[i-1].getWidth() - 10, 560, "resources/heart.png");
        }

        numOfLives = 5;
        stillHaveLives = true;
    }


    // Getters & Setters
    public boolean stillHaveLives() { return stillHaveLives; }
    public void resetNumOfLives() { numOfLives = 5; stillHaveLives = true; };
    public int getLives() { return numOfLives; }


    public void showAllPostIts() {

        for (Picture l : lives) {
            l.draw();
        }

    }


    public void showLivesRemaining() {

        for (int i = 0; i < numOfLives; i++) {
            lives[i].draw();
        }

    }


    public void looseLife() {

        //game.setRandomizerAlertTime();

        if (numOfLives == 1) {
            stillHaveLives = false;
            return;
        }

        lives[--numOfLives].delete();

    }

}
