package org.laborator;

public class App 
{
    public static void main( String[] args )
    {
        Runnable positionGame=new Game(5,320,8,500);
        new Thread(positionGame).start();
    }
}
