package algorithms.search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    @Override
    public Solution solve(ISearchable s) {

        Queue<AState> queue = new ArrayDeque<>();

        queue.add(s.getStartState());

        while(!queue.isEmpty()){
            AState v = queue.poll();
        }
        return null;
    }
}
