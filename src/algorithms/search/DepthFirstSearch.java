package algorithms.search;

import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    @Override
    public Stack<AState> getStruct() {
        return new Stack<AState>();
    }

    /**
     * @param struct Stack of the DFS iterative algorithm
     * @param neighbour Potential cell
     * @param s Searchable problem
     *          inserting neighbour to the Stack if is not there yet
     */
    @Override
    public void insertStruct(Object struct, AState neighbour, ISearchable s) {
        if (struct == null || neighbour == null)
            return;
        if (!s.inStruct(neighbour))
            ((Stack<AState>)struct).push(neighbour);
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
