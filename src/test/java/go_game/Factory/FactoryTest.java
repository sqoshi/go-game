package go_game.Factory;

import go_game.Game;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class FactoryTest {

    @Test
    void createGame9() {
        GameI gameI = GameFactory.getGame(9);
        Game game1 = gameI.createGame();
        Assert.assertTrue(game1 instanceof Game);
    }

    @Test
    void createGame19() {
        GameI gameI = GameFactory.getGame(19);
        Game game1 = gameI.createGame();
        Assert.assertTrue(game1 instanceof Game);
    }

    @Test
    void createGame13() {
        GameI gameI = GameFactory.getGame(13);
        Game game1 = gameI.createGame();
        Assert.assertTrue(game1 instanceof Game);
    }
}
