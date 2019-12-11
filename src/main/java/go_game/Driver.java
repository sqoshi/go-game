package go_game;

import go_game.Factory.GameFactory;
import go_game.Factory.GameI;

import java.util.Scanner;

import static go_game.PlayerColor.BLACK;

/**
 * Hello world!
 */
public class Driver {
    public static void main(String[] args) {
        GameI gameI = GameFactory.getGame(9);
        Game game1 = gameI.createGame();
        //Game game1 = new Game(9);
        String[] parts;
        int i = 0;
        PlayerColor pc;
        while (i >= 0) {
            System.out.println("type coordinates :");
            try {
                String str = new Scanner(System.in).next();
                parts = str.split(",");

                // game1.updateBoard(PlayerColor.BLACK, 4,4);
                // game1.updateBoard(PlayerColor.BLACK, 5,5);
                // game1.updateBoard(PlayerColor.BLACK, 5,3);
                // game1.updateBoard(PlayerColor.BLACK, 6,4);
                // game1.updateBoard(PlayerColor.BLACK, 6,3);
                // game1.updateBoard(PlayerColor.BLACK, 6,4);
                // game1.updateBoard(PlayerColor.WHITE,4,3);
                // game1.updateBoard(PlayerColor.WHITE,5,2);
                // game1.updateBoard(PlayerColor.WHITE,6,2);
                // game1.updateBoard(PlayerColor.WHITE,7,3);
                // game1.updateBoard(PlayerColor.WHITE,7,4);
                // game1.updateBoard(PlayerColor.WHITE,6,5);

            /*   game1.updateBoard(PlayerColor.BLACK,2,2);
               game1.updateBoard(PlayerColor.BLACK,2,3);
               game1.updateBoard(PlayerColor.BLACK,2,4);
               game1.updateBoard(PlayerColor.BLACK,2,5);
               game1.updateBoard(PlayerColor.BLACK,2,6);
               game1.updateBoard(PlayerColor.BLACK,2,6);
               game1.updateBoard(PlayerColor.BLACK,4,6);
               game1.updateBoard(PlayerColor.BLACK,5,6);
               game1.updateBoard(PlayerColor.BLACK,6,6);
               game1.updateBoard(PlayerColor.BLACK,3,6);
               game1.updateBoard(PlayerColor.BLACK,3,2);
               game1.updateBoard(PlayerColor.BLACK,4,2);
               game1.updateBoard(PlayerColor.BLACK,5,2);
               game1.updateBoard(PlayerColor.BLACK,6,2);
               game1.updateBoard(PlayerColor.BLACK,6,3);
               game1.updateBoard(PlayerColor.BLACK,6,4);
               game1.updateBoard(PlayerColor.BLACK,6,5);
               game1.updateBoard(PlayerColor.WHITE,3,3);
               game1.updateBoard(PlayerColor.WHITE,3,4);
               game1.updateBoard(PlayerColor.WHITE,3,5);
               game1.updateBoard(PlayerColor.WHITE,5,3);
               game1.updateBoard(PlayerColor.WHITE,5,4);
               game1.updateBoard(PlayerColor.WHITE,5,5);
               game1.updateBoard(PlayerColor.WHITE,4,5);
               game1.updateBoard(PlayerColor.WHITE,4,3);*/


                if (i % 2 == 0) pc = PlayerColor.BLACK;
                else pc = PlayerColor.WHITE;
                game1.updateBoard(pc, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

                i++;
                game1.gameShow();
            } catch (Exception ex) {
                System.out.println("you need to input data like x,y");
                i--;
            }
        }
    }
}
