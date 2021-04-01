package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{

    protected HashMap<AState, Boolean> visited;
    protected int _NumberOfNodesEvaluated;
    public abstract Object getStruct();
    public abstract void insertStruct(Object struct, AState aState);
    public abstract boolean isEmpty(Object struct);
    public abstract AState removeElement(Object struct);

    public Solution solve(ISearchable s){

        Object struct = this.getStruct();
        this.visited = new HashMap<>();
        this.insertStruct(struct, s.getStartState());
        AState tmp = null;
        while (!this.isEmpty(struct)){
            AState curr = this.removeElement(struct);
            curr.set_parent(tmp);
            if (!contains(this.visited, curr))
                this.visited.put(curr, true);
            this._NumberOfNodesEvaluated++;
            if (s.isSolved(curr, s.getGoalState()))
                return new Solution(curr);
            ArrayList<AState> neighbours = s.getAllSuccessors(curr);
            for (AState n: neighbours) {
                if (!contains(this.visited, n)){
                    this.insertStruct(struct, n);
                }
            }
            tmp = curr;
        }
        return null;
    }

    public boolean contains(HashMap<AState, Boolean> hash, AState aState){
        for (AState astate: hash.keySet()) {
            if (astate.getName().equals(aState.getName()))
                return true;
        }
        return false;
    }
    @Override
    public int getNumberOfNodesEvaluated() {
        return _NumberOfNodesEvaluated;
    }
}

