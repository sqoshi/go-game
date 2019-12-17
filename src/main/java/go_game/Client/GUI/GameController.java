package go_game.Client.GUI;


import go_game.Client.Game;
import javafx.scene.paint.Color;

import java.io.PrintWriter;

public class GameController {
    //    for Singleton Pattern
    private static GameController gameController = null;
    Field[][] fields;
    boolean canMove = true;
    boolean notPaused = true;
    Game game = new Game(9);

    //    for Singleton Pattern
    private GameController() {

    }

    //	Singleton Pattern
    public static GameController getInstance() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }

    public void onFieldClicked(Field field, PrintWriter out) {
        if (canMove && notPaused) {
            out.println("MOVE " + field.getY() + " " + field.getX());
            System.out.println("MOVE " + field.getY() + " " + field.getX());
        }
    }

    public void onPasClicked(PrintWriter out) {
        if (canMove && notPaused) {
            out.println("PAS");
        }
    }

    public void onResumeClicked(PrintWriter out) {
        if (!notPaused) {
            out.println("RESUME");
        }
    }

    public void canMove(boolean can) {
        canMove = can;
    }

    public void gamePaused(boolean pause) {
        notPaused = !pause;
    }

    void refreshBoard(char[][] consoleboard, int l) {
        char[][] consoleBoard = game.getConsoleBoard();
        for (int i = 0; i < l; i++) {
            for (int k = 0; k < l; k++) {
                if (consoleBoard[i][k] == 'B') {
                    fields[k][i].setFill(Color.BLACK);
                }
                if (consoleBoard[i][k] == 'W') {
                    fields[k][i].setFill(Color.WHITE);
                }
            }
        }
    }

    public void setFields(Field[][] fields) {
        this.fields = fields;
    }

}