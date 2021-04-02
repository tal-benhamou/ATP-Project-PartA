package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{

    protected HashMap<String, AState> visited;
    protected int _NumberOfNodesEvaluated;
    public abstract Object getStruct();
    public abstract void insertStruct(Object struct, AState aState);
    public abstract boolean isEmpty(Object struct);
    public abstract AState removeElement(Object struct);
    protected abstract boolean containStruct(Object struct, AState n);

    public Solution solve(ISearchable s){
        s.clearStruct();
        Object struct = this.getStruct();
        this.visited = new HashMap<>();
        this.insertStruct(struct, s.getStartState());
        s.setStruct(s.getStartState());
        AState tmp = null;
        while (!this.isEmpty(struct)){
            AState curr = this.removeElement(struct);
            if (this.visited.get(curr.getName()) == null)
                this.visited.put(curr.getName(), curr);
            else
                continue;
            //curr.set_parent(tmp);
            this._NumberOfNodesEvaluated++;
            if (s.isSolved(curr, s.getGoalState()))
                return new Solution(curr);
            ArrayList<AState> neighbours = s.getAllSuccessors(curr);
            for (AState n: neighbours) {
                if ((this.visited.get(n.getName()) == null) && !s.inStruct(n)){
                    this.insertStruct(struct, n);
                    s.setStruct(n);
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

