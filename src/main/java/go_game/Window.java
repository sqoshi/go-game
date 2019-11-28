package go_game;

import javax.swing.*;


import static javafx.application.Application.launch;

public class Window extends JFrame {
    private Window window;
    public boolean gameState;
    private int dismension;

    public static void main(String[] args) {
        launch(args);
    }

    Window(int dismension) {
        window=this;
        dismension = this.dismension;
        gameState = false;
    }
}
