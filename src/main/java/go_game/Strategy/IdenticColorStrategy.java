package go_game.Strategy;

import go_game.Direction;
import go_game.Game;
import go_game.Stone;


public class IdenticColorStrategy implements GroupStrategy {
    @Override
    public void getAdjacentGroup(Game game, Stone stone, Direction direction) {
        int adjacentGroup = -1024;
        if (direction == Direction.UP) {
            if (game.isLineValid(stone.getX(), direction)
                    && !game.isPositionAvaible(stone.getX() - 1, stone.getY())
                    && stone.getColor() == game.board[stone.getX() - 1][stone.getY()].getColor()) {
                adjacentGroup = game.board[stone.getX() - 1][stone.getY()].getGroup();
            } else {
                adjacentGroup = -1;
            }
        } else if (direction == Direction.DOWN) {
            if (game.isLineValid(stone.getX(), direction)
                    && !game.isPositionAvaible(stone.getX() + 1, stone.getY())
                    && stone.getColor() == game.board[stone.getX() + 1][stone.getY()].getColor()) {
                adjacentGroup = game.board[stone.getX() + 1][stone.getY()].getGroup();
            } else {
                adjacentGroup = -1;
            }
        } else if (direction == Direction.RIGHT) {
            if (game.isLineValid(stone.getY(), direction)
                    && !game.isPositionAvaible(stone.getX(), stone.getY() + 1)
                    && (stone.getColor() == game.board[stone.getX()][stone.getY() + 1].getColor()))
                adjacentGroup = game.board[stone.getX()][stone.getY() + 1].getGroup();
            else
                adjacentGroup = -1;
        } else if (direction == Direction.LEFT) {
            if (game.isLineValid(stone.getY(), direction)
                    && !game.isPositionAvaible(stone.getX(), stone.getY() - 1)
                    && (stone.getColor() == game.board[stone.getX()][stone.getY() - 1].getColor()))
                adjacentGroup = game.board[stone.getX()][stone.getY() - 1].getGroup();
            else
                adjacentGroup = -1;
        } else
            adjacentGroup = -1;
    }

}
