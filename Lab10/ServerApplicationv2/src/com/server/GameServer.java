package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final String serverAddress = "127.0.0.1";
    private static final int port=8001;
    public static boolean running=true;
    private GamesManager manager=GamesManager.getInstance();

    public GameServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println ("Server started on address "+serverAddress+":"+port);
            System.out.println("Waiting for client request...");
            while (running) {
                Socket socket = serverSocket.accept();
                System.out.println("Client request in progress...");
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err. println ("Something went wrong when trying to process user input");
        }
        finally {
            serverSocket.close();
        }
    }

}
