package org.laborator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game implements Runnable{

   private int numberOfPlayers;
   private int winnerProgressionLength;
   private Board board;
   private List<Runnable> players =new ArrayList<Runnable>();
   private CountDownLatch counter;
   private Timer timer;
   private boolean isResultAvailable=false;
   private int timePerGame;

    public Game(int numberOfPlayers,int numberOfTokens,int winnerProgressionLength,int timeInSeconds) {
        this.numberOfPlayers = numberOfPlayers;
        this.winnerProgressionLength = winnerProgressionLength;
        timePerGame=timeInSeconds;
        timer=new Timer(timeInSeconds);
        board=new Board(numberOfTokens);
        counter=new CountDownLatch(numberOfPlayers);
        board.setGame(this);
        fillPlayerList(numberOfPlayers);

    }

    private void fillPlayerList(int numberOfPlayers)
    {
        players= IntStream.range(1,numberOfPlayers+1).distinct().
                mapToObj(i->new Player("Player"+i,board,i,counter,timer)).collect(Collectors.toList());
    }


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public String toString() {
        return "Game{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", winnerProgressionLength=" + winnerProgressionLength +
                ", players=" + players +
                ", board=" + board +
                '}';
    }

    @Override
    public void run() {
        Thread timerThread=new Thread(timer);
        timerThread.setDaemon(true);
        timerThread.start();
        for(Runnable playerIterator:players)
        {
           new Thread(playerIterator).start();
        }
        try {
            counter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.setRunning(false);
        System.out.println("Running time of the game:"+timePerGame+"seconds");
        System.out.println("The game concluded in the following manner!");
        System.out.println("Elapsed time:"+(timePerGame-timer.getTimeInSeconds())+" seconds");
        System.out.println("Number of players:"+numberOfPlayers);
        System.out.println("Number of tokens:"+board.getTokensMaxValue());
        System.out.println("Winning condition:"+winnerProgressionLength);
        players.sort(new Comparator<Runnable>() {
            @Override
            public int compare(Runnable t1, Runnable t2) {
                Player p1=(Player) t1;
                Player p2=(Player) t2;
                if(p1.longestArithmeticProgression()<p2.longestArithmeticProgression())
                    return 1;
                else if(p1.longestArithmeticProgression()>p2.longestArithmeticProgression())
                    return -1;
                else return 0;
            }
        });
        for(Runnable player:players)
        {
            Player p=(Player)player;
            p.setScore(p.longestArithmeticProgression());
            System.out.println(p.getName()+"("+p.getTokens().toString()+")"+" got the score score:"+p.getScore());
        }
        for(Runnable player:players)
        {
            Player p=(Player)player;
            if(p.getScore()>=winnerProgressionLength) {
                System.out.println("Winner of the game:" + p.getName());
                break;
            }
        }

    }
}
