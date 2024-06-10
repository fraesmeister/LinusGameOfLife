package se.linusTest;

import org.junit.Assert;
import org.junit.Test;

public class GameOfLifeAppTest {
    /**
     * Test to make sure it follows the proper game rules when iterating from
     * state to state.
     */
    @Test
    public void testIterate() {
        /*
         * set initial board state
         * xxx..
         * .....
         * ..xxx
         * x.xx.
         */
        Grid grid = new Grid(new int[4][5]);
        grid.setAlive(0, 0, true);
        grid.setAlive(0, 1, true);
        grid.setAlive(0, 2, true);
        grid.setAlive(2, 2, true);
        grid.setAlive(2, 3, true);
        grid.setAlive(2, 4, true);
        grid.setAlive(3, 0, true);
        grid.setAlive(3, 2, true);
        grid.setAlive(3, 3, true);

        //iterate once
        grid.nextGen();

        /*
         * .x...
         * .....
         * .xx.x
         * .xx.x
         */
        // @formatter:off
        boolean[][] expected = new boolean[][] { { false, true, false, false, false }, { false, false, false, false, false }, { false, true, true, false, true }, { false, true, true, false, true } };
        // @formatter:on
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                Assert.assertEquals(expected[i][j], grid.isAlive(i, j));
            }
        }

        //iterate twice
        grid.nextGen();
        grid.nextGen();

        /*
         * .....
         * .xx..
         * .xx..
         * .xx..
         *
         * .....
         * .xx..
         * x..x.
         * .xx..
         */
        // @formatter:off
        expected = new boolean[][] { { false, false, false, false, false }, { false, true, true, false, false }, { true, false, false, true, false }, { false, true, true, false, false } };
        // @formatter:on
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                Assert.assertEquals(expected[i][j], grid.isAlive(i, j));
            }
        }
    }

    @Test
    public void testRunGame() {
        GameOfLifeApp.runGame(new Grid(Utils.GRID_20_X_20), 100);

        //no assertion, just testing that it runs without exceptions
    }

    @Test
    public void testMainFailsWithInvalidArgument() {
        try {
            GameOfLifeApp.main(new String[]{"--invalid"});
            Assert.fail("Should have thrown an exception");
        } catch (IllegalArgumentException e) {
            //expected
        }
    }

    @Test
    public void testMainWithAllValuesSet() {
        GameOfLifeApp.main(new String[]{"--rows=10", "--cols=10", "--gens=10", "--random=0.5"});
        //no assertion, just testing that it runs without exceptions
    }

}
