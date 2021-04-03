package algorithms.search;

import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    @Override
    public Stack<AState> getStruct() {
        return new Stack<AState>();
    }

    @Override
    public void insertStruct(Object struct, AState aState, ISearchable s) {
        if (struct == null || aState == null)
            return;
        if (!s.inStruct(aState))
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
    public String getName() {
        return "Depth First Search";
    }
}
