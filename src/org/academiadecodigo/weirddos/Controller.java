package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.keyboard.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Controller implements KeyboardHandler{

    private Keyboard keyboard;
    private CodeCadet codeCadet;
    private Game game;


    // Constructor
    public Controller(CodeCadet codeCadet, Game game) {
        keyboard = new Keyboard(this);
        this.codeCadet = codeCadet;
        this.game = game;
    }


    public void init() {
        KeyboardEvent startGame = new KeyboardEvent();
        startGame.setKey(KeyboardEvent.KEY_SPACE);
        startGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(startGame);

        KeyboardEvent moveRIGHT = new KeyboardEvent();
        moveRIGHT.setKey(KeyboardEvent.KEY_RIGHT);
        moveRIGHT.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveRIGHT);

        KeyboardEvent moveLEFT = new KeyboardEvent();
        moveLEFT.setKey(KeyboardEvent.KEY_LEFT);
        moveLEFT.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveLEFT);

        KeyboardEvent pauseGame = new KeyboardEvent();
        pauseGame.setKey(KeyboardEvent.KEY_P);
        pauseGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(pauseGame);

        KeyboardEvent toggleAudioOnOff = new KeyboardEvent();
        toggleAudioOnOff.setKey(KeyboardEvent.KEY_S);
        toggleAudioOnOff.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(toggleAudioOnOff);

        KeyboardEvent quitGame = new KeyboardEvent();
        quitGame.setKey(KeyboardEvent.KEY_Q);
        quitGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(quitGame);
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) { codeCadet.moveLeft();  }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT)  { codeCadet.moveRight(); }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) { game.start();          }
    }



    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

}
