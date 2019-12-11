package go_game.GUI;

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
        GameBoard layout = new GameBoard(9);
        GameController.getInstance().setFields(layout.getFields());
        stage.setScene(new Scene(layout,900,900));
        stage.setResizable(false);
        stage.show();
    }
}