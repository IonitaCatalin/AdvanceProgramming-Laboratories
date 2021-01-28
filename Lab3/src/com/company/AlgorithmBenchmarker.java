package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.company.Weapon.WeaponType.SWORD;

public class AlgorithmBenchmarker {
    GreedyAlgorithm greedyApproach;
    DpAlgorithm dynamicApproach;
    List<Item> samples=new ArrayList<>();
    long greedyRuntime=0;
    long dpProgrammingRuntime=0;
    double greedyBestValue=0.0;
    double dynamicBestValue=0.0;
    int sampleSize=0;

    public AlgorithmBenchmarker(int sampleSize) {

        this.sampleSize = sampleSize;
        Random capacityRand=new Random();
        greedyApproach=new GreedyAlgorithm(capacityRand.nextDouble()*1000);
        dynamicApproach=new DpAlgorithm(capacityRand.nextDouble()*1000);
    }
    private List<Item> generateSampleData()
    {

        List<Item> generated=new ArrayList<>();
        Random turnRand=new Random();
        Random weightRand=new Random();
        Random valueRand=new Random();
        Random pageNumRand=new Random();
        while(sampleSize!=0)
        {
            Item toBeGenerated;
            int turn=turnRand.nextInt(3)+1;
            switch(turn){
                case 1:
                {
                    toBeGenerated=new Book(pageNumRand.nextInt(150),"",valueRand.nextDouble());
                    break;
                }
                case 2:
                {
                    toBeGenerated=new Food(weightRand.nextDouble()*100,"");
                    break;
                }
                case 3:
                {
                    toBeGenerated=new Weapon(valueRand.nextDouble()*100,weightRand.nextDouble(),SWORD);
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + turn);
            }
            generated.add(toBeGenerated);
            sampleSize--;
        }
        return generated;
    }
    public void runBenchmark()
    {
        List<Item> items= new ArrayList<>(generateSampleData());
        greedyApproach.setAvailableItemsList(items);
        dynamicApproach.setAvailableItemsList(items);
        long start,end;
         start=System.nanoTime();
        greedyApproach.solve();
         end=System.nanoTime();
        greedyRuntime=(end-start);
        start=System.nanoTime();
        dynamicApproach.solve();
        end=System.nanoTime();
        dpProgrammingRuntime=(end-start);
        greedyBestValue=greedyApproach.getKnapsack().getValue();
        dynamicBestValue=dynamicApproach.getKnapsack().getValue();
    }

    public GreedyAlgorithm getGreedyApproach() {
        return greedyApproach;
    }

    public DpAlgorithm getDynamicApproach() {
        return dynamicApproach;
    }

    public List<Item> getSamples() {
        return samples;
    }

    public int getSampleSize() {
        return sampleSize;
    }

    @Override
    public String toString() {
        return "Greedy("+"time:"+ TimeUnit.NANOSECONDS.toMillis(greedyRuntime)+" ms"+","+"best-value:"+greedyBestValue+")"+"\n"+
                "Dynamic Programming("+"time:"+TimeUnit.NANOSECONDS.toMillis(dpProgrammingRuntime)+" ms"+","+"best-value:"+dynamicBestValue+")";
    }
}
