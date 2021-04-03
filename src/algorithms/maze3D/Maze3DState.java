package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

public class Maze3DState extends AState {
    private Position3D _pos;

    public Maze3DState(AState parent, double cost, Position3D pos) {
        super(parent, cost, pos.toString());
        this._pos = pos;
    }
    public Maze3DState(Position3D pos){
        super(null, 1, pos.toString());
        this._pos = pos;
    }
    public Position3D getPosition() {
        return _pos;
    }

    @Override
    public String toString() {
        return _pos.toString();
    }
}
