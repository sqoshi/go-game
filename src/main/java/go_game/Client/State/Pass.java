package go_game.Client.State;

public class Pass implements PlayerStateBehavior {
    @Override
    public PlayerState getState() {
        return PlayerState.Pass;
    }

    @Override
    public PlayerStateBehavior play() {
        return PlayerState.Play.getPlayerStateBehavior();
    }

    @Override
    public PlayerStateBehavior pass() {
        return this;
    }

    @Override
    public PlayerStateBehavior surrender() {
        return PlayerState.Surrender.getPlayerStateBehavior();
    }
}
