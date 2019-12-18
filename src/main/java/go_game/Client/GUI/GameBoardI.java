package go_game.Client.GUI;

import go_game.Client.PlayerColor;
import javafx.scene.paint.Color;

import java.io.PrintWriter;

public interface GameBoardI {
    public void setDimension(int dimension);

    public void createLayout(PrintWriter output);

    public void setColor(int i, int j, Color color);

    public int getDimension();
}
