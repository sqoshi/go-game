package go_game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Field extends Circle {
    private int player;
    private int x,y;

    public Field(int x, int y){
        this.x = x;
        this.y = y;

        setRadius(30);
        setStrokeWidth(1);
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setOnMouseClicked(t -> GameController.getInstance().onFieldClicked(this));
    }
}
