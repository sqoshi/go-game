package go_game;
//singleton

import javax.swing.*;

import static javafx.application.Application.launch;

public class Window extends JFrame {
    private JCheckBoxMenuItem jCheckBoxMenuItem1;
    JTextPane jTextPane1;
    private JLabel jLabel16;
    public static JTabbedPane jTabbedPane1;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JTextField jTextField7;
    private JTextField jTextField8;
    private JTextField jTextField9;
    private JTextField jTextField10;
    private JTextField jTextField11;
    private JTextField jTextField12;
    private JButton buttonAgree;
    private JButton buttonSend;
    private JButton buttonResume;
    private JButton buttonPass;
    private Window window;
    public boolean gameState;
    private int dismension;

    public static void main(String[] args) {
        launch(args);
    }

    Window(int dismension) {
        window=this;
        dismension = this.dismension;
        gameState = false;

    }
}
