package algorithms.search;

import java.util.ArrayList;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    /**
     * ArrayList struct for the algorithm's operation
     */
    ArrayList<AState> struct;

    public BreadthFirstSearch() {
        super();
        this.struct = new ArrayList<>();
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return _NumberOfNodesEvaluated;
    }

    /**
     * @param neighbour potential cell
     *          inserting neighbour to the ArrayList if is not there yet
     */
    @Override
    public void insertStruct(AState neighbour) {
        if (neighbour == null)
            return;
        if (this._inStruct.get(neighbour.getName()) == null)
            this.struct.add(neighbour);
    }

    @Override
    public boolean isEmptyStruct() {
        return struct.isEmpty();
    }

    @Override
    public AState removeElementfromStruct() {
        return this.struct.remove(0);
    }

}
