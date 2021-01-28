package org.laborator;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Problem {
    private Matching matching=new Matching();
    private Map<Hospital,List<Resident>> hosPrefMap=new LinkedHashMap<>();
    private Map<Resident,List<Hospital>> resPrefMap=new LinkedHashMap<>();
    boolean isPossible=true;
    /*
        Am ales sa construiesc un map separat pentru a pastra capacitatea disponibila per spital
        pentru a pastra pe cat se poate mapele cu preferinte intacte pe parcursul rularii algoritmului
     */
    private Map<Hospital,Integer> spacePerHospital=new HashMap<>();

    public Matching getMatching() {
        return matching;
    }

    public void setMatching(Matching matching) {
        this.matching = matching;
    }

    public Map<Hospital, List<Resident>> getHosPrefMap() {
        return hosPrefMap;
    }

    public void setHosPrefMap(Map<Hospital, List<Resident>> hosPrefMap) {
        this.hosPrefMap = hosPrefMap;
    }

    public Map<Resident, List<Hospital>> getResPrefMap() {
        return resPrefMap;
    }

    public void setResPrefMap(Map<Resident, List<Hospital>> resPrefMap) {
        this.resPrefMap = resPrefMap;
    }
    public boolean isStableMatching()
    {
        if(isPossible==false)
        {
            return false;
        }
        if(matching.isEmpty())
        {
            return false;
        }
        else
        {
            /*
                Conditiile sunt aceleasi ca si cele expuse in curs sa nu exista (r,h) astfel inca r ii este asignat lui h' dar r il prefera mai mult pe h
                decat pe h il prefera pe r mai mult decat pe orice alt resident posibil
                Am considerat ca relatia de "potrivire" este maxima atunci cand rezidentul respectiv spitalul se afla pe pozitia 0 in lista de rezidenti
                respectiv de spitale a mapelor asociate relatiilor de preferinte.
             */
                for(Element elemIterator:matching.getMatching())
                {
                    if(resPrefMap.get(elemIterator.getRes()).get(0).equals(elemIterator.getHos())==false && hosPrefMap.get(elemIterator.getHos()).get(0).equals(elemIterator.getRes())==true)
                        return false;
                }
        }
        return true;
    }
    public void generatePossMatching()
    {


        for(Map.Entry<Resident,List<Hospital>> entry:resPrefMap.entrySet())
        {
            boolean isAnySpotFree=false;
            for(Hospital preferredHospital:entry.getValue())
            {
                if(spacePerHospital.get(preferredHospital)>0 && hosPrefMap.get(preferredHospital).contains(entry.getKey()))
                {
                    isAnySpotFree=true;
                    matching.addNewElement(new Element(entry.getKey(),preferredHospital));
                    spacePerHospital.put(preferredHospital ,spacePerHospital.get(preferredHospital).intValue()-1);
                    break;
                }
            }
            if(!isAnySpotFree)
            {
                isPossible=false;
            }
        }
        if(isPossible==false)
        {
            System.out.printf("ATENTIE!Algoritmul v-a oferii un matching insa aceasta este incomplet!\n");
        }

    }

    public Problem(Map<Hospital, List<Resident>> hosPrefMap, Map<Resident, List<Hospital>> resPrefMap) {
        this.hosPrefMap = hosPrefMap;
        this.resPrefMap = resPrefMap;
        for(Map.Entry<Hospital,List<Resident>> entry:hosPrefMap.entrySet())
        {
            spacePerHospital.put(entry.getKey(),entry.getKey().getCapacity());
        }
    }

    @Override
    public String toString() {
        return "Problema:\n" +
                "Preferintele spitalelor:" + hosPrefMap +
                "\nPreferintele rezidentilor:" + resPrefMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return Objects.equals(matching, problem.matching) &&
                Objects.equals(hosPrefMap, problem.hosPrefMap) &&
                Objects.equals(resPrefMap, problem.resPrefMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matching, hosPrefMap, resPrefMap);
    }
}
