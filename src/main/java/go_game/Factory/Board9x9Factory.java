package go_game.Factory;

import go_game.Game;

public class Board9x9Factory extends BoardFactory {
    @Override
    protected Board instantiateGame() {
        return new Board9x9();

    }
}
