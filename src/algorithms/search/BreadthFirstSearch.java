package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    public BreadthFirstSearch() {
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return _NumberOfNodesEvaluated;
    }

    public ArrayList<AState> getStruct(){
        return new ArrayList<AState>();
    }

    @Override
    public void insertStruct(Object obj, AState aState, ISearchable s) {
        if (obj == null || aState == null)
            return;
        if (!s.inStruct(aState))
            ((ArrayList<AState>)obj).add(aState);
    }

    @Override
    public boolean isEmpty(Object obj) {
        return ((ArrayList<AState>)obj).isEmpty();
    }

    @Override
    public AState removeElement(Object obj) {
        return ((ArrayList<AState>)obj).remove(0);
    }

    @Override
    public boolean Finish(ISearchable s, AState curr) {
        return s.isSolved(curr, s.getGoalState());
    }

}
