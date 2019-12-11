package go_game.Factory;

import go_game.Game;

public class Board9x9 extends AbstractGame{
    @Override
    public void generateGame(int data) {
        setGameContent(9);
        new Game(9);
    }
}
