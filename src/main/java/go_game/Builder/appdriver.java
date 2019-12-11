package go_game.Builder;

import go_game.Game;
import go_game.PlayerColor;


public class appdriver {
    public static void main(String[] args) {
        GameI gameI = GameFactory.getGame(9);
        Game game = gameI.createGame();
        game.updateBoard(PlayerColor.BLACK,7,5);


    }
}