package go_game;


import java.util.Arrays;
import java.util.Collections;

import static go_game.PlayerColor.BLACK;
import static go_game.PlayerColor.WHITE;

public class Bot {
    Game game;
    int lastGroup;
    private PlayerColor playerColor;
    int[] enemyGroups;
    int[] enemyGroupsBreaths;

    public Bot(Game game) {

        this.game = game;
        setPlayerColor(WHITE);
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public void move(int Bx, int By) {
        int newX = 5, newY = 5;
        int[][] positionsToFill = new int[game.dismension * game.dismension][2];
        int minBreathGroupIndex = -1;
        enemyGroups = new int[game.dismension * game.dismension];
        enemyGroupsBreaths = new int[game.dismension * game.dismension];
        boolean[] was = new boolean[game.dismension * game.dismension];
        int m = 0;
        lastGroup = game.getGroupsBoard()[Bx][By];
        for (int i = 0; i < game.board.length; i++)
            for (int j = 0; j < game.board.length; j++)
                if (game.getConsoleBoard()[i][j] == 'B' && !was[game.getGroupsBoard()[i][j]]) {
                    enemyGroups[m] = game.getGroupsBoard()[i][j];
                    was[game.getGroupsBoard()[i][j]] = true;
                    m++;
                }
        for (int i = 0; enemyGroups[i] != 0; i++) {
            enemyGroupsBreaths[i] = game.findGroupBreaths(enemyGroups[i]);
            System.out.println("EG: " + enemyGroups[i] + ", B: " + game.findGroupBreaths(enemyGroups[i]));
        }
        int min = Arrays.stream(enemyGroupsBreaths).filter(i -> i > 0).min().orElse(0);
        for (int i = 0; enemyGroups[i] != 0; i++) {
            if (enemyGroupsBreaths[i] == min) {
                minBreathGroupIndex = enemyGroups[i];
            }
        }
        System.out.println("LOOKING FOR EG:" + minBreathGroupIndex + ", G:" + min);
        for (int i = 0; i < game.getGroupsBoard().length; i++)
            for (int j = 0; j < game.getGroupsBoard().length; j++) {
                System.out.println("minalem 2 petle");
                if (game.getGroupsBoard()[i][j] == minBreathGroupIndex) {
                    //up case
                    if (game.isLineValid(i, Direction.UP) && game.isPositionAvaible(i - 1, j) && game.isInsideBoard(i - 1, j)) {
                        System.out.println("Wszedlem w up case ");
                        newX = i - 1;
                        newY = j;
                    }
                    //left case
                    else if (!game.isPositionAvaible(i - 1, j) &&
                            game.isLineValid(j, Direction.LEFT) &&
                            game.isPositionAvaible(i, j - 1) && game.isInsideBoard(i, j - 1)) {
                        System.out.println("Wszedlem w left case ");
                        newX = i;
                        newY = j - 1;

                    }
                    // down case
                    else if (!game.isPositionAvaible(i, j - 1) &&
                            game.isLineValid(j, Direction.RIGHT) &&
                            game.isPositionAvaible(i + 1, j) && game.isInsideBoard(i + 1, j)) {
                        System.out.println("Wszedlem w down case ");
                        newX = i + 1;
                        newY = j;
                    }
                    //right case
                   else if (!game.isPositionAvaible(i + 1, j) &&
                            game.isLineValid(i, Direction.DOWN) &&
                            game.isPositionAvaible(i, j + 1) && game.isInsideBoard(i, j + 1)) {
                        System.out.println("Wszedlem w right case ");
                        newX = i;
                        newY = j + 1;
                    }else System.out.println("zaden z przypadkow nie odpowiada ");

                }
            }
        game.updateBoard(WHITE, newX, newY);
        System.out.println("Bot print group board = " + lastGroup);
        game.printer2D(game.getGroupsBoard());
    }


}
