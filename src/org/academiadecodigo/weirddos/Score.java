package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Score {

    private int score;
    private final Picture[][] scoreDisplayDigits;
    private final Picture[] display;
    private final int SCORE_INCREMENT = 25;
    private final int Y_COORDINATE = 560; // All score numbers will appear at status bar height


    // Constructor
    public Score() {
        score = 0;
        scoreDisplayDigits = new Picture[6][10]; // 6 place values with 10 digits each
        display = new Picture[6]; // actual display that holds current score
        int xCoordinateStart = 15;
        populateScoreDisplayDigits(xCoordinateStart);
        resetScoreDisplay();
    }

    // Getters & Setters
    public void upScore()    { score += SCORE_INCREMENT; }
    public void resetScore() { score = 0; resetScoreDisplay(); }

    public void populateScoreDisplayDigits(int xCoordinate) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                scoreDisplayDigits[i][j] = new Picture(xCoordinate, Y_COORDINATE, "/image/nums/" + j + ".png");
            }
            xCoordinate = scoreDisplayDigits[i][0].getMaxX();
        }
    }

    // Sets display to 000000
    public void resetScoreDisplay() {
        for (int i = 0; i < display.length; i++) {
            display[i] = scoreDisplayDigits[i][0];
        }
    }

    public void showScore() {
        for (Picture picture : display) {
            picture.draw();
        }
    }

    public void updateScore() {
        String scoreToString = score + "";

        for (int i = scoreToString.length() - 1, j = display.length - 1; i >= 0; i--, j--) {
            display[j].delete();
            int a = Character.getNumericValue(scoreToString.charAt(i));
            display[j] = scoreDisplayDigits[j][a];
        }
    }

    public void showScoreGameOver() {
        for (Picture pic : display) {
            pic.delete();
            pic.translate(85, -180);
            pic.draw();
        }
    }

    public void hideScoreGameOver() {
        for (Picture pic : display) {
            pic.delete();
            pic.translate(-85, 180);
        }
    }

}
