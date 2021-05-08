package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ASearchingAlgorithm alg;
            Configurations conf = Configurations.Instance();
            boolean found = false;

            /*checking which searching algorithm*/
            switch (conf.getProperty("mazeSearchingAlgorithm")) {
                case "BFS":
                    alg = new BreadthFirstSearch();
                    break;
                case "DFS":
                    alg = new DepthFirstSearch();
                    break;
                case "BEST":
                    alg = new BestFirstSearch();
                    break;
                default:
                    alg = null;
                    break;
            }
            assert alg != null;
            int index = 0;
            Maze maze = (Maze) fromClient.readObject();
            byte[] mazeInFile;
            Solution solutionInFile;
            int hashMaze = maze.hashCode();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");

            FileInputStream fileinstream;
            ObjectInputStream inFromFile;

            File tempDirectory = new File(tempDirectoryPath);

            FileOutputStream fileOutputStream;
            ObjectOutputStream outToFile;


//obj[0] = maze (byte[]), obj[1] = solution
            if (tempDirectory.listFiles() != null) {
                for (File file : Objects.requireNonNull(tempDirectory.listFiles())) {
                    if (file.isFile()) {
                        try {
                            fileinstream = new FileInputStream(file);
                            inFromFile = new ObjectInputStream(fileinstream);
                            Object[] obj = (Object[]) inFromFile.readObject();
                            mazeInFile = (byte[]) obj[0];
                            solutionInFile = ((Solution) obj[1]);
                        } catch (Exception e) {
                            continue;
                        }
                        if (mazeInFile.length == maze.toByteArray().length && Arrays.equals(maze.toByteArray(), mazeInFile)) {
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
                    if (file.getName().contains(Integer.toString(hashMaze)))
                        index++;
                }
            }
            if (!found){
                SearchableMaze problem = new SearchableMaze(maze);
                Solution solution = alg.solve(problem);
                toClient.writeObject(solution);
                toClient.flush();
                Object[] obj = new Object[2];
                obj[0] = maze.toByteArray();
                obj[1] = solution;
                File newfile = new File(tempDirectory.getAbsolutePath() + "/"+ hashMaze +"_"+index);
                fileOutputStream = new FileOutputStream(newfile);

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

