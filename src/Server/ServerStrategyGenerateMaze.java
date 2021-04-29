package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements ServerStrategy{


    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

        int[] arr = (int[]) fromClient.readObject();
        MyMazeGenerator generator = new MyMazeGenerator();
        Maze maze = generator.generate(arr[0], arr[1]);
        MyCompressorOutputStream compress = new MyCompressorOutputStream(toClient);
        compress.write(maze.toByteArray());
        compress.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
