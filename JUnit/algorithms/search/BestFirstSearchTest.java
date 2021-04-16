package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class BestFirstSearchTest {
    @Test
    public void testBest() throws Exception{
        BestFirstSearch best = new BestFirstSearch();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        assertNotNull(best.solve(searchableMaze));
    }
    @Test
    public void testBestPath() throws Exception{
        BestFirstSearch best1 = new BestFirstSearch();
        int[][] map =  {{0,0,0,0,0},{0,0,1,1,0},{1,0,0,1,0},{1,1,0,0,0}};
        Maze mazeCheckBest = new Maze(new Position(0,0), new Position(2,4), map);
        SearchableMaze searchableMaze = new SearchableMaze(mazeCheckBest);
        ArrayList<AState> pathbest = (best1.solve(searchableMaze)).getSolutionPath();
        System.out.println((pathbest.get(pathbest.size() - 1)).get_cost());
        assertEquals(65 , (pathbest.get(pathbest.size() - 1)).get_cost());
    }

    @Test
    public void testBFSpath() throws Exception{
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        int[][] map =  {{0,0,0,0,0},{0,0,1,1,0},{1,0,0,1,0},{1,1,0,0,0}};
        Maze mazeCheckBest = new Maze(new Position(0,0), new Position(2,4), map);
        SearchableMaze searchableMaze = new SearchableMaze(mazeCheckBest);
        ArrayList<AState> path = (bfs.solve(searchableMaze)).getSolutionPath();
        System.out.println((path.get(path.size() - 1)).get_cost());
        assertEquals(70,  (path.get(path.size()-1)).get_cost());
    }

    @Test
    public void testNull() throws Exception{
        BestFirstSearch best = new BestFirstSearch();
        int[][] map =  {{0,0,0,0},{1,1,0,1},{1,1,1,1},{0,1,0,1}};
        Maze mazeCheckBest = new Maze(new Position(0,0), new Position(3,2), map);
        SearchableMaze searchableMaze = new SearchableMaze(mazeCheckBest);
        Solution path = (best.solve(searchableMaze));
        assertNull(path);
    }

    @Test
    public void testInsert() throws Exception{
        BestFirstSearch best = new BestFirstSearch();
        best.insertStruct(null);
        assertEquals(0, best.struct.size());
    }
}