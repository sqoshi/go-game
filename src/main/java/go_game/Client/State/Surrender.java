package go_game.Client.State;


public class Surrender implements PlayerStateBehavior {
    @Override
    public PlayerState getState() {
        return PlayerState.Surrender;
    }

    @Override
    public PlayerStateBehavior play() {
        return PlayerState.Play.getPlayerStateBehavior();
    }

    @Override
    public PlayerStateBehavior pass() {
        return PlayerState.Pass.getPlayerStateBehavior();
    }

    @Override
    public PlayerStateBehavior surrender() {
        return this;
    }
}
