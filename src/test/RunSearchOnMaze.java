package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RunSearchOnMaze {
    public static void main(String[] args) {

        byte[] tmp;
        tmp = new byte[]{ (byte)116, 4, 0, 6, 6};
        for (byte b: tmp) {
            System.out.println(b);
        }
    }
//        IMazeGenerator mg = new MyMazeGenerator();
//        Maze maze = mg.generate(3, 2);
//        SearchableMaze searchableMaze = new SearchableMaze(maze);
//        BreadthFirstSearch bfs = new BreadthFirstSearch();
//        solveProblem(searchableMaze, bfs);
//
//        Maze maze2 = mg.generate(3, 2);
//        SearchableMaze searchableMaze2 = new SearchableMaze(maze2);
//        solveProblem(searchableMaze2, bfs);
//        maze.print();
//        System.out.println("------");
//        maze2.print();
////        solveProblem(searchableMaze, new BreadthFirstSearch());
////        solveProblem(searchableMaze, new DepthFirstSearch());
////        solveProblem(searchableMaze, new BestFirstSearch());
//    }
//    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
////Solve a searching problem with a searcher
//        Solution solution = searcher.solve(domain);
//        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
////Printing Solution Path
//                System.out.println("Solution path:");
//        ArrayList<AState> solutionPath = solution.getSolutionPath();
//        for (int i = 0; i < solutionPath.size(); i++) {
//            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
//        }
//    }
}


