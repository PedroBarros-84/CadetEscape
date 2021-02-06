package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CodeCadet {

    private Picture picture;

    // Constructor
    public CodeCadet() {
        picture = new Picture(350, 300, "resources/codeCadet.png");
    }

    // Getters & Setters
    public Picture getPicture() {
        return picture;
    }


    public void moveLeft()  { picture.translate(20,0);  }
    public void moveRight() { picture.translate(-20,0); }

}
