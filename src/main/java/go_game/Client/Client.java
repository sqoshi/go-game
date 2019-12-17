package go_game.Client;

import go_game.Client.GUI.GameBoard;
import go_game.Client.GUI.GameController;
import go_game.Server.Response;
import javafx.scene.paint.Color;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A client for a multi-player tic tac toe game. Loosely based on an example in
 * Deitel and Deitel’s “Java How to Program” book. For this project I created a
 * new application-level protocol called TTTP (for Tic Tac Toe Protocol), which
 * is entirely plain text. The messages of TTTP are:
 * <p>
 * Client -> Server
 * MOVE <n>
 * QUIT
 * <p>
 * Server -> Client
 * WELCOME <char>
 * VALID_MOVE
 * OTHER_PLAYER_MOVED <n>
 * OTHER_PLAYER_LEFT
 * VICTORY
 * DEFEAT
 * TIE
 * MESSAGE <text>
 */
public class Client {


    public GameBoard gameBoard;
    private Socket socket;
    private ObjectInputStream in;
    private PrintWriter out;
    private boolean canMove;

    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, 58901);
        in = new ObjectInputStream(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        gameBoard = new GameBoard(9, out, this);


    }

    /**
     * The main thread of the client will listen for messages from the server.
     * The first message will be a "WELCOME" message in which we receive our
     * mark. Then we go into a loop listening for any of the other messages,
     * and handling each message appropriately. The "VICTORY", "DEFEAT", "TIE",
     * and "OTHER_PLAYER_LEFT" messages will ask the user whether or not to
     * play another game. If the answer is no, the loop is exited and the server
     * is sent a "QUIT" message.
     */
    public void play() throws Exception {
        try {
            while (in.available() >= 0) {
                var response = (Response) in.readObject();
                System.out.println("WTF " + in.available() + " WTF");
                for (int i = 0; i < 9; i++) {
                    System.out.println();
                    for (int k = 0; k < 9; k++) {
                        System.out.print(response.bor[k][i]);
                        if (response.bor[i][k] == 'B') {
                            gameBoard.fields[k][i].setFill(Color.BLACK);
                        } else if (response.bor[i][k] == 'W') {
                            gameBoard.fields[k][i].setFill(Color.WHITE);
                        } else {
                            gameBoard.fields[k][i].setFill(Color.TRANSPARENT);
                        }
                    }
                }
                GameController.getInstance().canMove(response.canMove);
                GameController.getInstance().gamePaused(response.pause);
            }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}