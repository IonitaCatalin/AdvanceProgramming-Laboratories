package org.laborator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hospital implements Comparable {

    private int capacity;
    private String name;

    public Hospital(String name, int count, int capacity) {
        this.name = name+count;
        this.capacity=capacity;
    }

    public Hospital(String name,int capacity) {
        this.name = name;
        this.capacity=capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }
    public void lowerCapacity()
    {
        this.capacity=this.capacity-1;
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Hospital) o).getName());
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name+"("+this.getCapacity()+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return capacity == hospital.capacity &&
                Objects.equals(name, hospital.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, name);
    }
}
