package playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Math.max;


public class RecursionCheatSheet {

    // Cache
    static HashMap<Integer, Integer> calculated = new HashMap<>();
    static int counter = 0;

    // Input: fibonacci(6)
    // Output: fibonacci(5) + fibonacci(4)
    // base case:
    // 1 return 1, O return 0
    public static int fibonacciWithoutCache(int n) {

        counter++;
        //if (calculated.containsKey(n)) return calculated.get(n);

        if (n < 0 ) return -1; // prevent infinite loops
        if (n == 1) return 1; // base case when n = 2 then it will call fibonacci(1)
        if (n == 0) return 0; // base case when n = 2 then it will also call fibonacci(0)
//        int result = fibonacci(n-1) + fibonacci(n-2);
//        calculated.put(n, result);
        return fibonacciWithoutCache(n-1) + fibonacciWithoutCache(n-2); // general equation
    }

    public static int fibonacciWithCache(int n) {
        counter++;
        if (calculated.containsKey(n)) return calculated.get(n);

        if (n < 0 ) return -1; // prevent infinite loops
        if (n == 1) return 1; // base case when n = 2 then it will call fibonacci(1)
        if (n == 0) return 0; // base case when n = 2 then it will also call fibonacci(0)
        int result = fibonacciWithCache(n-1) + fibonacciWithCache(n-2);
        calculated.put(n, result);
        return fibonacciWithCache(n-1) + fibonacciWithCache(n-2); // general equation
    }

    // Pattern to deal with array
    public static String reverse(char[] c, int n) {
        if (n == 1) return String.valueOf(c[0]);
        return c[n - 1] + reverse(c, n - 1);
    }

    public static int findMax(int[] i, int n) {
        if (n == 1) return i[0];
        return max(i[n - 1], findMax(i, n - 1));
    }

    public static int recursiveRange(int num) {
        if (num == 0) return 0;
        return num + recursiveRange(num - 1);
    }

    public static int productofArray(int[] A, int n) {
        if (n == 1) return A[0];
        return A[n -1] * productofArray(A, n-1 );
    }
    public static void main(String[] args) {
        int n = 15;

        counter = 0;
        System.out.println("fibonacciWithoutCache for " + n +" is " + fibonacciWithoutCache(n));
        System.out.println("Total recursive called : " + counter);

        counter = 0;
        System.out.println("fibonacciWithoutCache for " + n +" is " + fibonacciWithoutCache(n));
        System.out.println("Total recursive called : " + counter);

        counter = 0;
        System.out.println("fibonacciWithCache for " + n +" is " + fibonacciWithCache(n));
        System.out.println("Total recursive called with cache: " + counter);

        counter = 0;
        System.out.println("fibonacciWithCache for " + n +" is " + fibonacciWithCache(n));
        System.out.println("Total recursive called with cache: " + counter);

        char[] c = "This is a book".toCharArray();
        System.out.println(reverse(c, c.length));

        int[] i = {5,3,6,7,11,4,8,9};
        System.out.println(findMax(i, i.length));

        System.out.println(mergeSortedList(new Integer[]{1,3,5,7,8,8,9}, new Integer[]{2,3,6,8,9}));
        System.out.println(mergeSortedList(new Integer[]{2,3,6,8,9}, new Integer[]{1,3,5,7,8,8,9}));
        System.out.println(mergeSortedList(new Integer[]{}, new Integer[]{1,3,5,7,8,8,9}));
        System.out.println(mergeSortedList(new Integer[]{2,3,6,8,9}, new Integer[]{}));
    }

    public static ArrayList<Integer> mergeSortedList(Integer[] one, Integer[] two) {
        return mergeSortedList(new ArrayList<>(Arrays.asList(one)), 0, new ArrayList<>(Arrays.asList(two)), 0);
    }

    public static ArrayList<Integer> mergeSortedList(ArrayList<Integer> one, int n, ArrayList<Integer> two, int m) {
        if (n == one.size()) return new ArrayList<>(two.subList(m, two.size()));
        if (m == two.size()) return new ArrayList<>(one.subList(n, one.size()));
        ArrayList<Integer> result = new ArrayList<>();
        Integer numOne = one.get(n);
        Integer numTwo = two.get(m);
        if (numOne < numTwo) {
            result.add(numOne);
            result.addAll(mergeSortedList(one, n+1, two, m));
        } else {
            result.add(numTwo);
            result.addAll(mergeSortedList(one, n, two, m+1));
        }
        return result;
    }
}
