package go_game.Strategy;

import go_game.Direction;
import go_game.Game;
import go_game.Stone;

public interface GroupStrategy {
    void getAdjacentGroup(Game game, Stone stone, Direction direction);
}
