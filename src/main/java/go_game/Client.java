package go_game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.ObjectInputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A client for a multi-player tic tac toe game. Loosely based on an example in
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
public class Client{


//    private Square[] board = new Square[9];
//    private Square currentSquare;

    GameBoard gameBoard;
    private Socket socket;
//    private Scanner in;
    private ObjectInputStream in;
    private PrintWriter out;

    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, 58901);
//        in = new Scanner(socket.getInputStream());
        in = new ObjectInputStream(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

//        messageLabel.setBackground(Color.lightGray);
//        frame.getContentPane().add(messageLabel, BorderLayout.SOUTH);
//
//        var boardPanel = new JPanel();
//        boardPanel.setBackground(Color.black);
//        boardPanel.setLayout(new GridLayout(3, 3, 2, 2));
//        for (var i = 0; i < board.length; i++) {
//            final int j = i;
//            board[i] = new Square();
//            board[i].addMouseListener(new MouseAdapter() {
//                public void mousePressed(MouseEvent e) {
//                    currentSquare = board[j];
//                    out.println("MOVE " + j);
//                }
//            });
//            boardPanel.add(board[i]);
//        }
//        frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
        gameBoard = new GameBoard(9,out,this);
    }

    /**
     * The main thread of the client will listen for messages from the server.
     * The first message will be a "WELCOME" message in which we receive our
     * mark. Then we go into a loop listening for any of the other messages,
     * and handling each message appropriately. The "VICTORY", "DEFEAT", "TIE",
     *  and "OTHER_PLAYER_LEFT" messages will ask the user whether or not to
     * play another game. If the answer is no, the loop is exited and the server
     * is sent a "QUIT" message.
     */
    public void play() throws Exception{
        try {
//            var response = in.nextLine();
//            var mark = response.charAt(8);
//            System.out.println(response + "         "+mark);
//            var opponentMark = mark == 'X' ? 'O' : 'X';
//            frame.setTitle("Tic Tac Toe: Player " + mark);
                while (in.available() >= 0) {
                    var response = (char[][])in.readObject();
                    System.out.println("WTF "+response+" WTF");
                        for(int i=0;i<9;i++){
                            System.out.println();
                            for(int k=0;k<9;k++){
                                System.out.print(response[k][i]);
                                if(response[i][k]=='B'){
                                    gameBoard.fields[k][i].setFill(Color.BLACK);
                                }
                                else if(response[i][k]=='W'){
                                    gameBoard.fields[k][i].setFill(Color.WHITE);
                                }
                                else {
                                    gameBoard.fields[k][i].setFill(Color.TRANSPARENT);
                                }
                            }
                        }

//                response = in.nextLine();
//                System.out.println(response);
//                if (response.startsWith("VALID_MOVE")) {
////                    messageLabel.setText("Valid move, please wait");
////                    currentSquare.setText(mark);
////                    currentSquare.repaint();
//                } else if (response.startsWith("OPPONENT_MOVED")) {
//                    var loc = Integer.parseInt(response.substring(15));
////                    board[loc].setText(opponentMark);
////                    board[loc].repaint();
////                    messageLabel.setText("Opponent moved, your turn");
//                } else if (response.startsWith("MESSAGE")) {
////                    messageLabel.setText(response.substring(8));
//                } else if (response.startsWith("VICTORY")) {
////                    JOptionPane.showMessageDialog(frame, "Winner Winner");
//                    break;
//                } else if (response.startsWith("DEFEAT")) {
////                    JOptionPane.showMessageDialog(frame, "Sorry you lost");
//                    break;
//                } else if (response.startsWith("TIE")) {
////                    JOptionPane.showMessageDialog(frame, "Tie");
//                    break;
//                } else if (response.startsWith("OTHER_PLAYER_LEFT")) {
////                    JOptionPane.showMessageDialog(frame, "Other player left");
//                    break;
//                }
                }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            socket.close();
//            frame.dispose();
        }
    }

//    static class Square extends JPanel {
//        JLabel label = new JLabel();
//
//        public Square() {
//            setBackground(Color.white);
//            setLayout(new GridBagLayout());
//            label.setFont(new Font("Arial", Font.BOLD, 40));
//            add(label);
//        }
//
//        public void setText(char text) {
//            label.setForeground(text == 'X' ? Color.BLUE : Color.RED);
//            label.setText(text + "");
//        }
//    }

//    public static void main(String[] args){
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        PrintWriter ou = new PrintWriter(System.out);
//        GameBoard gameBoard = new GameBoard(9,ou);
//        GameBoard layout = gameBoard;
//        GameController.getInstance().setFields(layout.getFields());
//        stage.setScene(new Scene(layout,900,900));
//        stage.setResizable(false);
//        stage.show();
//    }
}