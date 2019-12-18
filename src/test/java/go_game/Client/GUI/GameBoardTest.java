package go_game.Client.GUI;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    GameBoard gameBoard = new GameBoard();

    @Test
    void setDimension() {

    }

    @Test
    void createLayout() {
    }

    @Test
    void setColor() {
        ;
    }

    @Test
    void getDimension() {
        Assert.assertTrue(gameBoard.getDimension()==9);
    }
}