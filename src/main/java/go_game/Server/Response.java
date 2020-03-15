package go_game.Server;

import java.io.Serializable;

public class Response implements Serializable {
    public char[][] bor;
    public boolean canMove;
    public boolean pause;
    int blackPrisoners;
    int whitePrisoners;

    public Response(char[][] bor, boolean canMove, boolean pause, int bp, int wp) {
        this.bor = bor;
        this.canMove = canMove;
        this.pause = pause;
        blackPrisoners = bp;
        whitePrisoners = wp;
    }
}