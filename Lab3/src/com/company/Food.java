package com.company;

import java.util.Objects;

public class Food implements Item {
    private double weight;
    private String name;

    public Food(double weight, String name) {
        this.weight = weight;
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return getWeight()*2;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Object o) {
        return this.getName().compareTo(((Item)o).getName());
    }

    @Override
    public int compareByValue(Object obj) {
        Item toCompare=(Item)obj;
        if(this.getValue()<toCompare.getValue())
            return 1;
        else if(this.getValue()==toCompare.getValue())
            return 0;
        else return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return weight == food.weight &&
                Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, name);
    }

    @Override
    public String toString() {
        return "food:"+getName()+","+"weight="+getWeight()+","+"value="+getValue()
                +" "+"("+"profit factor="+profitFactor()+")"+"\n";
    }
}
