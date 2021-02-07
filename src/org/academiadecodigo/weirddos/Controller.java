package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.keyboard.*;

public class Controller implements KeyboardHandler{

    private Keyboard keyboard;
    private CodeCadet codeCadet;
    private Game game;
    private Lives lives;


    // Constructor
    public Controller(CodeCadet codeCadet, Game game, Lives lives) {
        keyboard = new Keyboard(this);
        this.codeCadet = codeCadet;
        this.game = game;
        this.lives = lives;
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
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            try { if (!game.isPaused()) { codeCadet.moveRight(); } }
            catch(InterruptedException e) { e.printStackTrace(); }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
            try { if (!game.isPaused()) { codeCadet.moveLeft(); } }
            catch(InterruptedException e) { e.printStackTrace(); }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            game.setStart();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
            game.setRandomizerTime();  // <<<<--------------------------------------------
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_P) {
            game.setPause();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_Q) {
            game.setQuit();
            lives.setStillHaveLives(false);
        }

    }



    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

}
