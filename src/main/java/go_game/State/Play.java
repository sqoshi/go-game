package go_game.State;

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
        return PlayerState.Pass.getStateBehavior();
    }
}
