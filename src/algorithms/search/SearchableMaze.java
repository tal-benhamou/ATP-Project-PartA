package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze maze;


    public SearchableMaze(Maze myMaze) {
        this.maze = myMaze;
    }

    @Override
    public AState getStartState() { return new MazeState(maze.getStartPosition()); }

    @Override
    public AState getGoalState() {
        return new MazeState(maze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState astate) {
        Position curr = ((MazeState)astate).getPosition();
        ArrayList<AState> neighbours = new ArrayList<AState>();
        int rows = this.maze.getMap().length;
        int columns = this.maze.getMap()[0].length;
        if ((curr.getRowIndex() - 1 >= 0) && (this.maze.getMap()[curr.getRowIndex() - 1][curr.getColumnIndex()] != 1)){ //upper
            neighbours.add(new MazeState(astate,astate.get_cost() + 1,new Position(curr.getRowIndex() - 1, curr.getColumnIndex())));
        }
        if (((curr.getRowIndex() - 1 >= 0) && (curr.getColumnIndex() + 1 < columns))
                && ((this.maze.getMap()[curr.getRowIndex()-1][curr.getColumnIndex()] == 0) ||
                    this.maze.getMap()[curr.getRowIndex()][curr.getColumnIndex() + 1] == 0) &&
                        this.maze.getMap()[curr.getRowIndex() - 1][curr.getColumnIndex() + 1] != 1){ //upperright
            neighbours.add(new MazeState(astate, astate.get_cost() + Math.sqrt(2), new Position(curr.getRowIndex() - 1, curr.getColumnIndex() + 1)));
        }
        if ((curr.getColumnIndex() + 1 < columns) && (this.maze.getMap()[curr.getRowIndex()][curr.getColumnIndex() + 1] != 1)){ //right
            neighbours.add(new MazeState(astate,astate.get_cost() + 1,new Position(curr.getRowIndex(), curr.getColumnIndex() + 1)));
        }
        if (((curr.getRowIndex() + 1 < rows) && (curr.getColumnIndex() + 1 < columns))
                && ((this.maze.getMap()[curr.getRowIndex() + 1][curr.getColumnIndex()] == 0) ||
                    this.maze.getMap()[curr.getRowIndex()][curr.getColumnIndex() + 1] == 0) &&
                        this.maze.getMap()[curr.getRowIndex() + 1][curr.getColumnIndex() + 1] != 1){ //downright
            neighbours.add(new MazeState(astate,astate.get_cost() + Math.sqrt(2),new Position(curr.getRowIndex() + 1, curr.getColumnIndex() + 1)));
        }
        if ((curr.getRowIndex() + 1 < rows) && (this.maze.getMap()[curr.getRowIndex() + 1][curr.getColumnIndex()] != 1)){ //down
            neighbours.add(new MazeState(astate,astate.get_cost() + 1,new Position(curr.getRowIndex() + 1, curr.getColumnIndex())));
        }
        if (((curr.getRowIndex() + 1 < rows) && (curr.getColumnIndex() - 1 >= 0))
                && ((this.maze.getMap()[curr.getRowIndex() + 1][curr.getColumnIndex()] == 0) ||
                    this.maze.getMap()[curr.getRowIndex()][curr.getColumnIndex() - 1] == 0) &&
                        this.maze.getMap()[curr.getRowIndex() + 1][curr.getColumnIndex() - 1] != 1){ //downleft
            neighbours.add(new MazeState(astate,astate.get_cost() + Math.sqrt(2),new Position(curr.getRowIndex() + 1, curr.getColumnIndex() - 1)));
        }
        if ((curr.getColumnIndex() - 1 >= 0) && (this.maze.getMap()[curr.getRowIndex()][curr.getColumnIndex() - 1] != 1)){ //left
            neighbours.add(new MazeState(astate,astate.get_cost() + 1,new Position(curr.getRowIndex(), curr.getColumnIndex() - 1)));
        }
        if (((curr.getRowIndex() - 1 >= 0) && ( curr.getColumnIndex() - 1 >= 0))
            && ((this.maze.getMap()[curr.getRowIndex()][curr.getColumnIndex() - 1] == 0) ||
                    this.maze.getMap()[curr.getRowIndex() - 1][curr.getColumnIndex()] == 0) &&
                        this.maze.getMap()[curr.getRowIndex() - 1][curr.getColumnIndex() - 1] != 1){ //upperleft
            neighbours.add(new MazeState(astate,astate.get_cost() + Math.sqrt(2),new Position(curr.getRowIndex() - 1, curr.getColumnIndex() - 1)));
        }
        return neighbours;
    }

    @Override
    public AState ChangeState(AState aState) {
        return aState;
    }

    public boolean isSolved(AState o1, AState o2){
        return ((MazeState)o1).getPosition().equals(((MazeState)o2).getPosition());
    }
}
