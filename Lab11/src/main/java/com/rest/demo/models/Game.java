package com.rest.demo.models;

import java.util.*;

public class Game {
    private List<Player> players;

    public Game() {
        players=new ArrayList<Player>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
