package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long start = System.currentTimeMillis();
        this.generate(rows, columns);
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
