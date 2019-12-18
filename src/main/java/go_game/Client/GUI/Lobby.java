package go_game.Client.GUI;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Lobby extends BorderPane {
    VBox vBox;
    ArrayList<Button> buttons = new ArrayList<Button>();

    public Lobby() {
        vBox = new VBox(10);
        Button button1 = new Button("9x9 vs PLAYER");
        buttons.add(button1);
        Button button2 = new Button("9x9 vs BOT");
        buttons.add(button2);
        Button button3 = new Button("13x13 vs PLAYER");
        buttons.add(button3);
        Button button4 = new Button("13x13 vs BOT");
        buttons.add(button4);
        Button button5 = new Button("19x19 vs PLAYER");
        buttons.add(button5);
        Button button6 = new Button("19x19 vs BOT");
        buttons.add(button6);
        for (Button b : buttons) {
            b.setMinWidth(300);
            b.setMinHeight(100);
        }
        vBox.getChildren().addAll(button1, button2, button3, button4, button5, button6);
        this.setRight(vBox);
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void changeScene(GameBoardI g) {
        this.getScene().setRoot((Parent) g);
    }

    public void changeScene2(GameBoard13 g) {
        this.getScene().setRoot(g);
    }
}