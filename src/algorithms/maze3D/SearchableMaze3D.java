package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private Maze3D maze3D;
    private boolean[][][] inStruct;

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
        if ((curr.getRowIndex() - 1 >= 0) && (this.maze3D.getMap()[curr.getDepthIndex()][curr.getRowIndex() - 1][curr.getColumnIndex()] != 1)) //upper
            neighbours.add(new Maze3DState(astate, astate.get_cost() + 1,new Position3D(curr.getDepthIndex(),curr.getRowIndex() - 1, curr.getColumnIndex())));
        if ((curr.getColumnIndex() + 1 < columns) && (this.maze3D.getMap()[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex() + 1] != 1))//right
            neighbours.add(new Maze3DState(astate,astate.get_cost() + 1,new Position3D(curr.getDepthIndex(),curr.getRowIndex(), curr.getColumnIndex() + 1)));
        if ((curr.getRowIndex() + 1 < rows) && (this.maze3D.getMap()[curr.getDepthIndex()][curr.getRowIndex() + 1][curr.getColumnIndex()] != 1)) //down
            neighbours.add(new Maze3DState(astate,astate.get_cost() + 1,new Position3D(curr.getDepthIndex(),curr.getRowIndex() + 1, curr.getColumnIndex())));
        if ((curr.getColumnIndex() - 1 >= 0) && (this.maze3D.getMap()[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex() - 1] != 1)) //left
            neighbours.add(new Maze3DState(astate,astate.get_cost() + 1,new Position3D(curr.getDepthIndex(),curr.getRowIndex(), curr.getColumnIndex() - 1)));
        if ((curr.getDepthIndex() - 1 >= 0) && (this.maze3D.getMap()[curr.getDepthIndex() - 1][curr.getRowIndex()][curr.getColumnIndex()] != 1)) // outside
            neighbours.add(new Maze3DState(astate, astate.get_cost() + 1, new Position3D(curr.getDepthIndex() - 1,curr.getRowIndex(),curr.getColumnIndex())));
        if ((curr.getDepthIndex() + 1 < depth) && (this.maze3D.getMap()[curr.getDepthIndex() + 1][curr.getRowIndex()][curr.getColumnIndex()] != 1)) // inside
            neighbours.add(new Maze3DState(astate, astate.get_cost() + 1, new Position3D(curr.getDepthIndex() + 1, curr.getRowIndex(), curr.getColumnIndex())));

        return neighbours;
    }

    @Override
    public AState ChangeState(ASearchingAlgorithm asa, Object struct) {
        return asa.removeElement(struct);
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

    /**
     * @param aState cell from the maze
     * @return true if aState is in the algorithm's struct
     */
    @Override
    public boolean inStruct(AState aState) {
        if (aState == null)
            return false;
        return this.inStruct[((Maze3DState)aState).getPosition().getDepthIndex()][((Maze3DState)aState).getPosition().getRowIndex()][((Maze3DState)aState).getPosition().getColumnIndex()];
    }

    /**
     * @param aState cell from the maze
     *               marker aState as "in the algorithm's struct"
     */
    @Override
    public void setStruct(AState aState) {
        if (aState == null)
            return;
        this.inStruct[((Maze3DState)aState).getPosition().getDepthIndex()][((Maze3DState)aState).getPosition().getRowIndex()][((Maze3DState)aState).getPosition().getColumnIndex()] = true;
    }

    @Override
    public void clearStruct() {
        this.inStruct = new boolean[this.maze3D.getMap().length][this.maze3D.getMap()[0].length][this.maze3D.getMap()[0][0].length];
    }
}
