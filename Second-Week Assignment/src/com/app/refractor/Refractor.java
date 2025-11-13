package com.app.refractor;

import java.util.*;
import java.util.stream.Collectors;

public class Refractor {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //Imperative way
        List<Integer> evenDoublesImperative = new ArrayList<>();
        for (int num : numbers) {
            if (num % 2 == 0) {        
                evenDoublesImperative.add(num * 2);  
            }
        }
        System.out.println("Imperative result: " + evenDoublesImperative);

        //Using Streams
        List<Integer> evenDoublesFunctional = numbers.stream()
                .filter(num -> num % 2 == 0)      
                .map(num -> num * 2)              
                .collect(Collectors.toList());    

        System.out.println("Functional result: " + evenDoublesFunctional);
    }
}
