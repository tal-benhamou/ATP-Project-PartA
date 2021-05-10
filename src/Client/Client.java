package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP;
    private int port;
    private IClientStrategy c_strategy;

    public Client(InetAddress serverIP, int port, IClientStrategy c_strategy) {
        this.serverIP = serverIP;
        this.port = port;
        this.c_strategy = c_strategy;
    }

    public void communicateWithServer(){
        try {
            Socket serverSocket = new Socket(serverIP, port);
            c_strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
