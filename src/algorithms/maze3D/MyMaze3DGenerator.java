package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import javafx.geometry.Pos;

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
        int currRow, currCol, currDepth;
        /*iterative*/
        Stack<Position3D[]> StackCells = new Stack<>();
        Stack<Position3D[]> StackNeighbour = new Stack<>();
        Position3D[] PosCurr = new Position3D[2];
        PosCurr[0] = null; // StartPosition dont have parent
        PosCurr[1] = curr;
        StackCells.push(PosCurr);
        while (!StackCells.isEmpty()) {
            PosCurr = StackCells.peek();
            curr = StackCells.pop()[1];

            currRow = curr.getRowIndex();
            currCol = curr.getColumnIndex();
            currDepth = curr.getDepthIndex();

            if (visited[currDepth][currRow][currCol])
                continue;

            visited[currDepth][currRow][currCol] = true;
            maze.getMap()[currDepth][currRow][currCol] = 0;

            if ((currCol == maze.getMap()[0][0].length - 2) || (currDepth == maze.getMap().length - 2)) { //if columns is even we cant achieve the last column
                CloseToGoal.add(curr);
            }
            if ((maze.getStartPosition().getRowIndex() == 0) && (currRow == maze.getMap()[0].length - 1) &&
                    (currDepth != 0)) {
                GoalCells.add(curr);
            } else if ((maze.getStartPosition().getColumnIndex() == 0) && (currCol == maze.getMap()[0][0].length - 1) &&
                    (currDepth != 0)) {
                GoalCells.add(curr);
            }

            insertNei(StackCells, StackNeighbour, curr, visited, depth, row, column);
            if (StackCells.isEmpty())
                continue;
            BreakTheWall(PosCurr, visited, maze);
        }

        /*choosing GoalCell*/
        if (!GoalCells.isEmpty())
            maze.setGoal(GoalCells.get(r.nextInt(GoalCells.size())));
        else { //we want to choose randomly cell close to the last column and open the wall for the goalCell.
            Position3D CloseToGoalCell;
            int gRow, gCol, gDepth;
            CloseToGoalCell = CloseToGoal.get(r.nextInt(CloseToGoal.size()));
            gRow = CloseToGoalCell.getRowIndex();
            gCol = CloseToGoalCell.getColumnIndex();
            gDepth = CloseToGoalCell.getDepthIndex();
            if (gDepth == maze.getMap().length - 2) {
                maze.getMap()[gDepth + 1][gRow][gCol] = 0;
                visited[gDepth + 1][gRow][gCol] = true;
                maze.setGoal(new Position3D(gDepth + 1, gRow, gCol));
            } else {
                maze.getMap()[gDepth][gRow][gCol + 1] = 0;
                visited[gDepth][gRow][gCol + 1] = true;
                maze.setGoal(new Position3D(gDepth, gRow, gCol + 1));
            }
        }
        randomizeBreaking(maze.getMap());
        return maze;
    }


    public void insertNei(Stack<Position3D[]> stackCells, Stack<Position3D[]> StackNeighbour, Position3D curr, Boolean[][][] visited, int d, int r, int c) {
        Random random = new Random();
        int currCol, currDepth, currRow;
        currCol = curr.getColumnIndex();
        currDepth = curr.getDepthIndex();
        currRow = curr.getRowIndex();
        if ((currCol + 2 < c) && !visited[currDepth][currRow][currCol + 2]) { //right
            Position3D[] nei1 = new Position3D[2];
            nei1[0] = (curr);//add the parent
            nei1[1] = (new Position3D(currDepth, currRow, currCol + 2));
            StackNeighbour.push(nei1);
        }
        if ((currRow + 2 < r) && !visited[currDepth][currRow + 2][currCol]) { //down
            Position3D[] nei2 = new Position3D[2];
            nei2[0] = (curr);//add the parent
            nei2[1] = (new Position3D(currDepth, currRow+ 2, currCol));
            StackNeighbour.push(nei2);
        }
        if ((currCol - 2 >= 0) && !visited[currDepth][currRow][currCol - 2]) { // left
            Position3D[] nei3 = new Position3D[2];
            nei3[0] = (curr);//add the parent
            nei3[1] = (new Position3D(currDepth, currRow, currCol - 2));
            StackNeighbour.push(nei3);
        }
        if (currRow - 2 >= 0 && !visited[currDepth][currRow - 2][currCol]) { //up
            Position3D[] nei4 = new Position3D[2];
            nei4[0] = (curr);//add the parent
            nei4[1] = (new Position3D(currDepth, currRow - 2, currCol));
            StackNeighbour.push(nei4);
        }
        if ((currDepth + 2 < d) && (!visited[currDepth + 2][currRow][currCol])) { //inside
            Position3D[] nei5 = new Position3D[2];
            nei5[0] = (curr);//add the parent
            nei5[1] = (new Position3D(currDepth + 2, currRow, currCol));
            StackNeighbour.push(nei5);
        }
        if ((currDepth - 2 >= 0) && (!visited[currDepth - 2][currRow][currCol])) { // outside
            Position3D[] nei6 = new Position3D[2];
            nei6[0] = (curr);//add the parent
            nei6[1] = (new Position3D(currDepth - 2, currRow, currCol));
            StackNeighbour.push(nei6);
        }
        while (!StackNeighbour.isEmpty()) {
            stackCells.push(StackNeighbour.remove(random.nextInt(StackNeighbour.size()))); // insert in random order to the stack cells
        }
    }

    private void BreakTheWall(Position3D[] PosCurr, Boolean[][][] visited, Maze3D maze) {
        Position3D curr = PosCurr[1];
        Position3D parent = PosCurr[0];
        if (parent != null) {
            int currCol,currDepth,currRow, pCol, pDepth, pRow;
            pCol = parent.getColumnIndex();
            pDepth = parent.getDepthIndex();
            pRow = parent.getRowIndex();
            currCol = curr.getColumnIndex();
            currDepth = curr.getDepthIndex();
            currRow = curr.getRowIndex();

            if (currRow - pRow < 0) { //neighbour chosen below from the curr
                maze.getMap()[currDepth][currRow + 1][currCol] = 0;
                visited[currDepth][currRow + 1][currCol] = true;
            } else if (currRow - pRow > 0) { //neighbour chosen above from the curr
                maze.getMap()[currDepth][currRow - 1][currCol] = 0;
                visited[currDepth][currRow - 1][currCol] = true;
            } else if (currCol - pCol < 0) { //neighbour chosen right from the curr
                maze.getMap()[currDepth][currRow][currCol + 1] = 0;
                visited[currDepth][currRow][currCol + 1] = true;
            } else if (currCol - pCol > 0) { //neighbour chosen left from the curr
                maze.getMap()[currDepth][currRow][currCol - 1] = 0;
                visited[currDepth][currRow][currCol - 1] = true;
            } else if (currDepth - pDepth > 0) { // neighbour chosen inside from curr
                maze.getMap()[currDepth - 1][currRow][currCol] = 0;
                visited[currDepth - 1][currRow][currCol] = true;
            } else if (currDepth - pDepth < 0) { // neighbour chosen outside from curr
                maze.getMap()[currDepth + 1][currRow][currCol] = 0;
                visited[currDepth + 1][currRow][currCol] = true;
            }
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
