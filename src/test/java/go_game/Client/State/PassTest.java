package go_game.Client.State;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PassTest {
    Pass ps = new Pass();

    @Test
    void getState() {
        Assert.assertNotNull(ps);
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