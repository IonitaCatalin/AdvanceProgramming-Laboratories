package org.laborator;


import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        List<Resident> residentList=new ArrayList<>();
        Set<Hospital> hospitalSet=new TreeSet<>();
        var residents= IntStream.rangeClosed(0,3).mapToObj(i->new Resident("R",i)).toArray(Resident[]::new);
        var hospitals= IntStream.rangeClosed(0,2).mapToObj(i->new Hospital("H",i,i)).toArray(Hospital[]::new);
        hospitals[0].setCapacity(1);
        hospitals[1].setCapacity(2);

        residentList.addAll(Arrays.asList(residents));
        hospitalSet.addAll(Arrays.asList(hospitals));

        Collections.sort(residentList);

        Map<Resident, List<Hospital>> resPrefMap=new LinkedHashMap<>();
        Map<Hospital, List<Resident>> hosPrefMap=new HashMap<>();

        resPrefMap.put(residents[0],new ArrayList<Hospital>(Arrays.asList(hospitals[0],hospitals[1],hospitals[2])));
        resPrefMap.put(residents[1],new ArrayList<Hospital>(Arrays.asList(hospitals[0],hospitals[1],hospitals[2])));
        resPrefMap.put(residents[2],new ArrayList<Hospital>(Arrays.asList(hospitals[0],hospitals[1])));
        resPrefMap.put(residents[3],new ArrayList<Hospital>(Arrays.asList(hospitals[0],hospitals[1])));

        hosPrefMap.put(hospitals[0],new ArrayList<Resident>(Arrays.asList(residents[3],residents[0],residents[1],residents[2])));
        hosPrefMap.put(hospitals[1],new ArrayList<Resident>(Arrays.asList(residents[0],residents[2],residents[1])));
        hosPrefMap.put(hospitals[2],new ArrayList<Resident>(Arrays.asList(residents[0],residents[1],residents[3])));

        System.out.println("Residentii si preferintelor lor:"+resPrefMap.toString());
        System.out.println("Spitalele si preferintelor lor:"+hosPrefMap.toString());

       List<Hospital> resTarget= Arrays.asList(hospitals[0],hospitals[2]);
        System.out.println("Residenti care prefera spitalele H0 si H2 sunt:");
        residentList.stream().
                filter(res->resPrefMap.get(res).containsAll(resTarget)).
                forEach(System.out::println);
        System.out.println("Spitalele care il au pe R0 ca si optiune principala sunt:");
        hospitalSet.stream()
                .filter(res -> hosPrefMap.get(res).get(0).equals(residentList.get(0)))
                .forEach(System.out::println);

        Problem exampleInstance=new Problem(hosPrefMap,resPrefMap);
        System.out.println(exampleInstance.toString());
        exampleInstance.generatePossMatching();

        System.out.println(exampleInstance.getMatching().toString());
        System.out.println("Matching_Stabil:"+exampleInstance.isStableMatching());

        InstanceRandomizer randInstance=new InstanceRandomizer(200,20);
        Problem instanceWithRandom =new Problem(randInstance.generateHosPrefList(),randInstance.generateResPrefList());
        System.out.println(instanceWithRandom.toString());
        instanceWithRandom.generatePossMatching();
        System.out.println(instanceWithRandom.getMatching().toString());
        System.out.printf("Matching_Stabil:"+instanceWithRandom.isStableMatching());



    }
}
