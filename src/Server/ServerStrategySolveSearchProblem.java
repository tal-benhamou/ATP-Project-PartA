package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ASearchingAlgorithm alg;
            Configurations conf = Configurations.Instance();

            /*checking which searching algorithm*/
            if (conf.getProperty("mazeSearchingAlgorithm") == "BFS")
                alg = new BreadthFirstSearch();
            else if (conf.getProperty("mazeSearchingAlgorithm") == "DFS")
                alg = new DepthFirstSearch();
            else if (conf.getProperty("mazeSearchingAlgorithm") == "BEST")
                alg = new BestFirstSearch();
            else
                alg = null;
            assert alg != null;

            Maze maze = (Maze) fromClient.readObject();
            byte[] mazeInFile;
            Solution solutionInFile;
            String algNameinFile;
           // Object[] obj;
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            ObjectInputStream inFromFile;
            ObjectOutputStream outToFile;
            File dir = new File(tempDirectoryPath);


            //OutputStream out = new FileOutputStream(tempDirectoryPath);

//obj[0] = maze (byte[]), obj[1] = solution, obj[2] = algorithm search
            for (File file: Objects.requireNonNull(dir.listFiles())) {
                if (file.isFile()){
                    FileInputStream fileinstream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    inFromFile = new ObjectInputStream(fileinstream);
                    outToFile = new ObjectOutputStream(fileOutputStream);
                    mazeInFile = ((byte[])((Object[])inFromFile.readObject())[0]);
                    solutionInFile = ((Solution)((Object[])inFromFile.readObject())[1]);
                    algNameinFile = ((String)((Object[])inFromFile.readObject())[2]);
                    if (Arrays.equals(maze.toByteArray(), mazeInFile) && algNameinFile.equals(conf.getProperty("mazeSearchingAlgorithm")))
                        toClient.writeObject(solutionInFile);
                    else {
                        SearchableMaze problem = new SearchableMaze(maze);
                        Solution solution = alg.solve(problem);
                        Object[] obj = new Object[3];
                        obj[0] = maze;
                        obj[1] = solution;
                        obj[2] = conf.getProperty("mazeSearchingAlgorithm");



                        toClient.writeObject(solution);
                    }

                }
            }






            //String tmpdir = Files.createTempDirectory("tmpDirPrefix").toFile().getAbsolutePath();


            //File.createTempFile();


            //toClient.writeObject(solution);
            toClient.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

