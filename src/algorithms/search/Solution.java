package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    private ArrayList<AState> _pathSolution;

    public ArrayList<AState> getSolutionPath() {
        return _pathSolution;
    }

    public Solution(AState GoalCell) {
        this._pathSolution = createPath(GoalCell);
    }

    /**
     * @param GoalCell the Goal cell
     * @return Path from StartCell to GoalCell
     * the Method create the Solution Path by walking back from Goal Cell to Start Cell with the
     * _parent field of each AState.
     */
    private ArrayList<AState> createPath(AState GoalCell) {
        if (GoalCell == null)
            return null;
        ArrayList<AState> path = new ArrayList<>();
        AState curr = GoalCell;
        path.add(GoalCell);
        while(curr.get_parent() != null){
            path.add(curr.get_parent());
            curr = curr.get_parent();
        }
        ArrayList<AState> finalPath = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0 ; i--) {
            finalPath.add(path.get(i));
        }
        return finalPath;
    }
}

