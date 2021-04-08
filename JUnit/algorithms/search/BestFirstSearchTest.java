package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class BestFirstSearchTest {
    @Test
    public void testBest() throws Exception{
        BestFirstSearch best = new BestFirstSearch();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(100, 100);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        assert(best.solve(searchableMaze) != null);
    }
    @Test
    public void testBestAndBfs() throws Exception{
        BestFirstSearch best1 = new BestFirstSearch();
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        int[][] map =  {{0,0,0,0,0},{0,0,1,1,0},{1,0,0,1,0},{1,1,0,0,0}};
        Maze mazeCheckBest = new Maze(new Position(0,0), new Position(4,4), map);
        SearchableMaze searchableMaze = new SearchableMaze(mazeCheckBest);
        ArrayList<AState> path = (bfs.solve(searchableMaze)).getSolutionPath();// bug here
        assert(((AState)path.get(path.size()-1)).get_cost() == 16+(8*Math.sqrt(2)));
    }


}