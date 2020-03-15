package go_game.Client.State;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PlayTest {
    Play ps = new Play();

    @Test
    void getState() {
        Assert.assertTrue(ps instanceof Play);

    }

    @Test
    void play() {
        Assert.assertTrue(ps.play() instanceof Play);
    }

    @Test
    void pass() {
        Assert.assertTrue(ps.pass() instanceof Pass);
    }

    @Test
    void surrender() {
        Assert.assertTrue(ps.surrender() instanceof Surrender);
    }
}