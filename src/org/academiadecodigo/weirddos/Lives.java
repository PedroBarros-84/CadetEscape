package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Lives {

    private Picture[] lives;
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
    public void setStillHaveLives(Boolean bool) { stillHaveLives = bool; }
    public void resetNumOfLives() { numOfLives = 5; stillHaveLives = true; };


    public void showPostIts() {

        for (Picture l : lives) {
            l.draw();
        }

    }

    public void hidePostIts() {

        for (Picture l : lives) {
            l.delete();
        }

    }


    public void looseLife() {

        if (numOfLives == 1) {
            stillHaveLives = false;
            return;
        }

        lives[--numOfLives].delete();

    }

}
