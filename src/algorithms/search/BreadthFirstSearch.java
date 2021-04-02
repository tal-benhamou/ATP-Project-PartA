package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    public BreadthFirstSearch() {
    }
    /*
    public Solution solve(ISearchable s) {

        ArrayList<AState> queue = new ArrayList<>();
        this.visited = new HashMap<>();
        this.visited.put(s.getStartState(), true);
        queue.add(s.getStartState());
        while(!queue.isEmpty()){
            AState curr = s.ChangeState(queue.remove(0));
            _NumberOfNodesEvaluated++;
            if (s.isSolved(curr, s.getGoalState())){
                return new Solution(curr);
            }
            ArrayList<AState> neighbours = s.getAllSuccessors(curr);
            for (AState n: neighbours ) {
                if (!this.visited.containsKey(n)){
                    this.visited.put(n,true);
                    queue.add(n);
                    n.set_parent(curr);
                }
            }
        }
        return null;
    }

     */

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
    public void insertStruct(Object obj, AState aState) {
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
    protected boolean containStruct(Object struct, AState n) {
        for (AState aState:((ArrayList<AState>)struct)) {
            if(aState.getName().equals(n.getName()))
                return true;
        }
        return false;
    }
}
