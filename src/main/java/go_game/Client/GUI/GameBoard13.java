package go_game.Client.GUI;

import go_game.Client.PlayerColor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.PrintWriter;

public class GameBoard13 extends GridPane implements GameBoardI {
    public Field[][] fields;
    public int dimension = 13;


    @Override
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public void createLayout(PrintWriter output) {
        fields = new Field[13][13];
        PrintWriter out = output;
        Image img = new Image("https://cdn.discordapp.com/attachments/393098632213037060/656684031286444062/bitmap1.png");
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));


        this.setVgap(13);
        this.setHgap(13);

        //creating fields
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                fields[i][j] = new Field(i, j, 20);

                GridPane.setConstraints(fields[i][j], i, j);

                this.getChildren().add(fields[i][j]);
                Field somefield = fields[i][j];
                somefield.setOnMouseClicked(t -> GameController.getInstance().onFieldClicked(somefield, out));
            }
        }

        Button pasbutton = new Button("PAS");
        pasbutton.setMinWidth(100);
        pasbutton.setMinHeight(50);
        this.setConstraints(pasbutton, 2, 14, 2, 2);
        this.getChildren().add(pasbutton);
        pasbutton.setOnMouseClicked(t -> GameController.getInstance().onPasClicked(out));

        Button resumebutton = new Button("RESUME");
        resumebutton.setMinWidth(100);
        resumebutton.setMinHeight(50);
        this.setConstraints(resumebutton, 9, 14, 2, 2);
        this.getChildren().add(resumebutton);
        resumebutton.setOnMouseClicked(t -> GameController.getInstance().onResumeClicked(out));

        Button surrenderbutton = new Button("SURRENDER");
        surrenderbutton.setMinWidth(100);
        surrenderbutton.setMinHeight(50);
        this.setConstraints(surrenderbutton, 5, 14, 2, 2);
        this.getChildren().add(surrenderbutton);
        surrenderbutton.setOnMouseClicked(t -> {
//            GameController.getInstance().onSurrenderClicked(out);
            this.getScene().getWindow().hide();
        });
    }

    @Override
    public void setColor(int i, int j, Color color) {
        fields[i][j].setFill(color);
    }

    @Override
    public int getDimension() {
        return dimension;
    }
}
