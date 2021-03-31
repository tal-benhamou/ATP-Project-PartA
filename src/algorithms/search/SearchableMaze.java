package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze maze;
    private Position position;

    public SearchableMaze(Maze myMaze) {
        maze = myMaze;
    }

    @Override
    public AState getStartState() {
        return null;
    }

    @Override
    public AState getGoalState() {
        return null;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        return null;
    }

    @Override
    public AState ChangeState(AState aState) {
        return null;
    }
}
