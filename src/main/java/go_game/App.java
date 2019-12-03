package go_game;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Game game1 = new Game(9);
        String[] parts;
        int i = 0;
        PlayerColor pc;
        while (i >= 0) {
            System.out.println("type coordinates :");
            try {
                String str = new Scanner(System.in).next();
                parts = str.split(",");
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
