package algorithms.search;

import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    /**
     * Stack struct for the algorithm's operation
     */
    Stack<AState> struct;

    public DepthFirstSearch() {
        super();
        this.struct = new Stack<>();
    }

    /**
     * @param neighbour Potential cell
     *          inserting neighbour to the Stack if is not there yet
     */
    @Override
    public void insertStruct(AState neighbour) {
        if (neighbour == null)
            return;
        if (this._inStruct.get(neighbour.getName()) == null)
            struct.push(neighbour);
    }

    @Override
    public boolean isEmptyStruct() {
        return struct.isEmpty();
    }

    @Override
    public AState removeElementfromStruct() {
        return struct.pop();
    }


    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}
