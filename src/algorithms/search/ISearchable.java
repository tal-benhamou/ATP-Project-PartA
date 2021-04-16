package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    ArrayList<AState> getAllSuccessors(AState aState);
    boolean isSolved(AState a1);
}
