package org.laborator;

import com.github.javafaker.Faker;

import java.util.*;

public class InstanceRandomizer {
    private int hospitalsSize=0;
    private int residentsSize=0;
    private List<Resident> randResidents;
    private List<Hospital> randHospitals;
    Faker faker=new Faker();
    public InstanceRandomizer(int residentsSize,int hospitalsSize) {
            this.hospitalsSize=hospitalsSize;
            this.residentsSize=residentsSize;
            randResidents=generateResidents(residentsSize);
            randHospitals=generateHospitals(hospitalsSize);
    }
    private List<Resident> generateResidents(int size)
    {
        List<Resident> retList=new ArrayList<>();
        while(size!=0)
        {
            Resident resObj=new Resident(faker.name().fullName());
            retList.add(resObj);
            size--;
        }
        return retList;
    }
    private List<Hospital> generateHospitals(int size)
    {
        List<Hospital> hosList=new ArrayList<>();
        Random rand=new Random();
        while(size!=0)
        {
            Hospital hosObj=new Hospital(faker.medical().hospitalName(),rand.nextInt(100)+1);
            hosList.add(hosObj);
            size--;
        }
        return hosList;
    }
    public Map<Resident,List<Hospital>> generateResPrefList()
    {
        Random random=new Random();
        Map<Resident,List<Hospital>> resPref=new LinkedHashMap<>();
        for(Resident resIterator:randResidents)
        {
            int prefCounter=random.nextInt(randResidents.size())+1;
            List<Hospital> hospitalsList=new ArrayList<>();
            while(prefCounter!=0)
            {
                hospitalsList.add(randHospitals.get(random.nextInt(randHospitals.size())));
                prefCounter--;
            }
            resPref.put(resIterator,hospitalsList);
        }
        return resPref;
    }
    public Map<Hospital,List<Resident>> generateHosPrefList()
    {
        Random random=new Random();
        Map<Hospital,List<Resident>> hosPref=new LinkedHashMap<>();
        for(Hospital hosIterator:randHospitals)
        {
            int prefCounter=random.nextInt(randResidents.size())+1;
            List<Resident> residentsList=new ArrayList<>();
            while(prefCounter!=0)
            {
                residentsList.add(randResidents.get(random.nextInt(randResidents.size())));
                prefCounter--;
            }
            hosPref.put(hosIterator,residentsList);
        }
        return hosPref;
    }

    public List<Resident> getRandResidents() {
        return randResidents;
    }

    public List<Hospital> getRandHospitals() {
        return randHospitals;
    }
}
