package org.laborator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Player implements Runnable {
    private String name;
    private int score=0;
    private int order;
    private int blankTokensNumber=0;
    boolean running=true;
    private List<Token> tokens = new ArrayList<Token>();
    private Board board;
    private Timer timer;

    private CountDownLatch latch;

    public Player(String name, Board board, int order, CountDownLatch latch,Timer timer) {
        this.name = name;
        this.board=board;
        this.order=order;
        this.latch=latch;
        this.timer=timer;
    }
    public int longestArithmeticProgression() {
        if (tokens.size() <= 2)
            return tokens.size();

        List<Token> blanks=new ArrayList<Token>();
        for(Token tokenIterator:tokens)
            if(tokenIterator.isBlank()) {
                blanks.add(tokenIterator);
                blankTokensNumber++;
            }
        tokens.removeAll(blanks);

        tokens.sort(new Comparator<Token>() {
            @Override
            public int compare(Token t1, Token t2) {
                if(t1.getValue()>t2.getValue())
                    return 1;
                else if(t1.getValue()==t2.getValue())
                    return 0;
                else return -1;
            }
        });

            int size=tokens.size();
            int lookup[][]=new int[size][size];
            int longestApLength=2;
            for(int i=0;i<size;i++)
                lookup[i][size-1]=2;

            for(int j=size-2;j>=1;j--)
            {
                int i=j-1;
                int k=j+1;
                while(i>=0 && k<=size-1)
                {
                    int isArithmeticProgression=(tokens.get(i).getValue()+tokens.get(k).getValue())-2*tokens.get(j).getValue();
                    if(isArithmeticProgression<0)
                    {
                        k++;
                    }
                    else if(isArithmeticProgression >0)
                    {
                        i--;
                    }
                    else
                    {
                        lookup[i][j]=lookup[j][k]+1;
                        longestApLength=Integer.max(longestApLength,lookup[i][j]);
                        k++;
                        i--;
                    }
                }
            }
            return longestApLength+blankTokensNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    public void drawSingleToken()
    {
        Token token=board.getRandomToken(order);
        if(token!=null) {
            tokens.add(token);
        }
        else running=false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void run() {
        while(running && timer.getTimeInSeconds()>0)
        {
                drawSingleToken();
        }
        latch.countDown();
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", tokens=" + tokens +
                '}';
    }
}
