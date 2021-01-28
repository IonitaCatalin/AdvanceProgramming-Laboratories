package org.laborator;

import java.util.*;
import java.util.Map;

public class Matching {
    List<Element> matching=new ArrayList<>();

    public List<Element> getMatching() {
        return matching;
    }

    public void setMatching(List<Element> matching) {
        this.matching = matching;
    }

    public void addNewElement(Element el)
    {
        matching.add(el);
    }

    public boolean isEmpty()
    {
        return matching.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matching matching1 = (Matching) o;
        return Objects.equals(matching, matching1.matching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matching);
    }

    @Override
    public String toString() {
        return "Posibil matching:\n"+matching.toString();
    }
}
