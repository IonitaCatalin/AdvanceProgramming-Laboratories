package org.laborator;

public class Timer implements Runnable{
    private int timeInSeconds;
    private boolean running=true;

    public Timer(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while(timeInSeconds>0 && running==true)
        {
            try
            {
                timeInSeconds--;
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
