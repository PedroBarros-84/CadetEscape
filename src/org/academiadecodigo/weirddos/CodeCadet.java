package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CodeCadet {

    private Picture picture;

    // Constructor
    public CodeCadet() {
        picture = new Picture(350, 325, "resources/codeCadet.png");
    }

    // Getters & Setters
    public Picture getPicture() {
        return picture;
    }


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
