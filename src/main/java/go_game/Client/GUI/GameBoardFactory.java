package go_game.Client.GUI;

import java.io.PrintWriter;

public abstract  class GameBoardFactory {
    public static GameBoardI getGamefactory(int dimension, PrintWriter ou){
        if(dimension==19){return new GameBoard19();}
        if(dimension==13){return new GameBoard13();}
        if(dimension==9){return new GameBoard();}
        return null;
    }
}
