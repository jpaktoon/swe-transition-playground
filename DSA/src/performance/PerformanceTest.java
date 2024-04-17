package performance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PerformanceTest {

    public static long testStringBuilderAppend(int iterations) {
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("String");
        }
        String result = sb.toString();
        return System.currentTimeMillis() - startTime;
    }

    public static long testStringConcat(int iterations) {
        long startTime = System.currentTimeMillis();
        String result = "";
        String start = "start";
        String end = "end";
        for (int i = 0; i < iterations; i++) {
            result += "String";
            System.out.println(start + " " + end);
        }
        return System.currentTimeMillis() - startTime;
    }

    public static long mergeUsingAddAll(ArrayList<Integer> l1, ArrayList<Integer> l2 ){
        long startTime = System.currentTimeMillis();
        l1.addAll(l2);
        return System.currentTimeMillis() - startTime;
    }

    public static long mergeUsingStreamAPI(ArrayList<Integer> l1, ArrayList<Integer> l2 ){
        long startTime = System.currentTimeMillis();
        List<Integer> ml = Stream.concat(l1.stream(), l2.stream())
                //.filter(i -> i % 2 == 0) // Filter only even numbers ****
                .toList();
        return System.currentTimeMillis() - startTime;
    }

    public static void memUsage(){
        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory(); // Total memory currently reserved for JVM
        long freeMemory = runtime.freeMemory();   // Currently free memory in the heap
        long maxMemory = runtime.maxMemory();     // Maximum memory the JVM will attempt to use

        System.out.println("Total Memory: " + totalMemory / (1024 * 1024) + " MB");
        System.out.println("Free Memory: " + freeMemory / (1024 * 1024) + " MB");
        System.out.println("Max Memory: " + maxMemory / (1024 * 1024) + " MB");

        // The JVM might use memory outside the heap for other purposes like code,
        // thread stacks, and native methods. This memory usage won't be reflected
        // in the calculation.
        System.out.println("Usage Memory: " + (totalMemory - freeMemory)  / (1024 * 1024) + " MB");
    }
    public static void main(String[] args) {
        int iterations = 1000000; // Number of iterations for testing
//        long timeOne = testStringBuilderAppend(iterations);
//        System.out.println("Time for String builder append: " + timeOne + " ms");
//
//        long timeTwo = testStringConcat(iterations);
//        System.out.println("Time for String concat: " + timeTwo + " ms");

        String[] s0 = new String[5];
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            list1.add(i);
        }

        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 10; i < iterations; i++) {
            list2.add(i);
        }

        long timeOne = mergeUsingAddAll(list1, list2);
        System.out.println("Time for addAll: " + timeOne + " ms");

        long timeTwo = mergeUsingStreamAPI(list1, list2);
        System.out.println("Time for stream API: " + timeTwo + " ms");

        //memUsage();
    }
}
