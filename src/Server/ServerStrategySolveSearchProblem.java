package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.nio.file.Files;

public class ServerStrategySolveSearchProblem implements ServerStrategy {
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze maze = (Maze) fromClient.readObject();
            BestFirstSearch best = new BestFirstSearch();
            SearchableMaze problem = new SearchableMaze(maze);
            Solution solution = best.solve(problem);
            String tmpdir = Files.createTempDirectory("tmpDirPrefix").toFile().getAbsolutePath();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            //File.createTempFile();

            toClient.writeObject(solution);
            toClient.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

