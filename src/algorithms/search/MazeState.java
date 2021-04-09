package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    private Position _pos;


    public MazeState(AState parent, double cost, Position pos) {
        super(parent, cost, pos.toString());
        this._pos = pos;
    }

    @Override
    public String toString() {
        return _pos.toString();
    }

    public MazeState(Position pos) {
        super(null,10, pos.toString());
        this._pos = pos;
    }


    public Position getPosition() {
        return _pos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        return this.getPosition().equals(((MazeState)obj).getPosition());
    }

}
