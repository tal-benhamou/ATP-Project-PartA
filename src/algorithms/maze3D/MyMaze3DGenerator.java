package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator {
    public Maze3D generate(int depth, int row, int column) {
        int[][][] map = new int[depth][row][column];
        Random r = new Random();
        Position3D curr = new Position3D();
        if (r.nextInt(2) == 0) { // choose randomly start cell
            curr.setDepth(0);
            curr.setRow(1);
            curr.setColumn(0);
        } else {
            curr.setDepth(0);
            curr.setColumn(0);
            curr.setRow(0);
        }
        // end choosing
        Maze3D maze = new Maze3D(map);
        maze.setStart(curr);
        Boolean[][][] visited = new Boolean[depth][row][column];
        // initialize the map with walls and without visited cells
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    maze.getMap()[i][j][k] = 1;
                    visited[i][j][k] = false;
                }
            }
        }
        ArrayList<Position3D> GoalCells = new ArrayList<>();
        ArrayList<Position3D> CloseToGoal = new ArrayList<>();
        /*iterative*/
        Stack<Position3D> StackCells = new Stack<>();
        Stack<Position3D> StackNeighbour = new Stack<>();
        Position3D neighbour;
        StackCells.push(curr);
        while (!StackCells.isEmpty()) {
            curr = StackCells.pop();
            visited[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()] = true;
            maze.getMap()[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()] = 0;

            if ((curr.getColumnIndex() == maze.getMap()[0][0].length - 2) || (curr.getDepthIndex() == maze.getMap().length - 2)) { //if columns is even we c`ant achieve the last column
                CloseToGoal.add(curr);
            }
            if ((maze.getStartPosition().getRowIndex() == 0) && (curr.getRowIndex() == maze.getMap()[0].length - 1) &&
                    (curr.getDepthIndex() != 0)) {
                GoalCells.add(curr);
            } else if ((maze.getStartPosition().getColumnIndex() == 0) && (curr.getColumnIndex() == maze.getMap()[0][0].length - 1) &&
                    (curr.getDepthIndex() != 0)) {
                GoalCells.add(curr);
            }

            insertNei(StackCells, StackNeighbour, curr, visited, depth, row, column);
            if (StackCells.isEmpty())
                continue;
            neighbour = StackCells.peek();

            if (curr.getRowIndex() - neighbour.getRowIndex() < 0) { //neighbour chosen below from the curr
                maze.getMap()[curr.getDepthIndex()][curr.getRowIndex() + 1][curr.getColumnIndex()] = 0;
                visited[curr.getDepthIndex()][curr.getRowIndex() + 1][curr.getColumnIndex()] = true;
            } else if (curr.getRowIndex() - neighbour.getRowIndex() > 0) { //neighbour chosen above from the curr
                maze.getMap()[curr.getDepthIndex()][curr.getRowIndex() - 1][curr.getColumnIndex()] = 0;
                visited[curr.getDepthIndex()][curr.getRowIndex() - 1][curr.getColumnIndex()] = true;
            } else if (curr.getColumnIndex() - neighbour.getColumnIndex() < 0) { //neighbour chosen right from the curr
                maze.getMap()[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex() + 1] = 0;
                visited[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex() + 1] = true;
            } else if (curr.getColumnIndex() - neighbour.getColumnIndex() > 0) { //neighbour chosen left from the curr
                maze.getMap()[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex() - 1] = 0;
                visited[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex() - 1] = true;
            } else if (curr.getDepthIndex() - neighbour.getDepthIndex() > 0) { // neighbour chosen inside from curr
                maze.getMap()[curr.getDepthIndex() - 1][curr.getRowIndex()][curr.getColumnIndex()] = 0;
                visited[curr.getDepthIndex() - 1][curr.getRowIndex()][curr.getColumnIndex()] = true;
            } else if (curr.getDepthIndex() - neighbour.getDepthIndex() < 0) { // neighbour chosen outside from curr
                maze.getMap()[curr.getDepthIndex() + 1][curr.getRowIndex()][curr.getColumnIndex()] = 0;
                visited[curr.getDepthIndex() + 1][curr.getRowIndex()][curr.getColumnIndex()] = true;
            }
        }

        /*choosing GoalCell*/
        if (!GoalCells.isEmpty())
            maze.setGoal(GoalCells.get(r.nextInt(GoalCells.size())));
        else { //we want to choose randomly cell close to the last column and open the wall for the goalCell.
            Position3D CloseToGoalCell;
            CloseToGoalCell = CloseToGoal.get(r.nextInt(CloseToGoal.size()));
            if (CloseToGoalCell.getDepthIndex() == maze.getMap().length - 2) {
                maze.getMap()[CloseToGoalCell.getDepthIndex() + 1][CloseToGoalCell.getRowIndex()][CloseToGoalCell.getColumnIndex()] = 0;
                visited[CloseToGoalCell.getDepthIndex() + 1][CloseToGoalCell.getRowIndex()][CloseToGoalCell.getColumnIndex()] = true;
                maze.setGoal(new Position3D(CloseToGoalCell.getDepthIndex() + 1, CloseToGoalCell.getRowIndex(), CloseToGoalCell.getColumnIndex()));
            } else {
                maze.getMap()[CloseToGoalCell.getDepthIndex()][CloseToGoalCell.getRowIndex()][CloseToGoalCell.getColumnIndex() + 1] = 0;
                visited[CloseToGoalCell.getDepthIndex()][CloseToGoalCell.getRowIndex()][CloseToGoalCell.getColumnIndex() + 1] = true;
                maze.setGoal(new Position3D(CloseToGoalCell.getDepthIndex(), CloseToGoalCell.getRowIndex(), CloseToGoalCell.getColumnIndex() + 1));
            }
        }
        randomizeBreaking(maze.getMap());
        return maze;
    }

    public void insertNei(Stack<Position3D> stackCells, Stack<Position3D> StackNeighbour, Position3D curr, Boolean[][][] visited, int d, int r, int c) {
        Random random = new Random();
        if ((curr.getColumnIndex() + 2 < c) && !visited[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex() + 2])  //right
            StackNeighbour.push(new Position3D(curr.getDepthIndex(), curr.getRowIndex(), curr.getColumnIndex() + 2));
        if ((curr.getRowIndex() + 2 < r) && !visited[curr.getDepthIndex()][curr.getRowIndex() + 2][curr.getColumnIndex()]) //down
            StackNeighbour.push(new Position3D(curr.getDepthIndex(), curr.getRowIndex() + 2, curr.getColumnIndex()));
        if ((curr.getColumnIndex() - 2 >= 0) && !visited[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex() - 2]) // left
            StackNeighbour.push(new Position3D(curr.getDepthIndex(), curr.getRowIndex(), curr.getColumnIndex() - 2));
        if (curr.getRowIndex() - 2 >= 0 && !visited[curr.getDepthIndex()][curr.getRowIndex() - 2][curr.getColumnIndex()]) //up
            StackNeighbour.push(new Position3D(curr.getDepthIndex(), curr.getRowIndex() - 2, curr.getColumnIndex()));
        if ((curr.getDepthIndex() + 2 < d) && (!visited[curr.getDepthIndex() + 2][curr.getRowIndex()][curr.getColumnIndex()])) //inside
            StackNeighbour.push(new Position3D(curr.getDepthIndex() + 2, curr.getRowIndex(), curr.getColumnIndex()));
        if ((curr.getDepthIndex() - 2 >= 0) && (!visited[curr.getDepthIndex() - 2][curr.getRowIndex()][curr.getColumnIndex()])) // outside
            StackNeighbour.push(new Position3D(curr.getDepthIndex() - 2, curr.getRowIndex(), curr.getColumnIndex()));
        while (!StackNeighbour.isEmpty()) {
            stackCells.push(StackNeighbour.remove(random.nextInt(StackNeighbour.size()))); // insert in random order to the stack cells
        }
    }

    public void randomizeBreaking(int[][][] map) {
        Random r = new Random();
        int row = map[0].length;
        int col = map[0][0].length;
        int dep = map.length;

        if (row % 2 == 0) {
            for (int i = 0; i < dep; i++) {
                for (int j = 0; j < col; j++) {
                    if (r.nextInt(2) == 0)
                        map[i][row - 1][j] = 0;
                }
            }
        }
        if (col % 2 == 0) {
            for (int i = 0; i < dep; i++) {
                for (int j = 0; j < row; j++) {
                    if (r.nextInt(2) == 0)
                        map[i][j][col - 1] = 0;
                }
            }
        }
        if (dep % 2 == 0) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (r.nextInt(2) == 0)
                        map[dep - 1][i][j] = 0;
                }
            }
        }
    }
}
