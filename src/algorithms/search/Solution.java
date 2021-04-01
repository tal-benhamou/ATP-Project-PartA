package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> _pathSolution;

    public ArrayList<AState> getSolutionPath() {
        return _pathSolution;
    }

    public Solution(AState start) {
        this._pathSolution = createPath(start);
    }

    private ArrayList<AState> createPath(AState start) {
        ArrayList<AState> path = new ArrayList<>();
        AState curr = start;
        path.add(start);
        while(curr.get_parent() != null){
            path.add(curr.get_parent());
            curr = curr.get_parent();
        }
        return path;
    }
}

