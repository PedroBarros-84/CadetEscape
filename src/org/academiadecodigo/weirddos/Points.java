package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Points {

    private int score;
    private Picture[] scoreArray;

    // Constructor
    public Points() {
        score = 0;
        scoreArray = new Picture[6];
        scoreArray[0] = new Picture(20, 560,"resources/nums/0.png");
        for (int i = 1; i < scoreArray.length; i++) {
            scoreArray[i] = new Picture(scoreArray[i-1].getX() + scoreArray[i-1].getWidth() + 10, 560,"resources/nums/0.png");
        }
    }

    // Getters & Setters
    public void setScore() { score += 0; }

    public void showScore() {
        //System.out.println(score);
        for (Picture picture : scoreArray) {
            picture.draw();
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
                case '0' -> scoreArray[j] = new Picture(previousX, previousY, "resources/nums/0.png");
                case '1' -> scoreArray[j] = new Picture(previousX, previousY, "resources/nums/1.png");
                case '2' -> scoreArray[j] = new Picture(previousX, previousY, "resources/nums/2.png");
                case '3' -> scoreArray[j] = new Picture(previousX, previousY, "resources/nums/3.png");
                case '4' -> scoreArray[j] = new Picture(previousX, previousY, "resources/nums/4.png");
                case '5' -> scoreArray[j] = new Picture(previousX, previousY, "resources/nums/5.png");
                case '6' -> scoreArray[j] = new Picture(previousX, previousY, "resources/nums/6.png");
                case '7' -> scoreArray[j] = new Picture(previousX, previousY, "resources/nums/7.png");
                case '8' -> scoreArray[j] = new Picture(previousX, previousY, "resources/nums/8.png");
                case '9' -> scoreArray[j] = new Picture(previousX, previousY, "resources/nums/9.png");
            }
        }
    }


}