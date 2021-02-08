package org.academiadecodigo.weirddos;

import org.academiadecodigo.simplegraphics.graphics.Canvas;

public class CollisionDetector {

    private final Summarizer[] summarizersArray;
    private final CodeCadet codeCadet;
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


    // Method checks if any summarizer hit the ground, if so, increases score
    public void rainAll(Score score) {
        for (Summarizer s : summarizersArray) {

            if (checkForCollision(s)) {

                s.resetPosition();
                codeCadet.looseLife();

            } else if (s.getY() + s.getHeight() >= FLOOR_LEVEL) {

                s.resetPosition();
                score.upScore();
                score.updateScore();
                score.showScore();
            }

            s.rain();
            s.getPicture().delete();
            s.getPicture().draw();

        }
    }


    public boolean checkForCollision(Summarizer s) {

        int cadetRightSide = codeCadet.getPicture().getX() + codeCadet.getPicture().getWidth();
        int cadetLeftSide = codeCadet.getPicture().getX();
        int cadetTop_Y = codeCadet.getPicture().getY();

        // Compare each summarizer vertex with codeCadet position
        if ((s.leftVertex_Y() > cadetTop_Y && s.leftVertex_X() > cadetLeftSide && s.leftVertex_X() < cadetRightSide) ||
            (s.rightVertex_Y() > cadetTop_Y && s.rightVertex_X() > cadetLeftSide && s.rightVertex_X() < cadetRightSide) ||
            (s.bottomVertex_Y() > cadetTop_Y && s.bottomVertex_X() > cadetLeftSide && s.bottomVertex_X() < cadetRightSide)) {
            return true;
        }

        return false;
    }


    public void resetAllSummarizers() {
        for (Summarizer s : summarizersArray) {
                s.resetPosition();
        }
    }

}
