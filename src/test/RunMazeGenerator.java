package test;
import algorithms.mazeGenerators.*;
public class RunMazeGenerator {
    public static void main(String[] args) {
      //  testMazeGenerator(new EmptyMazeGenerator());
      //  testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println("Maze generationtime(ms): ");
        long time = mazeGenerator.measureAlgorithmTimeMillis(1000/*rows*/,1000/*columns*/);
        System.out.println(time);
       // System.out.println(String.format("Maze generationtime(ms): %s", mazeGenerator.measureAlgorithmTimeMillis
      //                  (100/*rows*/,100/*columns*/)));
        // generate another maze
        int count = 0;
//        for (int i = 0; i < 10000; i++) {
//            IMazeGenerator mz = new MyMazeGenerator();
//            Maze maze = mz.generate(100/*rows*/, 100/*columns*/);
//            if(maze.getGoalPosition() == null)
//                count++;
//        }
//        System.out.println(count);
        Maze maze = mazeGenerator.generate(14/*rows*/, 14/*columns*/);
        // prints the maze
        maze.print();
        // get the maze entrance
        Position startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}
