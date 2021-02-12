package org.academiadecodigo.weirddos;


public class Main {

    public static  void main(String[] args) {

        Game game = new Game();
        try { game.init(); }
        catch (InterruptedException e) { e.printStackTrace(); }

    }
}
