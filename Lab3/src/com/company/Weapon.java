package com.company;

import java.util.Objects;

public class Weapon implements Item {
    private double value;
    private double weight;
    public enum WeaponType {SWORD,KNIFE,GUN,STICK};
    WeaponType type;


    public Weapon(double value, double weight, WeaponType type) {
        this.value = value;
        this.weight = weight;
        this.type = type;
    }

    @Override
    public int compareTo(Object o) {
        return this.getName().compareTo(((Item)o).getName());

    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public WeaponType getType() {
        return type;
    }

    @Override
    public String getName() {
        return type.name();
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getWeight() {
        return weight;
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
        return "weapon:"+type.name()+","+"weight="+getWeight()+","+"value="+getValue()+
                " "+"("+"profit factor="+profitFactor()+")"+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return value == weapon.value &&
                weight == weapon.weight &&
                type == weapon.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, weight, type);
    }
}
