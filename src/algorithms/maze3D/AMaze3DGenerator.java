package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    /**
     * @param row number of rows
     * @param column number of columns
     * @param depth number of depth
     * @return the time to generate a 3D maze with those dimensions
     */
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
            long start = System.currentTimeMillis();
            this.generate(depth ,row, column);
            long finish = System.currentTimeMillis();
            return finish - start;
        }
}

