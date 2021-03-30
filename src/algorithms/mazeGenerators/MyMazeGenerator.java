package algorithms.mazeGenerators;

import java.util.*;

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
        if (r.nextInt(2) == 0) { // choose randomly start cell
            curr.setRow(0);
            curr.setColumn(1);
        }
        else {
            curr.setColumn(0);
            curr.setRow(1);
        }
        // end choosing
        Maze maze = new Maze(map);
        maze.setStart(curr);
        Boolean[][] visited = new Boolean[rows][columns];
        // initialize the map with walls and without visited cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze.getMap()[i][j] = 1;
                visited[i][j] = false;
            }
        }
        ArrayList<Position> GoalCells = new ArrayList<>();
        GenerateRec(maze,visited, curr, GoalCells);

        maze.setGoal(GoalCells.get(r.nextInt(GoalCells.size())));

        return maze;
    }

    private void GenerateRec(Maze maze ,Boolean[][] visited ,Position curr ,ArrayList<Position> GoalCells) {
        visited[curr.getRowIndex()][curr.getColumnIndex()] = true;
        if ((maze.getGoalPosition() == null) && ((curr.getRowIndex() == maze.getMap().length - maze.getStartPosition().getRowIndex()) ||
                (curr.getColumnIndex() == maze.getMap()[0].length - maze.getStartPosition().getColumnIndex())))
        {
            GoalCells.add(curr);
        }
        maze.getMap()[curr.getRowIndex()][curr.getColumnIndex()] = 0;
        Stack<Position> StackNeighbour = new Stack<>();
        insertNei(StackNeighbour, curr,visited, maze.getMap().length, maze.getMap()[0].length);
        Random r = new Random();
        if (StackNeighbour.isEmpty())
            return;
        int random = r.nextInt(StackNeighbour.size()) ;// randomly number of removing neighbours
        int size = StackNeighbour.size();
        for (int i = 0; i < size - random - 1; i++) {
            visited[StackNeighbour.peek().getRowIndex()][StackNeighbour.peek().getColumnIndex()] = true;
            StackNeighbour.pop();
        }
        while(!StackNeighbour.isEmpty()) {
            GenerateRec(maze, visited, StackNeighbour.get(0), GoalCells);
            StackNeighbour.pop();
        }

    }

    /**
     * @param StackNeighbour Stack of neighbours
     * @param curr the current cell
     * @param r present the number of rows
     * @param c present the number of columns
     * @param visited present 2D array of visited cells
     *          insert the cell's neighbours to Stack
     */
    private void insertNei(Stack<Position> StackNeighbour, Position curr, Boolean[][] visited, int r, int c) {


        if ((curr.getColumnIndex() + 1 < c) && !visited[curr.getRowIndex()][curr.getColumnIndex() + 1]) {
            StackNeighbour.push(new Position(curr.getRowIndex(), curr.getColumnIndex() + 1));
         //   visited[curr.getRowIndex()][curr.getColumnIndex() + 1] = true;
        }
        if ((curr.getRowIndex() + 1 < r) && !visited[curr.getRowIndex() + 1][curr.getColumnIndex()]) {
            StackNeighbour.push(new Position(curr.getRowIndex() + 1, curr.getColumnIndex()));
         //   visited[curr.getRowIndex() + 1][curr.getColumnIndex()] = true;
        }
        if ((curr.getColumnIndex() - 1 >= 0) && !visited[curr.getRowIndex()][curr.getColumnIndex() - 1]) {
            StackNeighbour.push(new Position(curr.getRowIndex(), curr.getColumnIndex() - 1));
        //    visited[curr.getRowIndex()][curr.getColumnIndex() - 1] = true;
        }
        if (curr.getRowIndex() - 1 >= 0 && !visited[curr.getRowIndex() - 1][curr.getColumnIndex()]) {
            StackNeighbour.push(new Position(curr.getRowIndex() - 1, curr.getColumnIndex()));
        //    visited[curr.getRowIndex() - 1][curr.getColumnIndex()] = true;
        }


    }






}


