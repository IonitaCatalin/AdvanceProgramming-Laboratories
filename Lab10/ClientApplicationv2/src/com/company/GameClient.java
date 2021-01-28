package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    private static String serverAddress = "127.0.0.1";
    private static int serverPort = 8001;

    public static void main(String[] args) {
        while (true) {
            try (Socket socket = new Socket(serverAddress, serverPort);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream())) )
            {
                System.out.print("Input commands:");
                Scanner scanner=new Scanner(System.in);
                String dataIn=scanner.nextLine();
                if(dataIn.equals("exit"))
                {
                    System.out.println("Script execution ended on client request!Goodbye");
                    break;
                }
                else
                {
                    String requestToServer=dataIn;
                    out.println(requestToServer);
                    String responseFromServer = in.readLine ();
                    System.out.println(responseFromServer);
                }
            }
            catch (UnknownHostException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
