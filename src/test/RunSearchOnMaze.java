package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        int count = 0;
        int count2 = 0;
        double bfs, best;
            for (int i = 0; i < 1; i++) {
                IMazeGenerator mg = new MyMazeGenerator();
                Maze maze = mg.generate(4, 99);
                try {
                    SearchableMaze searchableMaze = new SearchableMaze(maze);
                    bfs = solveProblem(searchableMaze, new BreadthFirstSearch());
                    //solveProblem(searchableMaze, new DepthFirstSearch());
                    best = solveProblem(searchableMaze, new BestFirstSearch());
                    if (bfs < best)
                        count2++;
                } catch (Exception e) {
                    count++;
                }
            }
        System.out.println("nulls : "+ count);
        System.out.printf("bfs < best :%s",count2);
    }
    private static double solveProblem(ISearchable domain, ISearchingAlgorithm searcher) throws Exception {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        if(solution == null)
            throw new Exception("NullPointerException");
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        double cost = 0;
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
            //cost = cost + solutionPath.get(i).get_cost();
        }
        cost = solutionPath.get(solutionPath.size()-1).get_cost();
        return cost;
        //((SearchableMaze)domain).getMaze().print();

    }
}

