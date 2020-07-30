package server;

import controller.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private int serverPort;
    private ServerSocket serverSocket;
    private volatile boolean running;



    public Server(int serverPort){
        try {
            this.serverPort=serverPort;
            serverSocket=new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(this, socket);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}
