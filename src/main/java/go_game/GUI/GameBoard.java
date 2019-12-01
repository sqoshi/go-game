package go_game.GUI;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.transform.Translate;

//class for Board GUI
public class GameBoard extends GridPane {
    private Field[][] fields;

    GameBoard(int dimension) {
        fields = new Field[dimension + 1][dimension + 1];

        //setting board background
        Image img = new Image("https://cdn.discordapp.com/attachments/393098632213037060/651216725664071680/bitmap.png");
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        this.setVgap(31);
        this.setHgap(31);

        //creating fields
        for (int i = 0; i < dimension + 1; i++) {
            for (int j = 0; j < dimension + 1; j++) {
                fields[i][j] = new Field(i, j);

                GridPane.setConstraints(fields[i][j], i, j);

                this.getChildren().add(fields[i][j]);
            }
        }

    }
}