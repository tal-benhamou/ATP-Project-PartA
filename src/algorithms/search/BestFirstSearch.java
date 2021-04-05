package algorithms.search;


import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends ASearchingAlgorithm{
    AStateCompare comp;

    public BestFirstSearch() {
        this.comp = new AStateCompare();
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
     * @return priorityQueue with appropriate comp function
     */
    @Override
    public Object getStruct() {
        return new PriorityQueue<AState>(11, comp);
    }

    /**
     * @param struct priorityQueue of the BEST algorithm
     * @param neighbour potential cell
     * @param s the searchable problem
     *          inserting neighbour to the priorityQueue if the neighbour is not there yet or if the neighbour's cost is less than
     *          the cost of the same cell which in the 'srtuct'
     */
    @Override
    public void insertStruct(Object struct, AState neighbour, ISearchable s) {
        if (struct == null || neighbour == null)
            return;
        if (s.inStruct(neighbour)) {
            for (AState state : ((PriorityQueue<AState>) struct)) {
                if (state.getName().equals(neighbour.getName())) {
                    if (neighbour.get_cost() < state.get_cost()) {
                        ((PriorityQueue<AState>) struct).remove(state);
                        ((PriorityQueue<AState>) struct).add(neighbour);
                    }
                    break;
                }
            }
        } else
            ((PriorityQueue<AState>) struct).add(neighbour);

    }

    /**
     * @param struct priorityQueue of the BEST algorithm
     * @return true if the priorityQueue is empty
     */
    @Override
    public boolean isEmpty(Object struct) {
        return ((PriorityQueue<AState>)struct).isEmpty();
    }

    /**
     * @param struct priorityQueue of the BEST algorithm
     * @return the minimum AState from 'struct'
     */
    @Override
    public AState removeElement(Object struct) {
        return ((PriorityQueue<AState>)struct).poll();
    }

    @Override
    public String getName() {
        return "Best First Search";
    }
}
