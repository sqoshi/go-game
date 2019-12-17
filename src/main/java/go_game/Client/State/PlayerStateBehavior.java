package go_game.Client.State;

public interface PlayerStateBehavior {
    PlayerState getState();
    PlayerStateBehavior play();
    PlayerStateBehavior pass();
    PlayerStateBehavior surrender();
}
