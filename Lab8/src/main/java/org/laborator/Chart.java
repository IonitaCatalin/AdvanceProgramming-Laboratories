package org.laborator;

import java.util.Objects;

public class Chart {
    private String name;

    public Chart( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chart chart = (Chart) o;
        return Objects.equals(name, chart.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
