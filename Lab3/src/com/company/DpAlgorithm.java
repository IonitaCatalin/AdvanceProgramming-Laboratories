package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DpAlgorithm implements Algorithm {
    private List<Item> availableItems=new ArrayList<>();
    private Knapsack knapsack;
    private double runningTime=0;

    public DpAlgorithm(double capacity) {
       knapsack=new Knapsack(capacity);
    }

    public Knapsack getKnapsack() {
        return knapsack;
    }

    public void solve()
    {
        long startTime=System.nanoTime();
        int itemCount=availableItems.size();
        int weightTotal=(int)Math.ceil(knapsack.getCapacity());
        double dpMatrix[][] = new double[itemCount+1][weightTotal+1];

       for(int i=0;i<=itemCount;i++)
        for(int j=0;j<=weightTotal;j++){
            dpMatrix[i][j]=0;
        }

       for(int i=1;i<=itemCount;i++) {
           for (int j = 0; j <= weightTotal; j++) {


                dpMatrix[i][j]=dpMatrix[i-1][j];

                int weight=(int)availableItems.get(i-1).getWeight();
                int win=(int) availableItems.get(i-1).getValue();

                if((j >= weight) && (dpMatrix[i][j] < dpMatrix[i-1][j-weight]+win))
                {
                    dpMatrix[i][j]=dpMatrix[i-1][j-weight]+win;
                }
           }
       }


       while(itemCount!=0)
       {

           if(dpMatrix[itemCount][weightTotal]!=dpMatrix[itemCount-1][weightTotal])
           {
               knapsack.addItem(availableItems.get(itemCount-1));
               weightTotal-=availableItems.get(itemCount-1).getWeight();
           }
           itemCount--;
       }
       long stopTime=System.nanoTime();
       runningTime= TimeUnit.NANOSECONDS.toMillis(stopTime-startTime);

    }
    public double getRunningTime()
    {
        return runningTime;
    }
    @Override
    public void setAvailableItems(Item... allItems) {
        for(Item itemIterator:allItems)
        {
            availableItems.add(itemIterator);
        }
    }
    @Override
    public void setAvailableItemsList(List<Item> items) {
        this.availableItems=items;
    }

    @Override
    public String toString() {
        return "capacity of knapsack="+knapsack.getCapacity()+"\n"+"available items:"+"\n"+availableItems+"\n"+
                knapsack.toString().replace("[","").replace("]","")+"\n"
                +"\n"+"running time:"+this.getRunningTime()+"ms";
    }
}
