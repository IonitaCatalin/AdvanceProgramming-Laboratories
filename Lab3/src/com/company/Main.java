package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        Item book1=new Book(300,"Dragon Rising",5);
        Item book2=new Book(300,"A Blade in the Dark",5);
        Item food1=new Food(2,"Cabbage");
        Item food2=new Food(2,"Rabbit");
        Item weapon1=new Weapon(10,5, Weapon.WeaponType.SWORD);

        Knapsack knapsack=new Knapsack(10);

        List<Item> availableItems=new ArrayList<>(Arrays.asList(book1,book2,food1,food2,weapon1));
        Collections.sort(availableItems);
        knapsack.addItems(weapon1,book1,food1);
        System.out.println(availableItems.toString());
        System.out.println(knapsack.toString());

        Algorithm greedyAlg=new GreedyAlgorithm(10);
        Algorithm dpAlg=new DpAlgorithm(10);
        greedyAlg.setAvailableItemsList(availableItems);
        dpAlg.setAvailableItemsList(availableItems);
        System.out.println("Rezolvarea folosing Greedy:");
        greedyAlg.solve();
        System.out.println(greedyAlg.toString());
        System.out.printf("Rezolvarea folosing Dp:");
        dpAlg.solve();
        System.out.println(dpAlg.toString());

        AlgorithmBenchmarker benchmark=new AlgorithmBenchmarker(100000);
        benchmark.runBenchmark();
        System.out.println(benchmark.toString());


    }
}
