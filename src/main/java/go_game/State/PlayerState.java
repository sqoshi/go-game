package go_game.State;

public enum PlayerState {
    Play {
        public PlayerStateBehavior getPlayerStateBehavior() {
            return new Play();
        }
    },
    Pass {
        public PlayerStateBehavior getPlayerStateBehavior() {
            return new Pass();
        }
    };

    public PlayerStateBehavior getStateBehavior() {
        return null;
    }
}
