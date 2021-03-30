package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        int[][] map = new int[rows][columns];
        Maze maze = new Maze(map);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                    maze.getMap()[i][j] = 0;
            }
        }
        return maze;
    }
}
