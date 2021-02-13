package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.*;

public class Summarizer {

    private Picture summarizer;
    private int speed;


    // Constructor
    public Summarizer(int speed) {
        summarizer = new Picture((int)(Math.random() * (790 - 60)), ((int)(Math.random() * (-1000)) - 55), "/image/summarizer.png");
        this.speed = speed;
    }


    // Getters & Setters
    public int     getY()       { return summarizer.getY(); }
    public int     getHeight()  { return summarizer.getHeight(); }
    public Picture getPicture() { return summarizer; }


    // Summarizer will fall at constant speed until ground
    public void rain() {
        summarizer.translate(0, speed);
    }


    // Brings summarizer to random position above the canvas after hitting ground
    public void resetPosition() {
        int randomX = (int)(Math.random() * (790 - 60));
        int randomY = (int)((Math.random() * (-1000)) - getPicture().getHeight() - getPicture().getY());
        summarizer.translate(randomX - summarizer.getX(), randomY);
    }


    // Getters for X and Y coordinates right Vertex
    public int rightVertex_X() { return summarizer.getX() + summarizer.getWidth(); }
    public int rightVertex_Y() { return summarizer.getY() + 33; }


    // Getters for X and Y coordinates left Vertex
    public int leftVertex_X() { return summarizer.getX(); }
    public int leftVertex_Y() { return summarizer.getY() + 19; }


    // Getters for X and Y coordinates bottom Vertex
    public int bottomVertex_X() { return summarizer.getX() + 27; }
    public int bottomVertex_Y() { return summarizer.getY() + summarizer.getHeight(); }
}
