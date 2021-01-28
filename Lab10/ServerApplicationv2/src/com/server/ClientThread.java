package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket = null;
    private boolean running = false;


    public ClientThread(Socket socket) {
        this.running = true;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (running) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();

                if(request!=null) {
                    if(request.equals("stop"))
                    {
                        System.out.println("Received command:" + request);
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        String responseToClient = "Server stopped";
                        out.println(responseToClient);
                        out.flush();
                        this.running=false;
                        GameServer.running=false;
                    }
                    else {
                        System.out.println("Received command:" + request);
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        String responseToClient = "Server received the input:" + request;
                        out.println(responseToClient);
                        out.flush();
                    }
                }

            } catch (IOException e) {
                System.err.println("Communication error... " + e);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    public void parseRequest()
    {

    }

}
