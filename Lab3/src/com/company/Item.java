package com.company;



public interface Item extends Comparable
{
    String getName();
    double getValue();
    double getWeight();
    default double profitFactor()
    {
        return getValue()/getWeight();
    }
    int compareByValue(Object Obj);
}
