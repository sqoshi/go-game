package go_game;

import go_game.Observer.PlayerStateObserver;
import go_game.State.Play;
import go_game.State.PlayerState;

public class Game {
    private int dismension;
    private static int totalBlackStones;
    private static int totalWhiteStones;
    private int actualQuantityBlackStones;
    private int actualQuantityWhiteStones;
    private int whitePrisonersThatBlackGot;
    private int blackPrisonersThatWhiteGot;

    public Stone[][] board;
    private char[][] consoleBoard;

    public int[][] getGroupsBoard() {
        return groupsBoard;
    }

    int[][] groupsBoard; // W groups - B groups
    private int[][][] historyBoard;
    private char[][] territoryPointsBoard;
    boolean[][] deadStones;//states of stones

    private int[] moves;

    private int lastGroup;
    private int index;

    private PlayerColor currentPlayer;
    private double blackScore;
    private double whiteScore;
    private int blackTerritoryPoints;
    private int whiteTerritoryPoints;

    public Game(int dismension) {
        this.dismension = dismension;
        totalBlackStones = (dismension ^ 2) / 2 - 1; // because 1st is current black so -1
        totalWhiteStones = (dismension ^ 2) / 2;

        actualQuantityWhiteStones = 0;
        actualQuantityBlackStones = 0;
        blackPrisonersThatWhiteGot = 0;
        whitePrisonersThatBlackGot = 0;
        whiteScore = 0;
        blackScore = 0;
        board = new Stone[dismension][dismension];
        consoleBoard = new char[dismension][dismension];
        groupsBoard = new int[dismension][dismension]; // W groups - B groups
        territoryPointsBoard = new char[dismension][dismension];
        deadStones = new boolean[dismension][dismension];//states of stones

        moves = new int[3];
        currentPlayer = PlayerColor.BLACK;
        index = 0;

        historyBoard = new int[(int) Math.pow(dismension, 2)][dismension][dismension];

        //printing empty console
        emptyConsolePrinter(dismension);
    }

    private void emptyConsolePrinter(int dismension) {
        for (int i = 0; i < dismension; i++)
            for (int j = 0; j < dismension; j++) {
                if (dismension == 9) {
                    if (((i % 2) == 0) && ((i % 4) != 0)
                            && ((j % 2) == 0) && ((j % 4) != 0))
                        consoleBoard[i][j] = '+';
                    else
                        consoleBoard[i][j] = '.';
                } else if (dismension == 19) {
                    if (((i % 3) == 0) && ((i % 6) != 0)
                            && ((j % 3) == 0) && ((j % 6) != 0))
                        consoleBoard[i][j] = '+';
                    else
                        consoleBoard[i][j] = '.';
                } else if (dismension == 13) {
                    if (((i % 3) == 0) && ((i % 2) != 0)
                            && ((j % 3) == 0) && ((j % 2) != 0) || (i == 6 && j == 6))
                        consoleBoard[i][j] = '+';
                    else
                        consoleBoard[i][j] = '.';
                } else {
                    System.out.println("Size need to be from set{19,13,9}");
                    System.exit(1);
                }
            }
    }


    public Stone updateBoard(PlayerColor stoneColor, int x, int y) {
        PlayerState playerState = PlayerState.Play;
        //check if x,y is inside board.
        if (!isInsideBoard(x, y)) {
            System.out.println("Bad range.!");
            return null;
        } else if (!isPositionAvaible(x, y)) {// check if fiven position is avaible.
            System.out.print("Postition is not free!");
            return null;
        } else {//if inside and position is avaible add Stone, and increment group.
            Stone newStone = new Stone(x, y, stoneColor, ++lastGroup);
            int actualGroup = newStone.getGroup();

            int upNeighbour = getAdjacentGroups(newStone, true, Direction.UP);
            int downNeighbour = getAdjacentGroups(newStone, true, Direction.DOWN);
            int leftNeighbour = getAdjacentGroups(newStone, true, Direction.LEFT);
            int rightNeighbour = getAdjacentGroups(newStone, true, Direction.RIGHT);

            updateGroups(actualGroup, upNeighbour, downNeighbour, leftNeighbour, rightNeighbour);

            //inserting new stone to the board
            board[x][y] = newStone;
            //implement history
            updateHistoryBoard();

            if (CheckKO(index)) {
                System.out.println("you cant move like this, repeat of KO is not allowed!");
                historyBoard[index][x][y] = 0;
                newStone = null;
                board[x][y] = null;
            } else
                board[x][y] = newStone;

            //check suicide
            if (findGroupBreaths(actualGroup) == 0) {
                int[] groupsWithoutBreaths = getGroups2kill(actualGroup, newStone.getColor());
                if (groupsWithoutBreaths[0] == 0) { //theres are no groups with breaths
                    System.out.println("you cant kill yourself");
                    board[x][y] = null;
                    groupsBoard[x][y] = 0;
                    newStone = null;
                } else {
                    if (groupsWithoutBreaths[0] > 0) {
                        System.out.println("you get point by taking prisoner");
                        for (int i = 0; i <= groupsWithoutBreaths[0]; i++)
                            killGroup(groupsWithoutBreaths[i], newStone.getColor());
                        decreaseQuantityOfStones(newStone);
                        //changePlayer();//next turn so change player/
                    }
                }
            } else {
                int pointsWhiteForPrisoners = blackPrisonersThatWhiteGot;
                int pointsBlackForPrisoners = whitePrisonersThatBlackGot;
                afterInsertCheck(actualGroup, newStone);
                if (pointsWhiteForPrisoners != blackPrisonersThatWhiteGot
                        || pointsBlackForPrisoners != whitePrisonersThatBlackGot)
                    System.out.println("sth was taken to jail");//mean that u got a point for killing some units on ur map
                decreaseQuantityOfStones(newStone);
                changePlayer();
            }
            update(newStone);
            printer2D(groupsBoard);
            return newStone;

        }

    }


    private void decreaseQuantityOfStones(Stone newStone) {
        if (newStone.getColor().equals(PlayerColor.BLACK)) actualQuantityBlackStones--;
        else actualQuantityWhiteStones--;
    }


    private void update(Stone newStone) {
        if (newStone != null) {
            updateConsoleBoard(newStone);
            groupsBoard[newStone.getX()][newStone.getY()] = newStone.getGroup();
            index++;
            updateMoves(1);
        }
    }

    private void updateConsoleBoard(Stone stone) {
        if (stone.getColor().equals(PlayerColor.BLACK))
            consoleBoard[stone.getX()][stone.getY()] = 'B';
        else
            consoleBoard[stone.getX()][stone.getY()] = 'W';
    }

    private void updateMoves(int value) {
        // 1 if BLACk 2 if wHITE
        if (currentPlayer == PlayerColor.BLACK) {
            moves[2] = value;
            moves[0] = 2;
        } else {
            moves[1] = value;
            moves[0] = 1;
        }
    }

    /**
     * check if after insertion there is any group to be deleted.
     *
     * @param actualGroup
     * @param stone
     */
    private void afterInsertCheck(int actualGroup, Stone stone) {

        int up = getAdjacentGroups(stone, false, Direction.UP);
        int down = getAdjacentGroups(stone, false, Direction.DOWN);
        int right = getAdjacentGroups(stone, false, Direction.RIGHT);
        int left = getAdjacentGroups(stone, false, Direction.LEFT);

        if (up != actualGroup && findGroupBreaths(up) == 0)
            killGroup(up, stone.getColor());
        if (down != actualGroup && findGroupBreaths(down) == 0)
            killGroup(down, stone.getColor());
        if (left != actualGroup && findGroupBreaths(left) == 0)
            killGroup(left, stone.getColor());
        if (right != actualGroup && findGroupBreaths(right) == 0)
            killGroup(right, stone.getColor());
    }

    /**
     * pass method
     */
    PlayerColor pass() {
        changePlayer();
        index++;
        updateMoves(-1);
        return currentPlayer;
    }

    /**
     * change player.
     */
    void changePlayer() {
        if (currentPlayer.equals(PlayerColor.BLACK))
            currentPlayer = PlayerColor.WHITE;
        else
            currentPlayer = PlayerColor.BLACK;
    }

    /**
     * delete stones from killed group;
     *
     * @param group
     * @param playerColor
     */
    private void killGroup(int group, PlayerColor playerColor) {
        for (int i = 0; i < dismension; i++)
            for (int j = 0; j < dismension; j++) {
                if (board[i][j] != null && board[i][j].getGroup() == group) {
                    //increase prisoners //points
                    if (playerColor.equals(PlayerColor.WHITE)) blackPrisonersThatWhiteGot++;
                    else whitePrisonersThatBlackGot++;
                    groupsBoard[i][j] = 0;
                    board[i][j] = null;
                    consoleBoard[i][j] = '.';
                }
            }
    }

    /**
     * @param actualGroup
     * @param playerColor
     * @return
     */
    private int[] getGroups2kill(int actualGroup, PlayerColor playerColor) {
        int[] g2k = new int[dismension ^ 2];
        g2k[0] = 0;
        int index = 0;
        int breaths;
        boolean[] groups = new boolean[actualGroup + 1];

        for (int i = 0; i < dismension; i++)
            for (int j = 0; j < dismension; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getColor() != playerColor) {
                        breaths = findGroupBreaths(board[i][j].getGroup());
                        if (breaths == 0
                                && !groups[board[i][j].getGroup()]
                                && board[i][j].getGroup() != actualGroup) {
                            g2k[0]++;
                            g2k[index++] = board[i][j].getGroup();
                            groups[board[i][j].getGroup()] = true;
                        }
                    }
                }
            }

        return g2k;
    }

    /**
     * search for breaths of group.
     *
     * @param group
     * @return
     */
    private int findGroupBreaths(int group) {
        boolean[][] breathsMatrix = new boolean[dismension][dismension];
        int counter = 0;
        for (int i = 0; i < dismension; i++)
            for (int j = 0; j < dismension; j++) {
                if (board[i][j] != null && board[i][j].getGroup() == group) {
                    counter += breathsPerStoneInGroup(i, j, breathsMatrix);
                }
            }
        return counter;
    }

    /**
     * find breaths for stone in group.
     *
     * @param x
     * @param y
     * @param matrix
     * @return
     */
    private int breathsPerStoneInGroup(int x, int y, boolean[][] matrix) {
        int count = 0;
        if (isLineValid(x, Direction.UP) && isPositionAvaible(x - 1, y) && !matrix[x - 1][y]) {
            matrix[x - 1][y] = true;
            count++;
        }
        if (isLineValid(x, Direction.DOWN) && isPositionAvaible(x + 1, y) && !matrix[x + 1][y]) {
            matrix[x + 1][y] = true;
            count++;
        }
        if (isLineValid(y, Direction.LEFT) && isPositionAvaible(x, y - 1) && !matrix[x][y - 1]) {
            matrix[x][y - 1] = true;
            count++;
        }
        if (isLineValid(y, Direction.RIGHT) && isPositionAvaible(x, y + 1) && !matrix[x][y + 1]) {
            matrix[x][y + 1] = true;
            count++;
        }
        return count;
    }

    /**
     * Updates copy of boadrd.
     */
    private void updateHistoryBoard() {
        for (int i = 0; i < dismension; i++)
            for (int j = 0; j < dismension; j++) {
                if (board[i][j] == null)
                    historyBoard[index][i][j] = 0;
                else if (board[i][j].getColor().equals(PlayerColor.BLACK))
                    historyBoard[index][i][j] = -1;
                else if (board[i][j].getColor().equals(PlayerColor.WHITE))
                    historyBoard[index][i][j] = 1;
            }
    }

    private boolean CheckKO(int index) {
        if (index > 1) {
            for (int i = 0; i < dismension; ++i) {
                for (int j = 0; j < dismension; ++j) {
                    if (historyBoard[index][i][j] != historyBoard[index - 1][i][j])
                        return false;
                }
            }
        } else return false;
        return true;
    }

    /**
     * Sets old adjacent groups to new one group.
     *
     * @param actualGroup actual setting stone group.
     * @param up          up adjacent group
     * @param down        down adjacent group
     * @param left        left adjacent group
     * @param right       right adjacent group
     */
    private void updateGroups(int actualGroup, int up, int down, int left, int right) {
        for (int i = 0; i < dismension; ++i) {
            for (int j = 0; j < dismension; ++j) {
                if (board[i][j] != null) {
                    if (board[i][j].getGroup() == up || board[i][j].getGroup() == down
                            || board[i][j].getGroup() == left || board[i][j].getGroup() == right) {
                        groupsBoard[i][j] = actualGroup;
                        board[i][j].setGroup(actualGroup);
                    }
                }
            }
        }
    }

    public boolean isPositionAvaible(int x, int y) {
        return board[x][y] == null;
    }

    public boolean isInsideBoard(int x, int y) {
        return ((x < dismension) && (x > -1) && (y < dismension) && (y > -1));
    }

    /**
     * checks limit in case player try to put stone outside board.
     *
     * @param n
     * @param dir
     * @return
     */
    public boolean isLineValid(int n, Direction dir) {
        if (dir.equals(Direction.UP) || dir.equals(Direction.LEFT)) {
            return n > 0;
        } else
            return n < dismension - 1;
    }

    /**
     * Returns group numer that is a neighbour with given stone
     *
     * @param stone
     * @param isColorIdentic
     * @param direction
     * @return
     */
    private int getAdjacentGroups(Stone stone, boolean isColorIdentic, Direction direction) {
        int adjacentGroup = -1024;
        PlayerColor stoneColor = stone.getColor();
        if (!isColorIdentic) {
            if (direction == Direction.UP) {
                if (isLineValid(stone.getX(), direction)
                        && !isPositionAvaible(stone.getX() - 1, stone.getY())
                        && stoneColor != board[stone.getX() - 1][stone.getY()].getColor()) {
                    adjacentGroup = board[stone.getX() - 1][stone.getY()].getGroup();
                } else {
                    adjacentGroup = -1;
                }
            } else if (direction == Direction.DOWN) {
                if (isLineValid(stone.getX(), direction)
                        && !isPositionAvaible(stone.getX() + 1, stone.getY())
                        && stoneColor != board[stone.getX() + 1][stone.getY()].getColor()) {
                    adjacentGroup = board[stone.getX() + 1][stone.getY()].getGroup();
                } else {
                    adjacentGroup = -1;
                }
            } else if (direction == Direction.RIGHT) {
                if (isLineValid(stone.getY(), direction)
                        && !isPositionAvaible(stone.getX(), stone.getY() + 1)
                        && (stoneColor != board[stone.getX()][stone.getY() + 1].getColor()))
                    adjacentGroup = board[stone.getX()][stone.getY() + 1].getGroup();
                else
                    adjacentGroup = -1;
            } else if (direction == Direction.LEFT) {
                if (isLineValid(stone.getY(), direction)
                        && !isPositionAvaible(stone.getX(), stone.getY() - 1)
                        && (stoneColor != board[stone.getX()][stone.getY() - 1].getColor()))
                    adjacentGroup = board[stone.getX()][stone.getY() - 1].getGroup();
                else
                    adjacentGroup = -1;
            } else
                adjacentGroup = -1;
        } else {
            if (direction == Direction.UP) {
                if (isLineValid(stone.getX(), direction)
                        && !isPositionAvaible(stone.getX() - 1, stone.getY())
                        && stoneColor == board[stone.getX() - 1][stone.getY()].getColor()) {
                    adjacentGroup = board[stone.getX() - 1][stone.getY()].getGroup();
                } else {
                    adjacentGroup = -1;
                }
            } else if (direction == Direction.DOWN) {
                if (isLineValid(stone.getX(), direction)
                        && !isPositionAvaible(stone.getX() + 1, stone.getY())
                        && stoneColor == board[stone.getX() + 1][stone.getY()].getColor()) {
                    adjacentGroup = board[stone.getX() + 1][stone.getY()].getGroup();
                } else {
                    adjacentGroup = -1;
                }
            } else if (direction == Direction.RIGHT) {
                if (isLineValid(stone.getY(), direction)
                        && !isPositionAvaible(stone.getX(), stone.getY() + 1)
                        && (stoneColor == board[stone.getX()][stone.getY() + 1].getColor()))
                    adjacentGroup = board[stone.getX()][stone.getY() + 1].getGroup();
                else
                    adjacentGroup = -1;
            } else if (direction == Direction.LEFT) {
                if (isLineValid(stone.getY(), direction)
                        && !isPositionAvaible(stone.getX(), stone.getY() - 1)
                        && (stoneColor == board[stone.getX()][stone.getY() - 1].getColor()))
                    adjacentGroup = board[stone.getX()][stone.getY() - 1].getGroup();
                else
                    adjacentGroup = -1;
            } else
                adjacentGroup = -1;
        }
        return adjacentGroup;
    }

    void showBoard() {
        int lines = 0;
        char[][] matrix = consoleBoard;

        String string19 = "     A B C D E F G H I J K L M N O P Q R S\n";
        String letters;
        if (dismension == 19)
            letters = string19;
        else if (dismension == 13)
            letters = string19.substring(0, 31) + '\n';
        else
            letters = string19.substring(0, 23) + "\n";

        System.out.print("\n" + letters);
        for (char[] m : matrix)
            for (int j = 0; j < matrix.length; j++) {
                if (j == 0) {
                    if (lines < 10) {
                        System.out.print("  " + lines + " ");
                        lines++;
                    } else {
                        System.out.print(" " + lines + " ");
                        lines++;
                    }
                }
                if (j == matrix.length - 1)
                    System.out.println(" " + m[j] + "  " + (lines - 1));
                else
                    System.out.print(" " + m[j]);
            }
        System.out.print(letters);
    }

    void printer2D(int[][] array) {
        for (int[] x : array) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    void printer2DB(boolean[][] array) {
        for (boolean[] x : array) {
            for (boolean y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }


    void gameShow() {
        System.out.println();
        for (char[] x : consoleBoard) {
            for (char y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}