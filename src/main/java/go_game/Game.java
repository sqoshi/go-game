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

        //
        blackPrisonersThatWhiteGot = 0;
        whitePrisonersThatBlackGot = 0;
        whiteScore = 0;
        blackScore = 0;
    }
}
