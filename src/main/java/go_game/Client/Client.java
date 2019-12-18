package go_game.Client;

import go_game.Client.GUI.*;
import go_game.Server.Response;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client {


    public GameBoardI gameBoard;
    public Lobby lobby;
    private Socket socket;
    private ObjectInputStream in;
    private PrintWriter out;
    private boolean canMove;


    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, 58901);
        in = new ObjectInputStream(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("CONNECT");
        lobby = new Lobby();
        ArrayList<Button> buttons = lobby.getButtons();
        buttons.get(0).setOnMouseClicked(t -> {
            gameBoard = GameBoardFactory.getGamefactory(9, out);
            gameBoard.createLayout(out);
            lobby.changeScene(gameBoard);
            out.println("GAME 09 P");
        });
        buttons.get(1).setOnMouseClicked(t -> {
            gameBoard = GameBoardFactory.getGamefactory(9, out);
            gameBoard.createLayout(out);
            lobby.changeScene(gameBoard);
            try {
                Client client = new Client("127.0.0.1");
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("GAME 09 B");
        });
        buttons.get(2).setOnMouseClicked(t -> {
            gameBoard = GameBoardFactory.getGamefactory(13, out);
            gameBoard.createLayout(out);
            lobby.changeScene(gameBoard);
            out.println("GAME 13 P");
        });
        buttons.get(3).setOnMouseClicked(t -> {
            gameBoard = GameBoardFactory.getGamefactory(13, out);
            gameBoard.createLayout(out);
            lobby.changeScene(gameBoard);
            out.println("GAME 13 B");
        });
        buttons.get(4).setOnMouseClicked(t -> {
            gameBoard = GameBoardFactory.getGamefactory(19, out);
            gameBoard.createLayout(out);
            lobby.changeScene(gameBoard);
            out.println("GAME 19 P");
        });
        buttons.get(5).setOnMouseClicked(t -> {
            gameBoard = GameBoardFactory.getGamefactory(19, out);
            gameBoard.createLayout(out);
            lobby.changeScene(gameBoard);
            out.println("GAME 19 B");
        });

    }

    public void play() throws IOException {
        try {
            while (in.available() >= 0) {
                var response = (Response) in.readObject();
                System.out.println("WTF " + in.available() + " WTF");
                for (int i = 0; i < gameBoard.getDimension(); i++) {
                    System.out.println();
                    for (int k = 0; k < gameBoard.getDimension(); k++) {
                        System.out.print(response.bor[k][i]);
                        if (response.bor[i][k] == 'B') {
                            gameBoard.setColor(k, i, Color.BLACK);
                        } else if (response.bor[i][k] == 'W') {
                            gameBoard.setColor(k, i, Color.WHITE);
                        } else {
                            gameBoard.setColor(k, i, Color.TRANSPARENT);
                        }
                    }
                }
                GameController.getInstance().canMove(response.canMove);
                GameController.getInstance().gamePaused(response.pause);
            }
            out.println("QUIT");
        } catch (EOFException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}