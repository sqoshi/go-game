package go_game.Client.Factory;

import go_game.Client.Game;

public class Game19x19 implements GameI {
    @Override
    public Game createGame() {
        return new Game(19);

    }
}
