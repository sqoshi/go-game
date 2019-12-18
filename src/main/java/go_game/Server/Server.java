package go_game.Server;

import go_game.Client.Bot;
import go_game.Client.Game;
import go_game.Client.PlayerColor;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(58901)) {
            System.out.println("MP is running");
            var pool = Executors.newFixedThreadPool(200);
            while (true) {
                Gamee game = new Gamee();
                Game gam = new Game(9);
                pool.execute(game.new Player(listener.accept(), PlayerColor.BLACK, gam));
                pool.execute(game.new Player(listener.accept(), PlayerColor.WHITE, gam));
            }
        }
    }
}

class Gamee {

    Game gamee = null;
    Player currentPlayer;
    boolean pas = false;
    // Board cells numbered 0-8, top to bottom, left to right; null if empty
    private Player[] board = new Player[9];

    class Player implements Runnable {
        PlayerColor color;
        Player opponent;
        Socket socket;
        Scanner input;
        Game gam = null;
        ObjectOutputStream output;
        boolean hasBot = false;
        Bot bot;

        public Player(Socket socket, PlayerColor color, Game gam) {
            this.socket = socket;
            this.color = color;
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
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }

        private void setup() throws IOException {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new Scanner(socket.getInputStream());
            System.out.println("KAHAHA");
            if (color == PlayerColor.BLACK) {
                currentPlayer = this;
            } else {
                opponent = this;
                currentPlayer.opponent = this;
                opponent.opponent = currentPlayer;
            }
        }

        private void processCommands() {
            while (input.hasNextLine()) {
                var command = input.nextLine();
                System.out.println(command);
                if (command.startsWith("QUIT")) {
                    return;
                } else if (command.startsWith("MOVE")) {
                    int i = Integer.parseInt(command.substring(5, 7));
                    int j = Integer.parseInt(command.substring(8, 10));
                    if (checkIfMoveAvailable(i, j)) {
                        processMoveCommand(i, j);
                    }
                } else if (command.startsWith("PAS")) {
                    checkPas();
                } else if (command.startsWith("RESUME")) {
                    String i = command.substring(5, 6);
                    resumeGame(i);
                } else if (command.startsWith("GAME")) {
                    int i = Integer.parseInt(command.substring(5, 7));
                    String b = command.substring(8, 9);
                    if (gamee == null) {
                        gamee = new Game(i);
                        gam = gamee;
                    } else {
                        gam = gamee;
                    }
                    if (b.equals("B")) {
                        bot = new Bot(gam);
                        hasBot = true;
                    }
                }
            }
        }

        private void processMoveCommand(int y, int x) {
            try {
                if (hasBot) {
                    System.out.println("HEHE" + gam.dismension);
                    gam.updateBoard(color, x, y);
                    bot.move(y, x);
                    char[][] bor = gam.getConsoleBoard();
                    int blackPrisoners = gam.getBlackPrisoners();
                    int whitePrisoners = gam.getWhitePrisoners();
                    output.flush();
                    output.reset();
                    Response response1 = new Response(bor, true, false, blackPrisoners, whitePrisoners);
                    currentPlayer.output.writeUnshared(response1);
                } else {
                    gam.updateBoard(color, x, y);
                    char[][] bor = gam.getConsoleBoard();
                    int blackPrisoners = gam.getBlackPrisoners();
                    int whitePrisoners = gam.getWhitePrisoners();
                    output.flush();
                    output.reset();
                    Response response1 = new Response(bor, false, false, blackPrisoners, whitePrisoners);
                    currentPlayer.output.writeUnshared(response1);
                    output.flush();
                    output.reset();
                    Response response2 = new Response(bor, true, false, blackPrisoners, whitePrisoners);
                    currentPlayer.opponent.output.writeUnshared(response2);
                    currentPlayer = currentPlayer.opponent;
                    pas = false;
                }
            } catch (IllegalStateException | IOException e) {
//                output.println("MESSAGE " + e.getMessage());
            }
        }

        private void checkPas() {
            int blackPrisoners = gam.getBlackPrisoners();
            int whitePrisoners = gam.getWhitePrisoners();
            if (pas) {
                try {
                    output.flush();
                    output.reset();
                    Response response1 = new Response(gam.getConsoleBoard(), false, true, blackPrisoners, whitePrisoners);
                    currentPlayer.output.writeUnshared(response1);
                    output.flush();
                    output.reset();
                    Response response2 = new Response(gam.getConsoleBoard(), true, true, blackPrisoners, whitePrisoners);
                    currentPlayer.opponent.output.writeUnshared(response2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {

                    output.flush();
                    output.reset();
                    Response response1 = new Response(gam.getConsoleBoard(), false, false, blackPrisoners, whitePrisoners);
                    currentPlayer.output.writeUnshared(response1);
                    output.flush();
                    output.reset();
                    Response response2 = new Response(gam.getConsoleBoard(), true, false, blackPrisoners, whitePrisoners);
                    currentPlayer.opponent.output.writeUnshared(response2);
                    currentPlayer = currentPlayer.opponent;
                    pas = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        //check
        private void resumeGame(String i) {
            int blackPrisoners = gam.getBlackPrisoners();
            int whitePrisoners = gam.getWhitePrisoners();
            try {
                if (!((i.equals("B") && currentPlayer.color.equals(PlayerColor.BLACK)) || (i.equals("W") && currentPlayer.color.equals(PlayerColor.WHITE)))) {
                    currentPlayer = this;
                }
                output.flush();
                output.reset();
                Response response1 = new Response(gam.getConsoleBoard(), false, false, blackPrisoners, whitePrisoners);
                currentPlayer.output.writeUnshared(response1);
                output.flush();
                output.reset();
                Response response2 = new Response(gam.getConsoleBoard(), true, false, blackPrisoners, whitePrisoners);
                currentPlayer.opponent.output.writeUnshared(response2);
                currentPlayer = currentPlayer.opponent;
                pas = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private boolean checkIfMoveAvailable(int i, int j) {
            if (gam.isPositionAvaible(j, i)) {
                return true;
            }
            return false;
        }
    }
}