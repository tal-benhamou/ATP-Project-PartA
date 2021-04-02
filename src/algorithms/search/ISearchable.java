package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    ArrayList<AState> getAllSuccessors(AState aState);
    AState ChangeState(AState aState);
    boolean isSolved(AState a1, AState a2);
    boolean inStruct(AState aState);
    void setStruct(AState aState);
    void clearStruct();
}
