package go_game;

import java.net.Socket;
import java.net.SocketException;

public class clientControl {
    private int dim;
    private boolean Partner = false;
    private boolean Bot = false;

    /**
     * Socket creator.
     *
     * @param socket avaible socket for user-client.
     */
    public clientControl(Socket socket) {
        super();
        System.out.println("Making new socket for new client");
        try {
            socket.setSoTimeout(600);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println("New socket creation finished.");
    }

    /**
     * Disconnect player from server.
     */
    public void disconnect() {
        System.exit(1);
    }

    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }

    public boolean isPartner() {
        return Partner;
    }

    public void setPartner(boolean partner) {
        Partner = partner;
    }

    public boolean isBot() {
        return Bot;
    }

    public void setBot(boolean bot) {
        Bot = bot;
    }
}
