package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends ASearchingAlgorithm{
    AStateCompare comp;

    public BestFirstSearch() {
        this.comp = new AStateCompare();
    }

    public static class AStateCompare implements Comparator<AState>{
        @Override
        public int compare(AState o1, AState o2) {
            return Double.compare(o1.get_cost(), o2.get_cost());
        }
    }
    @Override
    public Object getStruct() {
        return new PriorityQueue<AState>(11, comp);
    }

    @Override
    public void insertStruct(Object struct, AState neighbour) {
        boolean found = false;
        for (AState state: ((PriorityQueue<AState>)struct)) {
            if (state.getName().equals(neighbour.getName())){
                found = true;
               if (neighbour.get_cost() < state.get_cost()){
                   ((PriorityQueue<AState>)struct).remove(state);
                   ((PriorityQueue<AState>)struct).add(neighbour);
               }
                break;
            }
        }
        if (!found)
            ((PriorityQueue<AState>)struct).add(neighbour);
    }

    @Override
    public boolean isEmpty(Object struct) {
        return false;
    }

    @Override
    public AState removeElement(Object struct) {
        return ((PriorityQueue<AState>)struct).poll();
    }

    @Override
    protected boolean containStruct(Object struct, AState n) {
        for (AState aState:(PriorityQueue<AState>)struct) {
            if(aState.getName().equals(n.getName()))
                return true;
        }
        return false;
    }


    @Override
    public String getName() {
        return "Best First Search";
    }
}
