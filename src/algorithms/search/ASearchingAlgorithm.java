package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{

    /**
     * _visited : HashMap of Cells that we visit already.
     * _NumberOfNodesEvaluated : number of nodes we visited.
     * _inStruct : HashMap that present if specific AState from ISearchable
     *            is in the algorithm's struct (ArrayList/Stack/PriorityQueue).
     */
    protected HashMap<String, AState> _visited;
    protected HashMap<String, AState> _inStruct;
    protected int _NumberOfNodesEvaluated;
    public abstract void insertStruct(AState aState);
    public abstract boolean isEmptyStruct();
    public abstract AState removeElementfromStruct();
    //public abstract void resetStruct();

    public ASearchingAlgorithm() {
        this._visited = new HashMap<>();
        this._inStruct = new HashMap<>();
        this._NumberOfNodesEvaluated = 0;
    }

    /**
     * @param s Searchable Problem
     * @return Solution Path
     * the Method solving a Searchable Problem
     */
    public Solution solve(ISearchable s){
        if (s == null){
            return null;
        }
        insertStruct(s.getStartState());
        _inStruct.put(s.getStartState().getName(),s.getStartState());
        while (!isEmptyStruct()){
            AState curr = removeElementfromStruct();
            if (_visited.get(curr.getName()) == null)
                _visited.put(curr.getName(), curr);
            else
                continue;
            _NumberOfNodesEvaluated++;
            if (s.isSolved(curr))
                return new Solution(curr);
            ArrayList<AState> neighbours = s.getAllSuccessors(curr);
            for (AState n: neighbours) {
                if (_visited.get(n.getName()) == null){
                    insertStruct(n);
                    _inStruct.put(n.getName(),n);
                }
            }
        }
        return null;
    }


    @Override
    public int getNumberOfNodesEvaluated() {
        return _NumberOfNodesEvaluated;
    }
}

