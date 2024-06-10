package se.linusTest;

public class Grid {
    /**
     * The grid of cells.
     */
    private int[][] grid;

    /**
     * Constructs a new grid.
     */
    public Grid(int[][] grid) {
        this.grid = grid;
    }

    /**
     * Gets the number of rows in the grid
     *
     * @return the number of rows in the grid
     */
    public int getRows() {
        return grid.length;
    }

    /**
     * Gets the number of columns in the grid.
     *
     * @return the number of columns in the grid
     */
    public int getCols() {
        return grid[0].length;
    }

    public int[][] getGrid() {
        return grid;
    }

    /**
     * Determines how many cells surrounding the given cell are alive.
     *
     * @param row the cell's row
     * @param col the cell's column
     * @return the number of cells surrounding the given cell that are alive
     */
    public int getAliveNeighbours(int row, int col) {
        int aliveNeighbours = 0;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if ((row + i >= 0 && row + i < this.getRows()) && (col + j >= 0 && col + j < this.getCols()))
                    aliveNeighbours += this.getGrid()[row + i][col + j];


        // The cell needs to be subtracted from
        // its neighbours as it was counted before
        aliveNeighbours -= this.getGrid()[row][col];
        return aliveNeighbours;
    }

    /**
     * Marks a cell as being alive (1) or dead (0)
     *
     * @param row   the cell's row
     * @param col   the cell's column
     * @param alive true to mark the cell as alive, false to mark it as dead
     */
    public void setAlive(int row, int col, boolean alive) {
        grid[row][col] = alive ? 1 : 0;
    }

    /**
     * Determines whether a cell is alive or dead
     *
     * @param row the cell's row
     * @param col the cell's column
     * @return true if the cell is alive, false if not
     */
    public boolean isAlive(int row, int col) {
        return grid[row][col] == 1;
    }

    /**
     * Moves the grid to the next generation
     */
    public void nextGen() {

        Grid futureGrid = new Grid(new int[this.getRows()][this.getCols()]);
        // Loop through every row
        for (int r = 0; r < this.getRows(); r++) {
            // Loop through every column(cell)
            for (int c = 0; c < this.getCols(); c++) {
                // finding number of neighbours that are alive

                int aliveNeighbours = this.getAliveNeighbours(r, c);

                // Implementing the Rules of Life

                // Cell is lonely and dies
                if ((this.getGrid()[r][c] == 1) && (aliveNeighbours < 2)) {
                    futureGrid.setAlive(r, c, false);
                }
                // Cell dies due to over population
                else if ((this.getGrid()[r][c] == 1) && (aliveNeighbours > 3)) {
                    futureGrid.setAlive(r, c, false);
                }
                // A new cell is born
                else if ((this.getGrid()[r][c] == 0) && (aliveNeighbours == 3)) {
                    futureGrid.setAlive(r, c, true);
                }
                // Remains the same
                else {
                    futureGrid.setAlive(r, c, this.getGrid()[r][c] == 1);
                }

            }
        }
        grid = futureGrid.getGrid();
    }

    /**
     * Creates a grid with random alive cells.
     *
     * @param rows   the number of rows in the grid
     * @param cols   the number of columns in the grid
     * @param random the probability that a cell is alive
     */
    public static Grid createGridWithRandomAliveCells(int rows, int cols, double random) {
        int[][] grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Math.random() < random ? 1 : 0;
            }
        }
        return new Grid(grid);
    }

}
