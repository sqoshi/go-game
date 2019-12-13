package go_game;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

import java.io.PrintWriter;

//class for Board GUI
public class GameBoard extends GridPane {
    Field[][] fields;

    GameBoard(int dimension, PrintWriter ou,Client cl) {
        fields = new Field[dimension][dimension];
        PrintWriter out=ou;
        //setting board background
        Image img = new Image("https://cdn.discordapp.com/attachments/393098632213037060/651216725664071680/bitmap.png");
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));


        this.setVgap(31);
        this.setHgap(31);

        //creating fields
        for (int i = 0; i < dimension ; i++) {
            for (int j = 0; j < dimension ; j++) {
                fields[i][j] = new Field(i, j);

                GridPane.setConstraints(fields[i][j], i, j);

                this.getChildren().add(fields[i][j]);
                Field somefield = fields[i][j];
                somefield.setOnMouseClicked(t -> GameController.getInstance().onFieldClicked(somefield,out));
            }
        }
        Rectangle rectangle = new Rectangle(80,30);
        rectangle.setFill(Color.BLACK);
        this.add(rectangle,4,9,2,2);
        rectangle.setOnMouseClicked(t -> GameController.getInstance().onButtonClicked());


    }

    Field[][] getFields(){
        return fields;
    }
}
