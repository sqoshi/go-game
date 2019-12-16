package go_game.Strategy;

import go_game.Game;
import go_game.PlayerColor;

public class FirstStrategy implements BotStrategy {
    @Override
    public void useStrategy(Game game) {
        if (game.isPositionAvaible(1, 1)) {
            game.updateBoard(PlayerColor.WHITE, 1, 1);
            return;
        }
    }
}
