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
            curr.setRow(1);
            curr.setColumn(0);
        }

        else {
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

        //ArrayList<Position> StackDfs = new ArrayList<>();
        //StackDfs.add(curr);
        GenerateRec(maze,visited, curr, GoalCells,CloseToGoal);
        if (!GoalCells.isEmpty())
            maze.setGoal(GoalCells.get(r.nextInt(GoalCells.size())));
        else { //we want to choose randomly cell close to the last column and open the wall for the goalCell.
            Position CloseToGoalCell;
            CloseToGoalCell = CloseToGoal.get(r.nextInt(CloseToGoal.size()));
            maze.getMap()[CloseToGoalCell.getRowIndex()][CloseToGoalCell.getColumnIndex()+1] = 0;
            visited[CloseToGoalCell.getRowIndex()][CloseToGoalCell.getColumnIndex()+1] = true;
            Position TheGoalCell = new Position();
            TheGoalCell.setRow(CloseToGoalCell.getRowIndex());
            TheGoalCell.setColumn(CloseToGoalCell.getColumnIndex()+1);
            maze.setGoal(TheGoalCell);
        }
        return maze;
    }

    private void GenerateRec(Maze maze, Boolean[][] visited, Position curr, ArrayList<Position> GoalCells, ArrayList<Position> CloseToGoal) {
        visited[curr.getRowIndex()][curr.getColumnIndex()] = true;
        maze.getMap()[curr.getRowIndex()][curr.getColumnIndex()] = 0;

        if (curr.getColumnIndex() == maze.getMap()[0].length-2){ //if columns is odd we c`ant achieve the last column
            CloseToGoal.add(curr);
        }
        if ((maze.getStartPosition().getRowIndex()==0)&&(curr.getRowIndex()==maze.getMap().length-1)){
            GoalCells.add(curr);
        }
        else if ((maze.getStartPosition().getColumnIndex()==0)&&(curr.getColumnIndex()==maze.getMap()[0].length-1)){
            GoalCells.add(curr);
        }
        /*
        if ((maze.getGoalPosition() == null) && ((curr.getRowIndex() == maze.getMap().length - maze.getStartPosition().getRowIndex()) ||
                (curr.getColumnIndex() == maze.getMap()[0].length - maze.getStartPosition().getColumnIndex())))
        {
            GoalCells.add(curr);
        }
        */
        //maze.getMap()[curr.getRowIndex()][curr.getColumnIndex()] = 0;
        Stack<Position> StackNeighbour = new Stack<>();
        insertNei(StackNeighbour, curr,visited, maze.getMap().length, maze.getMap()[0].length);
        //Random r = new Random();
        if (StackNeighbour.isEmpty()) {
            //StackDfs.remove(curr);
            return;
        }
        //int random = r.nextInt(StackNeighbour.size()) ;// randomly number of removing neighbours
        /*
        int size = StackNeighbour.size();
        for (int i = 0; i < size - random - 1; i++) {
            visited[StackNeighbour.peek().getRowIndex()][StackNeighbour.peek().getColumnIndex()] = true;
            StackNeighbour.pop();
        }
        */

        while(!StackNeighbour.isEmpty()) {
            Position neighbour;
            Random r = new Random();
            int random = r.nextInt(StackNeighbour.size());
            neighbour = StackNeighbour.get(random);
            if (visited[neighbour.getRowIndex()][neighbour.getColumnIndex()]){
                StackNeighbour.remove(neighbour);
                continue;
            }
            if (curr.getRowIndex()-neighbour.getRowIndex()<0){ //neighbour chosen randomly below from the curr
                maze.getMap()[curr.getRowIndex()+1][curr.getColumnIndex()] = 0;
                visited[curr.getRowIndex()+1][curr.getColumnIndex()] = true;
            }
            else if (curr.getRowIndex()-neighbour.getRowIndex()>0){ //neighbour chosen randomly above from the curr
                maze.getMap()[curr.getRowIndex()-1][curr.getColumnIndex()] = 0;
                visited[curr.getRowIndex()-1][curr.getColumnIndex()] = true;
            }
            else if (curr.getColumnIndex() - neighbour.getColumnIndex() < 0){ //neighbour chosen randomly right from the curr
                maze.getMap()[curr.getRowIndex()][curr.getColumnIndex()+1] = 0;
                visited[curr.getRowIndex()][curr.getColumnIndex()+1] = true;
            }
            else if (curr.getColumnIndex() - neighbour.getColumnIndex() > 0){ //neighbour chosen randomly left from the curr
                maze.getMap()[curr.getRowIndex()][curr.getColumnIndex()-1] = 0;
                visited[curr.getRowIndex()][curr.getColumnIndex()-1] = true;
            }
            //StackDfs.add(neighbour);
            GenerateRec(maze, visited, neighbour, GoalCells, CloseToGoal);
            //StackNeighbour.remove(neighbour);
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
    }






}


