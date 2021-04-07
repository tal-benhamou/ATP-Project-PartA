package algorithms.search;


import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends ASearchingAlgorithm{
    /**
     * comp - comparator
     * struct - PriorityQueue struct for the algorithm's operation
     */
    AStateCompare comp;
    PriorityQueue<AState> struct;

    public BestFirstSearch() {
        super();
        this.comp = new AStateCompare();
        this.struct = new PriorityQueue<>(11,comp);
    }

    /**
     * a comparator needed for the priorityQueue of the BEST algorithm
     */
    public static class AStateCompare implements Comparator<AState>{
        @Override
        public int compare(AState o1, AState o2) {
            return Double.compare(o1.get_cost(), o2.get_cost());
        }
    }

    /**
     * @param neighbour potential cell
     *          inserting neighbour to the priorityQueue if the neighbour is not there yet or if the neighbour's cost is less than
     *          the cost of the same cell which in the 'srtuct'
     */
    @Override
    public void insertStruct(AState neighbour) {
        if (neighbour == null)
            return;
        if (this._inStruct.get(neighbour.getName()) != null) {
            for (AState state :  struct) {
                if (state.getName().equals(neighbour.getName())) {
                    if (neighbour.get_cost() < state.get_cost()) {
                        struct.remove(state);
                        struct.add(neighbour);
                    }
                    break;
                }
            }
        } else
            struct.add(neighbour);
    }

    /**
     * @return true if the priorityQueue is empty
     */
    @Override
    public boolean isEmptyStruct() {
        return struct.isEmpty();
    }

    /**
     * @return the minimum AState from 'struct'
     */
    @Override
    public AState removeElementfromStruct() {
        return struct.poll();
    }


    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
