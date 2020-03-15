package go_game.Client;

import go_game.Client.Factory.GameFactory;
import go_game.Client.Factory.GameI;
import go_game.Client.Game;
import go_game.Client.PlayerColor;

import java.util.Scanner;

import static go_game.Client.PlayerColor.BLACK;

/**
 * Hello world!
 */
public class Driver {
    public static void main(String[] args) {
        GameI gameI = GameFactory.getGame(9);
        Game game1 = gameI.createGame();
        String[] parts;
        int i = 0;
        PlayerColor pc;
        game1.addBot();
        while (i >= 0) {
            System.out.println("type coordinates :");
            try {
                String str = new Scanner(System.in).next();
                parts = str.split(",");
                game1.updateBoard(BLACK, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                game1.getBot().move(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                i++;
                game1.showBoard();
            } catch (Exception ex) {
                System.out.println("you need to input data like x,y");
                i--;
            }
        }
    }
}
