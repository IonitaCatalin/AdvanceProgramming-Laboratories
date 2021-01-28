package org.laborator;

import java.util.*;
import java.util.HashMap;

public class Element {
    private Map<Resident, Hospital> preference=new LinkedHashMap<>();
    private Resident res;
    private Hospital hos;

    public Element(Resident res,Hospital hos) {
        this.res=res;
        this.hos=hos;
    }

    public Map<Resident, Hospital> getPreference() {
        return preference;
    }

    public void setPreference(Map<Resident, Hospital> preference) {
        this.preference = preference;
    }

    public Resident getRes() {
        return res;
    }

    public void setRes(Resident res) {
        this.res = res;
    }

    public Hospital getHos() {
        return hos;
    }

    public void setHos(Hospital hos) {
        this.hos = hos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(preference, element.preference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preference);
    }

    @Override
    public String toString() {
        return "("+res.toString()+"=>"+hos.toString()+")"+"\n";
    }
}
