package org.laborator;

import com.github.javafaker.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Resident implements Comparable {

    private String name;

    public Resident(String name, int count) {
        this.name = "R"+count;
    }

    public Resident(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resident resident = (Resident) o;
        return Objects.equals(name, resident.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public int compareTo(Object o) {
       return this.name.compareTo(((Resident) o).getName());
    }

    @Override
    public String toString() {
        return this.name;
    }
}
