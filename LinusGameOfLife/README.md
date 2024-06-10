# Overview

A Java implementation of The Game of Life.

# How to run

Navigate to location of jar and run it from the command line:

    java -jar LinusGameOfLife-1.0-SNAPSHOT.jar --rows=30 --cols=20 --gens=100 --random=0.25

The parameters are optional and can be omitted. The default values are:

    DEFAULT_GENS (10 generations)
    DEFAULT_RANDOM (0.10 chance of a cell being alive)

If rows or columns are not specified, the program will default to a 30x20 grid with predefined values of alive cells.

# How to build

To build the project, navigate to the project root and run the following Maven command:

    mvn clean package

This will generate a runnable JAR file.