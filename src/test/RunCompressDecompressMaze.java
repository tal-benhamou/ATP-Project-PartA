package test;
import IO.MyCompressorOutputStream;
//import IO.MyDecompressorInputStream;
import IO.MyDecompressorInputStream;
import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(900, 900); //Generate new maze
        try {
// save maze to a file
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            //OutputStream out = new SimpleCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try {
//read maze from file
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            //InputStream in = new SimpleDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals));

        byte[] tmp = new byte[2];

        short x = 1000;
        tmp[0] = (byte) (x & 0xff);
        tmp[1] = (byte) ((x >> 8) & 0xff);
        for (int i = 0; i<8 ;i++){
            if ((((tmp[0]+256) >>> i) & 1) != 0)
                System.out.println("index : " + i + " Of " +tmp[0] +" is ON");
            if ((((tmp[1]+256) >>> i) & 1) != 0)
                System.out.println("index : " + (i+8) + " Of " +tmp[1] +" is ON");
        }
        System.out.println(tmp[0]);
        System.out.println(tmp[1]);


//maze should be equal to loadedMaze
    }
}
