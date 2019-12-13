package go_game;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;

/**
 * A server for a multi-player tic tac toe game. Loosely based on an example in
 * Deitel and Deitel’s “Java How to Program” book. For this project I created a
 * new application-level protocol called TTTP (for Tic Tac Toe Protocol), which
 * is entirely plain text. The messages of TTTP are:
 *
 * Client -> Server
 *     MOVE <n>
 *     QUIT
 *
 * Server -> Client
 *     WELCOME <char>
 *     VALID_MOVE
 *     OTHER_PLAYER_MOVED <n>
 *     OTHER_PLAYER_LEFT
 *     VICTORY
 *     DEFEAT
 *     TIE
 *     MESSAGE <text>
 */
public class Server {
    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(58901)) {
            System.out.println("Tic Tac Toe Server is Running...");
            var pool = Executors.newFixedThreadPool(200);
            while (true) {
                Gamee game = new Gamee();
                Game gam = new Game(9);
                pool.execute(game.new Player(listener.accept(), PlayerColor.WHITE,gam));
                pool.execute(game.new Player(listener.accept(), PlayerColor.BLACK,gam));
            }
        }
    }
}

class Gamee {

    // Board cells numbered 0-8, top to bottom, left to right; null if empty
    private Player[] board = new Player[9];

    Player currentPlayer;

    public boolean hasWinner() {
        return (board[0] != null && board[0] == board[1] && board[0] == board[2])
                || (board[3] != null && board[3] == board[4] && board[3] == board[5])
                || (board[6] != null && board[6] == board[7] && board[6] == board[8])
                || (board[0] != null && board[0] == board[3] && board[0] == board[6])
                || (board[1] != null && board[1] == board[4] && board[1] == board[7])
                || (board[2] != null && board[2] == board[5] && board[2] == board[8])
                || (board[0] != null && board[0] == board[4] && board[0] == board[8])
                || (board[2] != null && board[2] == board[4] && board[2] == board[6]
        );
    }

    public boolean boardFilledUp() {
        return Arrays.stream(board).allMatch(p -> p != null);
    }

    public synchronized void move(int location, Player player) {
        if (player != currentPlayer) {
            throw new IllegalStateException("Not your turn");
        } else if (player.opponent == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        } else if (board[location] != null) {
            throw new IllegalStateException("Cell already occupied");
        }
        board[location] = currentPlayer;
        currentPlayer = currentPlayer.opponent;
    }

    /**
     * A Player is identified by a character mark which is either 'X' or 'O'.
     * For communication with the client the player has a socket and associated
     * Scanner and PrintWriter.
     */
    class Player implements Runnable {
        PlayerColor color;
        Player opponent;
        Socket socket;
        Scanner input;
        Game gam;
//        PrintWriter output;
        ObjectOutputStream output;

        public Player(Socket socket, PlayerColor color,Game gam) {
            this.socket = socket;
            this.color = color;
            this.gam=gam;
        }

        @Override
        public void run() {
            try {
                setup();
                processCommands();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (opponent != null && opponent.output != null) {
//                    opponent.output.println("OTHER_PLAYER_LEFT");
                }
                try {socket.close();} catch (IOException e) {}
            }
        }

        private void setup() throws IOException {
            output =  new ObjectOutputStream(socket.getOutputStream());
            input = new Scanner(socket.getInputStream());
//            output = new PrintWriter(socket.getOutputStream(), true);
//            output.println("WELCOME " + mark);
            if (color == PlayerColor.WHITE) {
                currentPlayer = this;
//                output.println("MESSAGE Waiting for opponent to connect");
            } else {
                opponent = currentPlayer;
                opponent.opponent = this;
//                opponent.output.println("MESSAGE Your move");
            }
        }

        private void processCommands() {
            System.out.println("SIEMA" + color);
            while (input.hasNextLine()) {
                var command = input.nextLine();
                System.out.println(command);
                if (command.startsWith("QUIT")) {
                    return;
                } else if (command.startsWith("MOVE")) {
                    System.out.println("RUSZAM SIE");
                    int i = Integer.parseInt(command.substring(5,6));
                    int j = Integer.parseInt(command.substring(7,8));
                    System.out.println("I:"+i);
                    System.out.println("J:"+j);
                   processMoveCommand(i,j);
                }
            }
        }

        private void processMoveCommand(int y, int x) {
            try {
                gam.updateBoard(color,x,y);
                char[][] bor = gam.getConsoleBoard();
                for(int i=0;i<9;i++){
                    System.out.println();
                    for(int k=0;k<9;k++){
                        System.out.print(bor[i][k]);
                    }
                }
                output.reset();
                currentPlayer.output.writeUnshared(bor);
                opponent.output.writeUnshared(bor);
                currentPlayer=opponent;
                if (hasWinner()) {
//                    output.println("VICTORY");
//                    opponent.output.println("DEFEAT");
                } else if (boardFilledUp()) {
//                    output.println("TIE");
//                    opponent.output.println("TIE");
                }
            } catch (IllegalStateException | IOException e) {
//                output.println("MESSAGE " + e.getMessage());
            }
        }
    }
}