package go_game.Client.State;

public enum PlayerState {
    Play {
        public PlayerStateBehavior getPlayerStateBehavior() {
            return new Play();
        }
    }, Surrender {
        public PlayerStateBehavior getPlayerStateBehavior() {
            return new Surrender();
        }
    },
    Pass {
        public PlayerStateBehavior getPlayerStateBehavior() {
            return new Pass();
        }
    };
    public PlayerStateBehavior getPlayerStateBehavior() {
        return null;
    }

}
