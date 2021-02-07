package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Score {

    private int score;
    private Picture[] scoreArray;

    // Constructor
    public Score() {
        score = 0;
        scoreArray = new Picture[6];
        scoreArray[0] = new Picture(20, 560,"resources/nums/0.png");
        for (int i = 1; i < scoreArray.length; i++) {
            scoreArray[i] = new Picture(scoreArray[i-1].getX() + scoreArray[i-1].getWidth() + 10, 560,"resources/nums/0.png");
        }
    }

    // Getters & Setters
    public int getScore() { return score; }
    public void upScore() { score += 10; }
    public void resetScore() { score = 0; resetScoreDisplay(); }


    public void resetScoreDisplay() {
        for (int i = 0; i < scoreArray.length; i++) {
            scoreArray[i] = new Picture(scoreArray[i].getX(), scoreArray[i].getY(), "resources/nums/0.png");
        }
    }


    public void showScore() {
        for (Picture picture : scoreArray) {
            picture.draw();
        }
    }

    public void hideScore() {
        for (Picture picture : scoreArray) {
            picture.delete();
        }
    }

    public void updateScore() {
        String scoreToString = score + "";
        for (int i = scoreToString.length() - 1, j = scoreArray.length - 1; i >= 0; i--, j--) {

            int previousX = scoreArray[j].getX();
            int previousY = scoreArray[j].getY();

            scoreArray[j].delete();
            scoreArray[j] = null;

            switch (scoreToString.charAt(i)) {
                case '0':
                    scoreArray[j] = new Picture(previousX, previousY, "resources/nums/0.png");
                    break;
                case '1':
                    scoreArray[j] = new Picture(previousX, previousY, "resources/nums/1.png");
                    break;
                case '2':
                    scoreArray[j] = new Picture(previousX, previousY, "resources/nums/2.png");
                    break;
                case '3':
                    scoreArray[j] = new Picture(previousX, previousY, "resources/nums/3.png");
                    break;
                case '4':
                    scoreArray[j] = new Picture(previousX, previousY, "resources/nums/4.png");
                    break;
                case '5':
                    scoreArray[j] = new Picture(previousX, previousY, "resources/nums/5.png");
                    break;
                case '6':
                    scoreArray[j] = new Picture(previousX, previousY, "resources/nums/6.png");
                    break;
                case '7':
                    scoreArray[j] = new Picture(previousX, previousY, "resources/nums/7.png");
                    break;
                case '8':
                    scoreArray[j] = new Picture(previousX, previousY, "resources/nums/8.png");
                    break;
                case '9':
                    scoreArray[j] = new Picture(previousX, previousY, "resources/nums/9.png");
            }
        }
    }


}