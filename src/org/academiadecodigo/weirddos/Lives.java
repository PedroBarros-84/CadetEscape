package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Lives {

    private Picture[] lives;
    private int numOfLives;

    // Constructor
    public Lives() {
        lives = new Picture[5];
        lives[0] = new Picture(790 - 63, 560, "resources/heart.png");
        for (int i = 1; i < lives.length; i++) {
            lives[i] = new Picture(lives[i-1].getX() - lives[i-1].getWidth() - 10, 560, "resources/heart.png");
        }
        numOfLives = 5;
    }


    public void showPostIts() {
        for (Picture l : lives) {
            l.draw();
        }
    }


    public void looseLife() {
        if (numOfLives == 0) {
            System.out.println("gameover");
            return;
        }
        lives[--numOfLives].delete();

    }

}
