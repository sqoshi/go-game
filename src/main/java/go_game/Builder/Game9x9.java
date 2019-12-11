package go_game.Builder;

import go_game.Game;

public class Game9x9 implements GameI {
    @Override
    public Game createGame() {
        return new Game(9);
    }
}


