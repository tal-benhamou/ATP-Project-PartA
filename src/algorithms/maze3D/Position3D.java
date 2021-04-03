package algorithms.maze3D;

public class Position3D {
    private int _row;
    private int _column;
    private int _depth;

    public Position3D(int depth, int row, int column) {
        this._row = row;
        this._column = column;
        this._depth = depth;
    }

    public Position3D() {}

    public int getRowIndex() {
        return _row;
    }

    public int getColumnIndex() {
        return _column;
    }

    public int getDepthIndex() {
        return _depth;
    }

    @Override
    public String toString() {
        return  "{" + _depth + ","+_row +","+ _column +"}";
    }

    public void setRow(int _row) {
        this._row = _row;
    }

    public void setColumn(int _column) {
        this._column = _column;
    }

    public void setDepth(int _depth) {
        this._depth = _depth;
    }

    @Override
    public boolean equals(Object obj) {
        return ((this.getDepthIndex() == ((Position3D)obj).getDepthIndex()) && (this.getRowIndex() == ((Position3D)obj).getRowIndex())
                 && (this.getColumnIndex() == ((Position3D)obj).getColumnIndex()));
    }
}
