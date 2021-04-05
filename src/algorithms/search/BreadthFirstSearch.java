package algorithms.search;

import java.util.ArrayList;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    public BreadthFirstSearch() {}

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

    /**
     * @param obj ArrayList of BFS algorithm
     * @param neighbour potential cell
     * @param s Searchable problem
     *          inserting neighbour to the ArrayList if is not there yet
     */
    @Override
    public void insertStruct(Object obj, AState neighbour, ISearchable s) {
        if (obj == null || neighbour == null)
            return;
        if (!s.inStruct(neighbour))
            ((ArrayList<AState>)obj).add(neighbour);
    }

    @Override
    public boolean isEmpty(Object obj) {
        return ((ArrayList<AState>)obj).isEmpty();
    }

    @Override
    public AState removeElement(Object obj) {
        return ((ArrayList<AState>)obj).remove(0);
    }

}
