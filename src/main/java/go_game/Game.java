package go_game;

public class Game {
    private int dismension;
    private static int totalBlackStones;
    private static int totalWhiteStones;
    private int actualQuantityBlackStones;
    private int actualQuantityWhiteStones;
    private int whitePrisonersThatBlackGot;
    private int blackPrisonersThatWhiteGot;

    private Stone[][] board;
    private char[][] consoleBoard;
    private int[][] groupsBoard; // W groups - B groups
    private int[][][] historyBoard;
    private char[][] territoryPointsBoard;
    boolean[][] deadStones;//states of stones

    private int[] moves;
    private int[] deadStoneDecision;//last 2 decision choosing phase

    private int lastGroup;
    private boolean white;
    private boolean black;
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

        blackPrisonersThatWhiteGot = 0;
        whitePrisonersThatBlackGot = 0;
        whiteScore = 0;
        blackScore = 0;

        board = new Stone[dismension][dismension];
        consoleBoard = new char[dismension][dismension];
        groupsBoard = new int[dismension][dismension]; // W groups - B groups
        territoryPointsBoard = new char[dismension][dismension];
        deadStones = new boolean[dismension][dismension];//states of stones

        deadStoneDecision = new int[3];
        moves = new int[3];
        currentPlayer = PlayerColor.BLACK;
        index = 0;

        historyBoard = new int[dismension ^ 2][dismension][dismension];

        //printing empty console
        emptyConsolePrinter(dismension);
        printer(consoleBoard);
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

    public void printer(char[][] array) {
        for (char[] x : array) {
            for (char y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    public Stone updateBoard(PlayerColor stoneColor, int x, int y) {
        if (!isInsideBoard(x, y)) {
            System.out.println("Bad range.!");
            return null;
        } else if (!isPositionAvaible(x, y)) {
            System.out.print("Postition is not free!");
            return null;
        } else {
            Stone newStone = new Stone(x, y, stoneColor, ++lastGroup);
            int actualGroup = newStone.getGroup();

            int[] neighbours = new int[4];
            return newStone;
        }
    }

    private boolean isPositionAvaible(int x, int y) {
        return board[x][y] == null;
    }

    private boolean isInsideBoard(int x, int y) {
        return ((x < dismension) && (x > -1) && (y < dismension) && (y > -1));
    }

    private int getNeighbours(Stone stone, boolean isColorIdentic, Direction direction) {
        int adjacentGroup = -1024;
        PlayerColor stoneColor = stone.getColor();
        if (!isColorIdentic) {
            if (direction == Direction.UP) {

            } else if (direction == Direction.DOWN) {

            } else if (direction == Direction.RIGHT) {

            } else if (direction == Direction.LEFT) {

            } else adjacentGroup = -1;
        }
    }
}