package go_game.Client.GUI;

import go_game.Client.Client;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;


/**
 * Hello world!
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Client client = new Client("127.0.0.1");
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                client.play();
                return null;
            }
        };
        new Thread(task).start();
        Lobby lobby = client.lobby;

//        GameController.getInstance().setFields(layout.getFields());
        stage.setScene(new Scene(lobby, 900, 900));
        stage.setResizable(false);
        stage.show();
    }
}