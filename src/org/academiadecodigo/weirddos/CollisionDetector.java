package org.academiadecodigo.weirddos;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class CollisionDetector {

    private final Summarizer[] summarizersArray;
    private final CodeCadet codeCadet;
    private final AudioSample summarizerHitAudioFX;
    private final Game game;
    private int swooshIterator;
    private AudioSample[] swooshArray;
    private final int TOTAL_SUMMARIZERS = 12;
    private final int FLOOR_LEVEL = 615 - 73; // Background height minus status bar height


    // Constructor
    public CollisionDetector(Game game, CodeCadet codeCadet) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        summarizersArray = new Summarizer[TOTAL_SUMMARIZERS];
        for (int i = 0; i < summarizersArray.length; i++) {
            summarizersArray[i] = new Summarizer(5);
        }

        swooshArray = new AudioSample[3];
        swooshArray[0] = new AudioSample("resources/swoosh1.wav", false);
        swooshArray[1] = new AudioSample("resources/swoosh2.wav", false);
        swooshArray[2] = new AudioSample("resources/swoosh3.wav", false);

        swooshIterator = 0;
        summarizerHitAudioFX = new AudioSample("resources/summarizerHit.wav", false);
        this.game = game;
        this.codeCadet = codeCadet;

    }


    // Method checks if any summarizer hit the ground, if so, increases score
    public void rainAll(Score score) {
        for (Summarizer s : summarizersArray) {

            if (checkForCollision(s)) {

                if (codeCadet.getLives() > 1) {
                    summarizerHitAudioFX.stop();
                    summarizerHitAudioFX.play(game.getSoundON());
                }
                s.resetPosition();
                codeCadet.looseLife();

            } else if (s.getY() + s.getHeight() >= FLOOR_LEVEL) {

                swooshIterator = swooshIterator < 2 ? swooshIterator + 1 : 0;
                swooshArray[swooshIterator].stop();
                swooshArray[swooshIterator].play(game.getSoundON());

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

        // Define cadet head area
        int cadetTopHead = codeCadet.getPicture().getY();
        int cadetHeadRightSide = codeCadet.getPicture().getX() + 46;
        int cadetHeadLeftSide = codeCadet.getPicture().getX() + 23;

        // Define cadet torso area
        int cadetTopShoulder = codeCadet.getPicture().getY() + 33;
        int cadetShoulderRightSide = codeCadet.getPicture().getX() + codeCadet.getPicture().getWidth();
        int cadetShoulderLeftSide = codeCadet.getPicture().getX();

        // Define cadet leg area
        int cadetTopWaist = codeCadet.getPicture().getY() + 103;
        int cadetWaistRightSide = codeCadet.getPicture().getX() + 53;
        int cadetWaistLeftSide = codeCadet.getPicture().getX() + 16;

        // First area (Head)
        if ((s.leftVertex_Y() > cadetTopHead && s.leftVertex_Y() < cadetTopShoulder && s.leftVertex_X() > cadetHeadLeftSide && s.leftVertex_X() < cadetHeadRightSide) ||
                (s.rightVertex_Y() > cadetTopHead && s.rightVertex_Y() < cadetTopShoulder && s.rightVertex_X() > cadetHeadLeftSide && s.rightVertex_X() < cadetHeadRightSide) ||
                (s.bottomVertex_Y() > cadetTopHead && s.bottomVertex_Y() < cadetTopShoulder && s.bottomVertex_X() > cadetHeadLeftSide && s.bottomVertex_X() < cadetHeadRightSide)){
            return true;
        }

        // Second area (Torso)
        else if ((s.leftVertex_Y() > cadetTopShoulder && s.leftVertex_Y() < cadetTopWaist && s.leftVertex_X() > cadetShoulderLeftSide && s.leftVertex_X() < cadetShoulderRightSide) ||
                (s.rightVertex_Y() > cadetTopShoulder &&  s.rightVertex_Y() < cadetTopWaist && s.rightVertex_X() > cadetShoulderLeftSide && s.rightVertex_X() < cadetShoulderRightSide) ||
                (s.bottomVertex_Y() > cadetTopShoulder &&  s.bottomVertex_Y() < cadetTopWaist && s.bottomVertex_X() > cadetShoulderLeftSide && s.bottomVertex_X() < cadetShoulderRightSide)) {
            return true;
        }

        // Third area (Legs)
        else if ((s.leftVertex_Y() > cadetTopWaist && s.leftVertex_X() > cadetWaistLeftSide && s.leftVertex_X() < cadetWaistRightSide) ||
                (s.rightVertex_Y() > cadetTopWaist && s.rightVertex_X() > cadetWaistLeftSide && s.rightVertex_X() < cadetWaistRightSide) ||
                (s.bottomVertex_Y() > cadetTopWaist && s.bottomVertex_X() > cadetWaistLeftSide && s.bottomVertex_X() < cadetWaistRightSide)){
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
