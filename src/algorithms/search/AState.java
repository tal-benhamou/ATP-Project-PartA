package algorithms.search;

public abstract class AState {

    private String state;
    private double cost;

    public AState(String state, double cost) {
        this.state = state;
        this.cost = cost;
    }
}
