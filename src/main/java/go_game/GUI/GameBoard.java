package go_game.GUI;


import go_game.PlayerColor;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//class for Board GUI
public class GameBoard extends GridPane {
    private Field[][] fields;

    GameBoard(int dimension) {
        fields = new Field[dimension][dimension];

        //setting board background
        Image img = new Image("https://cdn.discordapp.com/attachments/393098632213037060/651216725664071680/bitmap.png");
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        this.setVgap(31);
        this.setHgap(31);

        //creating fields
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                fields[i][j] = new Field(i, j);

                GridPane.setConstraints(fields[i][j], i, j);

                this.getChildren().add(fields[i][j]);


            }
        }
        Rectangle surrender = new Rectangle(80, 30);
        Rectangle pass = new Rectangle(80, 30);
        surrender.setFill(Color.DEEPPINK);
        surrender.setAccessibleText("surr");
        this.add(surrender, 2, 9, 2, 2);
        surrender.setOnMouseClicked(t -> GameController.getInstance().
                onSurrenderButtonClicked());
        pass.setFill(Color.DEEPSKYBLUE);
        this.add(pass, 7, 9, 2, 2);
        pass.setOnMouseClicked(t -> GameController.getInstance().
                onPassButtonClicked());

    }

    Field[][] getFields() {
        return fields;
    }
}