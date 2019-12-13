package Strategy;

public enum PlayerColor {
    WHITE, BLACK;

    public PlayerColor opponentColor() {
        switch (this) {
            case BLACK:
                return PlayerColor.WHITE;
            case WHITE:
                return PlayerColor.BLACK;
            default:
                throw new IllegalStateException("Should never happen");
        }
    }

}