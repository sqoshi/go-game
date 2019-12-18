package go_game.Client.GUI;


import com.sun.media.jfxmediaimpl.platform.Platform;
import go_game.Client.Client;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.PrintWriter;

//class for Board GUI
public class GameBoard extends GridPane {
    public Field[][] fields;
    public Circle[] prisoners;

    public GameBoard(PrintWriter ou) {
        fields = new Field[9][9];
        PrintWriter out = ou;
        Image img = new Image("https://cdn.discordapp.com/attachments/393098632213037060/651216725664071680/bitmap.png");
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));


        this.setVgap(31);
        this.setHgap(31);

        //creating fields
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new Field(i, j, 30);

                GridPane.setConstraints(fields[i][j], i, j);

                this.getChildren().add(fields[i][j]);
                Field somefield = fields[i][j];
                somefield.setOnMouseClicked(t -> GameController.getInstance().onFieldClicked(somefield, out));
            }
        }

        Button pasbutton = new Button("PAS");
        pasbutton.setMinWidth(100);
        pasbutton.setMinHeight(50);
        this.setConstraints(pasbutton, 2, 9, 2, 2);
        this.getChildren().add(pasbutton);
        pasbutton.setOnMouseClicked(t -> GameController.getInstance().onPasClicked(out));

        Button resumebutton = new Button("RESUME");
        resumebutton.setMinWidth(100);
        resumebutton.setMinHeight(50);
        this.setConstraints(resumebutton, 8, 9, 2, 2);
        this.getChildren().add(resumebutton);
        resumebutton.setOnMouseClicked(t -> GameController.getInstance().onResumeClicked(out));

        Button surrenderbutton = new Button("SURRENDER");
        surrenderbutton.setMinWidth(100);
        surrenderbutton.setMinHeight(50);
        this.setConstraints(surrenderbutton, 5, 9, 2, 2);
        this.getChildren().add(surrenderbutton);
        surrenderbutton.setOnMouseClicked(t -> {
//            GameController.getInstance().onSurrenderClicked(out);
            this.getScene().getWindow().hide();
        });

    }

    public Field[][] getFields() {
        return fields;
    }
}