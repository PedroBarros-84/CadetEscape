package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.pictures.*;

public class Field {

    private final Picture field;
    private final Picture menu;
    private final Picture game;
    private final Picture randomizerAlert;
    private final Picture randomizer;
    private final Picture gameOver;

    // Constructor
    public Field() {
        field = new Picture(0,0, "/image/field.png");
        menu = new Picture(0,0, "/image/menuBackground.png");
        game = new Picture(0,0, "/image/gameBackground.png");
        randomizerAlert = new Picture(0,0, "/image/randomizerAlert.png");
        randomizer = new Picture(0,0, "/image/randomizerBackground.png");
        gameOver = new Picture(0,0, "/image/gameOverBackground.png");
    }

    public void drawField() {
        clearField();
        field.draw();
    }

    public void drawMenu() {
        clearField();
        menu.draw();
    }

    public void drawGame() {
        clearField();
        game.draw();
    }

    public void drawRandomizerAlert() {
        randomizerAlert.draw();
    }

    public void deleteRandomizerAlert() {
        randomizerAlert.delete();
    }

    public void drawRandomizer() {
        clearField();
        randomizer.draw();
    }

    public void drawGameOver() {
        clearField();
        gameOver.draw();
    }

    public void clearField() {
        Shape[] shapes = Canvas.getInstance().getShapes().toArray(new Shape[0]);
        for (int i = 1; i < shapes.length; i++) {
            shapes[i].delete();
        }
    }

    public void getCanvasElements() {
        Shape[] shapes = Canvas.getInstance().getShapes().toArray(new Shape[0]);
        String shapesList = "";
        for (int i = 0; i < shapes.length; i++) {
            shapesList += shapes[i].toString() + "\n";
        }
        System.out.println(shapesList);
    }

}

