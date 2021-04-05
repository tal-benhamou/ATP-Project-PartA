package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    /**
     * @param rows number of rows
     * @param columns number of columns
     * @return the time to generate a maze with those dimensions
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long start = System.currentTimeMillis();
        this.generate(rows, columns);
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
