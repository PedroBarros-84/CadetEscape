package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CodeCadet {


    private final Picture picture;
    private final Lives lives;
    private final int STEP = 15;


    // Constructor
    public CodeCadet(Lives lives) {
        picture = new Picture(350, 340, "resources/codeCadet.png");
        this.lives = lives;
    }


    // Getters & Setters
    public Picture getPicture()    { return picture; }
    public void    looseLife()     { lives.looseLife(); }
    public void    resetPosition() { picture.translate(350 - picture.getX(), 0); }
    public int     getLives()      { return lives.getLives(); }


    // Cadet move STEP px at a time, has margin limit of 3px from border
    public void moveRight() { picture.translate(picture.getMaxX() + STEP <= 787 ? STEP : 787 - picture.getMaxX(), 0); }


    // Cadet move STEP px at a time, has margin limit of 3px from border
    public void moveLeft() {
        picture.translate(picture.getX() - STEP >= 3 ? -STEP : 3 - picture.getX(), 0);
    }

}
