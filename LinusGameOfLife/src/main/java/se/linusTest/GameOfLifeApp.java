package se.linusTest;


import static se.linusTest.Grid.createGridWithRandomAliveCells;

public class GameOfLifeApp {

    private static int gens = Utils.DEFAULT_GENS;

    public static void main(String[] args) {

        Grid gridFromArgs = createGridFromArgs(args);

        runGame(gridFromArgs, gens);
    }

    private static Grid createGridFromArgs(String[] args) {

        int rows = 0;
        int cols = 0;

        double random = Utils.DEFAULT_RANDOM;


        if (args.length > 0) {
            /**
             * The command-line arguments and their values.
             */
            for (String arg : args) {
                int i;
                if (arg.contains("=")) {
                    i = arg.indexOf("=");
                } else {
                    throw new IllegalArgumentException("Invalid argument: " + arg + " (arguments should be in the form --key=value)");
                }
                if (arg.startsWith("--rows")) {
                    rows = Integer.parseInt(arg.substring(i + 1));
                } else if (arg.startsWith("--cols")) {
                    cols = Integer.parseInt(arg.substring(i + 1));
                } else if (arg.startsWith("--gens")) {
                    gens = Integer.parseInt(arg.substring(i + 1));
                } else if (arg.startsWith("--random")) {
                    random = Double.parseDouble(arg.substring(i + 1));
                } else {
                    continue; //ignore arguments that are not implemented "--"
                }
            }
        } else {
            System.out.println("No arguments provided, using default values.");
        }

        if (rows == 0 || cols == 0) { //if not set, use default grid
            System.out.println("No grid size provided, using default grid.");
            return createGridWithRandomAliveCells(Utils.DEFAULT_GRID_30_X_20.length, Utils.DEFAULT_GRID_30_X_20[0].length, random);
        } else {
            return createGridWithRandomAliveCells(rows, cols, random);
        }

    }


    public static void runGame(Grid grid, int numberOfGens) {

        printGen(grid, 0);
        for (int i = 1; i < numberOfGens; i++) {
            System.out.println();
            grid.nextGen();
            printGen(grid, i);
        }
    }


    private static void printGen(Grid grid, int genNumber) {
        System.out.println("Generation #" + genNumber);
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                if (grid.isAlive(i, j)) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
}