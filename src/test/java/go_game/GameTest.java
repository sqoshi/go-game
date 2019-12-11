package go_game;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static go_game.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game = new Game(9);

    @Test
    void updateBoard() {
        game.updateBoard(PlayerColor.BLACK, 4, 4);
        Assert.assertEquals(game.board[4][4].getX(), 4);
        Assert.assertEquals(game.board[4][4].getY(), 4);
        Assert.assertEquals(game.board[4][4].getColor(), PlayerColor.BLACK);
        Assert.assertEquals(game.board[4][4].getGroup(), 1);


    }

    @Test
    void pass() {
    }

    @Test
    void changePlayer() {
    }

    @Test
    void isPositionAvaible() {
        Assert.assertTrue(game.isPositionAvaible(4, 4));
        Assert.assertNotEquals(false, game.isPositionAvaible(4, 4));
        game.updateBoard(PlayerColor.BLACK, 4, 4);
        Assert.assertNotEquals(true, game.isPositionAvaible(4, 4));
    }

    @Test
    void isInsideBoard() {
        Assert.assertTrue(game.isInsideBoard(4, 4));
        Assert.assertFalse(game.isInsideBoard(224, 412));
        Assert.assertNotEquals(true, game.isInsideBoard(224, 412));
        Assert.assertNotEquals(false, game.isInsideBoard(4, 4));

    }

    @Test
    void isLineValid() {
        Assert.assertTrue(game.isLineValid(9, UP));
        Assert.assertFalse(game.isLineValid(9, DOWN));
        Assert.assertTrue(game.isLineValid(0, DOWN));
        Assert.assertFalse(game.isLineValid(0, UP));

        Assert.assertFalse(game.isLineValid(9, RIGHT));
        Assert.assertTrue(game.isLineValid(9, LEFT));
        Assert.assertFalse(game.isLineValid(0, LEFT));
        Assert.assertTrue(game.isLineValid(0, RIGHT));


    }

    @Test
    public void testKillGroup() {
        game.updateBoard(PlayerColor.BLACK, 1, 2);
        game.updateBoard(PlayerColor.BLACK, 1, 1);
        game.updateBoard(PlayerColor.WHITE, 0, 2);
        game.updateBoard(PlayerColor.WHITE, 2, 2);
        game.updateBoard(PlayerColor.WHITE, 1, 3);
        game.updateBoard(PlayerColor.WHITE, 1, 0);
        game.updateBoard(PlayerColor.WHITE, 0, 1);
        game.updateBoard(PlayerColor.WHITE, 2, 1);
        String expectedValue = "sth was taken to jail";
        Assert.assertEquals(0,game.getGroupsBoard()[1][1]);
    }
}