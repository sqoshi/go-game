package go_game;

public class Stone {
    /**
     * postion cordinates.
     */
    private int x,y;
    /**
     * breaths of concrete stone.
     */
    private int breaths;
    /**
     * stone color to represent team.
     */
    final private stoneColor color;

    /**
     * Initiate object stone.
     * @param x first cord
     * @param y second cord
     * @param color color of
     */
    public Stone(int x, int y, stoneColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public stoneColor getColor() {
        return color;
    }


}
