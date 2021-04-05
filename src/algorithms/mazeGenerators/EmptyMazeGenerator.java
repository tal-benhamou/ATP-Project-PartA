package algorithms.mazeGenerators;

import java.util.Random;

public class EmptyMazeGenerator extends AMazeGenerator{
    /**
     * @param rows number of rows
     * @param columns number of columns
     * @return empty maze
     * here we generating an empty maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        int[][] map = new int[rows][columns];
        Maze maze = new Maze(new Position(0,0), new Position(rows - 1, columns - 1), map);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                    maze.getMap()[i][j] = 0 ;
            }
        }
        return maze;
    }
}
