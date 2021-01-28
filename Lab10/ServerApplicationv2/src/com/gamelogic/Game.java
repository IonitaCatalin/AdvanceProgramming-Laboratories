package com.gamelogic;

import java.util.*;

public class Game {
    private int gameId;
    private Board board;
    private List<Player> players=new ArrayList<Player>();
    private boolean waitingForPlayers;
    private int currentTurn;

    public Game() {
        Random random=new Random();
        this.gameId=random.nextInt(1400);
        this.board=new Board();
        this.waitingForPlayers=true;
        this.currentTurn=1;
    }
    public void addPlayer(Player player)
    {
        this.players.add(player);
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Board getBoard() {
        return board;
    }
    public void endTurn()
    {
        if(currentTurn==1)
            currentTurn=2;
        else currentTurn=1;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isWaitingForPlayers() {
        return waitingForPlayers;
    }

    public void setWaitingForPlayers(boolean waitingForPlayers) {
        this.waitingForPlayers = waitingForPlayers;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
}
