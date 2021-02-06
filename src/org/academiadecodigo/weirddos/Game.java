package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {


    private Controller controller;
    private CodeCadet codeCadet;
    private Picture menuBackground;
    private Picture gameBackground;
    private int highestScore;
    private AudioLibrary audioLibrary;
    private Points score;
    class


    // Constructor
    public Game() {
        highestScore = 0;
        codeCadet = new CodeCadet();
        score = new Points();
        controller = new Controller(codeCadet, this);
        controller.init();
        menuBackground = new Picture(0,0,"resources/cadetEscapeBackgroundMenu.png");
        gameBackground = new Picture(0,0, "resources/cadetEscapeBackgroundGame.png");
        //audioLibrary = new AudioLibrary("");
    }

    // Getters & Setters






    // Prompts main menu
    public void init() {
        menuBackground.draw();
    }

    // Starts playable game
    public void start() {
        gameBackground.draw();
        codeCadet.getPicture().draw();
        score.showScore();


    }




}
