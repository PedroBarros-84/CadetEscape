package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.graphics.Canvas;

public class CollisionDetector {

    private Summarizer[] summarizersArray;
    private CodeCadet codeCadet;
    private final int TOTAL_SUMMARIZERS = 10;
    private final int FLOOR_LEVEL = 615 - 73; // Background height minus status bar height



    // Constructor
    public CollisionDetector(CodeCadet codeCadet) {
        summarizersArray = new Summarizer[TOTAL_SUMMARIZERS];
        for (int i = 0; i < summarizersArray.length; i++) {
            summarizersArray[i] = new Summarizer(5);
        }
        this.codeCadet = codeCadet;
    }

    // Getters & Setters




    // Method checks if any summarizer hit the ground, if so, increases score
    public void rainAll(Score score){
        for (Summarizer s : summarizersArray) {
            if (s.getY() + s.getHeight() >= FLOOR_LEVEL) {
                s.resetPosition();
                score.setScore();
                score.updateScore();
                score.showScore();
            }
            s.rain();
            s.getPicture().delete();
            s.getPicture().draw();
        }
    }


    public void checkForCollision() {

    }




}
