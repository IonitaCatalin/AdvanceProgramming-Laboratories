package org.laborator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

public class Board {
    private int numberOfTokens;
    private int tokensMaxValue;
    private int order;
    private List<Token> tokens=new CopyOnWriteArrayList<Token>();
    private Game game;

    public Board( int numberOfTokens) {
        this.numberOfTokens=numberOfTokens;
        this.tokensMaxValue=numberOfTokens;
        fillTokenList(numberOfTokens);
        order=1;
    }
    private void fillTokenList(int numberOfTokens)
    {
        Random rand=new Random();
        tokens= IntStream.range(1,tokensMaxValue+1).distinct().limit(numberOfTokens).
                mapToObj(i->new Token(i,rand.nextInt(2)==1?true:false)).
                collect(Collectors.toList());
        int blankTokens=rand.nextInt(numberOfTokens/2);
    }

    public List<Token> getTokens() {
        return tokens;
    }
    public int tokensLeft()
    {
            return tokens.size();
    }
    public synchronized Token getRandomToken(int playerOrder)
    {
        while(order!=playerOrder) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(tokensLeft()==0) {
            order=order%getNumberOfPlayers()+1;
            notifyAll();
            return null;
        }
        else {
            Random rand = new Random();
            Token token = tokens.get(rand.nextInt(tokens.size()));
            tokens.remove(token);
            order = order % getNumberOfPlayers() + 1;
            notifyAll();
            return token;
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getNumberOfPlayers()
    {
        return game.getNumberOfPlayers();
    }

    public int getNumberOfTokens() {
        return numberOfTokens;
    }

    public int getTokensMaxValue() {
        return tokensMaxValue;
    }

    public void setTokensMaxValue(int tokensMaxValue) {
        this.tokensMaxValue = tokensMaxValue;
    }

    public void setNumberOfTokens(int numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
    }

    @Override
    public String toString() {
        return "Board{" +
                "tokens=" + tokens +
                '}';
    }

}
