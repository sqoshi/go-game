package go_game.Client.State;

public class Play implements PlayerStateBehavior{
    @Override
    public PlayerState getState() {
        return PlayerState.Play;
    }

    @Override
    public PlayerStateBehavior play() {
        return this;
    }

    @Override
    public PlayerStateBehavior pass() {
        return PlayerState.Pass.getPlayerStateBehavior();
    }

    @Override
    public PlayerStateBehavior surrender() {
        return PlayerState.Surrender.getPlayerStateBehavior();
    }
}
