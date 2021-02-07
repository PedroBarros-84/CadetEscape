package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CodeCadet {

    private Picture picture;
    private Lives lives;

    // Constructor
    public CodeCadet(Lives lives) {
        picture = new Picture(350, 330, "resources/codeCadet.png");
        this.lives = lives;
    }

    // Getters & Setters
    public Picture getPicture() { return picture;    }
    public void    looseLife()  { lives.looseLife(); }


    public void moveRight() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            if (picture.getX() + picture.getWidth() <= 787) {
                Thread.sleep(3);
                picture.translate(3, 0);
            }
        }
    }

    public void moveLeft() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            if (picture.getX() >= 3) {
                Thread.sleep(3);
                picture.translate(-3, 0);
            }
        }
    }

}
