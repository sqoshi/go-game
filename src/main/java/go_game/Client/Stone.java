package go_game.Client;

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


    public int getY() {
        return y;
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
