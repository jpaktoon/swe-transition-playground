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

    static HashMap<Integer, Integer> stairs = new HashMap<Integer, Integer>();

    // Recursive
    public int climbStairs1(int n) {
        if (stairs.containsKey(n)) return stairs.get(n);
        if (n < 0) return 0;
        if (n <= 1) return 1;
        int result = climbStairs1(n-1) + climbStairs2(n-2);
        stairs.put(n, result);
        return result;
    }

    // Dynamic programming
    public int climbStairs2(int n) {
        int a = 1;
        int b = 1;

        for (int i = 0; i < n - 1; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // Bottom-up recursion - Factorial
    public static int fac(int n) {
        if (n == 1) {
            return n;
        }
        return n * fac(n - 1 );
    }

    // Top-down recursive - factorial
    // initial total = 1
    public static int fac(int n, int total) {
        if (n == 1) {
            return total;
        }

        return fac( n - 1, n * total );
    }

    // Pattern to deal with array
    // bottom - up
    public static String reverse(char[] c, int n) {
        if (n == 1) return String.valueOf(c[0]);
        return c[n - 1] + reverse(c, n - 1);
    }

    // top - down
    // initial result = ""
    // n start from string length so n - 1 is the last index
    public static String reverse(char[] c, int n, String result) {
        if (n == 1) return result + c[0];
        return reverse(c, n - 1, result + c[n - 1]);
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
        System.out.println("reverse 1 " + reverse(c, c.length));
        System.out.println("reverse 2 " + reverse(c, c.length, ""));

        int[] i = {5,3,6,7,11,4,8,9};
        System.out.println(findMax(i, i.length));

        System.out.println(mergeSortedList(new Integer[]{1,3,5,7,8,8,9}, new Integer[]{2,3,6,8,9}));
        System.out.println(mergeSortedList(new Integer[]{2,3,6,8,9}, new Integer[]{1,3,5,7,8,8,9}));
        System.out.println(mergeSortedList(new Integer[]{}, new Integer[]{1,3,5,7,8,8,9}));
        System.out.println(mergeSortedList(new Integer[]{2,3,6,8,9}, new Integer[]{}));

        // Factorial
        System.out.println("fac(5) => " + fac(5));
        System.out.println("fac(5, 1) => " + fac(5, 1));

        // mazeSolver
        char[][] mySmallMaze = {
                {' ', ' ', ' '},
                {' ', '*', ' '},
                {' ', ' ', 'e'}
        };

        char[][] maze = {
                {' ', ' ', ' ', '*', ' ', ' ', ' '},
                {'*', '*', ' ', '*', ' ', '*', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', '*', '*', '*', '*', '*', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'e'}
        };

        // Test the function
        String smallMazeSolution = mazeSolver(mySmallMaze, new int[]{0, 0}, "");
        String largeMazeSolution = mazeSolver(maze, new int[]{0, 0}, "");

        System.out.println("Small Maze Solution: " + smallMazeSolution);  // Possible output: "RRDD"
        System.out.println("Large Maze Solution: " + largeMazeSolution);  // Possible output: "RRDDLLDDRRRRRR"

        System.out.println(rob(new int[] {1, 2, 3, 1, 1, 4}));
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

    public static int rob(int[] nums) {
        int rob1 = 0, rob2 = 0;

        for (int n : nums) {
            int temp = Math.max(n + rob1, rob2);
            System.out.println("max("+ (n + rob1) + ", " + rob2 + ") --> choose " + temp + ", keep " + rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }

    public static String mazeSolver(char[][] maze, int[] position, String path) {
        int x = position[0];
        int y = position[1];

        // Check if the current position is out of bounds or on a blocked cell
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || maze[x][y] == '*') {
            return null;
        }

        // Check if the current position is the exit
        if (maze[x][y] == 'e') {
            return path;  // Return the path taken to reach the exit
        }

        // Mark the current cell as visited (using a temporary marker)
        maze[x][y] = '*';  // Temporarily mark this as visited to avoid revisiting

        // Move right
        String right = mazeSolver(maze, new int[]{x, y + 1}, path + 'R');
        if (right != null) {
            return right;
        }

        // Move down
        String down = mazeSolver(maze, new int[]{x + 1, y}, path + 'D');
        if (down != null) {
            return down;
        }

        // Move left
        String left = mazeSolver(maze, new int[]{x, y - 1}, path + 'L');
        if (left != null) {
            return left;
        }

        // Move up
        String up = mazeSolver(maze, new int[]{x - 1, y}, path + 'U');
        if (up != null) {
            return up;
        }

        // Backtrack: restore the current cell to its original state
        maze[x][y] = ' ';

        // No path found
        return null;
    }
}
