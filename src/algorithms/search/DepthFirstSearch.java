package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    @Override
    public Stack<AState> getStruct() {
        return new Stack<AState>();
    }

    @Override
    public void insertStruct(Object struct, AState aState) {
        ((Stack<AState>)struct).push(aState);
    }

    @Override
    public boolean isEmpty(Object struct) {
        return ((Stack<AState>)struct).isEmpty();
    }

    @Override
    public AState removeElement(Object struct) {
        return ((Stack<AState>)struct).pop();
    }

    @Override
    protected boolean containStruct(Object struct, AState n) {
        for (AState aState:(Stack<AState>)struct) {
            if(aState.getName().equals(n.getName()))
                return true;
        }
        return false;
    }


    /*
        @Override
        public Solution solve(ISearchable s) {

            Stack<AState> stack = new Stack<>();
            this.visited = new HashMap<>();
            this.visited.put(s.getStartState(), true);
            stack.push(s.getStartState());
            AState curr = s.getStartState();
            while (!stack.isEmpty() && !s.isSolved(curr, s.getGoalState())){
                curr = stack.pop();
                this.visited.put(curr, true);
                for (AState n: s.getAllSuccessors(curr)) {
                    if(!this.visited.containsKey(n)) {
                        stack.push(n);
                        this.visited.put(n, true);
                        n.set_parent(curr);
                    }
                }
            }
            if(stack.isEmpty() && !s.isSolved(curr, s.getGoalState()))
                return null;
            else
                return new Solution(curr);
        }
    */
    @Override
    public String getName() {
        return "Depth First Search";
    }
}
