package algorithms.search;

import java.io.Serializable;

public abstract class AState implements Serializable {

    private AState _parent = null;
    private double _cost = 10;
    private String name = null;

    public AState(AState parent, double cost, String name) {
        this._parent = parent;
        this._cost = cost;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
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
