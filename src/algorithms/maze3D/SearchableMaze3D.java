package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private Maze3D maze3D;

    public SearchableMaze3D(Maze3D maze3D) {
        this.maze3D = maze3D;
    }

    @Override
    public AState getStartState() {
        return new Maze3DState(maze3D.getStartPosition());
    }

    @Override
    public AState getGoalState() {
        return new Maze3DState(maze3D.getGoalPosition());
    }

       /**
     *     @param astate Cell from the Maze
     *     @return ArrayList Of all his potential neighbours
     *     the method adding to ArrayList the potential neighbours of astate
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState astate) {
        if (astate == null)
            return null;
        Position3D curr = ((Maze3DState)astate).getPosition();
        ArrayList<AState> neighbours = new ArrayList<AState>();
        int depth = this.maze3D.getMap().length;
        int rows = this.maze3D.getMap()[0].length;
        int columns = this.maze3D.getMap()[0][0].length;
        int currRow = curr.getRowIndex();
        int currCol = curr.getColumnIndex();
        int currDepth = curr.getDepthIndex();
        if ((currRow - 1 >= 0) && (this.maze3D.getMap()[currDepth][currRow - 1][currCol] != 1)) //upper
            neighbours.add(new Maze3DState(astate, astate.get_cost() + 10,new Position3D(currDepth,currRow - 1, currCol)));
        if ((currCol + 1 < columns) && (this.maze3D.getMap()[currDepth][currRow][currCol + 1] != 1))//right
            neighbours.add(new Maze3DState(astate,astate.get_cost() + 10,new Position3D(currDepth,currRow, currCol + 1)));
        if ((currRow + 1 < rows) && (this.maze3D.getMap()[currDepth][currRow + 1][currCol] != 1)) //down
            neighbours.add(new Maze3DState(astate,astate.get_cost() + 10,new Position3D(currDepth,currRow + 1, currCol)));
        if ((currCol - 1 >= 0) && (this.maze3D.getMap()[currDepth][currRow][currCol - 1] != 1)) //left
            neighbours.add(new Maze3DState(astate,astate.get_cost() + 10,new Position3D(currDepth,currRow, currCol - 1)));
        if ((currDepth - 1 >= 0) && (this.maze3D.getMap()[currDepth - 1][currRow][currCol] != 1)) // outside
            neighbours.add(new Maze3DState(astate, astate.get_cost() + 10, new Position3D(currDepth - 1,currRow,currCol)));
        if ((currDepth + 1 < depth) && (this.maze3D.getMap()[currDepth + 1][currRow][currCol] != 1)) // inside
            neighbours.add(new Maze3DState(astate, astate.get_cost() + 10, new Position3D(currDepth + 1, currRow, currCol)));

        return neighbours;
    }
    /**
     * @param a1 cell from the maze
     * @return true if a1 is the goal Cell
     */
    @Override
    public boolean isSolved(AState a1) {
        if (a1 == null)
            return false;
        return ((Maze3DState)a1).getPosition().equals(this.maze3D.getGoalPosition());
    }

}
