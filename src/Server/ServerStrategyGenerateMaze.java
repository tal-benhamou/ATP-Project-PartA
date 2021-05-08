package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {


    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Configurations conf = Configurations.Instance();
            AMazeGenerator generator;
            if (conf.getProperty("mazeGeneratingAlgorithm").equals("My"))
                generator = new MyMazeGenerator();
            else if (conf.getProperty("mazeGeneratingAlgorithm").equals("Simple"))
                generator = new SimpleMazeGenerator();
            else if (conf.getProperty("mazeGeneratingAlgorithm").equals("Empty"))
                generator = new EmptyMazeGenerator();
            else
                generator = null;
            assert generator != null;

            int[] arr = (int[]) fromClient.readObject();
            System.out.println(arr[0]);
            System.out.println(arr[1]);
            Maze maze = generator.generate(arr[0], arr[1]);
            MyCompressorOutputStream compress = new MyCompressorOutputStream(toClient);
            //toClient.writeObject(compress.Compress(maze.toByteArray()));
            compress.write(maze.toByteArray());
            compress.flush();
            System.out.println("The Maze WRITED to the client");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
