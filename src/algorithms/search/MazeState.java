package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    private boolean visited;
    private Position pos;


    public MazeState(String state, double cost) {
        super(state, cost);
        this.visited = false;
        this.pos = null;
    }

    public boolean isVisited() {
        return visited;
    }

    public Position getPos() {
        return pos;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
