package algorithms.mazeGenerators;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator{
    /**
     * @param rows number of rows
     * @param columns number of columns
     * @return Maze
     * we choose the Randomized depth-first search
     */
    @Override
    public Maze generate(int rows, int columns) {
        int[][] map = new int[rows][columns];
        Random r = new Random();
        Position curr = new Position();
        if (r.nextInt(2)-1 == 0) { // choose randomly start cell
            curr.setRow(0);
            curr.setColumn(r.nextInt(columns+1) - 1);
        }
        else {
            curr.setColumn(0);
            curr.setRow(r.nextInt(rows + 1) - 1);
        }
        // end choosing
        Maze maze = new Maze(map);
        maze.setStart(curr);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze.getMap()[i][j] = 1;
            }
        }
        Boolean[][] visited = new Boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                visited[i][j] = false;
            }
        }

        GenerateRec(maze,visited, curr);


        return maze;
    }

    private void GenerateRec(Maze maze ,Boolean[][] visited ,Position curr) {
        visited[curr.getRowIndex()][curr.getColumnIndex()] = true;
        Stack<Position> stackNeighbour = new Stack<>();
        insertNei(stackNeighbour, curr,visited, maze.getMap().length, maze.getMap()[0].length);

    }

    /**
     * @param stackNeighbour stack of neighbours
     * @param curr the current cell
     * @param r present the number of rows
     * @param c present the number of columns
     * @param visited present 2D array of visited cells
     *          insert the cell's neighbours to stack
     */
    private void insertNei(Stack<Position> stackNeighbour, Position curr, Boolean[][] visited, int r, int c) {

        if (curr.getRowIndex() - 1 >= 0 && !visited[curr.getRowIndex() - 1][curr.getColumnIndex()]){

        }



    }
}
