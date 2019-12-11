package go_game.State;

public interface PlayerStateBehavior {
    PlayerState getState();
    PlayerStateBehavior play();
    PlayerStateBehavior pass();
}
