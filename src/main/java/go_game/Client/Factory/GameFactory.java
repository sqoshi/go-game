package go_game.Client.Factory;


public abstract class GameFactory {
    public static GameI getGame(int dismension) {
        if (dismension == 9)
            return new Game9x9();
        else if (dismension == 13)
            return new Game13x13();
        else if (dismension == 19)
            return new Game19x19();
        return null;
    }
}
