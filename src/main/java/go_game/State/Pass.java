package go_game.State;

public class Pass implements PlayerStateBehavior {
    @Override
    public PlayerState getState() {
        return PlayerState.Pass;
    }

    @Override
    public PlayerStateBehavior play() {
        return PlayerState.Play.getStateBehavior();
    }

    @Override
    public PlayerStateBehavior pass() {
        return this;
    }
}
