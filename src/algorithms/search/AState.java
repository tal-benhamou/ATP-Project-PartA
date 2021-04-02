package algorithms.search;

public abstract class AState {

    private AState _parent = null;
    private double _cost = 1;
    private String name = null;

    public AState(AState parent, double cost, String name) {
        this._parent = parent;
        this._cost = cost;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((AState)obj).getName());
    }

    public String getName() {
        return name;
    }

    public double get_cost() {
        return _cost;
    }

    public AState get_parent() {
        return _parent;
    }


}
