package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) {
        int count=0,count2 = 0;
        double bfs=0, best=0;
//        for (int i = 0; i < 1000; i++) {
//            IMazeGenerator3D mg = new MyMaze3DGenerator();
//            Maze3D maze3D = mg.generate(100, 100, 100);
//            SearchableMaze3D searchableMaze = new SearchableMaze3D(maze3D);
//            try {
//                bfs = solveProblem(searchableMaze, new BreadthFirstSearch());
//                //solveProblem(searchableMaze, new DepthFirstSearch());
//                best = solveProblem(searchableMaze, new BestFirstSearch());
//            } catch (Exception e) {
//                count++;
//            }
//            if (best > bfs)
//                count2++;
//        }
//        System.out.printf("nulls : %s\n",count);
//        System.out.printf("bfs < best : %s", count2);
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        long t1,t2;
        t1 = System.currentTimeMillis();
        System.out.println("Generating...");
        Maze3D maze3D = mg.generate(12, 14, 14);
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze3D);
        try {
            System.out.println("Solving...");
            t1 = System.currentTimeMillis();
            solveProblem(searchableMaze, new BreadthFirstSearch());
            solveProblem(searchableMaze, new DepthFirstSearch());
            solveProblem(searchableMaze, new BestFirstSearch());
            t2 = System.currentTimeMillis();
        } catch (Exception e) {
            System.out.println("ERROR");
            count++;
        }
        System.out.println(t2-t1);
    }
    private static double solveProblem(ISearchable domain, ISearchingAlgorithm searcher) throws Exception {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        if (solution == null)
            throw new Exception("NULL pointer");
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        System.out.println("Solution path:");
        double cost = 0;
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
        }
        cost = solutionPath.get(solutionPath.size()-1).get_cost();
        return cost;
    }
    private static double solveProblem2(ISearchable domain, ISearchingAlgorithm searcher) throws Exception {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        if (solution == null)
            throw new Exception("NULL pointer");
        //System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        //System.out.println("Solution path:");
        double cost = 0;
   //     ArrayList<AState> solutionPath = solution.getSolutionPath();
    //    for (int i = 0; i < solutionPath.size(); i++) {
    //        System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
//            cost = cost + solutionPath.get(i).get_cost();
    //    }
        return cost;
    }
}
