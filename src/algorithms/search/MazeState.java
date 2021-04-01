package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    private boolean _visited;
    private Position _pos;


    public MazeState(AState parent, double cost, Position pos) {
        super(parent, cost, pos.toString());
        this._visited = false;
        this._pos = pos;
    }

    @Override
    public String toString() {
        return _pos.toString();
    }

    public MazeState(Position pos) {
        super(null,1, pos.toString());
        this._pos = pos;
    }

    public boolean isVisited() {
        return _visited;
    }

    public Position getPosition() {
        return _pos;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getPosition().equals(((MazeState)obj).getPosition());
    }

    public void setVisited(boolean visited) {
        this._visited = visited;
    }

    public void setPosition(Position pos) {
        this._pos = pos;
    }
}
