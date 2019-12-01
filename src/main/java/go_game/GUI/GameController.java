package go_game.GUI;


import go_game.Game;
import javafx.scene.paint.Color;

public class GameController {
    //    for Singleton Pattern
    private static GameController gameController = null;

    //    for Singleton Pattern
    private GameController() {
    }

    Game game = new Game(9);

    void onFieldClicked(Field field) {
        field.setFill(Color.WHITE);
    }

    //	Singleton Pattern
    static GameController getInstance() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }
}
