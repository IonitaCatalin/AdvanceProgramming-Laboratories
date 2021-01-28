package com.server;

import com.gamelogic.Game;

import java.net.Socket;
import java.util.*;
import java.util.ArrayList;

public class GamesManager {
    private static volatile GamesManager instance;
    private static Object mutex =new Object();
    private List<Socket> sockets=new ArrayList<Socket>();
    private Map<Game,List<Socket>> gamesList=new HashMap<>();

    private GamesManager()
    {

    }
    public static GamesManager getInstance(){
        GamesManager game=instance;
        if(game==null)
        {
            synchronized (mutex){
                game=instance;
                if(game==null)
                    instance=game=new GamesManager();
            }
        }
        return game;
    }


    public List<Socket> getSockets() {
        return sockets;
    }

    public void setSockets(List<Socket> sockets) {
        this.sockets = sockets;
    }

    public Map<Game, List<Socket>> getGamesList() {
        return gamesList;
    }

    public void setGamesList(Map<Game, List<Socket>> gamesList) {
        this.gamesList = gamesList;
    }
    public void addBroadcastEnd(Socket socket)
    {
        sockets.add(socket);
    }
}
