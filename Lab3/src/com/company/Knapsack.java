package com.company;

import java.util.*;

public class Knapsack {
    private List<Item> items=new ArrayList<>();
    private double capacity;

    public Knapsack(double capacity) {
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
    public void addItems(Item...allItems)
    {
        for(Item itemIterator:allItems)
            items.add(itemIterator);
    }
    public double getCapacity() {
        return capacity;
    }

    public double getWeight()
    {
        int totalWeigh=0;
        for(Item itemIter:items)
            totalWeigh+=itemIter.getWeight();
        return totalWeigh;
    }
    public double getValue()
    {
        int totalVal=0;
        for(Item itemIter:items)
            totalVal+=itemIter.getValue();
        return totalVal;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }


    public void setItems(Item...allItems) {
        for(Item itemIterator:allItems)
            items.add(itemIterator);

    }

    @Override
    public String toString() {
        return "selected items:"+items+"("+"total weight="+this.getWeight()+","+"total value="+this.getValue()+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knapsack knapsack = (Knapsack) o;
        return Double.compare(knapsack.capacity, capacity) == 0 &&
                Objects.equals(items, knapsack.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, capacity);
    }
}
