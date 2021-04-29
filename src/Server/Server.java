package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    private int port;
    private int listeningIntervalMS;
    private ServerStrategy strategy;
    private boolean stop;

    public Server(int port, int listeningIntervalMS, ServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            while (!stop) {
                try {
                    Socket client = serverSocket.accept();
                    executor.submit(() -> handleClient(client));
                } catch (SocketTimeoutException ex) {
                    System.out.println("Socket TimeOut");
                }
            }
        }
         catch (IOException e) {
        e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket){
        System.out.println("Client accepted : " + clientSocket.toString());

        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        }catch (IOException e){
            System.out.println("IOException");
        }
        System.out.println("Done With Socket : " + clientSocket.toString());
    }

    public void stop(){
        System.out.println("Stopping the Server...");
        stop = true;
    }
}
