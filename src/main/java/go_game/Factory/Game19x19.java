package go_game.Factory;

import go_game.Game;

public class Game19x19 implements GameI {
    @Override
    public Game createGame() {
        return new Game(19);

    }
}
