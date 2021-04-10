package algorithms.mazeGenerators;

import algorithms.maze3D.Position3D;
import javafx.geometry.Pos;

import java.util.*;

public class MyMazeGenerator extends AMazeGenerator {
    /**
     * @param rows    number of rows
     * @param columns number of columns
     * @return Maze
     * we choose the Randomized depth-first search
     * creating a interesting and not simple maze
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
        ArrayList<Position> CloseToGoalColumn = new ArrayList<>();
        ArrayList<Position> CloseToGoalRow = new ArrayList<>();
        int currRow, currCol;
        /*iterative*/
        Stack<Position[]> StackCells = new Stack<>();//ArrayList consider parent in index 0 and position in index 1
        Stack<Position[]> StackNeighbour = new Stack<>();
        Position[] PosCurr = new Position[2];
        PosCurr[0] = null;//start goal dont have a parent
        PosCurr[1] = curr;
        StackCells.push(PosCurr);
        while (!StackCells.isEmpty()) {
            PosCurr = StackCells.peek();
            curr = StackCells.pop()[1];

            currRow = curr.getRowIndex();
            currCol = curr.getColumnIndex();

            if (visited[currRow][currCol])
                continue;
            //breaking wall and turn on the visited of the position.
            visited[currRow][currCol] = true;
            maze.getMap()[currRow][currCol] = 0;
            //breaking wall of the cell near to the new visited position.

            if (currCol == maze.getMap()[0].length - 2) //if columns is even we c`ant achieve the last column
                CloseToGoalColumn.add(curr);
            if (currRow == maze.getMap().length - 2) //if rows is even we c`ant achieve the last row
                CloseToGoalRow.add(curr);

            if ((maze.getStartPosition().getRowIndex() == 0) && (currRow == maze.getMap().length - 1)) {
                GoalCells.add(curr);
            }
            else if ((maze.getStartPosition().getRowIndex() == 1) && (currCol == maze.getMap()[0].length - 1)){
                GoalCells.add(curr);
            }
            BreakTheWall(PosCurr,visited,maze);
            insertNei(StackCells, StackNeighbour, curr, visited, rows, columns);
        }
        //choosing GoalCell
        Position CloseToGoalCell;
        int gRow, gCol;
        if ((rows % 2 == 0) && (maze.getStartPosition().getRowIndex() == 0)){ //choose from close to goal ROWS
            CloseToGoalCell = CloseToGoalRow.get(r.nextInt(CloseToGoalRow.size()));
            gRow = CloseToGoalCell.getRowIndex();
            gCol = CloseToGoalCell.getColumnIndex();

            maze.getMap()[gRow + 1][gCol] = 0;
            visited[gRow + 1][gCol] = true;
            maze.setGoal(new Position(gRow + 1, gCol));
        }
        else if ((columns % 2 == 0) && (maze.getStartPosition().getRowIndex() == 1)){ //choose from close to goal COLUMNS
            CloseToGoalCell = CloseToGoalColumn.get(r.nextInt(CloseToGoalColumn.size()));

            gRow = CloseToGoalCell.getRowIndex();
            gCol = CloseToGoalCell.getColumnIndex();

            maze.getMap()[gRow][gCol + 1] = 0;
            visited[gRow][gCol + 1] = true;
            maze.setGoal(new Position(gRow, gCol + 1));
        }
        else { // standard option
            if (!GoalCells.isEmpty())
                maze.setGoal(GoalCells.get(r.nextInt(GoalCells.size())));
        }

        randomizeBreaking(maze);
        return maze;
        /*iterative DONE*/
    }

    /**
     * @param PosCurr array of 2 Position - index 0 = parent, index 1 = curr
     * @param visited struct that marker the visited cells
     * @param maze the maze
     *             the method breaking the wall between the curr cell and his parent
     */
    private void BreakTheWall(Position[] PosCurr, Boolean[][] visited,Maze maze) {
        Position curr = PosCurr[1];
        Position parent = PosCurr[0];
        if (parent != null) {
            int currCol,currRow, pCol, pRow;
            pCol = parent.getColumnIndex();
            pRow = parent.getRowIndex();
            currCol = curr.getColumnIndex();
            currRow = curr.getRowIndex();

            if (currRow - pRow < 0) { //parent below from the curr Position
                maze.getMap()[currRow + 1][currCol] = 0;
                visited[currRow + 1][currCol] = true;
            } else if (currRow - pRow > 0) { //parent above from the curr Position
                maze.getMap()[currRow - 1][currCol] = 0;
                visited[currRow - 1][currCol] = true;
            } else if (currCol - pCol < 0) { //parent right from the curr Position
                maze.getMap()[currRow][currCol + 1] = 0;
                visited[currRow][currCol + 1] = true;
            } else if (currCol - pCol > 0) { //parent left from the curr Position
                maze.getMap()[currRow][currCol - 1] = 0;
                visited[currRow][currCol - 1] = true;
            }
        }
    }

    /**
     * @param stackCells     Stack Of All Cells
     * @param StackNeighbour Stack of neighbours
     * @param curr           the current cell
     * @param visited        present 2D array of visited cells
     * @param r              present the number of rows
     * @param c              present the number of columns
     *        the method insert all possible neighbours of curr to the StackCells
     */
    private void insertNei(Stack<Position[]> stackCells,Stack<Position[]> StackNeighbour, Position curr, Boolean[][] visited, int r, int c) {
        Random random = new Random();
        int currCol, currRow;
        currCol = curr.getColumnIndex();
        currRow = curr.getRowIndex();
        if ((currCol + 2 < c) && !visited[currRow][currCol + 2]) {
            Position[] nei1 = new Position[2];
            nei1[0] = curr;//add the parent
            nei1[1] = (new Position(currRow, currCol + 2));
            StackNeighbour.push(nei1);
        }
        if ((currRow + 2 < r) && !visited[currRow + 2][currCol]) {
            Position[] nei2 = new Position[2];
            nei2[0] = (curr);//add the parent
            nei2[1] = (new Position(currRow + 2, currCol));
            StackNeighbour.push(nei2);
        }
        if ((currCol - 2 >= 0) && !visited[currRow][currCol - 2]) {
            Position[] nei3 = new Position[2];
            nei3[0] = (curr);//add the parent
            nei3[1] = (new Position(currRow, currCol - 2));
            StackNeighbour.push(nei3);
        }
        if (currRow - 2 >= 0 && !visited[currRow - 2][currCol]) {
            Position[] nei4 = new Position[2];
            nei4[0] = (curr);//add the parent
            nei4[1] = (new Position(currRow - 2, currCol));
            StackNeighbour.push(nei4);
        }
        while (!StackNeighbour.isEmpty()) {
            stackCells.push(StackNeighbour.remove(random.nextInt(StackNeighbour.size()))); // insert in random order to the stack cells
        }
    }

    /**
     * @param maze the maze
     *            if the rows or the columns are even we can't achieve the frame of the maze
     *          so we randomly breaking walls from the frame of the maze
     */
    private void randomizeBreaking(Maze maze) {
        Random r = new Random();
        int row = maze.getMap().length;
        int col = maze.getMap()[0].length;
        if (((row % 2 == 0) && (maze.getStartPosition().getRowIndex() == 0)) || ((row % 2 == 1) && maze.getStartPosition().getRowIndex() == 1)){
            for (int i = 0; i < col; i++) {
                if (r.nextInt(2) == 0)
                    maze.getMap()[row - 1][i] = 0;
            }
        }
        if (col % 2 == 0){
            for (int i = 0; i < row; i++) {
                if (r.nextInt(2) == 0)
                    maze.getMap()[i][col - 1] = 0;
            }
        }

    }
}
