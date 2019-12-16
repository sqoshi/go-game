package go_game.Strategy;

import go_game.Game;
import go_game.PlayerColor;

public class SecondStrategy implements BotStrategy {
    @Override
    public void useStrategy(Game game) {
        if (game.isPositionAvaible(game.getGroupsBoard().length - 2, 1)
                && game.getConsoleBoard()[1][1] == 'W') {
            game.updateBoard(PlayerColor.WHITE, game.getGroupsBoard().length - 2, 1);
            return;
        }
    }
}
