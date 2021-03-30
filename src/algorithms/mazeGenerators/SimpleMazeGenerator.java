package algorithms.mazeGenerators;


import java.util.ArrayList;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    /**
     * @param rows
     * @param columns
     * @return
     */
    @Override
    public Maze generate(int rows, int columns) {
        int[][] map = new int[rows][columns];
        Maze maze = new Maze(new Position(0,0), new Position(rows-1,columns-1), map);

        ArrayList<Position> path = new ArrayList<Position>();
        path.add(maze.getStartPosition());

        Position curr = new Position(0,0);
        int step = 0;
        while (!(curr.equals(maze.getGoalPosition()))){
            Position tmp = new Position(curr.getRowIndex(), curr.getColumnIndex());
            if (step % 2 == 0)
            {
                 if (curr.getColumnIndex() + 1 < columns) {
                     tmp.setColumn(curr.getColumnIndex() + 1);
                     tmp.setRow(curr.getRowIndex());
                 }
                 else{
                     tmp.setColumn(curr.getColumnIndex());
                     tmp.setRow(curr.getRowIndex()+1);
                 }
            }
            else{
                if (curr.getRowIndex() + 1 < rows){
                    tmp.setColumn(curr.getColumnIndex());
                    tmp.setRow(curr.getRowIndex()+1);
                }
                else{
                    tmp.setColumn(curr.getColumnIndex() + 1);
                    tmp.setRow(curr.getRowIndex());
                }
            }
            path.add(tmp);
            curr = tmp;
            step++;
        }
        Random r = new Random();
        Position tmp = new Position();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tmp.setRow(i);
                tmp.setColumn(j);
                if (!path.contains(tmp))
                    maze.getMap()[i][j] = r.nextInt(2);
                }
        }

        return maze;
    }
}
