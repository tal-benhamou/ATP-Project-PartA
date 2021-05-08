package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    private ExecutorService threadPool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(Integer.parseInt(Configurations.Instance().getProperty("threadPoolSize")));
    }

    public void start() {
        new Thread(this::runServer).start();
    }

    private void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop) {
                try {
                    Socket client = serverSocket.accept();
                    threadPool.execute(() -> handleClient(client));
                } catch (SocketTimeoutException ex) {
                    System.out.println("Socket TimeOut");
                }
            }
            serverSocket.close();
            threadPool.shutdownNow();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        System.out.println("Client accepted : " + clientSocket.toString());

        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
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
