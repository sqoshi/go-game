package go_game.GUI;

import go_game.Factory.GameFactory;
import go_game.Factory.GameI;
import go_game.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameI gameI = GameFactory.getGame(9);
        Game game1 = gameI.createGame();
        GameBoard layout = new GameBoard(9);
        stage.setScene(new Scene(layout,900,900));
        stage.setResizable(false);
        stage.show();
    }
}
