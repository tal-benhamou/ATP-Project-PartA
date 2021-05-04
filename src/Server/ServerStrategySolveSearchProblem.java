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
            boolean found = false;

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

            FileInputStream fileinstream;
            FileOutputStream fileOutputStream;

//obj[0] = maze (byte[]), obj[1] = solution, obj[2] = algorithm search
            if (dir.listFiles() != null) {
                for (File file : Objects.requireNonNull(dir.listFiles())) {
                    if (file.isFile()) {
                        fileinstream = new FileInputStream(file);
                        inFromFile = new ObjectInputStream(fileinstream);
                        mazeInFile = ((byte[]) ((Object[]) inFromFile.readObject())[0]);
                        solutionInFile = ((Solution) ((Object[]) inFromFile.readObject())[1]);
                        algNameinFile = ((String) ((Object[]) inFromFile.readObject())[2]);
                        if (mazeInFile.length == maze.toByteArray().length && algNameinFile.equals(conf.getProperty("mazeSearchingAlgorithm")) && Arrays.equals(maze.toByteArray(), mazeInFile)) {
                            toClient.writeObject(solutionInFile);
                            toClient.flush();
                            inFromFile.close();
                            fileinstream.close();
                            found = true;
                            break;
                        }
                        else
                        {
                            inFromFile.close();
                            fileinstream.close();
                        }
                    }
                }
            }
            if (!found){
                SearchableMaze problem = new SearchableMaze(maze);
                Solution solution = alg.solve(problem);
                toClient.writeObject(solution);
                toClient.flush();
                Object[] obj = new Object[3];
                obj[0] = maze.toByteArray();
                obj[1] = solution;
                obj[2] = conf.getProperty("mazeSearchingAlgorithm");
//                    fileOutputStream = new FileOutputStream(dir);
//                    Writer writeFile = new BufferedWriter(new FileWriter(dir));
//                    outToFile = new ObjectOutputStream(fileOutputStream);
//                    writeFile.writeObject(obj);

                File file = new File(dir, "new");
                fileOutputStream = new FileOutputStream(file);
                outToFile = new ObjectOutputStream(fileOutputStream);
                outToFile.writeObject(obj);
                outToFile.flush();
                outToFile.close();

            }
            toClient.close();
            fromClient.close();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

