package go_game;

public class Stone {
    /**
     * postion cordinates.
     */
    private int x, y;

    /**
     * breaths of concrete stone.
     */
    private int breaths;

    /**
     * stone color to represent team.
     */
    final private PlayerColor color;

    /**
     * group mark.
     */
    private int group;

    /**
     * Initiate object stone.
     *
     * @param x     first cord
     * @param y     second cord
     * @param color color of
     */
    public Stone(int x, int y, PlayerColor color, int group) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBreaths() {
        return breaths;
    }

    public void setBreaths(int breaths) {
        this.breaths = breaths;
    }

    public PlayerColor getColor() {
        return color;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
