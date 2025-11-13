package com.app.comparable;

import java.util.concurrent.*;
import java.util.*;

public class Async{

        static CompletableFuture<String> fetchData(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
            
                Thread.sleep(1000 + (long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Data from " + name;
        });
    }

    public static void main(String[] args) {

        System.out.println("Starting async tasks...");


        CompletableFuture<String> api1 = fetchData("API 1");
        CompletableFuture<String> api2 = fetchData("API 2");
        CompletableFuture<String> api3 = fetchData("API 3");


        CompletableFuture<Void> allTasks = CompletableFuture.allOf(api1, api2, api3);


        allTasks.thenRun(() -> {
            System.out.println("\nAll API calls finished! Results:");
            System.out.println(api1.join());
            System.out.println(api2.join());
            System.out.println(api3.join());
        }).join();  

        System.out.println("\nProgram finished!");
    }
}
