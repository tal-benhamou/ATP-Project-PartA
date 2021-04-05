package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    ArrayList<AState> getAllSuccessors(AState aState);
    AState ChangeState(ASearchingAlgorithm Asa, Object obj);
    boolean isSolved(AState a1);
    boolean inStruct(AState aState);
    void setStruct(AState aState);
    void clearStruct();
}
