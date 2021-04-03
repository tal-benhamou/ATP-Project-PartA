package algorithms.mazeGenerators;

import java.util.*;

public class MyMazeGenerator extends AMazeGenerator {
    /**
     * @param rows    number of rows
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
            curr.setRow(1);
            curr.setColumn(0);
        } else {
            curr.setColumn(0);
            curr.setRow(0);
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
        ArrayList<Position> CloseToGoal = new ArrayList<>();
        /*iterative*/
        Stack<Position> StackCells = new Stack<>();
        Stack<Position> StackNeighbour = new Stack<>();
        Position neighbour;
        StackCells.push(curr);
        while (!StackCells.isEmpty()) {
            curr = StackCells.pop();
            visited[curr.getRowIndex()][curr.getColumnIndex()] = true;
            maze.getMap()[curr.getRowIndex()][curr.getColumnIndex()] = 0;

            if (curr.getColumnIndex() == maze.getMap()[0].length - 2) { //if columns is even we c`ant achieve the last column
                CloseToGoal.add(curr);
            }
            if ((maze.getStartPosition().getRowIndex() == 0) && (curr.getRowIndex() == maze.getMap().length - 1)) {
                GoalCells.add(curr);
            } else if ((maze.getStartPosition().getColumnIndex() == 0) && (curr.getColumnIndex() == maze.getMap()[0].length - 1)) {
                GoalCells.add(curr);
            }

            insertNei(StackCells, StackNeighbour, curr, visited, rows, columns);
            if (StackCells.isEmpty())
                continue;
            neighbour = StackCells.peek();

            if (curr.getRowIndex() - neighbour.getRowIndex() < 0) { //neighbour chosen below from the curr
                maze.getMap()[curr.getRowIndex() + 1][curr.getColumnIndex()] = 0;
                visited[curr.getRowIndex() + 1][curr.getColumnIndex()] = true;
            } else if (curr.getRowIndex() - neighbour.getRowIndex() > 0) { //neighbour chosen above from the curr
                maze.getMap()[curr.getRowIndex() - 1][curr.getColumnIndex()] = 0;
                visited[curr.getRowIndex() - 1][curr.getColumnIndex()] = true;
            } else if (curr.getColumnIndex() - neighbour.getColumnIndex() < 0) { //neighbour chosen right from the curr
                maze.getMap()[curr.getRowIndex()][curr.getColumnIndex() + 1] = 0;
                visited[curr.getRowIndex()][curr.getColumnIndex() + 1] = true;
            } else if (curr.getColumnIndex() - neighbour.getColumnIndex() > 0) { //neighbour chosen left from the curr
                maze.getMap()[curr.getRowIndex()][curr.getColumnIndex() - 1] = 0;
                visited[curr.getRowIndex()][curr.getColumnIndex() - 1] = true;
            }
        }
        /*choosing GoalCell*/
        if (!GoalCells.isEmpty())
            maze.setGoal(GoalCells.get(r.nextInt(GoalCells.size())));
        else { //we want to choose randomly cell close to the last column and open the wall for the goalCell.
            Position CloseToGoalCell;
            CloseToGoalCell = CloseToGoal.get(r.nextInt(CloseToGoal.size()));
            maze.getMap()[CloseToGoalCell.getRowIndex()][CloseToGoalCell.getColumnIndex() + 1] = 0;
            visited[CloseToGoalCell.getRowIndex()][CloseToGoalCell.getColumnIndex() + 1] = true;
            maze.setGoal(new Position(CloseToGoalCell.getRowIndex(), CloseToGoalCell.getColumnIndex() + 1));
        }
        randomizeBreaking(maze.getMap());
        return maze;
        /*iterative DONE*/
    }

    /**
     * @param stackCells     Stack Of All Cells
     * @param StackNeighbour Stack of neighbours
     * @param curr           the current cell
     * @param visited        present 2D array of visited cells
     * @param r              present the number of rows
     * @param c              present the number of columns
     */
    private void insertNei(Stack<Position> stackCells, Stack<Position> StackNeighbour, Position curr, Boolean[][] visited, int r, int c) {
        Random random = new Random();
        if ((curr.getColumnIndex() + 2 < c) && !visited[curr.getRowIndex()][curr.getColumnIndex() + 2]) {
            StackNeighbour.push(new Position(curr.getRowIndex(), curr.getColumnIndex() + 2));
        }
        if ((curr.getRowIndex() + 2 < r) && !visited[curr.getRowIndex() + 2][curr.getColumnIndex()]) {
            StackNeighbour.push(new Position(curr.getRowIndex() + 2, curr.getColumnIndex()));
        }
        if ((curr.getColumnIndex() - 2 >= 0) && !visited[curr.getRowIndex()][curr.getColumnIndex() - 2]) {
            StackNeighbour.push(new Position(curr.getRowIndex(), curr.getColumnIndex() - 2));
        }
        if (curr.getRowIndex() - 2 >= 0 && !visited[curr.getRowIndex() - 2][curr.getColumnIndex()]) {
            StackNeighbour.push(new Position(curr.getRowIndex() - 2, curr.getColumnIndex()));
        }
        while (!StackNeighbour.isEmpty()) {
            stackCells.push(StackNeighbour.remove(random.nextInt(StackNeighbour.size()))); // insert in random order to the stack cells
        }
    }

    private void randomizeBreaking(int[][] map) {
        Random r = new Random();
        int row = map.length;
        int col = map[0].length;
        if (row % 2 == 0) {
            for (int i = 0; i < col; i++) {
                if (r.nextInt(2) == 0)
                    map[row - 1][i] = 0;
            }
        }
        if (col % 2 == 0) {
            for (int i = 0; i < row; i++) {
                if (r.nextInt(2) == 0)
                    map[i][col - 1] = 0;
            }
        }
    }


}


