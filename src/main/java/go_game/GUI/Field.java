package go_game.GUI;

import go_game.PlayerColor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//Field class for represent player pawns
public class Field extends Circle {
    private int player;
    private int x, y;
    int counter = 0;

    //setting properties of single field  and adding listener to field
    public Field(int y, int x) throws NullPointerException {
        this.y = y;
        this.x = x;

        setRadius(30);
        setStrokeWidth(1);
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        counter++;
        setOnMouseClicked(t -> {
            if (t.getButton() == MouseButton.PRIMARY)
                GameController.getInstance().onFieldClicked(this, PlayerColor.BLACK);
            else if (t.getButton() == MouseButton.SECONDARY)
                GameController.getInstance().onFieldClicked(this, PlayerColor.WHITE);

        });
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}