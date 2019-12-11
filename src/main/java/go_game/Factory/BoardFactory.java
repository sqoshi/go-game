package go_game.Factory;


public abstract class BoardFactory {
    protected abstract Board instantiateGame();

    public Board generateGame(int data) {

        Board generatedGame = instantiateGame();
        generatedGame.generateGame(data);

        return generatedGame;
    }
}
