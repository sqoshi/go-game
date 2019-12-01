package go_game;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Game game1 = new Game(9);

        int i = 0;
        PlayerColor pc;
        while (i >= 0) {
            System.out.println("type coordinates :");
            int x = new Scanner(System.in).nextInt();
            System.out.println("type coordinates :");
            int y = new Scanner(System.in).nextInt();
            if (i % 2 == 0) pc = PlayerColor.BLACK;
            else pc = PlayerColor.WHITE;
            game1.updateBoard(pc, x, y);
            i++;
            game1.showBoard();
        }
    }
}
