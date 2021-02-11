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
        field = new Picture(0,0, "resources/field.png");
        menu = new Picture(0,0,"resources/cadetEscapeBackgroundMenu.png");
        game = new Picture(0,0, "resources/cadetEscapeBackgroundGame.png");
        randomizerAlert = new Picture(0,0,"resources/randomizerAlert.png");
        randomizer = new Picture(0,0,"resources/cadetEscapeBackgroundRandomizer.png");
        gameOver = new Picture(0,0,"resources/cadetEscapeBackgroundGameOver.png");
    }

    public void drawField() {
        field.draw();
    }

    public void drawMenu() {
        game.delete();
        gameOver.delete();
        menu.draw();
    }

    public void drawGame() {
        menu.delete();
        randomizer.delete();
        game.draw();
    }

    public void drawRandomizerAlert() {
        randomizerAlert.draw();
    }

    public void deleteRandomizerAlert() {
        randomizerAlert.delete();
    }

    public void drawRandomizer() {
        game.delete();
        randomizer.draw();
        getCanvasElements();
    }

    public void drawGameOver() {
        gameOver.draw();
        getCanvasElements();
    }


    public void clearField() {
        Shape[] shapes = Canvas.getInstance().getShapes().toArray(new Shape[0]);
        for (int i = 1; i < shapes.length; i++) {
            shapes[i].delete();
        }
        getCanvasElements();
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

