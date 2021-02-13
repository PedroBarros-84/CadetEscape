package org.academiadecodigo.weirddos.Audio;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class AudioLibrary {

    private final AudioSample menuBackgroundMusic;
    private final AudioSample gameBackgroundMusic;
    private final AudioSample randomizerAlarm;
    private final AudioSample randomizerBackgroundMusic;
    private final AudioSample gameOverTransition;
    private final AudioSample gameOverOhOh;
    private final AudioSample transitionSoundFX;
    private final AudioSample summarizerHitFX;
    private final AudioSample[] swooshArray;
    private int swooshIterator;
    private boolean soundON;

    public AudioLibrary() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        menuBackgroundMusic = new AudioSample("/audio/MacQuayle_FuckSociety.wav", true);
        gameBackgroundMusic = new AudioSample("/audio/JohnWilliams_BattleOfTheResistance.wav", true);
        randomizerAlarm = new AudioSample("/audio/randomizerAlert.wav", false);
        randomizerBackgroundMusic = new AudioSample("/audio/randomizerMusic.wav", false);
        gameOverTransition = new AudioSample("/audio/gameOverTransition.wav", false);
        gameOverOhOh = new AudioSample("/audio/gameOverOhOh.wav", false);
        transitionSoundFX = new AudioSample("/audio/transitionFX.wav", false);
        summarizerHitFX = new AudioSample("/audio/summarizerHitFX.wav", false);
        swooshArray = new AudioSample[3];
        swooshArray[0] = new AudioSample("/audio/swooshFX_1.wav", false);
        swooshArray[1] = new AudioSample("/audio/swooshFX_2.wav", false);
        swooshArray[2] = new AudioSample("/audio/swooshFX_3.wav", false);
        swooshIterator = 0;
        soundON = true;
    }

    public void toggleSound()   { soundON = !soundON; }

    public void play(Sample sample) {
        switch (sample) {
            case MENU_MUSIC:
                menuBackgroundMusic.play(soundON);
                break;
            case GAME_MUSIC:
                gameBackgroundMusic.play(soundON);
                break;
            case RANDOMIZER_ALARM:
                randomizerAlarm.play(soundON);
                break;
            case RANDOMIZER_MUSIC:
                randomizerBackgroundMusic.play(soundON);
                break;
            case GAMEOVER_TRANSITION:
                gameOverTransition.play(soundON);
                break;
            case GAMEOVER_OHOH:
                gameOverOhOh.play(soundON);
                break;
            case GAME_MODE_TRANSITION:
                transitionSoundFX.play(soundON);
                break;
            case SUMMARIZER_HIT:
                summarizerHitFX.play(soundON);
                break;
        }
    }

    public void pause(Sample sample) {
        switch (sample) {
            case MENU_MUSIC:
                menuBackgroundMusic.pause();
                break;
            case GAME_MUSIC:
                gameBackgroundMusic.pause();
                break;
            case RANDOMIZER_ALARM:
                randomizerAlarm.pause();
                break;
            case RANDOMIZER_MUSIC:
                randomizerBackgroundMusic.pause();
                break;
            case GAMEOVER_TRANSITION:
                gameOverTransition.pause();
                break;
            case GAMEOVER_OHOH:
                gameOverOhOh.pause();
                break;
        }
    }

    public void stop(Sample sample) {
        switch (sample) {
            case MENU_MUSIC:
                menuBackgroundMusic.stop();
                break;
            case GAME_MUSIC:
                gameBackgroundMusic.stop();
                break;
            case RANDOMIZER_ALARM:
                randomizerAlarm.stop();
                break;
            case RANDOMIZER_MUSIC:
                randomizerBackgroundMusic.stop();
                break;
            case GAMEOVER_TRANSITION:
                gameOverTransition.stop();
                break;
            case GAMEOVER_OHOH:
                gameOverOhOh.stop();
                break;
            case GAME_MODE_TRANSITION:
                transitionSoundFX.stop();
                break;
            case SUMMARIZER_HIT:
                summarizerHitFX.stop();
                break;
        }
    }

    public void replay(Sample sample) {
        stop(sample);
        play(sample);
    }

    public void pauseGameAudio() {
        gameBackgroundMusic.pause();
        randomizerAlarm.pause();
        randomizerBackgroundMusic.pause();
    }

    public void endOfRandomizerAlert() {
        randomizerAlarm.stop();
        gameBackgroundMusic.pause();
        randomizerBackgroundMusic.play(soundON);
    }

    public void endOfRandomizer() {
        randomizerBackgroundMusic.stop();
        transitionSoundFX.stop();
        transitionSoundFX.play(soundON);
        gameBackgroundMusic.play(soundON);
    }

    public void endOfGame() {
        gameBackgroundMusic.stop();
        randomizerAlarm.stop();
        randomizerBackgroundMusic.stop();
        menuBackgroundMusic.stop();
        gameOverTransition.stop();
        gameOverTransition.play(soundON);
        try { Thread.sleep(2000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        gameOverOhOh.stop();
        gameOverOhOh.play(soundON);
    }

    public void swoosh() {
        swooshIterator = swooshIterator < 2 ? swooshIterator + 1 : 0;
        swooshArray[swooshIterator].stop();
        swooshArray[swooshIterator].play(soundON);
    }

    public void setSoundOFF() {
        menuBackgroundMusic.pause();
        gameBackgroundMusic.pause();
        randomizerAlarm.pause();
        randomizerBackgroundMusic.pause();
    }


}
