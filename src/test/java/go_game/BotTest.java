package go_game;

import go_game.Client.Factory.GameFactory;
import go_game.Client.Factory.GameI;
import go_game.Client.Game;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static go_game.Client.PlayerColor.BLACK;

class BotTest {
    GameI gameI = GameFactory.getGame(9);
    Game game1 = gameI.createGame();

    @Test
    void getPlayerColor() {
    }

    @Test
    void setPlayerColor() {
    }

    @Test
    void move() {
        game1.addBot();
        game1.updateBoard(BLACK, 0, 0);
        game1.updateBoard(BLACK, 5, 5);
        game1.updateBoard(BLACK, 5, 4);
        game1.getBot().move(0, 0);
        game1.getBot().move(0, 0);
        Assert.assertNull(game1.board[0][0]);
    }
}