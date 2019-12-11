package go_game.Factory;

public abstract class AbstractGame implements Board {
    private int gameContent;

    public int getGameContent() {
        return gameContent;
    }

    protected void setGameContent(int content) {
        this.gameContent = content;
    }
}
