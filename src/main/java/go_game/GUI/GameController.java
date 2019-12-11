package go_game.GUI;


import go_game.Game;
import go_game.PlayerColor;
import javafx.scene.paint.Color;

public class GameController {
    Field[][] fields;
    //    for Singleton Pattern
    private static GameController gameController = null;

    //    for Singleton Pattern
    private GameController() {

    }

    Game game = new Game(9);


    void onFieldClicked(Field field) {

        game.updateBoard(PlayerColor.BLACK, field.getX(), field.getY());
        refreshBoard(9);
    }

    void refreshBoard(int dimension) {
        char[][] consoleBoard = game.getConsoleBoard();
        for (int i = 0; i < dimension; i++) {
            for (int k = 0; k < dimension; k++) {
                if (consoleBoard[i][k] == 'B') {
                    fields[k][i].setFill(Color.BLACK);
                }
                if (consoleBoard[i][k] == 'W') {
                    fields[k][i].setFill(Color.WHITE);
                }
            }
        }
    }

    //	Singleton Pattern
    static GameController getInstance() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }

    void setFields(Field[][] fields) {
        this.fields = fields;
    }

}