package go_game.Factory;

import go_game.Game;

public class Game13x13 implements GameI {
    @Override
    public Game createGame() {
        return new Game(13);
    }
}
