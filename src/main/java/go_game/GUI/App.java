package go_game.GUI;

import go_game.Client.Client;
import javafx.application.Application;
import javafx.concurrent.Task;
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
    public void start(Stage stage) throws NullPointerException {
        GameBoard layout = new GameBoard(9);
        GameController.getInstance().setFields(layout.getFields());
        stage.setScene(new Scene(layout, 900, 900));
        stage.setResizable(false);
        stage.show();
    }
   /* @Override
    public void start(Stage stage) throws Exception {
        Client client = new Client("127.0.0.1");
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                client.play();
                return null;
            }
        };
        GameBoard layout = client.gameBoard;
        GameController.getInstance().setFields(layout.getFields());
        stage.setScene(new Scene(layout, 900, 900));
        stage.setResizable(false);
        stage.show();
        new Thread(task).start();
    }
    */
}