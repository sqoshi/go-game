package go_game.Strategy;

import go_game.Direction;
import go_game.Stone;

public interface GroupStrategy {
    void getAdjacentGroup(Stone stone, Direction direction);
}
