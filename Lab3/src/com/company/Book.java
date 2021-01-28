package com.company;

import java.util.Objects;


public class Book implements Item {
    private int pageNumber;
    private double value;
    String name;

    public double getWeight() {
        return pageNumber/100;
    }

    public Book(int pageNumber,String name,double value) {
        this.pageNumber = pageNumber;
        this.name=name;
        this.value=value;

    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
    public String toString() {
        return "book:"+getName()+","+"weight="+getWeight()+","+"value="+getValue()
                +" "+"("+"profit factor="+profitFactor()+")"+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pageNumber == book.pageNumber &&
                value == book.value &&
                Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, value, name);
    }
}
