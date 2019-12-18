package go_game.Client.Factory;

import go_game.Client.Game;

public class Game13x13 implements GameI {
    @Override
    public Game createGame() {
        return new Game(13);
    }
}
