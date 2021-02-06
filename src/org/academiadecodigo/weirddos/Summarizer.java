package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.*;

public class Summarizer {

    private Picture summarizer;
    private int speed;

    // Constructor
    public Summarizer() {
        summarizer = new Picture(Math.random() * (790 - 72), (Math.random() * (-1000)) - 67, "resources/summarizer.png");
        speed = 1;
    }

    // Getters & Setters
    public int getX() { return summarizer.getX(); }
    public int getY() { return summarizer.getY(); }


    // Summarizer will fall at constant speed until ground
    public void rain() {
        summarizer.translate(0, speed);
        //Thread.sleep(100);
    }

    // Brings summarizer to random position above the canvas after hitting ground
    public void resetPosition() {
        speed += 1;
        summarizer.translate(Math.random() * (790 - 72), (Math.random() * (-1000)) - 67);
    }

}
