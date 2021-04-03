package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{

    protected HashMap<String, AState> visited;
    protected int _NumberOfNodesEvaluated;
    public abstract Object getStruct();
    public abstract void insertStruct(Object struct, AState aState, ISearchable searchable);
    public abstract boolean isEmpty(Object struct);
    public abstract AState removeElement(Object struct);


    public Solution solve(ISearchable s){
        if (s == null){
            return null;
        }
        s.clearStruct();
        Object struct = this.getStruct();
        this.visited = new HashMap<>();
        this.insertStruct(struct, s.getStartState(), s);
        s.setStruct(s.getStartState());
        while (!this.isEmpty(struct)){
            AState curr = this.removeElement(struct);
            if (this.visited.get(curr.getName()) == null)
                this.visited.put(curr.getName(), curr);
            else
                continue;
            this._NumberOfNodesEvaluated++;
            if (s.isSolved(curr, s.getGoalState()))
                return new Solution(curr);
            ArrayList<AState> neighbours = s.getAllSuccessors(curr);
            for (AState n: neighbours) {
                if (this.visited.get(n.getName()) == null){
                    this.insertStruct(struct, n, s);
                    s.setStruct(n);
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

