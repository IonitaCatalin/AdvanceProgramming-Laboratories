package com.server;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) {
        try {
            GameServer server = new GameServer();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
