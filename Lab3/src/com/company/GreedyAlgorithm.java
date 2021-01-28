package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GreedyAlgorithm implements Algorithm {
    private List<Item> availableItems=new ArrayList<>();
    private Knapsack knapsack;
    private double runningTime=0;

    public Knapsack getKnapsack() {
        return knapsack;
    }

    @Override
    public void setAvailableItems(Item... allItems) {
        for(Item itemIterator:allItems)
            availableItems.add(itemIterator);
    }

    @Override
    public void setAvailableItemsList(List<Item> availableItems) {
        this.availableItems = availableItems;
    }

    public GreedyAlgorithm(double capacity) {
       knapsack=new Knapsack(capacity);
    }

    public void solve()
    {
        long startTime=System.nanoTime();
        Collections.sort(availableItems,Item::compareByValue);
        double currentCapacity=0;
        for(Item itemIterator:availableItems) {
            if (currentCapacity + itemIterator.getWeight() <= knapsack.getCapacity()) {
                knapsack.addItem(itemIterator);
                knapsack.setCapacity(knapsack.getCapacity() - itemIterator.getWeight());
            }
        }
        long stopTime=System.nanoTime();
        runningTime= TimeUnit.NANOSECONDS.toMillis(stopTime-startTime);
    }
    public double getRunningTime()
    {
        return runningTime;
    }

    @Override
    public String toString() {
        return "capacity of knapsack="+knapsack.getCapacity()+"\n"+"available items:"+"\n"+availableItems+"\n"+
                knapsack.toString().replace("[","").replace("]","")+"\n"
                +"running time:"+this.getRunningTime()+"ms"+"\n";
    }
}
