package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3D {

    private int[][][] _map;
    private Position3D _start;
    private Position3D _goal;

    public Maze3D(int[][][] _map, Position3D _start, Position3D _goal) {
        this._map = _map;
        this._start = _start;
        this._goal = _goal;
    }

    public Maze3D(int[][][] _map) {
        this._map = _map;
    }

    public int[][][] getMap() {
        return _map;
    }
    public Position3D getStartPosition() {
        return _start;
    }

    public Position3D getGoalPosition() {
        return _goal;
    }

    public void setStart(Position3D _start) {
        this._start = _start;
    }

    public void setGoal(Position3D _goal) {
        this._goal = _goal;
    }
    public void print(){
        System.out.println("{");
        for (int i = 0; i < _map.length; i++) {
            for (int j = 0; j < _map[0].length; j++) {
                System.out.print("{ ");
                for (int k = 0; k < _map[0][0].length; k++) {
                    if (i == _start.getDepthIndex() && j == _start.getRowIndex() && k == _start.getColumnIndex())
                        System.out.print('S' + " ");
                    else if (i == _goal.getDepthIndex() && j == _goal.getRowIndex() && k == _goal.getColumnIndex())
                        System.out.print('E' + " ");
                    else
                        System.out.print(_map[i][j][k] + " ");
                }
                System.out.println("}");
            }
            if (i != _map.length - 1) {
                for (int j = 0; j < _map[0][0].length; j++) {
                    System.out.print("--");
                }
                System.out.println("---");
            }
        }
        System.out.println("}");
    }
}
