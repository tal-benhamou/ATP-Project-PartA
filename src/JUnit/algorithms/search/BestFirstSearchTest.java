package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;



class BestFirstSearchTest {
    public static void main(String[] args) {
        testing();
    }
    static void testing(){
        BestFirstSearch bfs = new BestFirstSearch();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        assert(bfs.solve(searchableMaze) == null);
    }

}